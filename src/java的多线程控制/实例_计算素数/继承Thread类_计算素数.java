package java的多线程控制.实例_计算素数;
//编写一个程序，该程序由两个线程组成，第一个线程用来计算2-1000之间的质数个数，第二个线程用来计算1000-2000之间的质数个数
class MyThread extends Thread{
    int min=0,max=0,num=0;
    void setRun(int min,int max){
        this.max=max;
        this.min=min;
    }
    public void run(){
        int number=min;
        while(number<=max){
            boolean isPrime=true;
            for (int divisor=2;divisor<=number/2;divisor++)
                if (number % divisor==0){
                    isPrime=false;
                    break;
                }
            if (isPrime==true){
                this.num++;
            }
            number++;
        }
        System.out.println("质数的个数是"+num);
    }
}
public class 继承Thread类_计算素数 {
    public static void main(String [] args){
        MyThread t1=new MyThread();
        t1.setRun(2,1000);
        t1.setPriority(3);
        MyThread t2=new MyThread();
        t2.setRun(1000,2000);
        t2.setPriority(7);
        t1.start();
        t2.start();
        System.out.print("打完收工");
    }
}
