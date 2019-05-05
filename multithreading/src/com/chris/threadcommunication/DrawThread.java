package com.chris.threadcommunication;

/**
 * @author Chris Chen
 * @date 2019/5/2 下午7:47
 */
public class DrawThread extends Thread{
    private Account account;
    private double drawAmount;

    public DrawThread(String name, Account account, double drawAmount){
        super(name);
        this.account = account;
        this.drawAmount = drawAmount;
    }

    @Override
    public void run(){
        /*
        重复100次执行取钱操作
         */
        for (int i = 0; i<100;i++){
            account.draw(drawAmount);
        }
    }

}
