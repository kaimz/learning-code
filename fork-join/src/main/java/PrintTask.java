import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * @author Zhang Kai
 * @version 1.0
 * @since <pre>2018/3/20 18:38</pre>
 */
// 没有返回值的 fork / join 任务框架
public class PrintTask extends RecursiveAction {
    private static final int THRESHOLD = 5;
    private int start;
    private int end;

    PrintTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public static void main(String[] args) {
        PrintTask task = new PrintTask(0, 25);
        // 分配四个线程给它
        ForkJoinPool pool = new ForkJoinPool(4);
        pool.execute(task);
        pool.shutdown();
    }

    @Override
    protected void compute() {
        if (THRESHOLD >= (end - start)) {
            // 满足小任务条件，分配打印任务
            for (int i = start; i < end; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        } else {
            // 任务还能继续拆分
            int division = (start + end) >> 1;
            PrintTask task1 = new PrintTask(start, division);
            PrintTask task2 = new PrintTask(division, end);
            invokeAll(task1, task2);
        }
    }
}
