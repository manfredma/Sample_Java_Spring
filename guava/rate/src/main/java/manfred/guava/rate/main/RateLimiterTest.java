package manfred.guava.rate.main;

import com.google.common.util.concurrent.RateLimiter;

import java.time.Instant;

public class RateLimiterTest {
    public static void main(String[] args) throws InterruptedException {
        final RateLimiter rateLimiter = RateLimiter.create(1);
        for (int i = 0; i < 3; i++) {
            System.out.println(Instant.now() + ", i=" + i + ", cost=" + rateLimiter.acquire(2));
        }
        System.out.println("============================");
        for (int i = 0; i < 3; i++) {
            System.out.println(Instant.now() + ", i=" + i + ", cost=" + rateLimiter.acquire());
        }

    }
}
