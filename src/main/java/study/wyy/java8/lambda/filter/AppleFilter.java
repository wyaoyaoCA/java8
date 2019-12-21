package study.wyy.java8.lambda.filter;

import study.wyy.java8.lambda.model.Apple;
@FunctionalInterface
public interface AppleFilter  {

    boolean filter(Apple apple);
}