package study.wyy.java8.collector.test;


import org.junit.Test;
import study.wyy.java8.stream.modle.Dish;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static study.wyy.java8.collector.test.CollectorsTest1.menu;
/**
 * @author ：wyy
 * @date ：Created in 2019-12-22 18:59
 * @description：
 * @modified By：
 * @version: $
 */
public class CollectorsTest2 {


    /**
     * 题外话：
     * 静态导入
     * import static study.wyy.java8.collector.test.CollectorsTest1.menu;
     *  不能是private修饰
     *  如果有final修饰则是被动加载
     *  没有final就是主动加载
     */


    /***
     * join
     *    字符串拼接
     */

    @Test
    public  void testJoin(){
        /**
         * 调用join方法，流里面数据比如是CharSequence这个类型
         * 而现在是Dish
         */
        String collect = menu.stream()
                // 将流里面的元素类型映射为CharSequence类型
                .map(Dish::getName)
                .collect(Collectors.joining());
        Optional.ofNullable(collect).ifPresent(System.out::println);

    }

    /**
     * 指定连字符
     */
    @Test
    public  void testJoinWith_(){
        String collect = menu.stream()
                .map(Dish::getName)
                .collect(Collectors.joining("_"));
        Optional.ofNullable(collect).ifPresent(System.out::println);

    }

    /**
     * 指定前缀和后缀
     */
    @Test
    public  void testJoinprefixAndSuffix(){
        String collect = menu.stream()
                .map(Dish::getName)
                .collect(Collectors.joining("_","Name[","]"));
        Optional.ofNullable(collect).ifPresent(System.out::println);

        // Name[pork_beef_chicken_french fries_rice_season fruit_pizza_prawns_salmon]
        // 断言
        Optional.ofNullable(collect).ifPresent(s -> assertThat(s,equalTo("Name[pork_beef_chicken_french fries_rice_season fruit_pizza_prawns_salmon]")));
    }

    /**
     *
     *   mapping
     * join
     * 之前进行了map操作
     * 如何直接join呢，使用mapping
     *
     * mapping(Function mapper, Collector downstream)
     *     Function ：接收一个T，返回一个R
     *     Collector：Function返回的R值，交给downstream进行处理
     */
    @Test
    public void testMapping(){
        String res = menu.stream()
                .collect(Collectors.mapping(Dish::getName, Collectors.joining("_", "Name[", "]")));

        Optional.ofNullable(res).ifPresent(s -> assertThat(s,equalTo("Name[pork_beef_chicken_french fries_rice_season fruit_pizza_prawns_salmon]")));
    }


    /**
     * maxBy
     */


    @Test
    public void testMaxBy(){
        Optional<Dish> res = menu.stream()
                .collect(Collectors.maxBy(Comparator.comparing(Dish::getCalories)));

        res.ifPresent(System.out::println);
    }

    /**
     * 复习
     * 通过reduce实现
     */
    @Test
    public void testReduce(){
        Optional<Dish> reduce = menu.stream()
                .reduce((dish1, dish2) -> {
                    return (dish1.getCalories() > dish2.getCalories()) ? dish1 : dish2;
                });
        reduce.ifPresent(System.out::println);

    }



}
