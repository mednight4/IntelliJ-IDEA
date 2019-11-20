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
        int [] order=new int [3];
        for (int i:order){
            //(数据类型)(最小值+Math.random()*(最大值-最小值+1))
            //产生（min，max）区间任意数
        order[i]=(int)(1+Math.random()*(3-2+1));
        }
        if ((order[0]>=order[1])&&(order[0]>=order[2])){
            Li.start();
            if (order[1]>=order[2]){
                Zhang.start();
                Wang.start();}
            else {Wang.start();
                Zhang.start();}
        }
        else if ((order[1]>=order[0])&&(order[1]>=order[2])) {
            Zhang.start();
            if (order[0] >= order[2]){
                Li.start();
                Wang.start();}
            else {Wang.start();
                Li.start();}
        }
        else{
            Wang.start();
            if (order[0] >= order[1]){
                Li.start();
                Zhang.start();}
            else {Zhang.start();
            Li.start();}
        }
    }
}
