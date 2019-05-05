package com.chris.threadcommunication;

/**
 * @author Chris Chen
 * @date 2019/5/1 下午9:39
 */
public class Account {
    private String accountNo;
    private double balance;
    /**
     * 是否有存款的标记
     */
    private boolean flag = false;

    public synchronized void draw(double drawAmount) {
        try {
            /*
            如果flag==false，表明账户中还没有人存钱进去，取钱方法阻塞
             */
            if (!flag) {
                wait();
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
                notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void deposit(double depositAmount){
        /*
        如果flag==true，表明账户中已有人存钱进去，存钱线程阻塞
         */
        try {
            if(flag){
                wait();
            }else{
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
                notifyAll();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }

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
