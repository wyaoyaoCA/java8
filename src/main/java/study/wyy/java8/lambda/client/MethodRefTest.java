package study.wyy.java8.lambda.client;

import org.junit.Test;
import study.wyy.java8.lambda.model.Apple;

import java.util.Comparator;
import java.util.function.*;

import static org.hamcrest.CoreMatchers.nullValue;
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


    /**
     * 类::实例方法名
     *     使用时机：
     *      lambda表达式参数列表中的第一个参数是实例方法的调用者，第二个参数是该实例方法的入参
     */
    @Test
    public void test4(){
        // 比较两个字符串是否一样

        // 1 匿名内部类
        BiPredicate<String,String> biPredicate = new BiPredicate<String,String> (){

            @Override
            public boolean test(String s1, String s2) {
                return s1.equals(s2);
            }
        };

        // 2 lambda表达式
        BiPredicate<String,String> biPredicate2 = (s1,s2) -> {return s1.equals(s2);};
        // 2.1 简化的lambda表达式
        BiPredicate<String,String> biPredicate3 = (s1,s2) ->  s1.equals(s2);

        // 3 方法引用
        /**
         * BiPredicate<String,String> biPredicate3 = (s1,s2) ->  s1.equals(s2);
         *  推导：
         *      1 String.equals方法是实例方法
         *      2 s1是equals方法的调用者
         *      3 s2是equals方法的形参
         *
         */
        BiPredicate<String,String> biPredicate4 = String::equals;
    }



    /*******************
     *              构造器引用
     *
     *                  格式：
     *                      ClassName::new
     * ************************/

    @Test
    public void test5(){

        // 1 匿名内部类
        Supplier<Apple> supplier = new Supplier<Apple>() {
            @Override
            public Apple get() {
                return new Apple();
            }
        };

        // 2 lambda
        Supplier<Apple> supplier1 = () -> {return new Apple();};
        // lambda简化
        Supplier<Apple> supplier2 = () -> new Apple();

        // 3 构造器引用
        Supplier<Apple> supplier3 = Apple::new;

        /**
         * 请问这里调用的是哪个构造器，目前Apple提供了两个构造函数：无参构造和两个参数的有参构造
         *
         *    和方法引用一样（说白了构造方法也是方法啊）
         *        Supplier函数式接口的get抽象方法是没有形参的，由于要和函数式接口的抽象方法的参数列表一致，
         *        所以这里对应的就是无参构造
         */
        // 断言一下是不是无参构造
        Apple apple = supplier3.get();
        assertThat(apple.getWeight(),nullValue());
        assertThat(apple.getColol(),nullValue());

    }

    /**
     * 测试两个参数的构造
     */
    @Test
    public void test6(){
        // 可以使用BiFunction

        // 1 匿名内部类
        BiFunction<String,Long,Apple> biFunction = new BiFunction<String,Long,Apple>() {
            @Override
            public Apple apply(String color, Long weight) {
                return new Apple(color,weight);
            }
        };

        // 2 lambda
        BiFunction<String,Long,Apple> biFunction1 = (color,weight) -> {return new Apple(color,weight);};
        // 简化lambda
        BiFunction<String,Long,Apple> biFunction2 = (color,weight) ->  new Apple(color,weight);

        // 3 构造器引用， 这就是两个参数的构造器
        // BiFunction函数是接口的apply(T t, U u)是接收两个参数
        // 所以推导出来的构造方法也是两个参数的，类型分别为String和Long
        BiFunction<String,Long,Apple> biFunction3 = Apple::new;

        // 断言一下是不是两个参数的构造器
        Apple apple = biFunction3.apply("red",150L);
        assertThat(apple.getWeight(),equalTo(150L));
        assertThat(apple.getColol(),equalTo("red"));
    }


    /*****************
     * 练习
     */

    /**
     * 返回指定字符串指定位置的的字符
     *      分析：
     *          需要接收一个指定字符串，一个指定位置
     *          返回一个字符
     *          BiFunction<T, U, R> 这个函数式接口满足我们的需求
     *          接收两个参数返回一个参数
     */
    @Test
    public void test7(){
        // 1 匿名内部类
        BiFunction<String,Integer,Character> biFunction = new BiFunction<String, Integer, Character>() {
            @Override
            public Character apply(String s, Integer index) {
                // 返回指定位置的字符
                return s.charAt(index);
            }
        };

        // 2 lambda
        BiFunction<String,Integer,Character> biFunction1 = (s,index) -> {return s.charAt(index);};

        // 2.1 简化lambda
        BiFunction<String,Integer,Character> biFunction2 = (s,idndex) -> s.charAt(idndex);

        // 3 方法引用
        /**
         * 1. lambda体中的方法已经实现，可以使用方法引用
         * 2. 并且charAt的方法的调用者是函数式接口BiFunction中apply方法的第一个参数
         *     charAt的方法的形参是函数式接口BiFunction中apply方法的第二个参数，使用类名:实例方法名
         */
        BiFunction<String,Integer,Character> biFunction3 = String::charAt;
    }


    /**
     * 返回"hello"这个字符串的指定位置的的字符
     *
     *      上述方式已经实现了，但是这里主要为了演示
     *          - 类::实例方法名（test7）
     *          - 对象::实例方法名
     *
     *
     */
    /**
     * 这里既然说明了就是hello这个字符串，那么Function这个函数式接口已经满足了
     *  - 接收一个位置
     *  - 返回一个字符
     */
    @Test
    public void test8(){

        // 1 匿名内部类
        Function<Integer,Character> function = new Function<Integer, Character>() {
            @Override
            public Character apply(Integer integer) {
                return "hello".charAt(integer);
            }
        };

        // 2 lambda表达式
        Function<Integer,Character> function1 = (index) -> {return "hello".charAt(index);};
        // 简化lambda表达式
        Function<Integer,Character> function2 = index -> "hello".charAt(index);

        // 方法引用
        String string = "hello";
        Function<Integer,Character> function3 = string::charAt;

        // 断言
        Character character = function3.apply(2);
        assertThat(character,equalTo('l'));


        /**
         * 这里我们已经是hello字符串，所以直接使用实例名进行调用
         * 而test7
         *  我们是拿不到这个指定的字符串的，是作为一个变量传入的，所以使用的就是类名::实例方法名
         */
    }


}
