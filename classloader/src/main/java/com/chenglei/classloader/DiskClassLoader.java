package com.chenglei.classloader;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class DiskClassLoader extends ClassLoader{

    private String path;

    public DiskClassLoader( String path) {
        this.path = path;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class clazz = null;

        byte[] data = loadClassByteCode(name);
        if (data == null)
            throw  new ClassNotFoundException();
        else
            clazz = defineClass(name,data,0,data.length);
        return clazz;
    }

    private byte[] loadClassByteCode(String name) {
        String fileName = getFileName(name);
        File file = new File(path,fileName);

        InputStream inputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            inputStream = new FileInputStream(file);
            byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buffer)) != -1){
                byteArrayOutputStream.write(buffer,0,length);
            }
            return byteArrayOutputStream.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (byteArrayOutputStream != null){
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private String getFileName(String name) {
        int index = name.lastIndexOf(".");
        if (index == -1)
            return name + ".class";
        else
            return name.substring(index + 1) + ".class";
    }
}
