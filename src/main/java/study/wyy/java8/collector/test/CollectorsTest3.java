package study.wyy.java8.collector.test;


/**
 * @author ：wyy
 * @date ：Created in 2019-12-22 18:59
 * @description：
 * @modified By：
 * @version: $
 */
import org.junit.Test;
import study.wyy.java8.stream.modle.Dish;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static study.wyy.java8.collector.test.CollectorsTest1.menu;
public class CollectorsTest3 {


    /**
     * partitioningBy(Predicate p)：分组
     *      返回一个map，key的类型为布尔
     */

    /**
     * 根据肉和素分类
     */
    @Test
    public void testPartitioningBy(){
        Map<Boolean, List<Dish>> collect = menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian));
        Optional.ofNullable(collect).ifPresent(System.out::println);
    }

    /**
     * partitioningBy(Predicate predicate,Collector downstream)
     * 分组完的结果交给downstream继续工作
     */
    /**
     * 分组后求平均卡路里
     */
    @Test
    public void testPartitioningBy2(){
        Map<Boolean, Double> collect = menu.stream()
                .collect(Collectors.partitioningBy(
                        Dish::isVegetarian,
                        Collectors.averagingInt(Dish::getCalories)
                ));
        Optional.ofNullable(collect).ifPresent(System.out::println);
    }

}
