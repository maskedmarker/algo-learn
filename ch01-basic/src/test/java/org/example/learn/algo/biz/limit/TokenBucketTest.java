package org.example.learn.algo.biz.limit;

import java.util.concurrent.TimeUnit;

public class TokenBucketTest {

    public static void main(String[] args) throws InterruptedException {
        // 桶容量 20，每秒生成 5 个令牌
        TokenBucket bucket = new TokenBucket(20, 5);

        System.out.println("=== 第一次突发 30 个请求 ===");
        for (int i = 1; i <= 30; i++) {
            boolean ok = bucket.tryAcquire();
            if (ok) {
                System.out.println(i + " → 拿到令牌，通过");
            } else {
                System.out.println(i + " → 无令牌，限流");
            }
        }

        System.out.println("\n等待 3 秒生成新令牌...\n");
        TimeUnit.SECONDS.sleep(3);

        System.out.println("=== 再次请求 30 个 ===");
        for (int i = 31; i <= 61; i++) {
            boolean ok = bucket.tryAcquire();
            System.out.println(i + " → " + (ok ? "通过" : "限流"));
        }
    }
}