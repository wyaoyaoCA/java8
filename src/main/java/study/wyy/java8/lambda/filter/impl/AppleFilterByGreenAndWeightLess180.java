package study.wyy.java8.lambda.filter.impl;

import study.wyy.java8.lambda.filter.AppleFilter;
import study.wyy.java8.lambda.model.Apple;

/**
 * @author ：wyy
 * @date ：Created in 2019-12-21 09:58
 * @description： 选出绿色并且重量小于180
 * @modified By：
 * @version: $
 */
public class AppleFilterByGreenAndWeightLess180 implements AppleFilter {

    @Override
    public boolean filter(Apple apple) {
        return (apple.getColol().equals("green") && apple.getWeight() <= 180);
    }
}
