package redis.test;

import redis.clients.jedis.Jedis;

/**
 * Create by koala on 2021-04-06
 * 测试联通
 */
public class TestPingday01 {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.56.10", 6380);
        System.out.println(jedis.ping());
    }

}
