import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.apache.log4j.BasicConfigurator;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 *
 */
public class HelloWorldHystrixCommand extends HystrixCommand<String> {

    private final String name;

    public HelloWorldHystrixCommand(String name) {
        super(HystrixCommandGroupKey.Factory.asKey(name));
        this.name = name;
    }

    /**
     * 命令逻辑
     */
    @Override
    protected String run() throws Exception {
        //testTimeOut();
        TimeUnit.SECONDS.sleep(1);
        return "Hello " + name;
    }

    /**
     * 服务降级，
     * 在 run 方法的命令逻辑中出现异常或者超时的情况下会调用服务降级处理。
     */
    @Override
    protected String getFallback() {
        return "fallback: " + name;
    }

    public static void main(String[] args) {
        BasicConfigurator.configure();
        // 每个 command 实例只能使用一次

        // 以同步堵塞方式执行run()
        HelloWorldHystrixCommand excuteCommand = new HelloWorldHystrixCommand("Excute");
        String excuteResult = excuteCommand.execute();
        System.out.println("使用 excute 执行的结果 " + excuteResult);

        // 以异步非堵塞方式执行run()
        HelloWorldHystrixCommand queueCommand = new HelloWorldHystrixCommand("Queue");
        Future<String> queueFuture = queueCommand.queue();
        // 不会阻塞直接返回一个 Future
        String queueResult = null;
        for (;;) {
            if (queueFuture.isDone()) {
                try {
                    queueResult = queueFuture.get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                } finally {
                    break;
                }
            }
        }
        System.out.println("使用 queue 的异步方式获取的结果：" + queueResult);
    }

    /**
     * 模拟超时
     */
    private void testTimeOut() {
        try {
            TimeUnit.SECONDS.sleep(10L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 模拟程序异常
     */
    private void testThrowException() {
        throw new RuntimeException();
    }
}
