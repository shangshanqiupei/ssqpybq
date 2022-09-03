import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Road {
    private List<String> vechicles = new ArrayList<String>();

    private String name;

    public Road(String name) {
        this.name = name;

        // 开启一个线程： 模拟车辆不断随机上路的过程, 1-10s会产生一辆车
        ExecutorService pool = Executors.newSingleThreadExecutor();
        pool.execute(new Runnable() {
            public void run() {
                for (int i = 1; i < 1000; i++) {
                    try {
                        Thread.sleep((new Random().nextInt(10) + 1) * 1000);
                        vechicles.add(Road.this.name + "_" + i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        // 开启定时器：每隔一秒检查相应的灯是否为绿，是则放行一辆车
        ScheduledExecutorService timer = Executors.newScheduledThreadPool(1);
        timer.scheduleAtFixedRate(new Runnable() {
            public void run() {
                if (vechicles.size() > 0) {
                    boolean lighted = Lamp.valueOf(Road.this.name).isLighted();
                    if (lighted) {
                        System.out.println(vechicles.remove(0) + " is traversing !");
                    }
                }
            }
        }, 1, 1, TimeUnit.SECONDS);
    }
}
