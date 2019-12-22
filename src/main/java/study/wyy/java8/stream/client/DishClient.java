package study.wyy.java8.stream.client;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import study.wyy.java8.stream.modle.Dish;
import study.wyy.java8.stream.service.DishService;

import java.util.Arrays;
import java.util.List;

/**
 * @author ：wyy
 * @date ：Created in 2019-12-21 22:09
 * @description：
 * @modified By：
 * @version: $
 */
public class DishClient {

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


    private final Logger logger = LoggerFactory.getLogger(DishClient.class);

    private final DishService dishService = new DishService();

    @Test
    public void testGetDishesByCaloriesLess400() {
        List<String> dishesByCaloriesLess400 = dishService.getDishesByCaloriesLess400(menu);
        // 卡路里低于400的菜品的名字为：[season fruit, prawns, rice]
        logger.info("卡路里低于400的菜品的名字为：{}", dishesByCaloriesLess400);
    }

    @Test
    public void testGetDishesByCaloriesLess400ByStream() {
        List<String> dishesByCaloriesLess400 = dishService.getDishesByCaloriesLess400ByStream(menu);
        // 卡路里低于400的菜品的名字为：[season fruit, prawns, rice]
        logger.info("卡路里低于400的菜品的名字为：{}", dishesByCaloriesLess400);
    }

    @Test
    public void testGetDishesByCaloriesLess400ByStream2() {
        List<String> dishesByCaloriesLess400 = dishService.getDishesByCaloriesLess400ByStream2(menu);
        // 卡路里低于400的菜品的名字为：[season fruit, prawns, rice]
        logger.info("卡路里低于400的菜品的名字为：{}", dishesByCaloriesLess400);
    }
}