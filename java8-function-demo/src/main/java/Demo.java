import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author KronChan
 * @version 1.0
 * @since <pre>2018/4/15 23:38</pre>
 */
public class Demo {
    public static void main(String[] args) {
        // Predicate 接口只有一个参数，返回boolean类型。
        // 该接口包含多种默认方法来将Predicate组合成其他复杂的逻辑（比如：与，或，非）：
        Predicate<String> predicate = (s) -> s.length() > 0;
        predicate.test("foo");              // true
        predicate.negate().test("foo");     // false
        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNotEmpty = isEmpty.negate();

        //Function 接口
        //Function 接口有一个参数并且返回一个结果，并附带了一些可以和其他函数组合的默认方法（compose, andThen）
        Function<String, Integer> toInteger = Integer::valueOf;
        Function<String, String> backToString = toInteger.andThen(String::valueOf);
        backToString.apply("123");     // "123"
    }
}
