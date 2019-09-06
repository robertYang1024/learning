package redis.jedis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.BinaryClient;
import redis.clients.jedis.Jedis;
import sun.security.ssl.HandshakeMessage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author bo.yang
 */
public class JedisDemo {

    private static final Logger logger = LoggerFactory.getLogger(JedisDemo.class);

    public static Jedis connect() {
//        // 不使用连接池
//        Jedis jedis = new Jedis("127.0.0.1", 6379);
//        jedis.auth("password");
//        // ping 通显示PONG
//        System.out.println(jedis.ping());
        Jedis jedis = JedisPoolDemo.getJedis();
        return jedis;
    }

    /**
     * 操作String
     */
    public void testString() {
        Jedis jedis = connect();
        jedis.set("name", "kim");
        // set 多个key、value
        jedis.mset("key1", "value1", "key2", "value2");

        //自增
        jedis.incr("age");
        System.out.println(jedis.get("age"));

        jedis.del("name");
        System.out.println(jedis.get("name"));

        jedis.close();
    }

    /**
     * 操作hash
     */
    public void testHash() {
        Jedis jedis = connect();
        // 从hash中取出value, hget参数是key和field
        jedis.hset("hashkey1", "hashfield1", "hashvalue");
        System.out.println(jedis.hget("hashkey1", "hashfield1"));

        Map<String, String> map = new HashMap<>();
        map.put("address", "上海");
        map.put("name", "lixiang");
        map.put("age", "27");
        jedis.hmset("user", map);
        List<String> hmget = jedis.hmget("user", "address", "name");
        System.out.println(hmget);

        // 删除hash中的某个键值
        jedis.hdel("user", "address");

        System.out.println("hash结构user的field个数:" + jedis.hlen("user"));
        System.out.println("redis是否存在为user的key:" + jedis.exists("user"));
        System.out.println("key为user的结构类型:" + jedis.type("user"));
        System.out.println("user --> all fields:" + jedis.hkeys("user"));
        System.out.println("user --> all values:" + jedis.hvals("user"));
    }

    /**
     * 操作list
     */
    public void testList() {
        Jedis jedis = connect();

        // 从链表左边插入value
        jedis.lpush("lists", "a1");
        jedis.lpush("lists", "a2");
        jedis.lpush("lists", "a3");
        // 从链表右边插入value
        jedis.rpush("lists", "a4");
        jedis.rpush("lists", "a5");
        
        System.out.println("lists的长度："+ jedis.llen("lists"));
        System.out.println("从左到右输出所有元素：" + jedis.lrange("user", 0, -1));
        jedis.linsert("user", BinaryClient.LIST_POSITION.BEFORE, "a4", "b");
        System.out.println("在a4前插入b:" + jedis.lrange("user", 0, -1));

        // 修改列表中指定下标2的值为Java
        jedis.lset("user", 2, "java");
        System.out.println("修改列表中下标2的值为Java" + jedis.lrange("user", 0, -1));

        jedis.lpush("lists", "ccc");
        jedis.lpush("lists", "ccc");
        // 从列表中找到等于value的元素进行删除  count=0:删除所有，
        // count>0:从左到右，删除最多count个元素； count<0:从右到左，删除最多count绝对值个元素
        jedis.lrem("user", 0, "cc");
        System.out.println("删除了ccc:"+jedis.lrange("user", 0, -1));
    }

    /**
     * 操作set
     */
    public void testSet() {
        Jedis jedis = connect();

        jedis.sadd("setkey", "b1");
        jedis.sadd("setkey", "b2", "b3", "b4");

        System.out.println("获取所有加入的key" + jedis.smembers("setkey"));

        jedis.srem("setkey", "b4");
        System.out.println(jedis.smembers("setkey"));
        System.out.println("是否存在b3：" + jedis.sismember("setkey", "b3"));
        System.out.println("返回集合中的一个随机元素：" + jedis.srandmember("setkey"));
        System.out.println("返回集合元素的个数：" + jedis.scard("setkey"));
    }

    /**
     * 操作有序的set:zset
     */
    public void testZset() {
        Jedis jedis = connect();

        jedis.zadd("sortkey", 100, "a");
        Map<String, Double> map = new HashMap<>();
        map.put("b", (double) 200);
        map.put("c", (double)300);
        map.put("d", (double)400);
        map.put("e", (double)500);
        jedis.zadd("sortkey", map);

        System.out.println("从小到大排序：" + jedis.zrange("sortkey", 0, -1));
        System.out.println("从大到小排序：" + jedis.zrevrange("sortkey", 0, -1));
        System.out.println("元素个数：" +jedis.zcard("sortkey"));
        System.out.println("元素的score：" + jedis.zscore("sortkey", "d"));
        
        jedis.zrem("sortkey", "c");
        
        System.out.println("返回指定权值范围的成员个数：" + jedis.zcount("sortkey", 0, 200));
        System.out.println("给a的权值加350：" + jedis.zincrby("sortkey", 350, "a"));
        System.out.println("权值0-200的值：" + jedis.zrangeByScore("sortkey", 0, 500));
        System.out.println("返回b的权值排名：" + jedis.zrank("sortkey", "a"));
        System.out.println("输出整个集合：" + jedis.zrange("sortkey", 0 ,-1));
        System.out.println("输出整个集合(带权值)：" + jedis.zrangeWithScores("sortkey", 0 ,-1));
    }
}
