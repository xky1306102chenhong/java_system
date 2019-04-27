/**
 * @author Chris Chen
 * @date 2019/4/27 上午9:16
 */
public class SecondThread2 {
    public static void main(String[] args) {
        for(int i=0; i<100;i++){
            System.out.println(Thread.currentThread().getName()+": "+i);

            if(i==20){
                Runnable runnable = new Runnable() {
                    public int i;
                    @Override
                    public void run() {
                        for (;i<100;i++){
                            System.out.println(Thread.currentThread().getName()+": "+i);
                        }
                    }
                };
                new Thread(runnable, "newThread-1").start();
                new Thread(runnable, "newThread-2").start();
            }
        }
    }
}
