import java.time.Instant;

public class StaticComponent {
    public static void main(String[] args) {
        System.out.println(Instant.now());
        Static.out("Hello, world");
        System.out.println(Instant.now());
    }
}

class Static {
    static {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000L);
            } catch (Exception e) {
                System.out.println("ERROR.when.sleep");
            }
        }
    }

    public static void out(String c) {
        System.out.println(c);
    }
}
