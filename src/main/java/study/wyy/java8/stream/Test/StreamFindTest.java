package study.wyy.java8.stream.Test;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Predicate;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author ：wyy
 * @date ：Created in 2019-12-22 13:48
 * @description：Stream.find演示
 * @modified By：
 * @version: $
 */
public class StreamFindTest {

    private final List<Integer> sourceInt = Arrays.asList(1, 2, 3, 4, 5, 6, 5, 7, 3);


    /**
     * find:
     *  findAny  返回流中任意一个
     *  findFirst 返回流中第一个
     */
    /**
     * findAny
     * 返回流中任意一个
     */
    @Test
    public void test1() {
        Optional<Integer> result = sourceInt.stream().filter((Integer i) -> i % 2 == 0).findAny();
        System.out.println(result.get());

    }

    /**
     * 为什么通过一个Optional来获取我们想要的值
     */
    @Test(expected = NoSuchElementException.class)
    public void test2() {
        // 没有满足条件的，会抛出NoSuchElementException这异常
        Optional<Integer> result = sourceInt.stream().filter((Integer i) -> i > 10).findAny();
        System.out.println(result.get());
    }

    /**
     * 定义一个方法，返回满足满足要求的数据
     * 如果没有满足的数据，返回指定的默认值
     */
    private Integer find(List<Integer> list, Integer defaultValue, Predicate<Integer> predicate) {
        try {
            Optional<Integer> any = list.stream().filter(predicate).findAny();
            return any.get();
        } catch (NoSuchElementException e) {
            System.out.println("没有满足要求的数据，返回指定的默认值");
            return defaultValue;
        } catch (Exception e) {

            throw new RuntimeException(e.getMessage());
        }
    }

    @Test
    public void testFind(){
        Integer res = find(sourceInt, -1, integer -> integer > 100);
        assertThat(res,equalTo(-1));



    }
    /**
     *   其实find方法的逻辑Optional已经给实现了
     */
    @Test
    public void testOrElse(){
        // 没有满足的返回-1
        Optional<Integer> optional = sourceInt.stream().filter(integer -> integer > 100).findAny();
        Integer res = optional.orElse(-1);
        assertThat(res,equalTo(-1));

        // 有满足的就返回满足的值
        Optional<Integer> any = sourceInt.stream().filter(integer -> integer < 10).findAny();
        Integer integer = any.orElse(-1);
        System.out.println(integer);

    }
}
