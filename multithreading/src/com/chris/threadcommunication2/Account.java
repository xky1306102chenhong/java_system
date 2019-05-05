package com.chris.threadcommunication2;

import java.util.PropertyResourceBundle;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Chris Chen
 * @date 2019/5/4 上午10:36
 */
public class Account {
    /**
     * 显式定义Lock对象
     */
    private final Lock lock = new ReentrantLock();
    /**
     * 获得指定Lock对象对应的Condition
     */
    private final Condition condition = lock.newCondition();

    private String accountNo;
    private double balance;

    private boolean flag = false;

    public void draw(double drawAmount) {
        /*
        加锁
         */
        lock.lock();
        try {
            if (!flag) {
                condition.await();
            } else {
                /*
                执行取钱操作
                 */
                System.out.println(Thread.currentThread().getName() + " 取钱：" + drawAmount);
                balance -= drawAmount;
                System.out.println("账户余额为：" + balance);
                /*
                将标识账户是否已有存款的旗标设为false
                 */
                flag = false;
                /*
                唤醒其他线程
                 */
                condition.signalAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void deposit(double depositAmount) {
        lock.lock();
        try {
            if (flag) {
                condition.await();
            } else {
                /*
                执行存款操作
                 */
                System.out.println(Thread.currentThread().getName() + " 存款：" + depositAmount);
                balance += depositAmount;
                System.out.println("账户余额为： " + balance);
                /*
                将表示账户是否已有存款的旗标设为true
                 */
                flag = true;
                /*
                唤醒其他线程
                 */
                condition.signalAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public Account() {
    }

    ;

    public Account(String accountNo, double balance) {
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


}
