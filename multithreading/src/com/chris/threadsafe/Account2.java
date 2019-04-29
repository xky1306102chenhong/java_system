package com.chris.threadsafe;

/**
 * @author Chris Chen
 * @date 2019/4/29 上午9:26
 */
public class Account2 {
    private String accountNo;
    private double balance;

    public Account2(){

    }

    public Account2(String accountNo, double balance) {
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

    /*
    因为账户余额不允许随便修改，所以只为balance提供getter
     */

    /**
     * 提供一个线程安全的draw()方法来完成取钱操作
     * param：drawAmount 取钱金额
     */
    public synchronized void draw(double drawAmount){
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
}
