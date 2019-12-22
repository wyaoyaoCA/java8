package study.wyy.java8.collector.test;

import org.junit.Test;
import study.wyy.java8.stream.modle.Dish;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author ：wyy
 * @date ：Created in 2019-12-22 18:59
 * @description：
 * @modified By：
 * @version: $
 */
public class CollectorsTest1 {

    /**
     * 模拟一份菜单
     */
    public static final List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT), // 猪肉
            new Dish("beef", false, 700, Dish.Type.MEAT), // 牛肉
            new Dish("chicken", false, 400, Dish.Type.MEAT), //  鸡肉
            new Dish("french fries", true, 530, Dish.Type.OTHER), //薯条
            new Dish("rice", true, 350, Dish.Type.OTHER), // 米饭
            new Dish("season fruit", true, 120, Dish.Type.OTHER), // 时令水果
            new Dish("pizza", true, 550, Dish.Type.OTHER), // tes披萨
            new Dish("prawns", false, 300, Dish.Type.FISH), // 对虾
            new Dish("salmon", false, 450, Dish.Type.FISH) // 鲑鱼肉
    );


    @Test
    public void testAveragingDouble(){
        System.out.println("averagingDouble............");
        // 需要的是ToDoubleFunction，进去一个T（此处就是Dish），返回一个Double
        /**
         * 返回的就是卡路里的平均数
         */
        Double res = menu.stream().collect(Collectors.averagingDouble(Dish::getCalories));
        Optional.ofNullable(res).ifPresent(System.out::println);

    }

    @Test
    public void testAveragingInt(){
        System.out.println("averagingInt............");
        // 需要的是ToDoubleFunction，进去一个T（此处就是Dish），返回一个Double
        /**
         * 返回的就是卡路里的平均数
         */
        Double res = menu.stream().collect(Collectors.averagingInt(Dish::getCalories));
        Optional.ofNullable(res).ifPresent(System.out::println);

    }

    @Test
    public void testvAeragingLong(){
        System.out.println("averagingLong............");
        // 需要的是ToDoubleFunction，进去一个T（此处就是Dish），返回一个Double
        /**
         * 返回的就是卡路里的平均数
         */
        Double res = menu.stream().collect(Collectors.averagingLong(Dish::getCalories));
        Optional.ofNullable(res).ifPresent(System.out::println);

    }

    /**
     * collectingAndThen: 聚合之后，然后在干点事儿: 再干的事儿就是第二参数Function中apply中定义的逻辑
     * 【案例】在计算出卡洛里的平均值之后, 转成一句话   "卡路里的平均值为：xxx
     */
    @Test
    public void testCollectingAndThen(){
        String res = menu.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.averagingInt(Dish::getCalories),
                        averagCalories -> "卡路里的平均值为：" + averagCalories
                        )
                );

        Optional.ofNullable(res).ifPresent(System.out::println);

    }

    /**
     * 返回一个不能修改的list
     */
    @Test
    public void testCollectingAndThen2(){
        List<Dish> list = menu.stream()
                .filter(dish -> dish.getType().equals(Dish.Type.MEAT))
                .collect(Collectors.toList());
        // 这个时候是可以对结果进行修改
        list.add(new Dish("",false,100, Dish.Type.OTHER));
        System.out.println(list);

        System.out.println("=====================================================");

        List<Dish> list2 = menu.stream()
                .filter(dish -> dish.getType().equals(Dish.Type.MEAT))
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));

        list2.add(new Dish("",false,100, Dish.Type.OTHER));
        System.out.println(list);

        // 使用匿名内部类
        menu.stream()
                .filter(dish -> dish.getType().equals(Dish.Type.MEAT))
                .collect(Collectors.collectingAndThen(Collectors.toList(), new Function<List<Dish>, List<Dish>>() {
                    @Override
                    public List<Dish> apply(List<Dish> dishes) {
                        return Collections.unmodifiableList(dishes);
                    }
                }));

    }

    /**
     * 统计counting
     */
    @Test
    public void testCounting(){
        Long count = menu.stream()
                .collect(Collectors.counting());
        System.out.println(count);

    }


    /***
     * 分组
     *      Collectors.groupingBy(Function f)
     *           T t apply传入的对象，一般就是流中的元素，下面的例子就是Dish
     *           R r apply方法的返回值，groupingBy就是根据这个返回值进行分组
     *           同样结果会返回一个map，key就是R
     *
     *      Collectors.groupingBy(Function f,Collector c)
     *          分组之后在进行聚合   eg：计算每个分组的数量
     *
     *      上面两个返回的都是hashMap，想换一个Map的实现呢
     *
     *      groupingBy的第三个重载方法
     *      groupingBy(Function f,Supplier =s,Collector downstream)
     *      第二个参数就可以用来指定返回的map的类型，使用Supplier.get()的方法就是返回一个T类型的
     *      比如可以返回个HashTable
     *
     *
 *
     */

    /**
     * 根据菜品类型分类
     */
    @Test
    public void testGroupingBy(){

        /*Map<Dish.Type, List<Dish>> collect = menu.stream()
                .collect(Collectors.groupingBy(new Function<Dish, Dish.Type>() {

                    @Override
                    public Dish.Type apply(Dish dish) {
                        return dish.getType();
                    }
                }));*/
        Map<Dish.Type, List<Dish>> res = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType));
        Optional.ofNullable(res).ifPresent(System.out::println);

    }

    /**
     * 统计每个菜品类型的数量
     */

    @Test
    public void testGroupingCount1(){

        Map<Dish.Type, Long> res = menu.stream()
                .collect(Collectors.groupingBy((Dish::getType),Collectors.counting()));
        Optional.ofNullable(res).ifPresent(System.out::println);

    }


    /**
     * 计算每个分组的平均卡路里
     */

    @Test
    public void testGroupingCount2(){

        Map<Dish.Type, Double> res = menu.stream()
                .collect(Collectors.groupingBy((Dish::getType),Collectors.averagingInt(Dish::getCalories)));
        Optional.ofNullable(res).ifPresent(System.out::println);

    }

    /**
     * 上面返回的都是hashMap，想换一个Map的实现呢
     */
    @Test
    public void test(){
        /**
         *
         * 看一下是不是HashMap
         */
        Map<Dish.Type, Double> res1 = menu.stream()
                .collect(Collectors.groupingBy((Dish::getType),Collectors.averagingInt(Dish::getCalories)));
        // class java.util.HashMap
        Optional.ofNullable(res1.getClass()).ifPresent(System.out::println);

        // groupingBy的第三个重载方法
        /**
         * groupingBy(Function f,Supplier =s,Collector downstream)
         *
         *  第二个参数就可以用来指定返回的map的类型，使用Supplier.get()的方法就是返回一个T类型的
         *  比如可以返回个HashTable
         *
         */

        Hashtable<Dish.Type, Double> res2 = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType, Hashtable::new, Collectors.averagingInt(Dish::getCalories)
                ));
        Optional.ofNullable(res2).ifPresent(System.out::println);
        Optional.ofNullable(res2.getClass()).ifPresent(System.out::println);

    }
}
