package redis.test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * Create by koala on 2021-04-06
 * 使用之前先在redis中添加balance与debt
 * 127.0.0.1:6379> set balance 100
 * 127.0.0.1:6379> set debt 0
 */

public class TestTXday04 {
    public boolean transMethod() throws InterruptedException {
        Jedis jedis = new Jedis("192.168.56.10", 6382);
        int balance;// 可用余额
        int debt;// 欠额
        int amtToSubtract = 10;// 实刷额度

        jedis.watch("balance");
        //jedis.set("balance","5");//此句不该出现，讲课方便。模拟其他程序已经修改了该条目

        /**
         * 在程序停留的7秒内，在redis客户端那边 127.0.0.1:6379> set balance 5
         * 结果为false说明了加塞篡改
         */
        Thread.sleep(7000);
        balance = Integer.parseInt(jedis.get("balance"));
        if (balance < amtToSubtract) {
            jedis.unwatch();
            System.out.println("modify");
            return false;
        } else {
            System.out.println("***********transaction");
            Transaction transaction = jedis.multi();
            transaction.decrBy("balance", amtToSubtract);
            transaction.incrBy("debt", amtToSubtract);
            transaction.exec();
            balance = Integer.parseInt(jedis.get("balance"));
            debt = Integer.parseInt(jedis.get("debt"));

            System.out.println("*******" + balance);
            System.out.println("*******" + debt);
            return true;
        }
    }

    /**
     * 通俗点讲，watch命令就是标记一个键，如果标记了一个键，
     * 在提交事务前如果该键被别人修改过，那事务就会失败，这种情况通常可以在程序中
     * 重新再尝试一次。
     * 首先标记了键balance，然后检查余额是否足够，不足就取消标记，并不做扣减；
     * 足够的话，就启动事务进行更新操作，
     * 如果在此期间键balance被其它人修改，那在提交事务（执行exec）时就会报错，
     * 程序中通常可以捕获这类错误再重新执行一次，直到成功。
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        TestTXday04 test = new TestTXday04();
        boolean retValue = test.transMethod();
        System.out.println("main retValue-------: " + retValue);
    }
}