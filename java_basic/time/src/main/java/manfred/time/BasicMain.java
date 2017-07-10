package manfred.time;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class BasicMain {
    public static void main(String[] args) {
        System.out.println(ZonedDateTime.ofInstant(Instant.now(), ZoneOffset.ofHours
                (6).normalized()));
    }
}
