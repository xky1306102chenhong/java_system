/**
 * @author Chris Chen
 * @date 2019/4/26 下午3:41
 */
public class SecondThread implements Runnable{
    private int i;

    @Override
    public void run(){
        for (; i<100; i++){
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }

    public static void main(String[] args) {
        for (int i=0; i< 100; i++){
            System.out.println(Thread.currentThread().getName() + " " + i);
            if(i==20){
                SecondThread secondThread = new SecondThread();
                new Thread(secondThread, "newThread1").start();
                new Thread(secondThread, "newThread2").start();

            }
        }
    }
}
