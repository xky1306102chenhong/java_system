package com.chris.threadcommunication;

/**
 * @author Chris Chen
 * @date 2019/5/2 下午8:25
 */
public class Test {
    public static void main(String[] args) {
        Account account = new Account("1234567", 0);
        new DrawThread("取钱者", account, 800).start();
        new DepositThread("存钱者甲", account, 800).start();
        new DepositThread("存钱者乙", account, 800).start();
        new DepositThread("存钱者丙", account, 800).start();

    }
}
