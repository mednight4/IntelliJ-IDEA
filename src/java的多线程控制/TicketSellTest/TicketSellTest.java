package java的多线程控制.TicketSellTest;
//class TicketSellerTest extends Thread{
//    static TicketSellTest Li,Zhang,Wang;
//    //因为在本类中定义该类类型变量，程序会报错，所以此处用了接口
//}
public class TicketSellTest implements Runnable{
    static Thread Li,Zhang,Wang;
    static TicketSell Seller;
    public void run(){
        if (Thread.currentThread()==Zhang){
            Seller.sell(20);
        }
        else if (Thread.currentThread()==Wang) {
            Seller.sell(10);
        }
        else if (Thread.currentThread()==Li){
            Seller.sell(5);
        }
    }
    public static void main(String [] args){
        TicketSellTest t=new TicketSellTest();
        Zhang=new Thread(t);
        Li=new Thread(t);
        Wang=new Thread(t);
        Seller=new TicketSell();
        Li.start();
        Zhang.start();
        Wang.start();
    }
}
