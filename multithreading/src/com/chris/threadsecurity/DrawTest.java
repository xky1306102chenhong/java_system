package com.chris.threadsecurity;

/**
 * @author Chris Chen
 * @date 2019/4/28 上午10:34
 */
public class DrawTest {
    public static void main(String[] args) {
        /*
        创建一个账户
         */
        Account account = new Account("1234567", 1000);
        new DrawThread("甲", account, 800).start();
        new DrawThread("乙", account, 800).start();
    }
}
