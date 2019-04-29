package com.chris.threadsafe;

/**
 * @author Chris Chen
 * @date 2019/4/28 下午7:46
 */
public class DrawThread2 extends Thread{
    private Account account;
    private double drawAmount;

    public DrawThread2(String name, Account account, double drawAmount){
        super(name);
        this.account = account;
        this.drawAmount = drawAmount;
    }

    /**
    当多个线程修改同一个共享数据时，将涉及数据安全问题
     */
    @Override
    public void run(){
        synchronized (account){
            /*
        如果余额大于取钱数目
         */
            if(account.getBalance() >= drawAmount){
            /*
            吐出钞票
             */
                System.out.println(getName() + "取钱成功！吐出钞票：" + drawAmount);
            /*
            修改余额
             */
                account.setBalance(account.getBalance() - drawAmount);
                System.out.println("\t余额为：" + account.getBalance());
            }else {
                System.out.println(getName() + "取钱失败！余额不足！");
            }
        }
    }
}
