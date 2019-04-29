package com.chris.threadsafe;

/**
 * @author Chris Chen
 * @date 2019/4/28 下午7:46
 */
public class DrawThread3 extends Thread{
    private Account2 account2;
    private double drawAmount;

    public DrawThread3(String name, Account2 account2, double drawAmount){
        super(name);
        this.account2 = account2;
        this.drawAmount = drawAmount;
    }

    /**
     当多个线程修改同一个共享数据时，将涉及数据安全问题
     */
    @Override
    public void run(){
        account2.draw(drawAmount);
    }
}
