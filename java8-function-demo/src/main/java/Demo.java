import java.util.Comparator;
import java.util.function.*;

/**
 * @author KronChan
 * @version 1.0
 * @since <pre>2018/4/15 23:38</pre>
 */
public class Demo {
    private String demoName;

    public Demo() {
    }

    public Demo(String demoName) {
        this.demoName = demoName;
    }

    public String getDemoName() {
        return demoName;
    }

    @Override
    public String toString() {
        return "Demo{" +
                "demoName='" + demoName + '\'' +
                '}';
    }

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
        // 类似的 有操作的函数式接口 UnaryOperator ，一元参数和返回类型规则相同
        Function<String, Integer> toInteger = Integer::valueOf;
        Function<Integer, Demo> int2Demo = integer -> new Demo(integer.toString());
        Function<String, Demo> str2Demo = toInteger.andThen(int2Demo);
        System.out.printf("Function toInteger is %d%n", toInteger.apply("123"));
        System.out.printf("Function toString is %s%n", str2Demo.apply("123"));
        // Function backToString is Demo{demoName='123'}

        //Supplier 接口
        //Supplier 接口返回一个任意范型的值，和Function接口不同的是该接口没有任何参数
        Supplier<Demo> supplier = Demo::new;
        // Get a result.
        System.out.println(supplier.get()); // Demo{demoName='null'}

        // Consumer 接口
        // Consumer 接口只是实现操作没有任何返回值
        Consumer<Integer> consumer = System.out::println;
        consumer.accept(2); // 2

        // 基础多参数的 Bi 前缀的函数接口 BiConsumer，BiFunction，BiPredicate……
        // 举个栗子 BiFunction()
        BiFunction<Integer, String, Demo> biFunction = (i, s) -> {
            String demoName = s + i;
            return new Demo(demoName);
        };
        System.out.println(biFunction.apply(1, "KronChan")); //  Demo{demoName='KronChan1'}

        // BinaryOperator 继承 BiFunction，表示二元参数 和 返回类型一样
        BinaryOperator<Integer> integerBinaryOperator = (l, r) -> l + r;
        System.out.println(integerBinaryOperator.apply(4, 5));

        //制作比较规则，输出大的 Demo
        ToIntFunction<Demo> toIntFunction = d -> {
            String value = d.getDemoName();
            return Integer.valueOf(value);
        };
        BinaryOperator<Demo> compareBinaryOperator = BinaryOperator.maxBy(Comparator.comparingInt(toIntFunction));
        System.out.println(compareBinaryOperator.apply(new Demo("1"), new Demo("2")));
        // Demo{demoName='2'}

        // LongBinaryOperator 继承自 BinaryOperator 实现 两个 long 参数方法，返回 long 类型，此外还有 double int……
        LongBinaryOperator longBinaryOperator = (l, r) -> l + r;
        System.out.println(longBinaryOperator.applyAsLong(1, 2)); // 3
    }
}
