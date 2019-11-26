package testannotation;

import org.junit.Test;

public class TestAnnotation {
    static void execute(){
        System.out.println("execute()");
    }
    @Test
    public void f(){
        execute();
    }
}
