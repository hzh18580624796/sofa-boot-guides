package com.alipay.sofa.isle.sample.bootstrap;

import com.alipay.sofa.isle.sample.facade.A;
import org.springframework.stereotype.Component;
import sun.misc.Launcher;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.util.Vector;

//@Component
//@SofaService(uniqueId = "a")
//@SofaService
@Component
public class HzhA implements A {

    @PostConstruct
    public void init() {
        System.out.println("provider2 HzhA");
    }

    public void tt() {
        System.out.println("sofa-boot-run HzhA");
    }

    public void tt2() {
        System.out.println("sofa-boot-run HzhA");
    }

    public void ttxx() {
        System.out.println("sofa-boot-run HzhAxx");
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException {

        ClassLoader classLoaderNew = new Launcher().getClassLoader();

        HzhA hzhA = new HzhA();
        hzhA.ttxx();

//        Class<? extends HzhA> classHzhA = hzhA.getClass();
//        Field classLoaderField = classHzhA.getDeclaredField("classLoader");
//        classLoaderField.setAccessible(true);
//        classLoaderField.set(classHzhA, classLoaderNew);


        ClassLoader classLoader = hzhA.getClass().getClassLoader();
        try {
            Field classes = classLoader.getClass().getSuperclass().getSuperclass().getSuperclass().getDeclaredField("classes");
            classes.setAccessible(true);
            classes.set(classLoader, new Vector<Class<?>>());


//            Field parallelLockMap = classLoader.getClass().getSuperclass().getSuperclass().getSuperclass().getDeclaredField("parallelLockMap");
//            parallelLockMap.setAccessible(true);
//            parallelLockMap.set(classLoader, new ConcurrentHashMap<String, Object>());
            System.out.println();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        Constants.class.getClassLoader();
        HzhA.class.getClassLoader();

        //Class<HzhA> aClass = (Class<HzhA>) new Launcher().getClassLoader().loadClass("com.alipay.sofa.isle.sample.bootstrap.HzhA");
        Class<?> aClass = classLoaderNew.loadClass("com.alipay.sofa.isle.sample.bootstrap.HzhA");
        Object o = aClass.newInstance();

        if (o instanceof HzhA) {

        } else {

        }

        System.out.println();

    }

}
