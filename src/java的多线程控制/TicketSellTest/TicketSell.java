package java的多线程控制.TicketSellTest;

public class TicketSell {
    int sumOfFive=3,sumOfTen=0,sumOfTwenty=0;
    public synchronized void sell(int money){
        if (money==5){
            System.out.println("li ,your money get");
            sumOfFive++;
        }
        else if (money==10){
            while(!(sumOfFive>=1)){
                try{
                    wait();
                }catch(InterruptedException e){
                    System.out.println("Wang is waiting...");
                }
            }
            sumOfTen++;
            sumOfFive--;
            System.out.println("Wang,your money get,give you 5 yuan");
        }
        else if (money==20){
            while(!((sumOfFive>=1 && sumOfTen>=1)||(sumOfFive>=3))){
                try{
                wait();
                }catch (InterruptedException e){
                    System.out.println("Zhang is waiting...");
                }
            }
            sumOfFive-=3;
            sumOfTwenty+=1;
            System.out.println("Zhang,your money get,return you 15 yuan");
        }
        notifyAll();
    }
}
