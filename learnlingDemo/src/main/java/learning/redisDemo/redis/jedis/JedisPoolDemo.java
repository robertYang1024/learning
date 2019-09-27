package learning.redisDemo.redis.jedis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author bo.yang
 */
public class JedisPoolDemo {

    private static final Logger logger = LoggerFactory.getLogger(JedisPoolDemo.class);
    /**
     *  host
      */
    private static String host = "10.118.80.53";

    private static int port = 6379;

    /**
     *  password
      */
//    private static String password = "" ;

    private static JedisPool jedisPool =  null;

    private static int timeout = 10000;

    /**
     *等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException
      */
    private static int maxWait = 10000;

    /**
     * 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的
      */
    private static boolean testOnBorrow = true;

    /**
     * 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
      */
    private static int maxIdle = 10;

    static  {
        try {

            JedisPoolConfig poolConfig  = new JedisPoolConfig();
            poolConfig.setMaxIdle(maxIdle);
            poolConfig.setTestOnBorrow(testOnBorrow);
            poolConfig.setMaxWaitMillis(maxWait);
             jedisPool = new JedisPool(poolConfig, host, port, timeout);
        } catch (Exception e) {
            logger.error("初始化失败", e);
        }
    }

    public synchronized  static Jedis getJedis() {
        if (null != jedisPool) {
            Jedis jedis = jedisPool.getResource();
            return jedis;
        }
        return null;
    }

    public synchronized static void close(Jedis jedis) {
        if (null != jedis) {
            jedis.close();
        }
    }
}
