package org.example.learn.algo.biz.limit;

import java.util.concurrent.TimeUnit;

public class LeakyBucketTest {

    public static void main(String[] args) throws InterruptedException {
        // 桶容量 10，每秒放行 2 个请求
        LeakyBucket bucket = new LeakyBucket(10, 2);

        // 模拟 30 个并发突发请求
        for (int i = 1; i <= 30; i++) {
            boolean allowed = bucket.tryAcquire();
            if (allowed) {
                System.out.println(i + " → 请求通过");
            } else {
                System.out.println(i + " → 请求被限流");
            }
        }

        System.out.println("========================");
        System.out.println("等待漏桶放水...");
        TimeUnit.SECONDS.sleep(3);

        // 再次尝试
        for (int i = 31; i <= 61; i++) {
            boolean allowed = bucket.tryAcquire();
            if (allowed) {
                System.out.println(i + " → 请求通过");
            } else {
                System.out.println(i + " → 请求被限流");
            }
        }
    }
}