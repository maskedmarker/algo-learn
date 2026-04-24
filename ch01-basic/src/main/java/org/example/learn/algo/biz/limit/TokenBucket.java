package org.example.learn.algo.biz.limit;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 令牌桶限流算法
 */
public class TokenBucket {

    // 桶的最大容量（最大突发令牌数）
    private final long capacity;

    // 令牌生成速率：每秒生成多少个令牌
    private final long tokenRatePerSecond;

    // 当前桶内剩余令牌数
    private long tokens;

    // 上一次生成令牌的时间戳（毫秒）
    private long lastTokenTime;

    // 锁保证线程安全
    private final ReentrantLock lock = new ReentrantLock();

    /**
     * 构造令牌桶
     *
     * @param capacity          桶容量
     * @param tokenRatePerSecond 每秒生成令牌数
     */
    public TokenBucket(long capacity, long tokenRatePerSecond) {
        this.capacity = capacity;
        this.tokenRatePerSecond = tokenRatePerSecond;
        this.tokens = capacity; // 初始满令牌💥💥💥
        this.lastTokenTime = System.currentTimeMillis();
    }

    /**
     * 非阻塞尝试获取 1 个令牌
     */
    public boolean tryAcquire() {
        return tryAcquire(1);
    }

    /**
     * 非阻塞尝试获取多个令牌
     */
    public boolean tryAcquire(int permits) {
        if (permits <= 0) {
            return false;
        }

        lock.lock();
        try {
            long now = System.currentTimeMillis();
            // 计算这段时间应该生成的令牌数
            long durationMs = now - lastTokenTime;
            long newTokens = (durationMs * tokenRatePerSecond) / 1000;

            if (newTokens > 0) {
                tokens = Math.min(capacity, tokens + newTokens);
                lastTokenTime = now;
            }

            // 有足够令牌则扣除
            if (tokens >= permits) {
                tokens -= permits;
                return true;
            }

            return false;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 阻塞获取，直到成功
     */
    public void acquire() throws InterruptedException {
        acquire(1);
    }

    /**
     * 阻塞获取多个令牌
     */
    public void acquire(int permits) throws InterruptedException {
        while (!tryAcquire(permits)) {
            TimeUnit.MILLISECONDS.sleep(10);
        }
    }
}
