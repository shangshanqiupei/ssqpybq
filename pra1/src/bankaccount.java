import java.util.*;
import java.lang.Thread;
import java.util.Random;
public class bankaccount extends Thread{

    private String name;
    public bankaccount(String name) {
        this.name = name;
    }
    public  void run(){
        int i;
        int time =new Random().nextInt(10);
        int add = new Random().nextInt(1000);
        //(int)(Math.random()*14+1)
        for(i=1;i<=time;i++){

            banktest.save(name,add);
        }

    }
}