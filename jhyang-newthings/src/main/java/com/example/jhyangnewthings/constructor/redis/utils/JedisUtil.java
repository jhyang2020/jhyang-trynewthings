package com.example.jhyangnewthings.constructor.redis.utils;//package com.fiberhome.uqbing.constructor.redis.utils;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//import redis.clients.jedis.*;
//
//import java.nio.charset.Charset;
//import java.util.*;
//
///**
// * @ClassName JedisUtil
// * @Description redis连接池工具类
// * @Author longPan
// * @Version 1.0
// * @Date 2018/11/21 13:53
// */
//@Component
//public class JedisUtil {
//    private static final Log log = LogFactory.getLog(JedisUtil.class);
//
//    private static JedisPool jedisPool = null;
//
//    @Value()
//    private String REDIS_MAX_TOTAL;
//
//    /** 队列最大消息个数 */
////	private static final long MAX_MSG_COMBINE_SIZE = 10;
//
//    /**
//     * key超时时间
//     **/
////	private static final int REDIS_KEY_EXPRIE_SECOND = 7200;
//
//    private static final JedisUtil jedisUtil = new JedisUtil();
//
//    private JedisUtil() {
//
//    }
//
//    static {
//        try {
//            // 连接池配置项
//            JedisPoolConfig config = new JedisPoolConfig();
//            // 最大活跃连接数
//            config.setMaxTotal(Integer.parseInt(ConfigConstant.REDIS_MAX_TOTAL));
//            // 最大空闲连接数
//            config.setMaxIdle(Integer.parseInt(ConfigConstant.REDIS_MAX_IDLE));
//            // 初始化连接数
//            config.setMinIdle(Integer.parseInt(ConfigConstant.REDIS_MIN_IDLE));
//            // 最大等待时间
//            config.setMaxWaitMillis(Integer.parseInt(ConfigConstant.REDIS_MAX_WAIT));
//            // 从池中获取连接时，是否进行有效检查
//            config.setTestOnBorrow(Boolean.parseBoolean(ConfigConstant.REDIS_TEST_ON_BORROW));
//            // 归还连接时，是否进行有效检查
//            config.setTestOnReturn(Boolean.parseBoolean(ConfigConstant.REDIS_TEST_ON_RETURN));
//            /*
//             * jedisPool = new JedisPool(config, ConfigConstant.REDIS_HOST,
//			 * Integer.parseInt(ConfigConstant.REDIS_PORT),
//			 * Integer.parseInt(ConfigConstant.REDIS_TIMEOUT),
//			 * ConfigConstant.REDIS_PASSWORD);
//			 */
//            //大连redis需要密码链接
//
//            if (StringUtils.isNotBlank(ConfigConstant.REDIS_PASSWORD)) {
//                jedisPool = new JedisPool(config, ConfigConstant.REDIS_HOST, Integer.parseInt(ConfigConstant.REDIS_PORT),
//                        Integer.parseInt(ConfigConstant.REDIS_TIMEOUT), ConfigConstant.REDIS_PASSWORD);
//            } else {
//                jedisPool = new JedisPool(config, ConfigConstant.REDIS_HOST, Integer.parseInt(ConfigConstant.REDIS_PORT),
//                        Integer.parseInt(ConfigConstant.REDIS_TIMEOUT));
//            }
//        } catch (Exception e) {
//            log.error("初始化连接池失败！: " + e);
//        }
//    }
//
//    /**
//     * 从jedis连接池中获取获取jedis对象
//     */
//    public Jedis getJedis() {
//        Jedis jedis = null;
//        try {
//            jedis = jedisPool.getResource();
//            log.debug("获取redis连接成功！");
//        } catch (Exception e) {
//            log.error("获取redis连接失败！ : " + e);
//        }
//        return jedis;
//    }
//
//    /**
//     * 获取JedisUtil实例
//     *
//     * @return
//     */
//    public static JedisUtil getInstance() {
//        return jedisUtil;
//    }
//
//    private static final String LOCK_SUCCESS = "OK";
//    private static final String SET_IF_NOT_EXIST = "NX";
//    private static final String SET_WITH_EXPIRE_TIME = "EX";
//
//
//    /**
//     * 不同客户加锁方法
//     *
//     * @param lockKey    加锁key
//     * @param requestId  NX 当key不存在set，XX当key存在set
//     * @param expireTime EX代表秒,PX代表毫秒
//     * @return
//     */
//    public boolean setRedisLock(String lockKey, String requestId, int expireTime) {
//        Jedis jedis = getJedis();
//        String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
//        returnJedis(jedis);
//        if (LOCK_SUCCESS.equals(result)) {
//            return true;
//        }
//        return false;
//    }
//
//    private static final Long RELEASE_STATUS = 1L;
//
//    public boolean releaseRedisLock(String lockKey, String requestId) {
//        Jedis jedis = getJedis();
//        String script = "if redis.call('get',KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
//        Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));
//        returnJedis(jedis);
//        if (RELEASE_STATUS.equals(result)) {
//            return true;
//        }
//        return false;
//    }
//
//    /**
//     * 回收jedis(放到finally中)
//     *
//     * @param jedis
//     */
//    public void returnJedis(Jedis jedis) {
//        if (null != jedis) {
//            jedis.close();
//        }
//    }
//
//    /**
//     * 取多个集合中的交集
//     *
//     * @param keys 多个集合的key
//     * @return
//     */
//    public Set<String> setInter(String... keys) {
//        Jedis jedis = getJedis();
//        Set<String> values = jedis.sinter(keys);
//        returnJedis(jedis);
//        return values;
//    }
//
//    /**
//     * 取set集合中的元素数量 set的集合key
//     */
//    public long getSetCount(String key) {
//        Jedis jedis = getJedis();
//        long count = jedis.scard(key);
//        returnJedis(jedis);
//        return count;
//    }
//
//    /**
//     * 获取set集合中的所有元素 set的集合key
//     */
//    public Set<String> getSetElements(String key) {
//        Jedis jedis = getJedis();
//        Set<String> smembers = jedis.smembers(key);
//        returnJedis(jedis);
//        return smembers;
//    }
//
//    /**
//     * 获取set集合中的所有元素 set的集合key
//     */
//    public Set<byte[]> getSetElements(byte[] key) {
//        Jedis jedis = getJedis();
//        Set<byte[]> smembers = jedis.smembers(key);
//        returnJedis(jedis);
//        return smembers;
//    }
//
//    /**
//     * 删除set集合中的某一元素 set的集合key，需删除的元素member
//     */
//    public void delSetElements(byte[] key, byte[] member) {
//        Jedis jedis = getJedis();
//        jedis.srem(key, member);
//        returnJedis(jedis);
//    }
//
//    /**
//     * 获取模糊匹配key的keys
//     *
//     * @param key
//     * @return
//     */
//    public Set<String> getRegexKeys(String key) {
//        Jedis jedis = getJedis();
//        Set<String> keys = jedis.keys(key);
//        returnJedis(jedis);
//        return keys;
//    }
//
//    /**
//     * 将多个set集合中的交集保存到destKey的目标set中并返回目标结合中的元素集合
//     *
//     * @param destKey 目标set的key
//     * @param keys    做交集的set的键
//     */
//    public Set<String> interToDestKey(String destKey, String... keys) {
//        Jedis jedis = getJedis();
//        jedis.sinterstore(destKey, keys);
//        Set<String> mongoIds = jedis.smembers(destKey);
//        returnJedis(jedis);
//        return mongoIds;
//    }
//
//    /**
//     * 将多个set集合中的并集保存到destKey的目标set中,并返回目标set的元素集合
//     *
//     * @param destKey 目标set的key
//     * @param keys    做并集的set的键
//     */
//    public Set<String> unionToDestKey(String destKey, String... keys) {
//        Jedis jedis = getJedis();
//        jedis.sunionstore(destKey, keys);
//        Set<String> mongoIds = jedis.smembers(destKey);
//        returnJedis(jedis);
//        return mongoIds;
//    }
//
//    /**
//     * 取多个集合中的并集
//     *
//     * @param keys 多个集合的key
//     * @return
//     */
//    public Set<String> setUnion(String... keys) {
//        Jedis jedis = getJedis();
//        Set<String> values = jedis.sunion(keys);
//        returnJedis(jedis);
//        return values;
//    }
//
//    /**
//     * 将第一个key对多个key做差集后的数据保存到destKey的目标set中,并返回目标set的元素集合
//     *
//     * @param destKey 目标set的key
//     * @param keys    做差集的set的键
//     */
//    public Set<String> diffToDestKey(String destKey, String... keys) {
//        Jedis jedis = getJedis();
//        jedis.sdiffstore(destKey, keys);
//        Set<String> mongoIds = jedis.smembers(destKey);
//        returnJedis(jedis);
//        return mongoIds;
//    }
//
//    /**
//     * 取第一个key对所有key做差集后的元素集合
//     *
//     * @param keys 多个集合的key
//     * @return
//     */
//    public Set<String> setDiff(String... keys) {
//        Jedis jedis = getJedis();
//        Set<String> values = jedis.sdiff(keys);
//        returnJedis(jedis);
//        return values;
//    }
//
//    /**
//     * 添加元素到有序集合sortedSet
//     *
//     * @param key
//     * @param value
//     * @param score 用于排序
//     */
//    public void zadd(String key, String value, double score) {
//        Jedis jedis = getJedis();
//        jedis.zadd(key, score, value);
//        returnJedis(jedis);
//    }
//
//    /**
//     * 添加sorted set
//     *
//     * @param key     sorted的Key名称
//     * @param value   需要添加的值
//     * @param score   需要添加的分值
//     * @param limit   有序集合需要维持的长度
//     * @param reverse true,当队列达到规定的长度时，删除分值最小的值，false删除最大的值
//     */
//    public void zaddWithLimit(String key, String value, double score, int limit, boolean reverse) {
//        Jedis jedis = getJedis();
//        jedis.zadd(key, score, value);
//        long totalcount = jedis.zcard(key);
//        if (limit < totalcount) {
//            if (reverse) {
//                jedis.zremrangeByRank(key, 0, 0);
//            } else {
//                jedis.zremrangeByRank(key, totalcount, totalcount);
//            }
//        }
//        returnJedis(jedis);
//    }
//
//    //↓↓↓↓↓↓↓↓↓↓==K V==↓↓↓↓↓↓↓↓↓↓
//
//    /**
//     * 设置key的值为value
//     *
//     * @param key
//     * @param value
//     */
//    public void setKey(String key, String value) {
//        Jedis jedis = getJedis();
//        jedis.set(key, value);
//        returnJedis(jedis);
//    }
//
//    public void setKey(byte[] key, byte[] value) {
//        Jedis jedis = getJedis();
//        jedis.set(key, value);
//        returnJedis(jedis);
//    }
//
//    /**
//     * 设置key的值为value，并规定Key的失效时间
//     *
//     * @param key 集合的键 second 过期时间(秒数) vaule 需要加入的值
//     * @return
//     */
//    public void setKeyWithExpire(String key, int second, String value) {
//        Jedis jedis = getJedis();
//        jedis.set(key, value);
//        jedis.expire(key, second);
//        returnJedis(jedis);
//    }
//
//    public void setKeyWithExpire(byte[] key, int second, byte[] value) {
//        Jedis jedis = getJedis();
//        jedis.set(key, value);
//        jedis.expire(key, second);
//        returnJedis(jedis);
//    }
//
//    /**
//     * 获取指定key的值
//     *
//     * @param key
//     */
//    public String getValue(String key) {
//        Jedis jedis = getJedis();
//        String value = jedis.get(key);
//        returnJedis(jedis);
//        return value;
//    }
//
//    public byte[] getValue(byte[] key) {
//        Jedis jedis = getJedis();
//        byte[] value = jedis.get(key);
//        returnJedis(jedis);
//        return value;
//    }
//    //↑↑↑↑↑↑↑↑↑↑==K V==↑↑↑↑↑↑↑↑↑↑
//
//    /**
//     * 获取指定set中的元素集合
//     *
//     * @param key
//     */
//    public Set<String> getSetValue(String key) {
//        Jedis jedis = getJedis();
//        Set<String> valueSet = jedis.smembers(key);
//        returnJedis(jedis);
//        return valueSet;
//    }
//
//    /**
//     * 返回sortedSet指定范围的集合元素,0为第一个元素，-1为最后一个元素（元素已按由小到大排序）
//     *
//     * @param key
//     * @param start
//     * @param end
//     * @return
//     */
//    public Set<String> zrange(String key, int start, int end) {
//        Jedis jedis = getJedis();
//        Set<String> set = jedis.zrange(key, start, end);
//        returnJedis(jedis);
//        return set;
//    }
//
//    /**
//     * 获取给定区间的元素，原始按照权重由高到低排序
//     *
//     * @param key
//     * @param start
//     * @param end
//     * @return
//     */
//    public Set<String> zrevrange(String key, int start, int end) {
//        Jedis jedis = getJedis();
//        Set<String> set = jedis.zrevrange(key, start, end);
//        returnJedis(jedis);
//        return set;
//    }
//
//    /**
//     * 获取所有list元素
//     *
//     * @param key
//     * @return
//     */
//    public List<String> getAllList(String key) {
//        Jedis jedis = getJedis();
//        List<String> list = jedis.lrange(key, 0, -1);
//        returnJedis(jedis);
//        return list;
//    }
//    /**
//     * 获取所有list元素
//     *
//     * @param key
//     * @return
//     */
//    public List<byte[]> getAllList(byte[] key) {
//        Jedis jedis = getJedis();
//        List<byte[]> list = jedis.lrange(key, 0, -1);
//        returnJedis(jedis);
//        return list;
//    }
//    /**
//     * 添加对应关系，如果对应关系已存在，则覆盖
//     *
//     * @param key
//     * @param map 对应关系
//     * @return 状态，成功返回OK
//     */
//    public String hmset(String key, Map<String, String> map) {
//        Jedis jedis = getJedis();
//        String s = jedis.hmset(key, map);
//        returnJedis(jedis);
//        return s;
//    }
//
//    /**
//     * 向List尾部追加记录
//     *
//     * @param key
//     * @param value
//     * @return 记录总数
//     */
//    public long rpush(String key, String value) {
//        Jedis jedis = getJedis();
//        long count = jedis.rpush(key, value);
//        returnJedis(jedis);
//        return count;
//    }
//    /**
//     * 向list集合中尾部添加数据，并规定Key的失效时间
//     *
//     * @param key 集合的键 second 过期时间(秒数) vaule 需要加入的值
//     * @return
//     */
//    public void addListValueWithExpire(String key, int second, String value) {
//        Jedis jedis = getJedis();
//        jedis.rpush(key, value);
//        jedis.expire(key, second);
//        returnJedis(jedis);
//    }
//    /**
//     * 向List头部追加记录
//     *
//     * @param key
//     * @param value
//     * @return 记录总数
//     */
//    public long lpush(String key, String value) {
//        Jedis jedis = getJedis();
//        long count = jedis.lpush(key, value);
//        returnJedis(jedis);
//        return count;
//    }
//
//    /**
//     * 截取list并返回截取后的元素
//     *
//     * @param key
//     * @param start
//     * @param end
//     * @return
//     */
//    public List<String> ltrim(String key, int start, int end) {
//        Jedis jedis = getJedis();
//        jedis.ltrim(key, start, end);
//        List<String> list = jedis.lrange(key, 0, -1);
//        returnJedis(jedis);
//        return list;
//    }
//
//    /**
//     * 获取list的元素个数
//     *
//     * @param key
//     * @return
//     */
//    public long llen(String key) {
//        Jedis jedis = getJedis();
//        Long len = jedis.llen(key);
//        returnJedis(jedis);
//        return len;
//    }
//
//    /**
//     * 删除
//     *
//     * @param key
//     * @return 被移除key的数量
//     */
//    public long del(String key) {
//        Jedis jedis = getJedis();
//        long s = jedis.del(key);
//        returnJedis(jedis);
//        return s;
//    }
//
//    public long del(byte[] key) {
//        Jedis jedis = getJedis();
//        long s = jedis.del(key);
//        returnJedis(jedis);
//        return s;
//    }
//
//    /**
//     * 向set集合中添加集合数据
//     *
//     * @param key    集合的键 vaules 需要加入的值
//     * @param expire 超时时间
//     * @return
//     */
//    public void addSetWithExpire(String key, Set<String> vaules, int expire) {
//        Jedis jedis = getJedis();
//        Pipeline pipeline = jedis.pipelined();
//        for (String value : vaules) {
//            pipeline.sadd(key, value);
//            pipeline.expire(key, expire);
//        }
//        pipeline.sync();
//        returnJedis(jedis);
//    }
//
//    /**
//     * 向set集合中添加集合数据，并规定Key的失效时间
//     *
//     * @param key 集合的键 second 过期时间(秒数) vaules 需要加入的值
//     * @return
//     */
//    public void updateKeyWithExpire(String key, int second, Set<String> vaules) {
//        Jedis jedis = getJedis();
//        jedis.del(key);
//        Pipeline pipeline = jedis.pipelined();
//        for (String value : vaules) {
//            pipeline.sadd(key, value);
//            pipeline.expire(key, second);
//        }
//        pipeline.sync();
//        returnJedis(jedis);
//    }
//
//    /**
//     * 向set集合中添加数据
//     *
//     * @param key 集合的键  vaule 需要加入的值
//     * @return
//     */
//    public void addSetValue(String key, String value) {
//        Jedis jedis = getJedis();
//        jedis.sadd(key, value);
//        returnJedis(jedis);
//    }
//
//    /**
//     * 向set集合中添加数据
//     *
//     * @param key 集合的键  vaule 需要加入的值
//     * @return
//     */
//    public void addSetValue(byte[] key, byte[] value) {
//        Jedis jedis = getJedis();
//        jedis.sadd(key, value);
//        returnJedis(jedis);
//    }
//
//    /**
//     * 向set集合中添加数据，并规定Key的失效时间
//     *
//     * @param key 集合的键 second 过期时间(秒数) vaule 需要加入的值
//     * @return
//     */
//    public void addSetValueWithExpire(String key, int second, String value) {
//        Jedis jedis = getJedis();
//        jedis.sadd(key, value);
//        jedis.expire(key, second);
//        returnJedis(jedis);
//    }
//
//    public void addSetValueWithExpire(byte[] key, int second, byte[] value) {
//        Jedis jedis = getJedis();
//        jedis.sadd(key, value);
//        jedis.expire(key, second);
//        returnJedis(jedis);
//    }
//
//    /**
//     * 向set集合中添加集合数据，并规定Key的失效时间
//     *
//     * @param valueMap 集合的键 second 过期时间(秒数) vaules 需要加入的值
//     * @return
//     */
//    public void addSetWithExpire(Map<String, HashSet<String>> valueMap, int second) {
//        Jedis jedis = getJedis();
//        Pipeline pipeline = jedis.pipelined();
//        for (Map.Entry<String, HashSet<String>> entry : valueMap.entrySet()) {
//            for (String str : entry.getValue()) {
//                pipeline.sadd(entry.getKey(), str);
//                pipeline.expire(entry.getKey(), second);
//            }
//        }
//        pipeline.sync();
//        returnJedis(jedis);
//    }
//
//    /**
//     * 向redis队列中发布消息
//     *
//     * @param channel 通道名
//     * @return
//     */
//    public void publishMsg(String channel, Object redisMsg) {
//        Jedis jedis = getJedis();
//        jedis.publish(channel.getBytes(Charset.forName("UTF-8")), SerializeUtil.serialize(redisMsg));
//        returnJedis(jedis);
//    }
//
//    /**
//     * 向redis队列中发布消息
//     *
//     * @param channel 通道名
//     * @return
//     */
//    public void publishMsg(byte[] channel, byte[] redisMsg) {
//        Jedis jedis = getJedis();
//        jedis.publish(channel, redisMsg);
//        returnJedis(jedis);
//    }
//
//    /**
//     * 从redis队列中消费消息(此方法会阻塞) 在 binaryJedisPubSub的onMessage中自动接受队列中的消息
//     *
//     * @return
//     */
////	public void subscribeMsg(BinaryJedisPubSub binaryJedisPubSub, String channel) {
////		Jedis jedis = jedisPool.getResource();
////		jedis.subscribe(binaryJedisPubSub, channel.getBytes(Charset.forName("UTF-8")));
//////		returnJedis(jedis);
////	}
//    public void subscribeMsg(BinaryJedisPubSub binaryJedisPubSub, String channel) {
//        int times = 0;
//        while (true) {
//            try {
//                Jedis tmpJedis = getJedis();
//                tmpJedis.subscribe(binaryJedisPubSub, channel.getBytes(Charset.forName("UTF-8")));
//                returnJedis(tmpJedis);
//                break;
//            } catch (Exception e) {
//                log.error(String.format("--------subscribe error channelName=【%s】,try times: %s----------", channel, times), e);
//                if (times > NUM_5) {
//                    log.error("throw Jedis Exception...");
//                    throw e;
//                }
//                times++;
//                try {
//                    Thread.sleep(NUM_2000);
//                } catch (InterruptedException e1) {
////                    e1.getStackTrace();
//                }
//            }
//        }
//    }
//
//    /**
//     * 从集合中删除成员
//     *
//     * @param key
//     * @param value
//     * @return 返回1成功
//     */
//    public long zrem(String key, String... value) {
//        Jedis jedis = getJedis();
//        long s = jedis.zrem(key, value);
//        returnJedis(jedis);
//        return s;
//    }
//
//    public void saveValueByKey(int dbIndex, byte[] key, byte[] value, int expireTime) throws Exception {
//        Jedis jedis = null;
//        try {
//            jedis = getJedis();
//            jedis.select(dbIndex);
//            jedis.set(key, value);
//            if (expireTime > 0) {
//                jedis.expire(key, expireTime);
//            }
//        } catch (Exception e) {
//            throw e;
//        } finally {
//            returnJedis(jedis);
//        }
//    }
//
//    public byte[] getValueByKey(int dbIndex, byte[] key) throws Exception {
//        Jedis jedis = null;
//        byte[] result = null;
//        try {
//            jedis = getJedis();
//            jedis.select(dbIndex);
//            result = jedis.get(key);
//        } catch (Exception e) {
//            throw e;
//        } finally {
//            returnJedis(jedis);
//        }
//        return result;
//    }
//
//    public void deleteByKey(int dbIndex, byte[] key) throws Exception {
//        Jedis jedis = null;
//        try {
//            jedis = getJedis();
//            jedis.select(dbIndex);
//            jedis.del(key);
//        } catch (Exception e) {
//            throw e;
//        } finally {
//            returnJedis(jedis);
//        }
//    }
//
//    /**
//     * 获取总数量
//     *
//     * @param key
//     * @return
//     */
//    public long zcard(String key) {
//        Jedis jedis = getJedis();
//        long count = jedis.zcard(key);
//        returnJedis(jedis);
//        return count;
//    }
//
//    /**
//     * 是否存在KEY
//     *
//     * @param key
//     * @return
//     */
//    public boolean exists(String key) {
//        Jedis jedis = getJedis();
//        boolean exists = jedis.exists(key);
//        returnJedis(jedis);
//        return exists;
//    }
//
//    public boolean exists(byte[] key) {
//        Jedis jedis = getJedis();
//        boolean exists = jedis.exists(key);
//        returnJedis(jedis);
//        return exists;
//    }
//
//    /**
//     * 重命名KEY
//     *
//     * @param oldKey
//     * @param newKey
//     * @return
//     */
//    public String rename(String oldKey, String newKey) {
//        Jedis jedis = getJedis();
//        String result = jedis.rename(oldKey, newKey);
//        returnJedis(jedis);
//        return result;
//    }
//
//    /**
//     * 设置失效时间
//     *
//     * @param key
//     * @param seconds
//     */
//    public void expire(String key, int seconds) {
//        Jedis jedis = getJedis();
//        jedis.expire(key, seconds);
//        returnJedis(jedis);
//    }
//
//    public void expire(byte[] key, int seconds) {
//        Jedis jedis = getJedis();
//        jedis.expire(key, seconds);
//        returnJedis(jedis);
//    }
//
//    /**
//     * 删除失效时间
//     *
//     * @param key
//     */
//    public void persist(String key) {
//        Jedis jedis = getJedis();
//        jedis.persist(key);
//        returnJedis(jedis);
//    }
//
//    public void persist(byte[] key) {
//        Jedis jedis = getJedis();
//        jedis.persist(key);
//        returnJedis(jedis);
//    }
//
//    /**
//     * 添加一个键值对，如果键存在不在添加，如果不存在，添加完成以后设置键的有效期
//     *
//     * @param key
//     * @param value
//     * @param timeOut
//     */
//    public void setnxWithTimeOut(String key, String value, int timeOut) {
//        Jedis jedis = getJedis();
//        if (0 != jedis.setnx(key, value)) {
//            jedis.expire(key, timeOut);
//        }
//        returnJedis(jedis);
//    }
//
//    /**
//     * 返回指定key序列值
//     *
//     * @param key
//     * @return
//     */
//    public long incr(String key) {
//        Jedis jedis = getJedis();
//        long l = jedis.incr(key);
//        returnJedis(jedis);
//        return l;
//    }
//
////    public static void main(String[] args) {
////        JedisUtil instance = JedisUtil.getInstance();
////        Jedis jedis = instance.getJedis();
////        String id = "test-201904091516";
////        for (int i = 0; i < NUM_100; i++) {
////            instance.addSetValueWithExpire(id, NUM_7200, i + "");
/////*			jedis.sadd("test-20190409", i + "");
////            jedis.expire("test-20190409", 7200);*/
////        }
//////        System.out.println(jedis.smembers(id));
/////*		jedis.smembers("DOCSTAT-16-1545642976923_0000".getBytes()).forEach(uInfo -> {
////            UqueryInfo dd = (UqueryInfo) SerializeUtil.unSerialize(uInfo);
////			System.out.println(dd);
////		});*/
////    }
//}
