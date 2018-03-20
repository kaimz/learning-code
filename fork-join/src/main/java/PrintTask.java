import java.util.concurrent.RecursiveAction;

/**
 * https://www.liaoxuefeng.com/article/001493522711597674607c7f4f346628a76145477e2ff82000
 * http://blog.csdn.net/ouyang_peng/article/details/46491217
 *
 * @author Zhang Kai
 * @version 1.0
 * @since <pre>2018/3/20 18:38</pre>
 */
// 没有返回值的 fork / join 任务框架
public class PrintTask extends RecursiveAction {
    private static final int THRESHOLD = 100;
    private int start;
    private int end;

    PrintTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        if (THRESHOLD > (end - start)) {
            // 满足小任务条件，只分配 100 个打印任务
            for (int i = start; i < end; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        } else {
            int division = (start + end) >> 1;
            PrintTask task1 = new PrintTask(start, division);
            PrintTask task2 = new PrintTask(division, end);
            invokeAll(task1, task2);
        }
    }
}
