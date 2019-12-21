package study.wyy.java8.start.service;

import study.wyy.java8.start.filter.AppleFilter;
import study.wyy.java8.start.model.Apple;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：wyy
 * @date ：Created in 2019-12-21 09:21
 * @description：
 * @modified By：
 * @version: $
 */
public class AppleService {

    /**
     * 过滤出绿色苹果
     */
    public List<Apple> filterAppleByGreen(List<Apple> apples) {
        List<Apple> greenApples = new ArrayList<>();
        for (Apple apple : apples) {
            if ("green".equals(apple.getColol())) {
                greenApples.add(apple);
            }
        }
        return greenApples;
    }

    /**
     * 根据颜色选出想要的苹果
     */

    public List<Apple> filterAppleByColor(String color, List<Apple> apples) {

        List<Apple> greenApples = new ArrayList<>();
        for (Apple apple : apples) {
            if (color.equals(apple.getColol())) {
                greenApples.add(apple);
            }
        }
        return greenApples;
    }

    public List<Apple> filterApple(List<Apple> apples, AppleFilter appleFilter){
        List<Apple> result = new ArrayList<>();
        for (Apple apple : apples) {
            if(appleFilter.filter(apple)){
                result.add(apple);
            }
        }
        return result;
    }

}