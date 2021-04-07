package redis.test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * Create by koala on 2021-04-06
 * 事务
 */
public class TestTXday03 {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.56.10", 6382);

        Transaction multi = jedis.multi();

        multi.set("k4","v4");
        multi.set("k5","v5");

        multi.exec();
        //multi.discard();
    }

}
