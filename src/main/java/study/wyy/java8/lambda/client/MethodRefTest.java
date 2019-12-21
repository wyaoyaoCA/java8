package study.wyy.java8.lambda.client;

import org.junit.Test;
import study.wyy.java8.lambda.model.Apple;

import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author ：wyy
 * @date ：Created in 2019-12-21 14:25
 * @description：方法引用演示
 * @modified By：
 * @version: $
 */
public class MethodRefTest {

    /**
     * 对象::实例方法名
     */
    @Test
    public void test1(){
        //Consumer<String> consumer = (String s) -> {System.out.println(s);};
        Consumer<String> consumer = (s) -> System.out.println(s);

        // lambda体的方法是已经实现的方法(已经有方法实现了lambda方法体要实现的逻辑)，所以可以使用方法引用
        // 比如这里方法方法体中的功能就是打印指定的字符串，这个功能的已经有 System.out.println这个方法实现了
        /**
         *
         * println是一个实例方法：是`java.io.PrintStream`的方法，
         * 这里只需要方法名，不需要入参，，java会进行推断
         *  所以就必须有个要求：
         *      需要实现的接口的方法的参数列表和返回值类型必须一致，才能进行推断
         *      这里的Consumer接口的accept()方法的入参就是一个字符串，返回值void
         *      println这个方法的参数也是一个字符串，返回值是void
         */
        Consumer<String> consumer1 = System.out::println;

    }


    @Test
    public void test2(){
        Apple apple = new Apple("red",100L);
        // 这里返回的就是苹果的颜色
        // 完整的lambda表达式
        Supplier<String> stringSupplier = () -> {return apple.getColol();};

        // 简化的lambda
        Supplier<String> stringSupplier1 = () -> apple.getColol();

        // 方法引用，这里返回苹果的颜色的逻辑已经实现了，就是APPle对象的getColol方法
        // 并且和Supplier接口定义的接口方法的入参返回值是一致的
        Supplier<String> stringSupplier2 = apple::getColol;
    }



    /**
     * 类::静态方法名
     */
    @Test
    public void test3(){
        // 比较两个整数的大小

        // 1 匿名内部类
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer x, Integer y) {
                return Integer.compare(x,y);
            }
        };
       // System.out.println(comparator.compare(1,2));

        // 2 lambda表达式
        //Comparator<Integer> comparator1 = (x,y) -> {return Integer.compare(x,y);};
        Comparator<Integer> comparator1 = (x,y) -> Integer.compare(x,y);

        // 3 方法引用
        // lambda体中方法已经实现了，是一个Integer的静态方法
        Comparator<Integer> comparator2 = Integer::compare;

        int res = comparator2.compare(1, 2);

        // 断言
        assertThat(res, equalTo(-1));
    }




}
