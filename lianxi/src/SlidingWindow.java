import java.util.concurrent.atomic.AtomicInteger;

public class SlidingWindow {
    private final long windowStart;
    private AtomicInteger successNum;
    private AtomicInteger failNum;
    private AtomicInteger timeoutNum;

    public SlidingWindow(long windowStart) {
        this.windowStart = windowStart;
    }
}
