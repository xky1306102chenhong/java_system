/**
 * @author Chris Chen
 * @date 2019/4/27 上午9:25
 */

public class SecondThread3 {
    public static void main(String[] args) {
        for (int i=0; i<100;i++){
            System.out.println(Thread.currentThread().getName() +": "+ i);
            if(i==20){
                /*
                无法在实现Runnable接口的类里增加实例变量，因此没有所谓共享线程类的实例变量
                 */
                Runnable runnable = ()->{
                    for (int j=0;j<100;j++){
                        System.out.println(Thread.currentThread().getName()+": "+j);
                    }
                };
                new Thread(runnable, "newThread-1").start();
                new Thread(runnable,"newThread-2").start();
            }
        }
    }
}
