
## java 8

### 1 引入案例

> 需求
从一堆苹果中找出想要的苹果

#### 需求一：挑选出绿色的苹果

- 提供了如下的方法：`study.wyy.java8.start.service.AppleService.filterAppleByGreen`
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
package study.wyy.java8.start.filter;

import study.wyy.java8.start.model.Apple;

public interface AppleFilter  {

    boolean filter(Apple apple);
}
```
- 提供一个实现类
```java
package study.wyy.java8.start.filter.impl;

import study.wyy.java8.start.filter.AppleFilter;
import study.wyy.java8.start.model.Apple;

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

`study.wyy.java8.start.client.AppleClientClass`

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