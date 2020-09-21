package java3.lesson7;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

public class RunClassTest {

    private static List<Method> methodList = new LinkedList<>();
    private static Method beforeSuite;
    private static Method afterSuite;
    private static Annotation[] annotations;

    public static void start(Class testClass) {
        try {
            Method[] method = testClass.getDeclaredMethods();
            if (method.length > 0) {
                checkDoubleAnnotations(method);
                insertMethodInList(method);
                if (beforeSuite != null) {
                    beforeSuite.invoke(testClass);
                }
                runMethod(testClass);
                if (afterSuite != null) {
                    afterSuite.invoke(testClass);
                }
            }
        } catch (IllegalAccessException | InvocationTargetException | RuntimeException e) {
            e.printStackTrace();
        }
    }

    private static void runMethod(Class testClass) throws IllegalAccessException, InvocationTargetException {
        Integer prev = 0, next = 0;
        for (Method m : methodList) {
            if (next < m.getAnnotation(Order.class).value()) {
                next = m.getAnnotation(Order.class).value();
            }
        }
        for (int i = 0; i < methodList.size(); i++) {
            for (int j = 0; j < methodList.size(); j++) {
                if (methodList.get(j).getAnnotation(Order.class).value() == next) {
                    methodList.get(j).invoke(testClass);
                }
                if (methodList.get(j).getAnnotation(Order.class).value() < next &&
                        methodList.get(j).getAnnotation(Order.class).value() > prev) {
                    prev = methodList.get(j).getAnnotation(Order.class).value();
                }
            }
            next = Integer.valueOf(prev);
            prev = 0;
        }
    }


    private static void checkDoubleAnnotations(Method[] method) throws RuntimeException {
        int count;
        for (Method m : method) {
            annotations = m.getDeclaredAnnotations();
            count = 0;
            for (Annotation n : annotations) {
                if (n.annotationType().getSimpleName().equals("BeforeSuite")) {
                    if (count == 0) {
                        beforeSuite = m;
                    }
                    count++;
                }
                if (n.annotationType().getSimpleName().equals("AfterSuite")) {
                    if (count == 0) {
                        afterSuite = m;
                    }
                    count++;
                }
            }
            if (count > 1) {
                beforeSuite = null;
                afterSuite = null;
                throw new RuntimeException();
            }
        }
    }

    private static void insertMethodInList(Method[] method) {
        int count;
        for (Method m : method) {
            annotations = m.getDeclaredAnnotations();
            count = 0;
            for (Annotation n : annotations) {
                if (n.annotationType().getSimpleName().equals("Test")) {
                    count++;
                }
                if (n.annotationType().getSimpleName().equals("BeforeSuite") || n.annotationType().getSimpleName().equals("AfterSuite")) {
                    count--;
                }
            }
            if (count == 1) {
                methodList.add(m);
            }
        }
    }
}
