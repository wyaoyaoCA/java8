package study.wyy.java8.lambda.client;

import org.junit.Test;
import study.wyy.java8.lambda.model.Apple;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author ：wyy
 * @date ：Created in 2019-12-21 11:28
 * @description：consumer演示
 * @modified By：
 * @version: $
 */

public class FunctionTest {

    // 模拟一堆苹果
    private static final List<Apple> apples = Arrays.asList(
            new Apple("green", 150L),
            new Apple("yellow", 160L),
            new Apple("red", 140L));


    @Test
    public void testString2Integer(){
        Integer result = string2Integer("2", s -> Integer.valueOf(s));
        assertThat(result,equalTo(2));

    }
    private Integer string2Integer(String s, Function<String,Integer> function){
        return function.apply(s);
    }

    @Test
    public void testBiFunction(){
        Apple result = biFunction("red", 100L, (s,w) -> new Apple(s,w) );
        assertThat(result.getColol(),equalTo("red"));
        assertThat(result.getWeight(),equalTo(100L));

    }
    private Apple biFunction(String c, Long w, BiFunction<String,Long,Apple> function){
        return function.apply(c,w);
    }

}
