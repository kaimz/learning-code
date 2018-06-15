import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.BasicConfigurator;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;

/**
 * @author KronChan
 * @version 1.0
 * @since <pre>2018/4/5 22:28</pre>
 */
@Slf4j
public class HelloWorldHystrixObservableCommand extends HystrixObservableCommand<String> {
    private final String commandName;

    public HelloWorldHystrixObservableCommand(String commandName) {
        super(HystrixCommandGroupKey.Factory.asKey(commandName));
        this.commandName = commandName;
    }
    @Override
    protected Observable<String> construct() {
        log.info("When begin into construct method the current thread's is: {}",
                Thread.currentThread().getName());
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                log.info("In call method the thread's name is: {}", Thread.currentThread().getName());;
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext("Hello1" + " thread:" + Thread.currentThread().getName());
                    subscriber.onNext("Hello2" + " thread:" + Thread.currentThread().getName());
                    subscriber.onNext(commandName + " thread:" + Thread.currentThread().getName());
                    log.info("complete before------" + " thread: {}.", Thread.currentThread().getName());
                    subscriber.onCompleted();	// 不会往下执行observer的任何方法
                }
            }
        });
    }

    public static void main(String[] args) {
        BasicConfigurator.configure();
        HelloWorldHystrixObservableCommand command = new HelloWorldHystrixObservableCommand("kronchan");
        Observable<String> observable = command.observe();
        observable.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                log.info("completed");
            }

            @Override
            public void onError(Throwable throwable) {
                log.error(throwable.getMessage());
            }

            @Override
            public void onNext(String o) {
                log.info("Observe onNext: {}", o);
            }
        });
    }
}
