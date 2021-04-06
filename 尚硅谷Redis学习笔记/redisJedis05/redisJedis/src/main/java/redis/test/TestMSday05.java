package redis.test;

import redis.clients.jedis.Jedis;

/**
 * Create by koala on 2021-04-06
 * 内存速度过快的原因，所以初次执行结果会为null，需再执行一次才能得到正确结果
 */
public class TestMSday05 {

    public static void main(String[] args) {
        Jedis jedis_M = new Jedis("192.168.56.10", 6382);
        Jedis jedis_S = new Jedis("192.168.56.10", 6381);

        jedis_S.slaveof("192.168.56.10", 6382);

        jedis_M.set("class","1122V2");

        String result = jedis_S.get("class");
        System.out.println(result);
    }

}
