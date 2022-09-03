import java.util.Scanner;
public class marrysystem {
    public static void main(String []args){
        Scanner s = new Scanner(System.in);
        person p1 = new person();
        p1.name="xx1";
        p1.age=20;
        p1.gender=1;
        p1.partner="xxx";
        person p2 = new person();
        p2.gender=0;
        p2.age=19;
        p2.name="xx2";
        p2.partner="xxx";
        person p3 = new person();
        p3.name="xx3";
        p3.gender=1;
        p3.partner="xx4";
        p3.age=24;
        person p4 = new person();
        p4.age=24;
        p4.partner="xx3";
        p4.name="xx4";
        p4.gender=0;
        person p5 = new person();
        p5.gender=0;
        p5.age=21;
        p5.name="xx5";
        p5.partner="xxx";
        person p6 = new person();
        p6.gender=1;
        p6.age=21;
        p6.name="xx6";
        p6.partner="xxx";
        person p = new person();
        p.partner="xxx";
        while(true){
            System.out.println("------------------婚恋系统------------------------");
            System.out.println("输入0：登记信息");
            System.out.println("输入1：进行婚配");
            System.out.println("输入2，查看自身信息");
            System.out.println("输入3：退出系统");
            int x= s.nextInt();
            switch (x) {
                case 0:

                    System.out.println("请输入你的名字：");
                    p.name = s.next();
                    System.out.println("请输入你的年龄：");
                    p.age = s.nextInt();
                    System.out.println("请输入你的性别（1表示男，0表示女）：");
                    p.gender = s.nextInt();
                    System.out.println("登记成功");

                    break;
                case 1:
                    System.out.println("选择是否查看对象信息（是：1，否：0）");
                    int check = s.nextInt();
                    while(check==1)
                    {System.out.println("请选择一个对象，进行信息查看（输入姓名）");
                    System.out.println(p1.name);
                    System.out.println(p2.name);
                    System.out.println(p3.name);
                    System.out.println(p4.name);
                    System.out.println(p5.name);
                    System.out.println(p6.name);
                    String input= s.next();
                    if(input.equals(p1.name)){
                        System.out.println("姓名："+p1.name);
                        System.out.println("年龄："+p1.age);
                        System.out.println("性别："+p1.gender);
                        System.out.println("伴侣："+p1.partner);
                    }
                    else if(input.equals(p1.name)){
                        System.out.println("姓名："+p1.name);
                        System.out.println("年龄："+p1.age);
                        System.out.println("性别："+p1.gender);
                        System.out.println("伴侣："+p1.partner);
                    }
                    else if(input.equals(p2.name)){
                        System.out.println("姓名："+p2.name);
                        System.out.println("年龄："+p2.age);
                        System.out.println("性别："+p2.gender);
                        System.out.println("伴侣："+p2.partner);
                    }
                    else if(input.equals(p3.name)){
                        System.out.println("姓名："+p3.name);
                        System.out.println("年龄："+p3.age);
                        System.out.println("性别："+p3.gender);
                        System.out.println("伴侣："+p3.partner);
                    }
                    else if(input.equals(p4.name)){
                        System.out.println("姓名："+p4.name);
                        System.out.println("年龄："+p4.age);
                        System.out.println("性别："+p4.gender);
                        System.out.println("伴侣："+p4.partner);
                    }
                    else if(input.equals(p5.name)){
                        System.out.println("姓名："+p5.name);
                        System.out.println("年龄："+p5.age);
                        System.out.println("性别："+p5.gender);
                        System.out.println("伴侣："+p5.partner);
                    }
                    else if(input.equals(p6.name)){
                        System.out.println("姓名："+p6.name);
                        System.out.println("年龄："+p6.age);
                        System.out.println("性别："+p6.gender);
                        System.out.println("伴侣："+p6.partner);
                    }
                    else {
                        System.out.println("请输入正确的姓名");
                    }
                    System.out.println("选择是否查看对象信息（是：1，否：0）");
                    check = s.nextInt();}
                    System.out.println("请输入想结婚的对象：");
                    String input1=s.next();
                    if(input1.equals(p1.name)){
                        p.marry(p,p1);
                    }
                    else if(input1.equals(p2.name)){
                        p.marry(p,p2);
                    }
                    else if(input1.equals(p3.name)){
                        p.marry(p,p3);
                    }
                    else if(input1.equals(p4.name)){
                        p.marry(p,p4);
                    }
                    else if(input1.equals(p5.name)){
                        p.marry(p,p5);
                    }
                    else if(input1.equals(p6.name)){
                        p.marry(p,p6);
                    }
                    else {
                        System.out.println("请输入正确的姓名");
                    }
                    break;
                case 2:System.out.println("姓名："+p.name);
                    System.out.println("性别："+p.gender);
                    System.out.println("年龄："+p.age);
                    System.out.println("伴侣："+p.partner);
                    break;
                case 3:
                    System.out.println("退出成功");
                    System.exit(0);
                    break;
                default:
                    System.out.println("请输入正确的信息");
                    break;

            }
        }
    }

}
class person{
    String name ;
    int age ;
    int gender ;
    String partner;
    int marry(person p,person q){
        if(p.gender==q.gender){
            System.out.println("同性不允许结婚");
            return  0;
        }
        if(!(p.partner).equals("xxx")||!(q.partner).equals("xxx")){
            System.out.println("对方已有配偶");
            return  0;
        }
        if(p.gender==1){
            if(p.age<23||q.age<21){
                System.out.println("年龄不足无法结婚");
                return 0;
            }
        }
        else  {
            if(p.age<21||q.age<23){
                System.out.println("年龄不足无法结婚");
                return 0;
            }

        }
        p.partner=q.name;
        q.partner=p.name;
        System.out.println("恭喜结婚成功");
        return 0;

    }
}
