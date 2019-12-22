package study.wyy.java8.stream.Test;

import org.junit.Test;
import study.wyy.java8.stream.modle.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author ：wyy
 * @date ：Created in 2019-12-22 10:10
 * @description：
 * @modified By：
 * @version: $
 */
public class StreamMapTest {

    private final List<Integer> sourceInt = Arrays.asList(1, 2, 3, 4, 5, 6,5,7,3);

    /**
     * 模拟一份菜单
     */
    final List<Dish> menu = Arrays.asList(
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



    /**
     * map介绍
     *      1 需要的一个Function函数式接口：接收一个类型的T，返回另一个类型的R，当然也可以是相同类型
     *      2 map就是通过Function.apply(T t)这个方法，将流中的数据映射为另一个类型的数据
     */

    /**
     *  1 sourceInt中的数据方法2倍
     */
    @Test
    public void test1(){
        List<Integer> result = null;
        // 匿名内部类
        /*result = sourceInt.stream().map(new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer sourceItem) {
                return sourceItem *2;
            }
        }).collect(Collectors.toList());*/

        // lambda
        //result = sourceInt.stream().map((integer) -> {return integer * 2;}).collect(Collectors.toList());
        result = sourceInt.stream().map(integer -> integer *2).collect(Collectors.toList());

        System.out.println(result);
    }


    /**
     * 2 只收集菜单中中菜品的名字：List<String> dishNames
     *  1 要将List<Dish> -> Stream -> 将流里面的数据Dish映射为String -> 收集流里面的数据 -> List<String> dishNames
     */
    @Test
    public void test2(){
        List<String> dishNames = null;

        // 1 匿名内部类
       /* dishNames = menu.stream().map(new Function<Dish, String>() {
            @Override
            public String apply(Dish dish) {
                // 流里面的数据原先为dish，映射为String类型的菜名
                return dish.getName();
            }
        }).collect(Collectors.toList());*/

        // 2 lambda表达式
        //  dishNames = menu.stream().map(dish -> dish.getName()).collect(Collectors.toList());
        // 3 方法引用
        dishNames = menu.stream().map(Dish::getName).collect(Collectors.toList());

        System.out.println(dishNames);
    }

    /**
     * flatmap ：扁平化
     *      参数依然是个Function，但是这个Function有了限制，接收一个T，返回一个流Stream<R>
     *
     */

    /**
     * 从一个字符串数组中提取出里面的字符，并去重
     *  1 获取字符串数组的流  ： Arrays.stream(strings)
     *  2 此时流里的数据就是字符串的元素，为String类型
     *  3 将流的数据进行处理，进行split.("")，得到的就是每个字符串的元素数组
     *  4 将最终的数据再转成一个流
     *  综上，可见就是一个流映射成了另一个流，就可以使用flatmap
     */

    @Test
    public void test3(){
        List<String> list = null;
       String[] strings = new String[]{"Hello","World"};

       // 1 流里是啥 : Hello World
        //Arrays.stream(strings).forEach(s -> System.out.println(s));


       list = Arrays.stream(strings).flatMap(new Function<String, Stream<String>>() {
            @Override
            public Stream<String> apply(String s) {
                String[] split = s.split("");
                return Arrays.stream(split);
            }
        }).distinct().collect(Collectors.toList());


        System.out.println("匿名内部类："+ list);

        // lambda
        list = Arrays.stream(strings).flatMap(s -> {
            // 将字符串数组中的每个字符串转成一个新的字符数组
            String[] split = s.split("");
            // 再转成流
            return Arrays.stream(split);
        }).distinct().collect(Collectors.toList());

        System.out.println("lambda： " + list);

    }


}
