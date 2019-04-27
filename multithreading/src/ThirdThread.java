import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author Chris Chen
 * @date 2019/4/27 上午10:26
 */
public class ThirdThread implements Callable<Integer> {
    //public int i = 0;

    @Override
    public Integer call() throws Exception {
        int i = 0;
        for (; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + i);
        }
        return i;
    }

    public static void main(String[] args) {

        Callable<Integer> callable = new ThirdThread();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(callable);

        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " 的循环变量i的值: " + i);
            if (i == 20) {
                new Thread(futureTask, "有返回值的线程").start();
            }
        }

        try {
            /*
            futureTask.get()会导致主线程被阻塞，直到call()方法结束并返回为止
             */
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
