package study.wyy.java8.stream.service;

import study.wyy.java8.stream.modle.Dish;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author ：wyy
 * @date ：Created in 2019-12-21 22:23
 * @description：
 * @modified By：
 * @version: $
 */
public class DishService {

    public List<String> getDishesByCaloriesLess400(List<Dish> menu) {
        List<Dish> caloriesLess400Dishes = new ArrayList<>();

        // 1 过滤出卡洛里小于400的
        for (Dish dish : menu) {
            if (400 > dish.getCalories()) {
                caloriesLess400Dishes.add(dish);
            }
        }

        // 2 排序
        // 2.1 匿名内部类实现
        /*Collections.sort(caloriesLess400Dishes, new Comparator<Dish>() {
            @Override
            public int compare(Dish dish1, Dish dish2) {
                return Integer.compare(dish1.getCalories(),dish1.getCalories());
            }
        });*/

        // 2.2 使用lambda表达式
        Collections.sort(caloriesLess400Dishes, (dish1, dish2) -> Integer.compare(dish1.getCalories(), dish2.getCalories()));

        // 3 返回对应的菜品的名字
        List<String> dishNameList = new ArrayList<>();
        for (Dish dish : caloriesLess400Dishes) {
            dishNameList.add(dish.getName());
        }
        return dishNameList;
    }


    /**
     * 如何推导出最终结果，看下面的两个测试方法
     * @param menu
     * @return
     */
    public List<String> getDishesByCaloriesLess400ByStream(List<Dish> menu) {

        List<String> dishNameList = menu.stream()
                // 过滤
                .filter(dish -> dish.getCalories() < 400)
                // 根据卡路里排序
                .sorted(Comparator.comparing(Dish::getCalories))
                // 映射将名字映射到stream流中
                .map(Dish::getName)
                // 收集Stream流里的数据，这时流里的数据类型就是String
                .collect(Collectors.toList());
        return dishNameList;


    }

    /**
     * 使用匿名内部类
     *
     * @param menu
     * @return
     */
    public List<String> getDishesByCaloriesLess400ByStream2(List<Dish> menu) {

        List<String> dishNameList = menu.stream()
                // 过滤
                .filter(new Predicate<Dish>() {
                    @Override
                    public boolean test(Dish dish) {
                        return dish.getCalories() < 400;
                    }
                })
                // 根据卡路里排序
                .sorted(Comparator.comparing(new Function<Dish, Integer>() {
                    @Override
                    public Integer apply(Dish dish) {
                        return dish.getCalories();
                    }
                }))
                //  映射将名字映射到stream流中
                .map(new Function<Dish, String>() {
                    @Override
                    public String apply(Dish dish) {
                        return dish.getName();
                    }
                })
                // 收集Stream流里的数据，这时流里的数据类型就是String
                .collect(Collectors.toList());


        return dishNameList;


    }


    /**
     * 使用lambda表达式
     *
     * @param menu
     * @return
     */
    public List<String> getDishesByCaloriesLess400ByStream3(List<Dish> menu) {

        List<String> dishNameList = menu.stream()
                // 过滤， filter需要的是Predicate<T>， 根据Predicate中的test(T t)方法进行过滤，将结果为true的留在Stream流中
                //.filter((Dish dish) -> {return dish.getCalories()<400;});
                .filter(dish -> dish.getCalories() < 400)

                // 根据卡路里排序 Comparator.comparing需要的是Function函数式接口，通过Function的apply方法，将流中的数据得到排序的字段，进行排序
                // 这里就是根据卡路里排序，所以就是讲dish通过function接口的得到对应的卡路里
                //.sorted(Comparator.comparing((Dish dish) -> {return dish.getName();}));
                //.sorted(Comparator.comparing(dish -> dish.getName()))
                // 这里lambda体中逻辑已经实现了，可以使用方法引用，并且函数接口apply方法和getName方法的是形参是对应的
                .sorted(Comparator.comparing(Dish::getCalories))

                //  映射将名字映射到stream流中,需要的是Function函数式接口，通过apply方法将将流中的数据进行转换
                // .map((Dish dish) -> {return dish.getName();})
                //.map(dish -> dish.getName())
                .map(Dish::getName)
                .collect(Collectors.toList());
        return dishNameList;


        /**
         * Comparator.comparing 代码实现
         *  public static <T, U extends Comparable<? super U>> Comparator<T> comparing(
         *             Function<? super T, ? extends U> keyExtractor)
         *     {
         *         Objects.requireNonNull(keyExtractor);
         *         return (Comparator<T> & Serializable)
         *             (c1, c2) -> keyExtractor.apply(c1).compareTo(keyExtractor.apply(c2));
         *     }
         */
    }
}
