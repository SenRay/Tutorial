package com.chenglei.classloader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestClassLoader {
    public static void main(String[] args) {
        DiskClassLoader diskClassLoader = new DiskClassLoader("C:\\GitHub\\Tutorial\\path");

        try {
            //此处的类名一定要是完整的类名
            Class clazz = diskClassLoader.loadClass("com.chenglei.classloader.HellClassLoader");
            if (clazz != null){
                Object object = clazz.newInstance();
                System.out.println(object.getClass().getClassLoader());
                Method method = clazz.getMethod("say",null);
                method.invoke(object,null);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
