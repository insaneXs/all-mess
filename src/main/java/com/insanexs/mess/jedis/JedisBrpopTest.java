package com.insanexs.mess.jedis;

import redis.clients.jedis.Jedis;

/**
 * @Author: xieshang
 * @Description:
 * @Date: Create at 2019-12-17
 */
public class JedisBrpopTest {

    public static void main(String[] args){
        for(int i=0; i<100; i++){
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    Jedis jedis = new Jedis("127.0.0.1", 6379);
                    while(true){

                        jedis.brpop(10, "NEVER PRODUCE MESSAGE");
                    }

                }
            });
            t.start();
        }


    }
}
