package com.koala.redis.iomultiplex.bio.accept;

import java.io.IOException;
import java.net.Socket;

/**
 * day14：
 *   案例实战：BIO
 * Create by koala on 2021-11-29
 */
public class RedisClient01
{
    public static void main(String[] args) throws IOException
    {
        System.out.println("------RedisClient01 start");
        Socket socket = new Socket("127.0.0.1", 6379);
    }
}
