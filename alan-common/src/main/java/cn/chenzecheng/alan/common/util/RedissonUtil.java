package cn.chenzecheng.alan.common.util;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.*;
import org.redisson.client.codec.StringCodec;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author
 */
@Configuration
@Slf4j
public class RedissonUtil {

    /**
     * redisson client对象
     */
    @Resource
    private RedissonClient redissonClient;

    /**
     * 默认缓存时间
     */
    private static final Long DEFAULT_EXPIRED = 5 * 60L;

    /**
     * redis key前缀
     */
    private static final String REDIS_KEY_PREFIX = "";

    /**
     * 读取缓存
     *
     * @param key 缓存key
     * @param <T>
     * @return 缓存返回值
     */
    public <T> T get(String key) {
        RBucket<T> bucket = redissonClient.getBucket(REDIS_KEY_PREFIX + key);
        return bucket.get();
    }

    /**
     * 以string的方式读取缓存
     *
     * @param key 缓存key
     * @return 缓存返回值
     */
    public String getString(String key) {
        RBucket<String> bucket = redissonClient.getBucket(REDIS_KEY_PREFIX + key, StringCodec.INSTANCE);
        return bucket.get();
    }

    /**
     * 设置缓存（注：redisson会自动选择序列化反序列化方式）
     *
     * @param key   缓存key
     * @param value 缓存值
     * @param <T>
     */
    public <T> void put(String key, T value) {
        RBucket<T> bucket = redissonClient.getBucket(REDIS_KEY_PREFIX + key);
        bucket.set(value, DEFAULT_EXPIRED, TimeUnit.SECONDS);
    }

    /**
     * 以string的方式设置缓存
     *
     * @param key
     * @param value
     */
    public void putString(String key, String value) {
        RBucket<String> bucket = redissonClient.getBucket(REDIS_KEY_PREFIX + key, StringCodec.INSTANCE);
        bucket.set(value, DEFAULT_EXPIRED, TimeUnit.SECONDS);
    }

    /**
     * 以string的方式保存缓存（与其他应用共用redis时需要使用该函数）
     *
     * @param key     缓存key
     * @param value   缓存值
     * @param expired 缓存过期时间
     */
    public void putString(String key, String value, long expired) {
        RBucket<String> bucket = redissonClient.getBucket(REDIS_KEY_PREFIX + key, StringCodec.INSTANCE);
        bucket.set(value, expired <= 0 ? DEFAULT_EXPIRED : expired, TimeUnit.SECONDS);
    }

    /**
     * 如果不存在则写入缓存（string方式，不带有redisson的格式信息）
     *
     * @param key     缓存key
     * @param value   缓存值
     * @param expired 缓存过期时间
     */
    public boolean putStringIfAbsent(String key, String value, long expired) {
        RBucket<String> bucket = redissonClient.getBucket(REDIS_KEY_PREFIX + key, StringCodec.INSTANCE);
        return bucket.trySet(value, expired <= 0 ? DEFAULT_EXPIRED : expired, TimeUnit.SECONDS);
    }

    /**
     * 如果不存在则写入缓存（string方式，不带有redisson的格式信息）（不带过期时间，永久保存）
     *
     * @param key   缓存key
     * @param value 缓存值
     */
    public boolean putStringIfAbsent(String key, String value) {
        RBucket<String> bucket = redissonClient.getBucket(REDIS_KEY_PREFIX + key, StringCodec.INSTANCE);
        return bucket.trySet(value);
    }

    /**
     * 设置缓存
     *
     * @param key     缓存key
     * @param value   缓存值
     * @param expired 缓存过期时间
     * @param <T>     类型
     */
    public <T> void put(String key, T value, long expired) {
        RBucket<T> bucket = redissonClient.getBucket(REDIS_KEY_PREFIX + key);
        bucket.set(value, expired <= 0 ? DEFAULT_EXPIRED : expired, TimeUnit.SECONDS);
    }

    /**
     * 移除缓存
     *
     * @param key
     */
    public void remove(String key) {
        redissonClient.getBucket(REDIS_KEY_PREFIX + key).delete();
    }

    /**
     * 判断缓存是否存在
     *
     * @param key
     * @return
     */
    public boolean exists(String key) {
        return redissonClient.getBucket(REDIS_KEY_PREFIX + key).isExists();
    }


    /**
     * 暴露redisson的RList对象
     *
     * @param key
     * @param <T>
     * @return
     */
    public <T> RList<T> getRedisList(String key) {
        return redissonClient.getList(REDIS_KEY_PREFIX + key);
    }

    /**
     * 暴露redisson的RMapCache对象
     *
     * @param key
     * @param <K>
     * @param <V>
     * @return
     */
    public <K, V> RMapCache<K, V> getRedisMap(String key) {
        return redissonClient.getMapCache(REDIS_KEY_PREFIX + key);
    }

    /**
     * 暴露redisson的RSET对象
     *
     * @param key
     * @param <T>
     * @return
     */
    public <T> RSet<T> getRedisSet(String key) {
        return redissonClient.getSet(REDIS_KEY_PREFIX + key);
    }


    /**
     * 暴露redisson的RScoredSortedSet对象
     *
     * @param key
     * @param <T>
     * @return
     */
    public <T> RScoredSortedSet<T> getRedisScoredSortedSet(String key) {
        return redissonClient.getScoredSortedSet(REDIS_KEY_PREFIX + key);
    }

    /**
     * 暴露redisson的Lock对象
     *
     * @param key
     * @return
     */
    public RLock getRedisLock(String key) {
        return redissonClient.getLock(REDIS_KEY_PREFIX + key);
    }

}