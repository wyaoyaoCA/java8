package study.wyy.java8.lambda.client;

import com.google.common.base.Strings;
import org.junit.Test;
import study.wyy.java8.lambda.model.Apple;

import java.util.function.BiPredicate;
import java.util.function.DoublePredicate;
import java.util.function.Predicate;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author ：wyy
 * @date ：Created in 2019-12-21 11:28
 * @description：predicate演示
 * @modified By：
 * @version: $
 */

public class PredicateTest {


    @Test
    public void testPredicate() {
        // 这里就可以根据自己的需要进行字符串的判断
        // 这里简单的判断是否为空串或者是null，使用的guava提供的方法
        boolean result = predicateString("hello", s -> Strings.isNullOrEmpty(s));
        assertThat(result, equalTo(false));

    }

    /**
     * 断言一个字符串
     *
     * @param string
     * @param predicate
     */
    private boolean predicateString(String string, Predicate<String> predicate) {
        return predicate.test(string);
    }


    @Test
    public void testPredicateDouble() {
        // 大于0？
        boolean result = predicateDouble(1d, num -> num > 0);
        assertThat(result, equalTo(true));
    }

    /**
     * java.util.function.DoublePredicate 接受的就是一个Double
     * 还有其他的一些Predicate，比如
     *  java.util.function.IntPredicate 接受的是int
     *  java.util.function.LongPredicate 接收的是Long
     */
    private boolean predicateDouble(Double num, DoublePredicate doublePredicate) {
        return doublePredicate.test(num);
    }

    @Test
    public void testBiPredicate() {
        // 苹果是黄色吗，并且重量大于100？
        Apple apple = new Apple("yellow", 90L);
        boolean result = biPredicate(apple,
                (colol,weight) ->
                 "yellow".equals(colol) && weight>100
        );
        assertThat(result, equalTo(false));
    }

    /**
     * java.util.function.BiPredicate 接收两个参数
     */
    private boolean biPredicate(Apple apple, BiPredicate<String,Long> biPredicate) {
        return biPredicate.test(apple.getColol(), apple.getWeight());
    }


}
