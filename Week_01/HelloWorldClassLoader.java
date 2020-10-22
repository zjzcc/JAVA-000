package cn.zjzcc.java.base.jike;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HelloWorldClassLoader extends ClassLoader {

    private static final String CLASS_PATH = "Hello.xlass";

    private static final String METHOD_NAME = "hello";

    public static void main(String[] args) {
        try {
            HelloWorldClassLoader helloWorldClassLoader = new HelloWorldClassLoader();
            Class<?> helloClass = helloWorldClassLoader.findClass(CLASS_PATH);
            Method helloMethod = helloClass.getDeclaredMethod(METHOD_NAME);
            helloMethod.invoke(helloClass.newInstance());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            InputStream inputStream = ClassLoader.getSystemResourceAsStream(name);
            byte[] data = new byte[inputStream.available()];
            inputStream.read(data);
            for (int i = 0; i < data.length; i++) {
                data[i] = (byte) (255 - data[i]);
            }
            return this.defineClass(null, data, 0, data.length);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
