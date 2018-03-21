import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/**
 * @author Zhang Kai
 * @version 1.0
 * @since <pre>2018/3/21 8:49</pre>
 */
// 有返回值的 fork / join 任务框架 RecursiveTask<T>
public class SumTask extends RecursiveTask<Integer> {
    private static final int THRESHOLD = 50;
    private int start;
    private int end;

    public SumTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public static void main(String[] args) throws InterruptedException {
        final int works = 200;
        SumTask task = new SumTask(0, works);
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);
        long beginTime = System.currentTimeMillis();
        int result = forkJoinPool.invoke(task);
        long consumeTime = System.currentTimeMillis() - beginTime;
        System.out.println("Fork Join calculated the result is " + result + ",consume " + consumeTime + "ms");
        forkJoinPool.shutdown();

        result = 0;
        beginTime = System.currentTimeMillis();
        for (int i = 0; i < works; i++) {
            TimeUnit.SECONDS.sleep(1);
            result += i;
        }
        consumeTime = System.currentTimeMillis() - beginTime;
        System.out.println("The correct result is " + result + ",consume " + consumeTime + "ms");
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        if (THRESHOLD >= (end - start)) {
            for (int i = start; i < end; i++) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sum += i;
            }
            System.out.println(Thread.currentThread().getName() + "结果是：" + sum);
            return sum;
        } else {
            int division = (start + end) >> 1;
            SumTask task1 = new SumTask(start, division);
            SumTask task2 = new SumTask(division, end);
            invokeAll(task1, task2);
            int result1 = task1.join();
            int result2 = task2.join();
            return result1 + result2;
        }
    }
}
