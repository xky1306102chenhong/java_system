/**
 * @author Chris Chen
 * @date 2019/4/25 下午3:54
 */
public class FirstThread extends Thread{
    private int i;

    @Override
    public void run(){
        for (; i< 100; i++){
            System.out.println(getName() + " " + i);
        }
    }

    public static void main(String[] args) {
        for (int i=0; i<100; i++){
            System.out.println(Thread.currentThread() + " " + i);
            if (i == 20 ){
                new FirstThread().start();
                new FirstThread().start();
            }
        }
    }
}
