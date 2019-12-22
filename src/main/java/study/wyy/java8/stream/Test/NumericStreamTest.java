package study.wyy.java8.stream.Test;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author ：wyy
 * @date ：Created in 2019-12-22 13:48
 * @description：Stream.reduce演示
 * @modified By：
 * @version: $
 */
public class NumericStreamTest {


    private final List<Integer> sourceInt = Arrays.asList(1, 2, 3, 4, 5, 6, 5, 7, 3);

    /**
     * NumericStream：指的不是泛型不是包装类的Stream
     *  - java.util.stream.IntStream
     *  -java.util.stream.LongStream
     *  .....
     *
     *  基本类型占用的内存空间是远远小于基本类型的
     *
     */


    /**
     * 如何将Stream<Integer> -> IntStream
     */

    public void test1(){
        Stream<Integer> stream = sourceInt.stream();

        /**
         * mapToInt:
         *      ToIntFunction：接收一个T，返回一个int
         */
        stream.mapToInt(Integer::intValue);
        stream.mapToInt(i->i.intValue());
        /* IntStream intStream = stream.mapToInt(i -> {
            return i.intValue();
        });*/
    }

    /**
     * 案例： 求和
     */

    public void test2(){
        // IntStream有一个sum求和方法，注意返回值就是一个int
        int sum = sourceInt.stream().mapToInt(Integer::intValue).sum();
        System.out.println(sum);
    }


    /**
     *
     * 案例：求出9在1到100满足勾股定理的剩下的两个数
     * 返回的结果：[a,b,c]数组
     */

    @Test
    public void test3(){
        int a = 9;
        // 生成1，100的方法,
        //IntStream intStream = IntStream.rangeClosed(1, 100);
        // 打印看一下
        //intStream.forEach(System.out::println);

        Stream<int[]> stream = IntStream.rangeClosed(1, 100)
                // 找到流中数据和a求平方和，并且开方为整数的
                /*.filter(b->{
                    return Math.sqrt(a*a + b*b) % 1 ==0;
                })*/
                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                // 进行map映射成数组，但是发现IntStream的map功能很单一，就是int类型还是映射为int，而不是数组
                // 所以进行包装
                .boxed() // 这个时候返回的就是Stream<Integer>
                .map(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)});


        stream.forEach(i->System.out.println("a=" + i[0]+",b=" + i[1] + ",c="+i[2]));

    }
}
