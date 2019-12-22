package study.wyy.java8.stream.Test;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;

/**
 * @author ：wyy
 * @date ：Created in 2019-12-22 13:48
 * @description：Stream.reduce演示
 * @modified By：
 * @version: $
 */
public class StreamReduceTest {


    private final List<Integer> sourceInt = Arrays.asList(1, 2, 3, 4, 5, 6, 5, 7, 3);


    /**
     * reduce
     *      将流中的数据一个接一个的进行归约
     */

    /**
     * 案例：
     * 求sourceInt的和
     * <p>
     * T reduce(T identity, BinaryOperator<T> accumulator);
     * BinaryOperator<T> extends BiFunction<T,T,T>
     * identity：默认值，可以为添加一个初始值放在流中数据的的最前面
     */
    @Test
    public void test1() {
        Integer sum = sourceInt.stream().reduce(0, new BinaryOperator<Integer>() {
            @Override
            public Integer apply(Integer i1, Integer i2) {
                return Integer.sum(i1, i2);
            }
        });
        System.out.println(sum);

        // lambda
        Integer sum1 = sourceInt.stream().reduce(0, Integer::sum);
        System.out.println(sum1);

        // 不为添加一个初始值放在流中数据的的最前面，此时返回的就是一个Optional

        Optional<Integer> optional = sourceInt.stream().reduce(Integer::sum);

        /**
         * ifPresent：如果存在则进行一定的操作，接收的就是一个Consumer函数式接口，
         * `            具体的操作就是Consumer接口中accpet方法中实现的
         */
        // 这里就是进行输出
        // optional.ifPresent(integer -> System.out.println(integer));
        optional.ifPresent(System.out::println);
    }

    /**
     * 寻找最大值
     */
    @Test
    public void test2() {
        sourceInt.stream().reduce((i, j) -> {
            return i > j ? i : j;
        }).ifPresent(System.out::println);

    }


    /**
     * 计算偶数的乘积
     */

    @Test
    public void test3() {
        sourceInt.stream()
                // 过滤出偶数
                .filter(i -> i % 2 == 0)
                .reduce((i, j) -> i * j)
                .ifPresent(System.out::println);
    }
}
