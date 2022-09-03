import java.util.Scanner;
public class operaterlab {
    public static void main(String []ags){
        int i;
        pcb []PCB = new pcb[3];
        PCB[0]=new pcb();
        PCB[0].name="aa";
        PCB[0].rtime=6;
        PCB[0].alrtime=0;
        PCB[0].state="w";
        PCB[1]=new pcb();
        PCB[1].name="bb";
        PCB[1].rtime=4;
        PCB[1].alrtime=0;
        PCB[1].state="w";
        PCB[2]=new pcb();
        PCB[2].name="cc";
        PCB[2].state="w";
        PCB[2].rtime=5;
        PCB[2].alrtime=0;
        String []line = new String[3];
        if(PCB[0].rtime>PCB[1].rtime&&PCB[0].rtime>PCB[2].rtime){
            line[2]=PCB[0].name;
        }
        if(PCB[1].rtime<PCB[2].rtime){
            line[0]=PCB[1].name;
            line[1]=PCB[2].name;
        }
        String runstate="NULL";
        while(PCB[0].state!="f"||PCB[1].state!="f"||PCB[2].state!="f"){

            if(runstate=="NULL"){
                runstate = line[0];
                System.out.println("当前正在运行的进程为"+runstate);
                for(i=0;i<=2;i++){
                    if(PCB[i].name==runstate){
                        System.out.println("该进程运行所需时间为"+PCB[i].rtime);
                        System.out.println("该进程已经运行的时间为"+PCB[i].alrtime);
                        PCB[i].state="r";
                        System.out.println("该进程的状态为"+PCB[i].state);
                        PCB[i].alrtime+=1;
                        break;
                    }
                }

                line[0]=line[1];
                line[1]=line[2];
                line[2]="NULL";
                System.out.println("就绪队列的进程为"+line[0]);
                    if(line[1]!="NULL"){
                        System.out.println("就绪队列的进程为"+line[1]);
                }


            }
            else{
                for(i=0;i<3;i++){
                    if(PCB[i].name==runstate){
                        System.out.println("当前正在运行的进程为"+runstate);
                        System.out.println("该进程运行所需时间为"+PCB[i].rtime);
                        System.out.println("该进程已经运行的时间为"+PCB[i].alrtime);
                        PCB[i].state="r";
                        System.out.println("该进程的状态为"+PCB[i].state);


                        System.out.println("就绪队列的进程为"+line[0]);

                        if(line[1]!="NULL"){
                            System.out.println("就绪队列的进程为"+line[1]);
                        }
                        if(PCB[i].alrtime==PCB[i].rtime){
                            PCB[i].state="f";
                            System.out.println("进程"+PCB[i].name+"已完成");
                            runstate="NULL";
                        }
                        PCB[i].alrtime+=1;

                    }
                }
            }


            Scanner sc = new Scanner(System.in);
            int x=sc.nextInt();
            if(x!=0){
                break;
            }

        }
    }

}
class pcb{
    String name;
    String state;
    int rtime;     //运行时间
    int alrtime;   //已经运行的时间

}