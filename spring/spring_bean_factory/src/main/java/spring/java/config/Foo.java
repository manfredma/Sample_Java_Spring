package spring.java.config;

public class Foo {
    private Bar bar;

    public void init() {
        // initialization logic
        System.out.println("Foo" + " -> " + "init");
    }

    public void setBar(Bar bar) {
        this.bar = bar;
    }
}