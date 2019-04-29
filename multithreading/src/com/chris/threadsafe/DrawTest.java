package com.chris.threadsafe;

/**
 * @author Chris Chen
 * @date 2019/4/28 上午10:34
 */
public class DrawTest {
    public static void main(String[] args) {
        /*
        创建一个账户
         */
        Account2 account2 = new Account2("1234567", 1000);
        new DrawThread3("甲", account2, 800).start();
        new DrawThread3("乙", account2, 800).start();
    }
}
