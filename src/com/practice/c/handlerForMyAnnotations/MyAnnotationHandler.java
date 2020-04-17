package com.practice.c.handlerForMyAnnotations;

import com.practice.c.my_annotation.MyClassAnnotation;
import com.practice.c.my_annotation.MyFieldAnnotation;
import com.practice.c.my_annotation.MyMethodAnnotation;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Find in specific package all classes,
 * annotated with MyClassAnnotation and creates object,
 * initialises fields and invokes methods
 */
public class MyAnnotationHandler {
    static class ClassFinder {
        private static final char PKG_SEPARATOR = '.';

        private static final char DIR_SEPARATOR = '/';

        private static final String CLASS_FILE_SUFFIX = ".class";

        private static final String BAD_PACKAGE_ERROR = "Unable to get resources from path '%s'. Are you sure the package '%s' exists?";

        /**
         * Возвращает список классов в пакете
         */
        private static List<Class<?>> find(String scannedPackage) {
            String scannedPath = scannedPackage.replace(PKG_SEPARATOR, DIR_SEPARATOR);
            URL scannedUrl = Thread.currentThread().getContextClassLoader().getResource(scannedPath);
            if (scannedUrl == null) {
                throw new IllegalArgumentException(String.format(BAD_PACKAGE_ERROR, scannedPath, scannedPackage));
            }
            File scannedDir = new File(scannedUrl.getFile());
            List<Class<?>> classes = new ArrayList<>();
            for (File file : scannedDir.listFiles()) {
                classes.addAll(find(file, scannedPackage));
            }
            return classes;
        }

        private static List<Class<?>> find(File file, String scannedPackage) {
            List<Class<?>> classes = new ArrayList<>();
            String resource = scannedPackage + PKG_SEPARATOR + file.getName();
            if (file.isDirectory()) {
                for (File child : file.listFiles()) {
                    classes.addAll(find(child, resource));
                }
            } else if (resource.endsWith(CLASS_FILE_SUFFIX)) {
                int endIndex = resource.length() - CLASS_FILE_SUFFIX.length();
                String className = resource.substring(0, endIndex);
                try {
                    classes.add(Class.forName(className));
                } catch (ClassNotFoundException ignore) {
                }
            }
            return classes;
        }

        private static Object createObject(Class<?> cl) throws IllegalAccessException, InstantiationException {
            MyClassAnnotation myClassAnnotation = cl.getAnnotation(MyClassAnnotation.class);
            Object newObject = cl.newInstance();
            ResourceBundle resourceBundle = ResourceBundle.getBundle(myClassAnnotation.fileName());
            Arrays.stream(cl.getDeclaredFields()).forEach(field -> {
                if (field.getAnnotation(MyFieldAnnotation.class) != null) {
                    field.setAccessible(true);
                    try {
                        field.set(newObject, resourceBundle.getString(field.getName()));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            });
            Arrays.stream(cl.getDeclaredMethods()).filter(method -> {
                return method.getAnnotation(MyMethodAnnotation.class) != null;
            }).forEach(method -> {
                try {
                    method.setAccessible(true);
                    String my_method = method.getAnnotation(MyMethodAnnotation.class).name();
                    method.invoke(newObject, resourceBundle.getString(my_method));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            });
            return newObject;
        }

        public static List<Object> showCreatedObjectOfMyAnnotatedClasses(String packageUrl) {
            List<Object> listOfObjects = new ArrayList<>();
            List<Class<?>> classes = find(packageUrl);
            classes.stream().filter(cl -> {
                return cl.getAnnotation(MyClassAnnotation.class) != null;
            }).forEach(cl -> {
                try {
                    listOfObjects.add(createObject(cl));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
            });
            return listOfObjects;
        }
    }

    public static void showCreatedObjectOfMyAnnotatedClasses(String packageUrl) {
        ClassFinder.showCreatedObjectOfMyAnnotatedClasses(packageUrl);
    }
}
