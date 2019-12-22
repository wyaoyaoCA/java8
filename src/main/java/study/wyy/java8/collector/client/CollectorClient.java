package study.wyy.java8.collector.client;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import study.wyy.java8.collector.model.Apple;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ：wyy
 * @date ：Created in 2019-12-22 18:17
 * @description：
 * @modified By：
 * @version: $
 */
public class CollectorClient {

    private final Logger logger = LoggerFactory.getLogger(CollectorClient.class);

    // 模拟一堆苹果
    private static final List<Apple> apples = Arrays.asList(
            new Apple("green", 150),
            new Apple("yellow", 120),
            new Apple("green", 170),
            new Apple("green", 150),
            new Apple("yellow", 120),
            new Apple("green", 170)
    );


    /**
     * 收集流中的数据：聚合
     */
    @Test
    public void test1(){
        List<Apple> res = apples.stream()
                .filter(apple -> apple.getWeight() > 150)
                .collect(Collectors.toList());

        Optional.ofNullable(res).ifPresent(System.out::println);

        /**
         * jdk中java.util.stream.Collector的实现都在java.util.stream.Collectors这里面
         */
    }


    /**
     * 分组
     *  相同颜色的放到一起
     *  Map<String,List<apple>> ：key为颜色，value为对应颜色苹果
     */
    @Test
    public void test2(){
        Map<String,List<Apple>> map = new HashMap<>();
        for (Apple apple:apples) {
            List<Apple> appleList = map.get(apple.getColol());
            if(appleList ==null){
                appleList = new ArrayList<>();
                map.put(apple.getColol(),appleList);
            }
            appleList.add(apple);
        }

        System.out.println(map);
    }

    /**
     * 使用java8的一些知识完成：lambda
     */
    @Test
    public void test3(){
        Map<String,List<Apple>> map = new HashMap<>();
        apples.stream()
                .forEach(apple -> {
                    List<Apple> appleList = map.get(apple.getColol());
                    if (null == appleList){
                        appleList = new ArrayList<>();
                        map.put(apple.getColol(),appleList);
                    }
                    appleList.add(apple);
                });
        System.out.println(map);
    }

    /**
     * 使用Optional
     */
    @Test
    public void test4(){
        Map<String,List<Apple>> map = new HashMap<>();
        apples.stream()
                .forEach(apple -> {
                    List<Apple> appleList = Optional
                            // 获取的list可能是null
                            .ofNullable(map.get(apple.getColol()))
                            // 如果是空，还要创建一个
                            .orElseGet(() -> {
                                List<Apple> list = new ArrayList<>();
                                map.put(apple.getColol(), list);
                                return list;
                            });
                    appleList.add(apple);
                });
        System.out.println(map);
    }

    /**
     * 使用collector
     */
    @Test
    public void test5(){
        /**
         * groupingBy: 需要一个function：根据function返回的值进行分组，
         * 其实传入的就是流中的元素（此处是apple）
         */
        Map<String,List<Apple>> map  = apples.stream().collect(Collectors.groupingBy(Apple::getColol));
        System.out.println(map);
    }


}
