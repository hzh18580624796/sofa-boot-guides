package com.alipay.sofa.isle.sample.bootstrap.controller;

import com.alipay.sofa.boot.constant.SofaBootConstants;
import com.alipay.sofa.boot.util.NamedThreadFactory;
import com.alipay.sofa.isle.ApplicationRuntimeModel;
import com.alipay.sofa.isle.deployment.DependencyTree;
import com.alipay.sofa.isle.deployment.DeploymentDescriptor;
import com.alipay.sofa.isle.deployment.impl.FileDeploymentDescriptor;
import com.alipay.sofa.isle.loader.DynamicSpringContextLoader;
import com.alipay.sofa.isle.loader.SpringContextLoader;
import com.alipay.sofa.isle.sample.bootstrap.SofaURLClassLoader;
import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.alipay.sofa.runtime.component.impl.ComponentManagerImpl;
import com.alipay.sofa.runtime.log.SofaLogger;
import com.alipay.sofa.runtime.spi.binding.BindingAdapterFactory;
import com.alipay.sofa.runtime.spi.component.ComponentInfo;
import com.alipay.sofa.runtime.spi.component.SofaRuntimeContext;
import org.apache.catalina.loader.WebappClassLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.Launcher;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@RestController
public class MainController {

    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private DefaultListableBeanFactory defaultListableBeanFactory;
    @Autowired
    private SofaRuntimeContext sofaRuntimeContext;
    @Autowired
    private BindingAdapterFactory bindingAdapterFactory;

    @GetMapping("/refreshSpringContextParallel")
    public void refreshSpringContextParallel() {
//        ClassLoader classLoader = this.getClass().getClassLoader();
//        try {
//            Field field = classLoader.getClass().getSuperclass().getSuperclass().getSuperclass().getDeclaredField("classes");
//            field.setAccessible(true);
//            field.set(classLoader, new Vector<Class<?>>());
//            System.out.println();
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }


        sofaRuntimeContext.getSofaRuntimeManager();
        Map<String, Object> beans = applicationContext.getBeansOfType(Object.class);

        for (String beanName : beans.keySet()) {
            if (beanName.contains("Controller")) {
                Object bean = applicationContext.getBean(beanName);
                Class<?> clazz = bean.getClass();

                Field[] declaredFields = clazz.getDeclaredFields();
                for (Field declaredField : declaredFields) {
                    SofaReference sofaReferenceAnnotation = declaredField.getAnnotation(SofaReference.class);

                    if (sofaReferenceAnnotation != null) {
                        ComponentManagerImpl componentManager = (ComponentManagerImpl) sofaRuntimeContext.getComponentManager();

                        Collection<ComponentInfo> components = componentManager.getComponents();

                        for (ComponentInfo componentInfo : components) {
                            if (componentInfo.getName().getName().contains(declaredField.getType().getSimpleName())) {
                                ComponentInfo toRemove = componentManager.getComponentInfo(componentInfo.getName());

                                if (null != toRemove) {
                                    componentManager.unregister(toRemove);
                                }
                            }
                        }
                    }
                }

                defaultListableBeanFactory.applyBeanPostProcessorsBeforeInitialization(applicationContext.getBean(beanName), beanName);
            }
        }

        ApplicationRuntimeModel application = applicationContext.getBean(SofaBootConstants.APPLICATION, ApplicationRuntimeModel.class);

        SpringContextLoader springContextLoader = createSpringContextLoader();
        installSpringContext(application, springContextLoader);
        refreshSpringContextParallel(application);
    }


    //=========================================================================================================
    //=========================================================================================================


    protected SpringContextLoader createSpringContextLoader() {
        return new DynamicSpringContextLoader(applicationContext);
    }

    private void installSpringContext(ApplicationRuntimeModel application,
                                      SpringContextLoader springContextLoader) {
        ClassLoader oldClassLoader = Thread.currentThread().getContextClassLoader();

        for (DeploymentDescriptor deployment : application.getResolvedDeployments()) {

            try {
                Field classLoader = ((FileDeploymentDescriptor) deployment).getClass().getSuperclass().getDeclaredField("classLoader");

                classLoader.setAccessible(true);
                //classLoader.set(deployment, new SofaURLClassLoader());
                classLoader.set(deployment, new Launcher().getClassLoader());
            } catch (Exception e) {
                e.printStackTrace();
            }


            if (deployment.isSpringPowered()) {
                SofaLogger.info("Start install " + application.getAppName() + "'s module: "
                        + deployment.getName());
                try {
                    Thread.currentThread().setContextClassLoader(deployment.getClassLoader());
                    springContextLoader.loadSpringContext(deployment, application);
                } catch (Throwable e) {
                    SofaLogger.error(e, "Install module {0} got an error!", deployment.getName());
                    application.addFailed(deployment);
                } finally {
                    Thread.currentThread().setContextClassLoader(oldClassLoader);
                }
            }
        }
    }

    private void refreshSpringContextParallel(ApplicationRuntimeModel application) {
        ClassLoader oldClassLoader = Thread.currentThread().getContextClassLoader();
        List<DeploymentDescriptor> coreRoots = new ArrayList<DeploymentDescriptor>();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(CPU_COUNT + 1, CPU_COUNT + 1, 60,
                TimeUnit.MILLISECONDS, new SynchronousQueue<Runnable>(), new NamedThreadFactory(
                "sofa-module-start"), new ThreadPoolExecutor.CallerRunsPolicy());
        try {
            for (DeploymentDescriptor deployment : application.getResolvedDeployments()) {
                Field[] fields = deployment.getClass().getFields();

                for (Field field : fields) {
                    if ("".equals(field.getName())) {

//                        field.setAccessible(true);
//                        field.set(deployment, new URLClassLoader());
                    }
                }

                DependencyTree.Entry entry = application.getDeployRegistry().getEntry(
                        deployment.getModuleName());
                if (entry != null && entry.getDependencies() == null) {
                    coreRoots.add(deployment);
                }
            }
            refreshSpringContextParallel(coreRoots, application.getResolvedDeployments().size(),
                    application, executor);

        } finally {
            executor.shutdown();
            Thread.currentThread().setContextClassLoader(oldClassLoader);
        }
    }

    private void refreshSpringContextParallel(List<DeploymentDescriptor> rootDeployments,
                                              int totalSize,
                                              final ApplicationRuntimeModel application,
                                              final ThreadPoolExecutor executor) {
        if (rootDeployments == null || rootDeployments.size() == 0) {
            return;
        }

        final CountDownLatch latch = new CountDownLatch(totalSize);
        List<Future> futures = new CopyOnWriteArrayList<Future>();

        for (final DeploymentDescriptor deployment : rootDeployments) {
            refreshSpringContextParallel(deployment, application, executor, latch, futures);
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException("Wait for Sofa Module Refresh Fail", e);
        }

        for (Future future : futures) {
            try {
                future.get();
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }

    }

    private void refreshSpringContextParallel(final DeploymentDescriptor deployment,
                                              final ApplicationRuntimeModel application,
                                              final ThreadPoolExecutor executor,
                                              final CountDownLatch latch, final List<Future> futures) {
        futures.add(executor.submit(new Runnable() {
            @Override
            public void run() {
                String oldName = Thread.currentThread().getName();
                try {
                    Thread.currentThread().setName(
                            "sofa-module-start-" + deployment.getModuleName());
                    Thread.currentThread().setContextClassLoader(deployment.getClassLoader());
                    if (deployment.isSpringPowered()
                            && !application.getFailed().contains(deployment)) {
                        doRefreshSpringContext(deployment, application);
                    }
                    DependencyTree.Entry<String, DeploymentDescriptor> entry = application
                            .getDeployRegistry().getEntry(deployment.getModuleName());
                    if (entry != null && entry.getDependsOnMe() != null) {
                        for (final DependencyTree.Entry<String, DeploymentDescriptor> child : entry
                                .getDependsOnMe()) {
                            child.getDependencies().remove(entry);
                            if (child.getDependencies().size() == 0) {
                                refreshSpringContextParallel(child.get(), application, executor,
                                        latch, futures);
                            }
                        }
                    }
                } catch (Throwable e) {
                    SofaLogger.error(e,
                            "Refreshing Spring Application Context of module {0} got an error."
                                    + deployment.getName());
                    throw new RuntimeException("Refreshing Spring Application Context of module "
                            + deployment.getName() + " got an error.", e);
                } finally {
                    latch.countDown();
                    Thread.currentThread().setName(oldName);
                }
            }
        }));
    }

    private void doRefreshSpringContext(DeploymentDescriptor deployment,
                                        ApplicationRuntimeModel application) {
        SofaLogger.info(
                "Begin refresh Spring Application Context of module {0} of application {1}.",
                deployment.getName(), application.getAppName());
        ConfigurableApplicationContext ctx = (ConfigurableApplicationContext) deployment
                .getApplicationContext();
        if (ctx != null) {
            try {
                deployment.startDeploy();
                ctx.refresh();
                application.addInstalled(deployment);
            } catch (Throwable t) {
                SofaLogger.error(t,
                        "Refreshing Spring Application Context of module {0} got an error.",
                        deployment.getName());
                application.addFailed(deployment);
            } finally {
                deployment.deployFinish();
            }
        } else {
            application.addFailed(deployment);
            SofaLogger.error(new RuntimeException("Spring Application Context of module "
                    + deployment.getName() + " is null!"), "");
        }
    }


}
