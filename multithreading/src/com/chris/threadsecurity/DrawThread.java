package com.chris.threadsecurity;

/**
 * @author Chris Chen
 * @date 2019/4/28 上午10:20
 */
public class DrawThread extends Thread {
    /**
     account: 模拟用户账户
     drawAmount: 当前取钱线程所希望取的钱数
     */
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
