package study.wyy.java8.start.client;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import study.wyy.java8.start.filter.AppleFilter;
import study.wyy.java8.start.filter.impl.AppleFilterByGreenAndWeightLess180;
import study.wyy.java8.start.model.Apple;
import study.wyy.java8.start.service.AppleService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author ：wyy
 * @date ：Created in 2019-12-21 09:25
 * @description：客户端
 * @modified By：
 * @version: $
 */
public class AppleClientClass {

    private final AppleService appleService = new AppleService();
    private final Logger logger = LoggerFactory.getLogger(AppleClientClass.class);

    // 模拟一堆苹果
    private static final List<Apple> apples = Arrays.asList(
            new Apple("green", 150L),
            new Apple("yellow", 160L),
            new Apple("red", 140L));

    @Test
    public void testFindAppleByGreen() {
        // 挑选出绿色的苹果
        List<Apple> greenApples = appleService.filterAppleByGreen(apples);
        logger.info("挑选出绿色的苹果 => [{}]", greenApples);
        assertThat(greenApples.size(), equalTo(1));
    }

    @Test
    public void testFindAppleByColor() {
        // 挑选出红色的苹果
        List<Apple> greenApples = appleService.filterAppleByColor("red", apples);
        logger.info("挑选出红色的苹果 => [{}]", greenApples);
        assertThat(greenApples.size(), equalTo(1));
    }


    @Test
    public void testFindAppleByFilter() {
        // 选出绿色并且重量小于180
        AppleFilter appleFilter = new AppleFilterByGreenAndWeightLess180();
        List<Apple> greenApples = appleService.filterApple(apples,appleFilter);
        logger.info("选出绿色并且重量小于180 => [{}]", greenApples);
        assertThat(greenApples.size(), equalTo(1));
    }

    @Test
    public void testFindAppleByFilter2(){
        // 使用匿名内部类，在调用的时候进行设置实时的过滤规则
        List<Apple> greenApples = appleService.filterApple(apples, new AppleFilter() {
            @Override
            public boolean filter(Apple apple) {

                return apple.getColol().equals("yellow");
            }
        });
        logger.info("选出黄苹果 => [{}]", greenApples);
        assertThat(greenApples.size(), equalTo(1));
    }



}
