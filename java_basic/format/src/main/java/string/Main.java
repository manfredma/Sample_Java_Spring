package string;

import java.util.Formatter;

public class Main {
    private static Formatter f = new Formatter(System.out);

    public static void printHead() {
        f.format("%-10s %5s %5s\n", "username", "level", "score");
        f.format("%-10s %5s %5s\n", "---", "---", "---");
    }

    public static void printData() {
        f.format("%-10s %5d %5.2f\n", "Jason", 1, 9.87654321);
        f.format("%-10s %5d %5.2f\n", "arthinking", 2, 9.6512);
    }

    public static void main(String[] args) {
        printHead();
        printData();
    }
}
