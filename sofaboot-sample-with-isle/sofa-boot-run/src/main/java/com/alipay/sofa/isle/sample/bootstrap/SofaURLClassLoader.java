package com.alipay.sofa.isle.sample.bootstrap;

import sun.misc.Launcher;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SofaURLClassLoader extends ClassLoader {

    private Map<String, Class> store = new HashMap<String, Class>();

    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        if (name.contains("alipay")) {
            return findClass(name);
        } else {
            return super.loadClass(name, resolve);
        }
    }

    public Class<?> findClass(String name) throws ClassNotFoundException {
        if (store.containsKey(name)) {
            return store.get(name);
        }

        byte[] buffer = loadClassFromFile(name);
        Class<?> clazz = defineClass(name, buffer, 0, buffer.length);

        store.put(name, clazz);
        return clazz;
    }

    public byte[] loadClassFromFile(String fileName) {

        String name = fileName.replace(".", File.separator) + ".class";
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(name);

        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        int next;
        try {
            while ((next = inputStream.read()) != -1) {
                byteStream.write(next);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return byteStream.toByteArray();
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);

        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                SofaURLClassLoader classLoader = new SofaURLClassLoader();
                Class<?> clazz = null;
                try {
                    clazz = classLoader.findClass("com.alipay.sofa.isle.sample.bootstrap.HzhA");

                    Object o = clazz.newInstance();


                    List<String> list = new ArrayList<String>();
                    for (Method declaredMethod : o.getClass().getDeclaredMethods()) {
                        list.add(declaredMethod.getName());
                    }
                    System.out.println(list);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 1, 10, TimeUnit.SECONDS);


//         if (o instanceof HzhA) {
//                        HzhA hzhA = (HzhA) o;
//                        ClassLoader orginClassLoader = hzhA.getClass().getClassLoader();
//
//                        hzhA.tt();
//                    }


//        Class<?> aClass = Class.forName("com.alipay.sofa.isle.sample.bootstrap.Launcher.AppClassLoader");
//        Object o = aClass.newInstance();

        SofaURLClassLoader classLoader = new SofaURLClassLoader();

        Launcher launcher = new Launcher();
        launcher.getClassLoader();

        Launcher launcher2 = new Launcher();
        launcher2.getClassLoader();
        System.out.println();


    }
}
