package com.chris.threadsafe;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Chris Chen
 * @date 2019/4/29 下午3:18
 */
public class Account3 {
    /**
     * 定义锁对象
     */
    private final ReentrantLock lock = new ReentrantLock();

    private String accountNo;
    private double balance;

    public Account3() {
    }

    public Account3(String accountNo, double balance) {
        this.accountNo = accountNo;
        this.balance = balance;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj != null && obj.getClass() == Account.class){
            Account target = (Account) obj;
            return target.getAccountNo().equals(accountNo);
        }
        return false;
    }

    @Override
    public int hashCode(){
        return accountNo.hashCode();
    }

    public void draw(double drawAmount){
        /*
        加锁
         */
        lock.lock();

        try {
            /*
        如果余额大于取钱数目
         */
            if(balance >= drawAmount){
            /*
            吐出钞票
             */
                System.out.println(Thread.currentThread().getName() + "取钱成功！吐出钞票：" + drawAmount);
            /*
            修改余额
             */
                balance -= drawAmount;
                System.out.println("\t余额为：" + balance);
            }else {
                System.out.println(Thread.currentThread().getName() + "取钱失败！余额不足！");
            }
        }finally {
            /*
            修改完成，释放锁
             */
            lock.unlock();
        }
    }
}
