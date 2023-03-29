package site.penghao.simple_ioc.ioc;

import site.penghao.simple_ioc.annotation.AutoWired;
import site.penghao.simple_ioc.annotation.Bean;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple implement of ApplicationContext。
 *
 * @author hope
 * @date 2023/3/28 - 20:16
 */
public class SimpleApplicationContext implements ApplicationContext {
    private String rootPath;
    private final Map<Class<?>, Object> beanFactory = new HashMap<>();

    public SimpleApplicationContext(String classPath) throws Exception {
        String s = classPath.replaceAll("\\.", "/");
        Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources(s);
        while (resources.hasMoreElements()) {
            URL url = resources.nextElement();
            String path = url.getPath();
            rootPath = path.substring(1, path.length() - s.length()); // path is like /E:/..., we don't need the first /
            File file = new File(path);
            loadBean(file);
        }
        loadAutoWired();
    }

    private void loadAutoWired() throws IllegalAccessException {
        // @AutoWired
        for (Map.Entry<Class<?>, Object> entry : beanFactory.entrySet()) {
            Object obj = entry.getValue();
            // do not use entry.getKey() because that could be an interface instead of an implement class
            Class<?> klass = obj.getClass();
            for (Field field : klass.getDeclaredFields()) {
                if (field.getAnnotation(AutoWired.class) == null) continue;
                field.setAccessible(true);
                field.set(obj, beanFactory.get(field.getType()));
            }
        }
    }

    private void loadBean(File f)
            throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException {

        File[] listFiles = f.listFiles();

        // f is a directory
        if (listFiles != null) {
            for (File listFile : listFiles) {
                loadBean(listFile);
            }
            return;
        }

        // f is not a directory
        String fileName = f.getName();

        // is not a class
        if (!fileName.endsWith(".class")) return;

        // load bean
        String classPath = f.getAbsolutePath().replace(".class", "").substring(rootPath.length()).replace('\\', '.');
        Class<?> klass = Class.forName(classPath);

        // @Bean
        if (klass.getAnnotation(Bean.class) != null) {
            Constructor<?> constructor = klass.getDeclaredConstructor();
            constructor.setAccessible(true);
            Object obj = constructor.newInstance();

            Class<?>[] interfaces = klass.getInterfaces();

            // if the class implements one and only one interface, then use the interface as key instead
            if (interfaces.length == 1) {
                beanFactory.put(interfaces[0], obj);
            } else {
                beanFactory.put(klass, obj);
            }
        }
    }

    @Override
    public Object getBean(Class<?> klass) {
        return beanFactory.get(klass);
    }
}
