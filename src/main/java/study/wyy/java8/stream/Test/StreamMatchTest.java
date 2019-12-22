package study.wyy.java8.stream.Test;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author ：wyy
 * @date ：Created in 2019-12-22 13:35
 * @description：Stream.match演示
 * @modified By：
 * @version: $
 */
public class StreamMatchTest {

    private final List<Integer> sourceInt = Arrays.asList(1, 2, 3, 4, 5, 6,5,7,3);


    /**
     * match
     */

    /**
     * allMatch: 流中的数据是不是都匹配
     *      1. 需要的是一个predicate断言，
     *      2. 会判断流里的数据是不是都满足predicate.test(T t)方法中定义的规则
     *
     *   【案例】判断sourceInt中的数据是不是都大于1
     *
     */
    @Test
    public void test1(){
        boolean result = sourceInt.stream().allMatch((Integer i) -> i > 1);
        assertThat(result,equalTo(false));
    }


    /**
     * anyMatch: 流里面的数据只要一个满足规则就可以
     *
     * 【案例】判断sourceInt中的数据存在大于1的？
     */
    @Test
    public void test2(){
       // boolean result = sourceInt.stream().anyMatch((Integer i) -> i > 1);
        boolean result = sourceInt.stream().anyMatch(i -> i > 1);
        assertThat(result,equalTo(true));

    }

    /**
     * noneMatch
     *  没有一个满足
     */
    @Test
    public void test3(){
        // boolean result = sourceInt.stream().anyMatch((Integer i) -> i > 1);
        boolean result = sourceInt.stream().noneMatch(i -> i > 1);
        assertThat(result,equalTo(false));

    }

}
