package com.chris.threadcommunication;

/**
 * @author Chris Chen
 * @date 2019/5/2 下午8:19
 */
public class DepositThread extends Thread{
    private Account account;
    private double depositAmount;

    public DepositThread(String name, Account account, double depositAmount){
        super(name);
        this.account = account;
        this.depositAmount = depositAmount;

    }

    @Override
    public void run(){
        for (int i=0;i<100;i++){
            account.deposit(depositAmount);
        }
    }
}
