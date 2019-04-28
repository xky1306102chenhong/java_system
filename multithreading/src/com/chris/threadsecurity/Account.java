package com.chris.threadsecurity;

/**
 * @author Chris Chen
 * @date 2019/4/28 上午10:04
 */
public class Account {

    private String accountNo;
    private double balance;

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

    public void setBalance(double balance) {
        this.balance = balance;
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
