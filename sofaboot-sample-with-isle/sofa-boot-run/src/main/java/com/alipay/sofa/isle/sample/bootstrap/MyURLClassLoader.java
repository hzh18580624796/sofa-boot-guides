//package com.alipay.sofa.isle.sample.bootstrap;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.net.URL;
//import java.net.URLClassLoader;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.jar.JarEntry;
//import java.util.jar.JarInputStream;
//
//public class MyURLClassLoader extends URLClassLoader {
//
//    private static String MyClasspath =
//            new String("/Users/hezhihong/sping-origin-source/sofa-boot-guides-master/sofaboot-sample-with-isle/sofa-boot-run/target");
//    private static Map<String, Class> loadClassHashTable = new HashMap<String, Class>();
//
//    public MyURLClassLoader(ClassLoader parent) {
//        super(new URL[0], parent);
//    }
//
//    public Class findClass(String name) {
//        byte[] classData = null;
//        try {
//            classData = loadClassData(name);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Class c = defineClass(name, classData, 0, classData.length);
//        loadClassHashTable.put(name, c);
//        System.out.println("加载" + name + "类成功。");
//        return c;
//    }
//
//    /**
//     * 加载类 参数1：字节码数组 参数2：类名
//     */
//    public Class loadClass(byte[] classData, String className)
//            throws ClassNotFoundException {
//        Class c = defineClass(className, classData, 0, classData.length);
//        loadClassHashTable.put(className, c);
//        System.out.println("加载" + className + "类成功。");
//        return c;
//    }
//
//    /**
//     * 加载类 参数1：类名
//     */
//    public Class loadClass(String name) throws ClassNotFoundException {
//        return loadClass(name, false);
//    }
//
//    /**
//     * 加载类 参数1：类名 参数2：是否分析这个类
//     */
//    protected Class loadClass(String name, boolean resolve)
//            throws ClassNotFoundException {
//        byte[] classData = null;
//        Class temp = null;
//        // 如是系统类直接装载并记录后返回
//        if (name.startsWith("java.")) {
//            temp = findSystemClass(name); // 调用父类方法
//            loadClassHashTable.put(name, temp);
//            System.out.println("loadClass: SystemLoading " + name);
//            return temp;
//        }
//
//        try {
//            temp = findLoadedClass(name);// here need try again it is how to
//            // work
//            if (temp != null) {
//                System.out.println(name + " have loaded");
//                return (temp);
//            }
//            try {
//                temp = findClass(name);
//            } catch (Exception fnfe) {
//            }
//            if (temp == null) {
//                temp = findSystemClass(name);
//            }
//            if (resolve && (temp != null)) {
//                resolveClass(temp);
//            }
//            return temp;
//        } catch (Exception e) {
//            throw new ClassNotFoundException(e.toString());
//        }
//    }
//
//    /**
//     * 加载类 参数1：要加载的类名 参数2：类所在的jar包名
//     */
//    protected Class loadClass(String className, String jarName)
//            throws ClassNotFoundException {
//        String jarPath = searchFile(MyClasspath, jarName + ".jar");
//        JarInputStream in = null;
//        if (!(jarPath == null || jarPath == "")) {
//            try {
//                in = new JarInputStream(new FileInputStream(jarPath));
//                JarEntry entry;
//                while ((entry = in.getNextJarEntry()) != null) {
//                    // System.out.println(entry.getName());
//                    String outFileName = entry.getName().substring(
//                            entry.getName().lastIndexOf("/") + 1,
//                            entry.getName().length());
//                    // System.out.println(outFileName);
//                    if (outFileName.equals(className + ".class")) {
//                        if (entry.getSize() == -1) {
//                            System.out.println("错误：无法读取该文件！");
//                            return null;
//                        }
//                        byte[] classData = new byte[(int) entry.getSize()];
//                        in.read(classData);
//                        return loadClass(classData, className);
//                    }
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            } finally {
//                try {
//                    in.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        } else {
//            System.out.println("don't find the " + jarName + ".jar");
//            return null;
//        }
//        return null;
//    }
//
//    /**
//     * 读取文件字节码 参数1：文件名 被 findClass() 调用
//     */
//    private byte[] loadClassData(String name) throws IOException {
//        String filePath = searchFile(MyClasspath, name + ".class");
//        filePath = "/Users/hezhihong/sping-origin-source/sofa-boot-guides-master/sofaboot-sample-with-isle/sofa-boot-run/target/classes/com/alipay/sofa/isle/sample/bootstrap/" + name + ".class";
//        System.out.println(filePath);
//        if (!(filePath == null || filePath == "")) {
//            FileInputStream inFile = new FileInputStream(filePath);
//            byte[] classData = new byte[inFile.available()];
//            inFile.read(classData);
//            inFile.close();
//            return classData;
//        }
//        System.out.println("读取字节码失败。");
//        return null;
//    }
//
//    /**
//     * 查询文件 参数1：路径名 参数2：文件名
//     */
//    public String searchFile(String classpath, String fileName) {
//        File f = new File(classpath + fileName);
//        // 测试此路径名表示的文件是否是一个标准文件
//        if (f.isFile()) {
//            return f.getPath();
//        } else {
//            // 返回由此抽象路径名所表示的目录中的文件和目录的名称所组成字符串数组
//            String objects[] = new File(classpath).list();
//            for (int i = 0; i < objects.length; i++) {
//                // 测试此抽象路径名表示的文件是否是一个目录
//                if (new File(classpath + f.separator + objects[i]).isDirectory()) {
//                    // 迭代遍历。separator是与系统有关的默认名称分隔符
//                    return searchFile(classpath + f.separator + objects[i] + f.separator, fileName);
//                }
//            }
//        }
//        System.out.println("没有找到文件：" + fileName);
//        return null;
//    }
//
//
//    public static void main(String[] args) throws ClassNotFoundException {
//        MyURLClassLoader classLoader = new MyURLClassLoader(null);
//        Class clazz = classLoader.loadClass("HzhA");
//        System.out.println(clazz);
//    }
//
//
//}
