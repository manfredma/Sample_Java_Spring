package formatter;

import org.springframework.format.datetime.DateFormatter;

import java.util.Date;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        DateFormatter dateFormatter = new DateFormatter();
        System.out.println(dateFormatter.print(new Date(), Locale.CHINA));
        System.out.println(dateFormatter.print(new Date(), Locale.US));
        System.out.println(dateFormatter.print(new Date(), Locale.FRENCH));
    }
}
