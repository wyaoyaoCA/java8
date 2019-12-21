
## java 8

### 1 引入案例

`study.wyy.java8.lambda`

> 需求
从一堆苹果中找出想要的苹果

#### 需求一：挑选出绿色的苹果

- 提供了如下的方法：`study.wyy.java8.lambda.service.AppleService.filterAppleByGreen`
```java
public List<Apple> filterAppleByGreen(List<Apple> apples) {
        List<Apple> greenApples = new ArrayList<>();
        for (Apple apple : apples) {
            if ("green".equals(apple.getColol())) {
                greenApples.add(apple);
            }
        }
        return greenApples;
    }
```
#### 需求二：需求变了，找出红色的苹果
- 要么提供一个新的方法
- 或者修改上述的方法，将颜色作为一个变量传入

```java
public List<Apple> filterAppleByColor(String color, List<Apple> apples) {

        List<Apple> greenApples = new ArrayList<>();
        for (Apple apple : apples) {
            if (color.equals(apple.getColol())) {
                greenApples.add(apple);
            }
        }
        return greenApples;
    }
```
    
#### 需求又变了？怎么办，如何应对？

> 方案
- 提共一个接口，提供一个方法，方法内部的逻辑就是苹果的过滤规则，只需要实现该接口，实现不同的过滤规则
```java
package study.wyy.java8.lambda.filter;

import study.wyy.java8.lambda.model.Apple;

public interface AppleFilter  {

    boolean filter(Apple apple);
}
```
- 提供一个实现类
```java
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

```

- 测试

`study.wyy.java8.lambda.client.AppleClientClass`

```java
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

```

#### 使用lambda表达式

如果一个接口只有一个方法（排除default方法和静态方法），则可以使用lambda表达式
这个可以使用`@FunctionalInterface`标注，不标注也可以啦

```java
@Test
public void testFindAppleByFilterLambda(){
    // 使用匿名内部类，在调用的时候进行设置实时的过滤规则
    List<Apple> greenApples = appleService.filterApple(apples,(Apple apple) -> {
        return apple.getColol().equals("yellow");
    });

    logger.info("选出黄苹果 => [{}]", greenApples);
    assertThat(greenApples.size(), equalTo(1));
}
```

> 简写
```java

@Test
public void testFindAppleByFilterLambda(){
    // 使用匿名内部类，在调用的时候进行设置实时的过滤规则
    List<Apple> greenApples = appleService.filterApple(apples,apple -> 
         apple.getColol().equals("yellow")
    );
    logger.info("选出黄苹果 => [{}]", greenApples);
    assertThat(greenApples.size(), equalTo(1));
}
```
####  Lambda标准格式

Lambda表达式的**标准格式**为：

```
(参数类型 参数名称) -> { 代码语句 }
```

格式说明：

* 小括号内的语法与传统方法参数列表一致：无参数则留空；多个参数则用逗号分隔。
* `->`是新引入的语法格式，代表指向动作。
* 大括号内的语法与传统方法体要求基本一致。
注意：

- 参数类型可省略，编译器可以自己推断
- 如果只有一个参数，圆括号可以省略
- 代码块如果只是一行代码，大括号也可以省略
- 如果代码块是一行，且是有结果的表达式，return可以省略

####  Lambda的使用前提
Lambda的语法非常简洁，完全没有面向对象复杂的束缚。但是使用时有几个问题需要特别注意：

1. 使用Lambda必须具有接口，且要求**接口中有且仅有一个抽象方法**。
   无论是JDK内置的`Runnable`、`Comparator`接口还是自定义的接口，只有当接口中的抽象方法存在且唯一时，才可以使用Lambda。
2. 使用Lambda必须具有**上下文推断**。
   也就是方法的参数或局部变量类型必须为Lambda对应的接口类型，才能使用Lambda作为该接口的实例。

> 备注：有且仅有一个抽象方法的接口，称为“**函数式接口**”。

>事实上，把Lambda表达式可以看做是匿名内部类的一种简写方式。当然，前提是这个匿名内部类对应的必须是接口，
而且接口中必须只有一个函数！Lambda表达式就是直接编写函数的：参数列表、代码体、返回值等信息，用函数来代替完整的匿名内部类！

### 2 java8函数接口介绍
- java.util.function.Predicate<T>
    - boolean test(T t); 接收一个参数，返回一个Boolean，可用于断言
- java.util.function.Consumer<T>
    - void accept(T t);
- java.util.function.Function<T,R>
    -  R apply(T t); 接收一个T，返回一个R
- java.util.function.Supplier<T>  
    - T get(); 返回一个T
#### predicate 
这个函数接口和入门案例的AppleFilter是类似的，完全可以用predicate实现，不需要自己定好一个AppleFilter接口

```java
public List<Apple> filterApplePredicate(List<Apple> apples, Predicate<Apple> appleFilter){
    List<Apple> result = new ArrayList<>();
    for (Apple apple : apples) {
        if(appleFilter.test(apple)){
            result.add(apple);
        }
    }
    return result;
}

```
> 调用

```java
@Test
public void testFindApplePredicate(){
    List<Apple> yellowApples = appleService.filterApplePredicate(AppleClientClass.apples, apple -> apple.getColol().equals("yellow"));
    logger.info("选出黄苹果 => [{}]", yellowApples);
    assertThat(yellowApples.size(), equalTo(1));
}
```

> 其他的演示代码：study.wyy.java8.lambda.client.PredicateTest


#### Consumer

消费一个对象

`study.wyy.java8.lambda.client.ConsumerTest` 

#### Function
接收一个T，返回一个R

`study.wyy.java8.lambda.client.FunctionTest`

#### Supplier
返回一个T

`study.wyy.java8.lambda.client.SupplierTest`

### 3 方法引用
`study.wyy.java8.lambda.client.MethodRefTest`

如果lambda体中的内容的方法已经实现了，我们可以使用"方法引用"
可以理解为方法引用是lambda的另一种表现形式



> 主要有三种语法格式
- 对象::实例方法名
- 类::静态方法名
- 类::实例方法名

> 注意
- lambda体中的调用方法的参数列表与返回值类型，要与函数式接口中的抽象方法的函数列表和返回值类型保持一致
- lambda表达式参数列表中的第一个参数是实例方法的调用者，第二个参数是该实例方法的入参，就可以使用 `类::实例方法名`

#### 对象::实例方法名
```java
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
```

#### 类::静态方法名
```java
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
```

#### 类::实例方法名



```java
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
```

### 4 构造器引用

`study.wyy.java8.lambda.client.MethodRefTest`

> 格式：
> ClassName::new


```java
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

}
```
 