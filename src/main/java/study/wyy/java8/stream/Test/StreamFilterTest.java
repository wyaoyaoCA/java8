package study.wyy.java8.stream.Test;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ：wyy
 * @date ：Created in 2019-12-22 09:56
 * @description：
 * @modified By：
 * @version: $
 */
public class StreamFilterTest {

    private final List<Integer> sourceInt = Arrays.asList(1, 2, 3, 4, 5, 6,5,7,3);
    /**
     * 1 filter介绍
     *      1 需要的是一个Predicate
     *      2 Predicate中test方法返回结果为True的留在流里
     */
    @Test
    public void test1(){
        List<Integer> source = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> result = null;
        // 1 使用匿名内部类
        /*result = source.stream().filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                // 留下偶数
                return integer % 2 ==0 ;
            }
        }).collect(Collectors.toList());*/

        // 2 使用lambda
        result = source.stream().filter(i -> i % 2 == 0).collect(Collectors.toList());
        System.out.println(result);
    }


    /**
     * 2 去重
     */
    @Test
    public void test2(){

        List<Integer> result = sourceInt.stream().distinct().collect(Collectors.toList());
        System.out.println(result);
    }


    /**
     * 3 截断，比如跳过前面5个元素
     */
    @Test
    public void test3(){
        List<Integer> result = sourceInt.stream().skip(5).collect(Collectors.toList());
        System.out.println(result);
    }

    /**
     * 3 截断，只需要前面5个元素
     */
    @Test
    public void test4(){
        List<Integer> result = sourceInt.stream().limit(5).collect(Collectors.toList());
        System.out.println(result);
    }

}
