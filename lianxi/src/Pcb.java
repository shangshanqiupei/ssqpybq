public class Pcb {
    //成员属性
    private String name;//进程名
    private String id;//进程id
    private int priorityNum;//优先数，自己指定，优先数越大，优先级越低
    private int arriveTime;//到达时间 //自己指定
    private int totalRuntime; //需要运行的时间 ,自己指定
    private int useCpuTime;//剩余运行时间=需要运行时间-固定时间片大小 自己求
    private int finishTime; //完成时间=上一进程的完成时间+当前进程的服务时间 自己求
    private int waitTime;//等待时间=上一进程的完成时间-当前进程的到达时间
    private double response;//响应比=1+等待时间/服务时间

}

