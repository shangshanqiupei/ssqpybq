public class BucketArray {
    private volatile int size = 10;//滑动窗口(偏移量)
    private static int maxSize = 60;//数据最大值
    private volatile int dataLength = 0;//当前数据长度
    private volatile Bucket[] data = new Bucket[60];//数组
    private int head;//数组头部
    private int tail;//数组尾部


    public void addBucket(Bucket bucket) {
        if (++dataLength > maxSize) {
            resetTailHead();
        } else {
            incrementTail();
        }
        data[tail] = bucket;
    }
    //当数据大小不超过最大数据时
    public void incrementTail() {
        if (dataLength >= size) {
            head = dataLength - size;
        }
        tail = dataLength - 1;
    }
    //当数据大小超过最大数据时
    public void resetTailHead() {
        if (head == maxSize - 1) {
            head = 0;
            dataLength = 10;
            tail++;
        } else {
            head++;
            tail = head + size - 1 - maxSize;
        }
    }
    public Bucket[] getResultData() {
        Bucket[] result = new Bucket[10];
        if (tail > head) {
            for (int i = head, j = 0; i <= tail; i++, j++) {
                result[j] = data[i];
            }
        } else {
            for (int i = head, j = 0; i < maxSize; i++, j++) {
                result[j] = data[i];
            }
            for (int i = 0, j = result.length - 1; i <= tail; i++, j++) {
                result[j] = data[i];
            }
        }
        return result;
    }
}