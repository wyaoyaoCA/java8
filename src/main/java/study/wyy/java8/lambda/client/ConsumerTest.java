package study.wyy.java8.lambda.client;

import org.junit.Test;
import study.wyy.java8.lambda.model.Apple;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * @author ：wyy
 * @date ：Created in 2019-12-21 11:28
 * @description：consumer演示
 * @modified By：
 * @version: $
 */

public class ConsumerTest {

    // 模拟一堆苹果
    private static final List<Apple> apples = Arrays.asList(
            new Apple("green", 150L),
            new Apple("yellow", 160L),
            new Apple("red", 140L));


    @Test
    public void testConsumerSimple(){
        consumerSimple(apples,apple -> System.out.println(apple));
    }
    private void consumerSimple(List<Apple> apples, Consumer<Apple> appleConsumer){
        for (Apple apple : apples) {
            appleConsumer.accept(apple);
        }
    }



    @Test
    public void testBiConsumerSimple(){
       biConsumerSimple("wyy",apples,
               (s,apple) -> System.out.println(s + "的" +apple));
    }
    private void biConsumerSimple(String s,List<Apple> apples, BiConsumer<String,Apple> appleConsumer){
        for (Apple apple : apples) {
            appleConsumer.accept(s,apple);
        }
    }


}
