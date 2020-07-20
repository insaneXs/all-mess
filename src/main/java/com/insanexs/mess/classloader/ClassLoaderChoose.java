package com.insanexs.mess.classloader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * @Author: xieshang
 * @Description:
 * @Date: Create at 2020-07-20
 */
public class ClassLoaderChoose {



    static class MyClassLoader extends ClassLoader{

        public MyClassLoader(){
            super();
        }

        @Override
        public Class<?> loadClass(String name) throws ClassNotFoundException {
            System.out.println("MyClassLoader try load class:" + name);
            return super.loadClass(name);
        }

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            if("Foo".equals(name)){
                return null;
            }
            byte[] bytes = new byte[2*1024];
            int length = 0;
            try {
                length = new FileInputStream("E:\\github\\all-mess\\src\\main\\java\\com\\insanexs\\mess\\classloader\\ClassLoaderChoose$Bar.class").read(bytes);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return defineClass(null, bytes, 0, length);
        }
    }

    static class MySubClassLoader extends ClassLoader{

        public MySubClassLoader(ClassLoader classLoader){
            super(classLoader);
        }

        @Override
        public Class<?> loadClass(String name) throws ClassNotFoundException {
            System.out.println("MySubClassLoader try load class:" + name);
            return super.loadClass(name);
        }

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            if(!"Foo".equals(name)){
                return null;
            }
            byte[] bytes = new byte[2*1024];
            int length = 0;
            try {
                length = new FileInputStream("E:\\github\\all-mess\\src\\main\\java\\com\\insanexs\\mess\\classloader\\ClassLoaderChoose$Foo.class").read(bytes);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return defineClass(null, bytes, 0, length);
        }
    }
//    只是用来生成字节码文件，生成后就可以删除这两个类，保留是为了做个对比
//    public static class Foo{
//
//        public void foo(){
//            Bar.getInstance();
//        }
//    }
//
//    public static class Bar{
//        public static Bar getInstance(){
//            return new Bar();
//        }
//    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        //定义两个类加载器
        MyClassLoader myClassLoader = new MyClassLoader();
        //Sub作为另一个的子加载器
        MySubClassLoader mySubClassLoader = new MySubClassLoader(myClassLoader);

        //用自定义类加载器加载
        Class clazz = mySubClassLoader.loadClass("Foo");

        System.out.println("Foo load success");
        //反射调用方法，会引起Bar类的加载，从打印看出Bar也是交给SubClassLoader先处理
        Object o = clazz.newInstance();

        o.getClass().getMethod("foo").invoke(o);
    }


}
