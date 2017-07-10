package manfred.reflect;

import java.lang.reflect.Method;

public class MethodMain {
    public static void main(String[] args) {
        Method[] methods = MethodMain.class.getMethods();
        for (Method method : methods) {
            System.out.println("=================================");
            System.out.println(method.getDeclaringClass().getName());
            System.out.println(method.getName());

        }
    }
}
