package com.ibeetl.code.ch05.caffeine;

import com.github.benmanes.caffeine.cache.*;
import com.github.benmanes.caffeine.cache.stats.CacheStats;
import com.google.common.testing.FakeTicker;
import com.ibeetl.code.ch05.caffeine.SkuInfo;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.*;

/**
 * Caffeine 使用测试Caffeine 使用测试
 *
 * @author 公众号 java系统优化
 */
public class CaffeineApplicationTests {

    SkuInfoService service = new SkuInfoService();

    ExecutorService pool = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        CaffeineApplicationTests test = new CaffeineApplicationTests();
        test.evictByReadTime();
    }


    /**
     * 手动填充
     */
    public void manualLoads() {
        Cache<String, SkuInfo> cache = Caffeine.newBuilder()
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .maximumSize(100)
                .build();
        String key = "11757834";
        // 如果缓存中存在key对应的数据，则返回对应数据；否则返回null
        SkuInfo skuInfo = cache.getIfPresent(key);
        System.out.println(skuInfo);
        skuInfo = new SkuInfo(key);
        cache.put(key, skuInfo);
        skuInfo = cache.getIfPresent(key);
        System.out.println(skuInfo);
        cache.invalidate(key);
        skuInfo = cache.get(key, k -> service.query(k));
        System.out.println(skuInfo);

    }

    /**
     * 同步载入
     */
    public void synchronizeLoading() {
        LoadingCache<String, SkuInfo> cache = Caffeine.newBuilder()
                .maximumSize(2)
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .build(k -> service.query(k));

        String key = "11757834";
        SkuInfo skuInfo = cache.get(key);
        System.out.println(skuInfo);

    }

    /**
     * 异步载入
     */
    public void asyncManual() {
        AsyncCache<String, SkuInfo> cache = Caffeine.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .buildAsync();
        String key = "11757834";
        CompletableFuture<SkuInfo> completableFuture = cache.get(key, k -> service.query(k));
        completableFuture.thenAccept(skuInfo -> {
            System.out.println(skuInfo);
            ;
        });
    }

    /**
     * 异步自动载入
     */
    public void asyncLoading() {
        AsyncLoadingCache<String, SkuInfo> cache = Caffeine.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .executor(pool)
                .buildAsync(k -> service.query(k));

        String key = "11757834";

        CompletableFuture<SkuInfo> completableFuture = cache.get(key);
        completableFuture.thenAccept(skuInfo -> {
            System.out.println(skuInfo);
        });


    }

    public void evictByNum1() {
        LoadingCache<String, SkuInfo> cache = Caffeine.newBuilder()
                .maximumSize(2)
                .build(k -> service.query(k));


        System.out.println(cache.get("A"));
        System.out.println(cache.get("C"));
        System.out.println(cache.get("D"));
        System.out.println(cache.get("E"));

        cache.cleanUp();
        System.out.println(cache.estimatedSize());
        System.out.println(cache.asMap());
    }

    /**
     * 淘汰：基于权重值的大小策略。在驱逐时，不考虑权重值
     */
    public void evictByNum2() {
        LoadingCache<String, SkuInfo> cache = Caffeine.newBuilder()
                .maximumWeight(5)//最大权重10
                .weigher((k, v) -> ((SkuInfo) v).getKey().length())//一个值的权重计算方法
                .build(k -> service.query(k));

        System.out.println(cache.get("a"));
        System.out.println(cache.get("abc"));
        System.out.println(cache.get("ef"));


        cache.cleanUp();
        System.out.println(cache.estimatedSize());
        System.out.println(cache.asMap());
    }

    public int getWeight(SkuInfo info) {
        return info.getKey().length();
    }


    /**
     * 回收：基于访问时间策略
     */
    public void evictByReadTime() {
        // Guava's testlib
        FakeTicker ticker = new FakeTicker();
        LoadingCache<String, SkuInfo> cache = Caffeine.newBuilder()
                .expireAfterAccess(2, TimeUnit.SECONDS)
                .ticker(ticker::read)
                .maximumSize(3)
                .build(k -> service.query(k));
        cache.get("A");
        cache.get("B");
        ticker.advance(5, TimeUnit.SECONDS);
        System.out.println(cache.asMap());
        cache.get("A");
        //调用下面代码，时间总共过了13秒，B未能有人访问，将被清除
        ticker.advance(8, TimeUnit.SECONDS);
        cache.cleanUp();
        System.out.println(cache.asMap());
    }


//
//	/**
//	 * 淘汰：基于写入时间策略
//	 */
//	@Test
//	public void evictByTime2() {
//		FakeTicker ticker = new FakeTicker(); // Guava's testlib
//		LoadingCache<String, SkuInfo> cache = Caffeine.newBuilder()
//				                                        .expireAfterWrite(10, TimeUnit.SECONDS)
//				                                        .refreshAfterWrite(5, TimeUnit.SECONDS)
//				                                        .ticker(ticker::read)
//				                                        .build(new CacheLoader<String, SkuInfo>() {
//					                                        @Nullable
//					                                        @Override
//					                                        public SkuInfo load(@NonNull String key) throws Exception {
//						                                        return SkuInfo.get(key);
//					                                        }
//
//					                                        /**
//					                                         * 在 refresh 时暂时返回一个数据给调用者用。refresh 完成后便会恢复。
//					                                         * @param key
//					                                         * @param oldValue
//					                                         * @return
//					                                         * @throws Exception
//					                                         */
//					                                        @Nullable
//					                                        @Override
//					                                        public SkuInfo reload(@NonNull String key, @NonNull SkuInfo oldValue) throws Exception {
//						                                        return SkuInfo.get("New ".concat(oldValue.getKey()));
//					                                        }
//				                                        });
//		cache.get("A");
//		ticker.advance(6, TimeUnit.SECONDS);
//		System.out.println(cache.asMap());
//		cache.get("A");
//		cache.get("B");
//		ticker.advance(8, TimeUnit.SECONDS);
//		System.out.println(cache.asMap());
//		cache.get("B");
//		ticker.advance(2, TimeUnit.SECONDS);
//		System.out.println(cache.asMap());
//		cache.get("C");
//		ticker.advance(8, TimeUnit.SECONDS);
//		System.out.println(cache.asMap());
//		ticker.advance(2, TimeUnit.SECONDS);
//		cache.cleanUp();
//		System.out.println(cache.asMap());
//	}
//
//	/**
//	 * 淘汰：基于自定义时间策略
//	 */
//	@Test
//	public void evictByTime3() {
//		FakeTicker ticker = new FakeTicker();
//		LoadingCache<String, SkuInfo> cache = Caffeine.newBuilder()
//				                                        .ticker(ticker::read)
//				                                        .expireAfter(new Expiry<String, SkuInfo>() {
//					                                        /**
//					                                         * 当数据在缓存中创建对应node后，多久后驱逐
//					                                         * @param key
//					                                         * @param value
//					                                         * @param currentTime 基于给定的基准时钟，默认是系统时钟
//					                                         * @return 返回一个持续时间，单位纳秒
//					                                         */
//					                                        @Override
//					                                        public long expireAfterCreate(
//																	String key, SkuInfo value, long currentTime) {
//						                                        return value.getKey().length() * 1000;
//					                                        }
//
//					                                        /**
//					                                         * 当对数据刷新后多久后驱逐
//					                                         * @param key
//					                                         * @param value
//					                                         * @param currentTime 基于给定的基准时钟，默认是系统时钟
//					                                         * @param currentDuration 当前该数据已经驻留缓存多长时间。此时间可从 {@link Node } 的 var time 获取。
//					                                         * @return 返回一个持续时间，单位纳秒
//					                                         */
//					                                        @Override
//					                                        public long expireAfterUpdate(
//																	String key, SkuInfo value, long currentTime, long currentDuration) {
//						                                        return value.getKey().length() * 2000;
//					                                        }
//
//					                                        /**
//					                                         * 当从缓存读取后，多久后被驱逐
//					                                         * @param key
//					                                         * @param value
//					                                         * @param currentTime 基于给定的基准时钟，默认是系统时钟
//					                                         * @param currentDuration 当前该数据已经驻留缓存多长时间。此时间可从 {@link Node } 的 var time 获取。
//					                                         * @return 返回一个持续时间，单位纳秒
//					                                         */
//					                                        @Override
//					                                        public long expireAfterRead(
//																	String key, SkuInfo value, long currentTime, long currentDuration) {
//						                                        return value.getKey().length() * 3000;
//					                                        }
//				                                        }).build(k -> SkuInfo.get(k));
//
//		cache.get("A");
//		ticker.advance(990, TimeUnit.NANOSECONDS);
//		System.out.println(cache.asMap());
//		ticker.advance(10, TimeUnit.NANOSECONDS);
//		System.out.println(cache.asMap());
//
//		cache.get("B");
//		cache.refresh("B");
//		ticker.advance(1990, TimeUnit.NANOSECONDS);
//		System.out.println(cache.asMap());
//		ticker.advance(10, TimeUnit.NANOSECONDS);
//		System.out.println(cache.asMap());
//
//		cache.get("C");
//		cache.get("C");
//		ticker.advance(2990, TimeUnit.NANOSECONDS);
//		System.out.println(cache.asMap());
//		ticker.advance(10, TimeUnit.NANOSECONDS);
//		System.out.println(cache.asMap());
//		cache.cleanUp(); // 由于移除过期的实体是在maintenance（维护期），调用cleanUp方法可以立刻触发该周期。
//		System.out.println(cache.asMap());
//	}
//
//	/**
//	 * 淘汰：基于弱引用的引用策略。 参考jvm的垃圾回收
//	 */
//	@Test
//	public void evictByWeakRef1() {
//		FakeTicker ticker = new FakeTicker();
//		Cache<Object, Object> cache = Caffeine.newBuilder()
//				                              .ticker(ticker::read)
//				                              .weakKeys() //key的弱引用
//				                              .weakValues() //value的弱引用
//				                              .build();
//		SkuInfo skuInfo = new SkuInfo("A");
//		String key = "A";
//		cache.put(key, skuInfo);
//		/*可以将这两条代码注释，看效果*/
//		skuInfo = null;
//		key = null;
//
//		System.out.println(cache.asMap());
//		System.gc();//由于是弱引用，手动触发一下jvm 的垃圾回收
//		System.out.println(cache.asMap());
//	}
//
//	/**
//	 * 淘汰：基于软引用的引用策略
//	 */
//	@Test
//	public void evictByWeakRef2() {
//		FakeTicker ticker = new FakeTicker();
//		LoadingCache<String, SkuInfo> cache = Caffeine.newBuilder()
//				                                        .ticker(ticker::read)
//				                                        .softValues() //软引用
//				                                        .build(k -> SkuInfo.get(k));
//
//		cache.get("A");
//		System.gc();
//		System.out.println(cache.asMap());
//	}
//
//	/**
//	 * caffeine 的 CacheWriter 和 removalListener功能
//	 */
//	@Test
//	public void writer() {
//		LoadingCache<String, SkuInfo> cache = Caffeine.newBuilder()
//				                                        .maximumSize(1)
//				                                        .writer(new CacheWriter<String, SkuInfo>() {
//					                                        /**
//					                                         * 模拟写入底层资源。
//					                                         * @param key
//					                                         * @param value
//					                                         */
//					                                        @Override
//					                                        public void write(@NonNull String key, @NonNull SkuInfo value) {
//						                                        System.out.println(value + " is writing into database.");
////						                                        如果在这里抛出异常，异常会传递给调用者，同时写入缓存的数据失败。
////						                                        throw new RuntimeException("cann't writer to database");
//					                                        }
//
//					                                        /**
//					                                         * value 从缓存中驱逐时触发
//					                                         * @param key
//					                                         * @param value
//					                                         * @param cause 驱逐原因包括：明确调用invalidate 方法；超过size驱逐，被jvm垃圾收集器收集；时间过期等
//					                                         *              请自行查看相应类说明。
//					                                         */
//					                                        @Override
//					                                        public void delete(@NonNull String key, @Nullable SkuInfo value, @NonNull RemovalCause cause) {
//						                                        System.out.println(value + " is deleted from database.");
//						                                        System.out.println("cause is " + cause);
//					                                        }
//				                                        })
//				                                        .removalListener((key, value, cause) -> {
//					                                        /*移除事件的监听器，有助于系统了解缓存的运行*/
//					                                        System.out.println(value + " is deleted from cache.");
//					                                        System.out.println("cause is " + cause);
//				                                        })
//				                                        .build(key -> SkuInfo.get(key));
//		cache.put("A", SkuInfo.get("A"));
//		cache.put("B", SkuInfo.get("B"));
//		cache.cleanUp();
//		System.out.println(cache.asMap());
//	}
//
//	/**
//	 * 手动调整caffeine的配置数据
//	 */
//	@Test
//	public void policy() {
//		LoadingCache<String, SkuInfo> cache = Caffeine.newBuilder()
//				                                        .maximumSize(10)
//				                                        .recordStats()
//				                                        .build(k -> SkuInfo.get(k));
//		cache.policy().eviction().ifPresent(eviction -> {
//			System.out.println("adjust evict policy.");
//			eviction.setMaximum(eviction.getMaximum() / 2);
//		});
//		cache.policy().expireAfterAccess().ifPresent(expiration -> {
//			System.out.println("adjust expireAfterAccess policy.");
//		});
//		cache.policy().expireAfterWrite().ifPresent(expiration -> {
//			System.out.println("adjust expireAfterWrite policy.");
//		});
//		cache.policy().expireVariably().ifPresent(expiration -> {
//			System.out.println("adjust expireVariably policy.");
//		});
//		cache.policy().refreshAfterWrite().ifPresent(expiration -> {
//			System.out.println("adjust refreshAfterWrite policy.");
//		});
//	}
//
//	@Test
//	public void statistics() {
//		LoadingCache<String, SkuInfo> cache = Caffeine.newBuilder()
//				                                        .maximumSize(10)
//				                                        .recordStats()
//				                                        .build(k -> SkuInfo.get(k));
//		cache.get("A");
//		cache.get("B");
//		cache.get("C");
//		cache.get("A");
//		cache.get("A");
//		cache.get("B");
//		cache.get("B");
//		cache.get("B");
//		cache.get("C");
//		cache.get("B");
//		cache.get("A");
//		cache.get("C");
//		cache.get("D");
//		cache.get("C");
//		cache.get("E");
//		printStat(cache.stats());
//	}
//
//	private void printStat(CacheStats cacheStats) {
//		System.out.println(" 请求数 : " + cacheStats.requestCount());
//		System.out.println(" 载入成功数 : " + cacheStats.loadSuccessCount());
//		System.out.println(" 载入失败数 : " + cacheStats.loadFailureCount());
//		System.out.println(" 载入失败率 : " + cacheStats.loadFailureRate());
//		System.out.println(" 命中数 : " + cacheStats.hitCount());
//		System.out.println(" 命中率 : " + cacheStats.hitRate());
//		System.out.println(" 丢失数 : " + cacheStats.missCount());
//		System.out.println(" 丢失率 : " + cacheStats.missRate());
//		System.out.println(" 驱逐数 : " + cacheStats.evictionCount());
//		System.out.println(" 驱逐权重 : " + cacheStats.evictionWeight());
//		System.out.println(" 载入总时间（包括载入失败） : " + cacheStats.totalLoadTime() / 1000d / 1000d + " s");
//		System.out.println(" 载入新值的平均时间（包括载入失败） : " + cacheStats.averageLoadPenalty() / 1000d / 1000d + " s");
//	}

}

