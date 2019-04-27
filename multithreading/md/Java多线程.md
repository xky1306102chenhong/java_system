Java多线程
=============

几乎所有的操作系统都支持同时运行多个任务，一个任务通常是一个程序，每个运行中的程序就是一个进程。当一个程序运行时，内部可能包含了多个顺序执行流，每个顺序执行流就是一个线程。


现代操作系统多支持多进程并发：
  + 共用式多任务操作策略，例如Windows 3.1和Mac OS 9
  + 抢占式多任务操作策略：例如Windows NT、Windows 2000以及UNIX和Linux（此方式效率高，是主流）

+ 进程（Process）：当一个程序进入内存运行时，即变成一个进程。进程是处于运行过程中当程序，进程是系统进行资源分配和调度的一个独立单位。
  + 独立性：可以拥有自己独立的资源，每个进程都拥有自己私有的地址空间。
  + 动态性：进程具有自己的生命周期和各种不同的状态，这和静态的程序是不同的。
  + 并发性：多个进程可以在单个处理器上并发执行，多个进程之间不会互相影响。
+ 线程（Thread）：每个顺序执行流就是一个线程。线程是进程的执行单位，线程在程序中是独立、并发的执行流。
  + 线程可以拥有自己的堆栈、程序计数器和局部变量，但不拥有系统资源，它与父进程但其他线程共享该进程所拥有的全部资源。要确保线程不会妨碍同一进程里的其他线程。
  + 线程的执行是抢占式的
  + 线程是独立运行的，它并不知道进程中是否还有其他线程的存在。
  + 一个线程可以创建和撤销另一个线程
  + 线程的调度和管理由进程本身完成
  

线程创建的三种方式
===============

+ 继承Thread类
  + 特点：
    + 多个线程间无法共享线程类的实例变量
  + 代码：
  ```java
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

  ```
+ 实现Runnable接口
  + 特点：
    + 共享线程类的实例变量
    + Runnable接口是函数式接口（Java 8）
    + Thread类的作用就是把run()方法包装成线程执行体
  + 代码：
  ```java
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

  ```
  + 匿名内部类写法：
  ```java
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

  ```
  + lambda表达式写法
  ```java
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

  ```
+ 使用Callable和Future（Runnable接口的增强版）
  + 特点： 
    + Future接口代表call()方法的返回值
  + 代码：
  ```java
  import java.util.concurrent.Callable;
  import java.util.concurrent.FutureTask;
  
  /**
   * @author Chris Chen
   * @date 2019/4/26 下午4:12
   */
  public class ThirdThread {
      public static void main(String[] args) {
          ThirdThread thirdThread = new ThirdThread();
  
          FutureTask<Integer> task = new FutureTask<Integer>((Callable<Integer>)() ->{
              int i=0;
              for (; i< 100; i++){
                  System.out.println(Thread.currentThread().getName() + " " + i);
              }
              return i;
          } );
  
          for (int i=0; i<100; i++){
              System.out.println(Thread.currentThread().getName() + " 的循环变量i的值: " + i);
              if(i==20){
                  new Thread(task, "有返回值的线程").start();
              }
          }
          try {
              System.out.println("子线程的返回值：" + task.get());
          } catch (Exception e){
              e.printStackTrace();
          }
      }
  }

  ```