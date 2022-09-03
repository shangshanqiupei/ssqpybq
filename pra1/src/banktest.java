import java.util.*;
import java.lang.Thread;
import java.util.Random;
public class banktest {
    private static int sum = 0;
    public synchronized static void save(String name,int money){
        try{
            Thread.sleep((int) (Math.random() * 2000));
        }catch (InterruptedException e){

        }
        sum+=money;
        System.out.println("用户"+name+"存了"+money+"元");
        System.out.println("账户总金额为"+sum+"元");
    }
}