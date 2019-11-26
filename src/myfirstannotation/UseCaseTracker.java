package myfirstannotation;

import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class UseCaseTracker {
    public static void trackUserCases(List<Integer> useCases, Class<?> cl) {
        for (Method m : cl.getDeclaredMethods()) {
            System.out.println("Method: "+m);
            UseCase uc = m.getAnnotation(UseCase.class);
            System.out.println("Usecase："+uc);
            if (uc != null) {
                System.out.println("found Use Case "+uc.id()+","+uc.description());
                useCases.remove(Integer.valueOf(uc.id()));
            }
        }
        useCases.forEach(i-> System.out.println("Miss use case "+i));
    }

    public static void main(String[] args) {
        List<Integer> useCases= IntStream.range(47,51).boxed().collect(Collectors.toList());
        System.out.println(useCases);
        trackUserCases(useCases,PasswordUtils.class);
    }
}
