import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SJF {
    private static List<Process> processList = new ArrayList<>();   //创建进程队列
    private static Float time; //系统处理线程的总耗时
    private static float averageTurnaroundTime; //平均周转时间
    private static float averageWaitingTime;//平均等待时间
    private static float averageWeightedTurnaroundTime;//平均带权周转时间


    public static void main(String[] args) {
        init();     //初始化进程队列
        input();    //对进程列表的录入
        List<Process> list = SJF();
        for (Process process: list) {
            System.out.println("进程名："+process.getJobName()+
                    "，到达时间："+process.getTimeOfArrival()+
                    "，运行时间："+process.getServiceHours()+
                    "，开始执行时间：" + process.getStartExecutionTime()+
                    "，完成时间："+process.getCompleteTime()+
                    "，周转时间："+process.getTurnaroundTime()+
                    "，带权周转时时间："+process.getWeightedTurnaroundTime()+
                    "，等待时间："+process.getWritTime());
        }
        calculateTheAverage(list);
        System.out.println("处理总时间为："+time+
                "，平均周转时间："+averageTurnaroundTime+
                "，平均带权周转时间："+averageWeightedTurnaroundTime+
                "，平均等待时间："+averageWaitingTime);
    }

    /**
     * 初始化进程队列
     */
    private static void init(){
        System.out.println("请输入要处理的线程数：");
        Scanner scanner = new Scanner(System.in);
        int length = scanner.nextInt();
        for (int i = 0;i<length;i++){
            processList.add(new Process());
        }
        System.out.println("初始化完成");
    }

    /**
     * 向队列中输入要处理的数据
     *  进程的一些信息
     */
    private static void input(){
        Scanner scanner = new Scanner(System.in);   //创建输入类
        Process process = null;
        for (int i = 0;i<processList.size();i++){
            process = processList.get(i);
            System.out.println("请输入第"+(i+1)+"个进程名：");
            process.setJobName(scanner.next());
            System.out.println("请输入第"+(i+1)+"个进程的到达时间：");
            process.setTimeOfArrival(scanner.nextFloat());
            System.out.println("请输入第"+(i+1)+"个进程的服务(运行)时间：");
            process.setServiceHours(scanner.nextFloat());
            process.setStatus("创建态");
        }
        System.out.println("信息录入完成");
    }


    private static int findMinSubscript(){
        float min = processList.get(0).getTimeOfArrival();
        for (Process process: processList) {
            if(min > process.getTimeOfArrival()){
                min = process.getTimeOfArrival();
            }
        }
        //循环过后，min就是队列中达到时间最短的线程的值
        for (int i = 0 ;i<processList.size();i++){
            if(min == processList.get(i).getTimeOfArrival()){
                return i;
            }
        }
        return 0;
    }//下标

    private static void whetherToBlock(){
        if (time == null){//到系统时间为空时
            return;
        }
        for (Process process : processList){
            if(time >= process.getTimeOfArrival()){
                process.setStatus("阻塞态");
            }
        }
    }//是否阻塞

    private static int ShortServiceTime(){
        int index = findMinSubscript(); //因为运行此函数时，系统已经处理过至少一个进程块了
        float min = processList.get(index).getServiceHours();   //返回出去第一个进程过后的最小服务时间
        //找到最小服务时间
        for (int i =0;i<processList.size();i++){
            if(processList.get(i).getStatus().equals("阻塞态")){
                if(min > processList.get(i).getServiceHours()){
                    min = processList.get(i).getServiceHours();
                }
            }
        }
        //根据最小服务时间返回对应的下标
        for (int i = 0;i<processList.size();i++){
            if (processList.get(i).getServiceHours() == min){
                return i;
            }
        }
        return -1;
    }//最短服务时间


    private static List<Process> SJF(){
        List<Process> completionQueue = new ArrayList<>();//完成队列
        while (processList.size() != 0){
            int index = findMinSubscript(); //先找到进程队列中达到时间最小的时间
            Process process = processList.get(index);   //得到对应
            //系统刚开始处理进程队列
            if(time == null){
                //第一个被处理的进程块的 开始时间 = 到达时间
                process.setStartExecutionTime(process.getTimeOfArrival());
                //完成时间 = 开始运行时间 + 运行(服务)时间
                process.setCompleteTime(process.getStartExecutionTime() + process.getServiceHours());
                //周转时间 = 完成时间 - 到达时间
                process.setTurnaroundTime(process.getCompleteTime() - process.getTimeOfArrival());
                //带权周转时间 = 周转时间 / 运行(服务)时间
                process.setWeightedTurnaroundTime(process.getTurnaroundTime() / process.getServiceHours());
                //等待时间 = 周转时间 - 运行时间
                process.setWritTime(process.getTurnaroundTime() - process.getServiceHours());
                //此时系统时间 = 给进程的完成时间
                time = process.getCompleteTime();
            }else {
                //如果前一个完成时间 ，小于到达时间，系统有空闲
                if(time < process.getTimeOfArrival()){
                    float idle = process.getTimeOfArrival() - time;//空闲出来的时间
                    time += idle;
                }
                //判断其他进程的状态
                whetherToBlock();
                //找到最短服务时间的进程下标
                index = ShortServiceTime();
                process = processList.get(index);
                //排除第一个进程 开始执行时间 = 系统时间
                process.setStartExecutionTime(time);
                //完成时间 = 开始执行时间 + 服务时间（运行时间）
                process.setCompleteTime(process.getStartExecutionTime() + process.getServiceHours());
                //周转时间 = 完成时间 - 到达时间
                process.setTurnaroundTime(process.getCompleteTime() - process.getTimeOfArrival());
                //带权周转时间 = 周转时间 / 运行时间（服务时间）
                process.setWeightedTurnaroundTime(process.getTurnaroundTime() / process.getServiceHours());
                //等待时间 = 周转时间 - 运行时间
                process.setWritTime(process.getTurnaroundTime() - process.getServiceHours());
                //修正系统时间  此时系统时间 = 原来系统时间 + 此进程的完成时间(服务时间)
                time += process.getServiceHours();
            }
            //进程的状态变为终止态
            process.setStatus("终止态");
            //将该线程添加进完成队列之中
            completionQueue.add(process);
            //将该线程从原先对列移出
            processList.remove(index);
        }
        return completionQueue;
    }

    public static void calculateTheAverage(List<Process> processList){
        float turnaroundTime = 0;//总周转时间
        float weightedTurnaroundTime = 0;//总带权周转时间
        float waitingTime = 0;//总等待时间
        for (Process process: processList) {
            turnaroundTime += process.getTurnaroundTime();
            weightedTurnaroundTime += process.getWeightedTurnaroundTime();
            waitingTime += process.getWritTime();
        }
        averageTurnaroundTime = turnaroundTime / processList.size();
        averageWaitingTime = waitingTime / processList.size();
        averageWeightedTurnaroundTime = weightedTurnaroundTime / processList.size();
    }//计算平均
}
