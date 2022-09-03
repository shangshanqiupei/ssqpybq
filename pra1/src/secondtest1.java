import  java.util.Scanner;
public class secondtest1 {


    public static void main(String[] args){
        System.out.println("请输入你的员工类型：（1代表固定月薪，2代表计时取酬，3代表销售额提成，4代表带底薪销售额提成）");
        Scanner s = new Scanner(System.in);
        int test = s.nextInt();
        if(test==1){
            SalariedEmployee people = new SalariedEmployee();
            System.out.println("今天是你的生日么？：（是：1，不是：0）");
            int judge = s.nextInt();
            if(judge==1){
                System.out.println("祝你生日快乐，恭喜你获得月薪："+(people.salary()+100));
            }
            else System.out.println("恭喜你获得月薪："+people.salary());
        }
        else if(test==2){
            HourlyEmployee people = new HourlyEmployee();
            System.out.println("请输入你的工作时长：");
            int hour = s.nextInt();
            System.out.println("今天是你的生日么？：（是：1，不是：0）");
            int judge = s.nextInt();
            if(judge==1){
                System.out.println("祝你生日快乐，恭喜你获得月薪："+(people.salary(hour)+100));
            }
            else System.out.println("恭喜你获得月薪："+people.salary(hour));


        }
        else if (test==3){
            CommissionEmployee people = new CommissionEmployee();
            System.out.println("请输入你的销售额：");
            int sale = s.nextInt();
            System.out.println("今天是你的生日么？：（是：1，不是：0）");
            int judge = s.nextInt();
            if(judge==1){
                System.out.println("祝你生日快乐，恭喜你获得月薪："+(people.salary(sale)+100));
            }
            else System.out.println("恭喜你获得月薪："+people.salary(sale));

        }
        else if (test==4) {
            BasePlusCommissionEmployee people = new BasePlusCommissionEmployee();
            System.out.println("请输入你的销售额：");
            int sale = s.nextInt();
            System.out.println("请输入你的底薪：");
            int up = s.nextInt();
            System.out.println("今天是你的生日么？：（是：1，不是：0）");
            int judge = s.nextInt();
            if(judge==1){
                System.out.println("祝你生日快乐，恭喜你获得月薪："+(people.salary(sale,up)+100));
            }
            else System.out.println("恭喜你获得月薪："+people.salary(sale,up));
        }
        else System.out.println("请按要求输入正确的数字。");


    }

}
class Employee
{
    int number;
    int birthday;
    String name;
    int salary;
}
class SalariedEmployee extends Employee{

    public  int salary(){
        return 3500;
    }
}
class HourlyEmployee extends Employee{
    public int salary(int time){
        if(time<=160){
            return time*15;
        }
        else return 160*15+(time-160)*20;
    }


}
class CommissionEmployee extends Employee{
    public int salary(int sale){
        return sale*100;
    }

}
class BasePlusCommissionEmployee extends CommissionEmployee{
    public int salary(int sale,int up){
        return  up+100*sale;
    }

}