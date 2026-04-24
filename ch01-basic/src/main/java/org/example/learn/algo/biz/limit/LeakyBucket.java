package org.example.learn.algo.biz.limit;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 漏桶限流算法
 */
public class LeakyBucket {

    // 桶的容量（最大可堆积请求数）
    private final long capacity;

    // 漏出速率：每秒允许通过的请求数
    private final long leakRatePerSecond;

    // 当前桶内水量（请求数）
    private long water;

    // 上一次漏水时间戳（ms）
    private long lastLeakTime;

    // 保证线程安全
    private final ReentrantLock lock = new ReentrantLock();

    /**
     * 构造漏桶
     * @param capacity 桶容量
     * @param leakRatePerSecond 每秒漏出多少请求（速率）
     */
    public LeakyBucket(long capacity, long leakRatePerSecond) {
        this.capacity = capacity;
        this.leakRatePerSecond = leakRatePerSecond;
        this.water = 0;
        this.lastLeakTime = System.currentTimeMillis();
    }

    /**
     * 尝试获取一个“桶位”
     * @return true=允许通过，false=被限流
     */
    public boolean tryAcquire() {
        lock.lock();
        try {
            // 1. 先漏水：计算从上次到现在应该漏出多少水
            long now = System.currentTimeMillis();
            long durationMs = now - lastLeakTime;

            // 计算漏出水量
            long leakedWater =(durationMs * leakRatePerSecond) / 1000;
            if (leakedWater > 0) {
                water = Math.max(0, water - leakedWater);
                lastLeakTime = now;
            }

            // 2. 判断桶是否已满
            if (water < capacity) {
                // 加水，放行
                water++;
                return true;
            }

            // 桶满，拒绝
            return false;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 阻塞式获取，直到成功
     */
    public void acquire() throws InterruptedException {
        while (!tryAcquire()) {
            TimeUnit.MILLISECONDS.sleep(10);
        }
    }
}