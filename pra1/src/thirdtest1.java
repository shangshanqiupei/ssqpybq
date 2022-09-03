import java.util.*;
import java.lang.Thread;
import java.util.Random;
public class thirdtest1 {
    public static void main(String[] args){
        bankaccount people1= new bankaccount("张三");
        bankaccount people2= new bankaccount("李四");
        bankaccount people3= new bankaccount("王五");
        people1.start();
        people2.start();
        people3.start();



    }



}
