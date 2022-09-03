import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
public class Guessnumber {
    public static int choice;
    public static int point=0;
    public void judge01()
    {
        try {
            Scanner sc = new Scanner(System.in);
            choice = sc.nextInt();
            switch (choice)
            {
                case 1: break;
                case 2: System.exit(0);
                default:
                {
                    System.out.println("请输⼊1-2：");
                    judge01();
                }
            }
        }
        catch (InputMismatchException e)
        {
            System.out.println("请输⼊整数(0-1)!");
            judge01();
        }
    }
    public int calcuont(int count){
        if(count==1){
            point+=3;
        }
        else if(count==2){
            point+=2;
        }
        else if (count==3){
            point+=1;
        }
        else {
            point-=2;
        }
        return  point;
    }
    public int guesspat(int count,int num){
        try{
            System.out.println("请输入数字(0~99)：");
            Scanner s = new Scanner(System.in);
            int x=s.nextInt();
            if(x<0||x>99){
                System.out.println("请按要求输入数字");
                guesspat(count,num);
            }
            else {
                if(x<num){
                    count++;
                    if(count==3){
                        System.out.println("游戏结束");
                        System.out.println("正确数字为"+num);
                        return ++count;
                    }
                    System.out.println("输入的数字太小");
                    return guesspat(count,num);
                }
                else if(x>num){
                    count++;
                    if(count==3){
                        System.out.println("游戏结束");
                        System.out.println("正确数字为"+num);
                        return ++count;
                    }
                    System.out.println("输入的数字太大");
                    return guesspat(count,num);
                }
                else {
                    count++;
                    System.out.println("恭喜猜对！");
                    return count;
                }
            }
        }
        catch (InputMismatchException e)
        {
            System.out.println("请输⼊整数!");
            guesspat(num, count);
        }
        return -1;
    }
    public void steep(int num){
        int count = 0;
        count=guesspat(count,num);
        point = calcuont(count);
        System.out.println("得分为："+point);
    }
    public static void main(String[] args){
        System.out.println("输入1开始，输入2结束");
        System.out.println("初始分数="+point);
        Guessnumber guess = new Guessnumber();
        guess.judge01();
        int num=new Random().nextInt(99);
        guess.steep(num);
        do{
            System.out.println("输入1再来一次，输入2结束");
            guess.judge01();
            num=new Random().nextInt(99);
            guess.steep(num);
        }while (choice!=2);


    }

}
