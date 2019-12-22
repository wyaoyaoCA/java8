
## Stream 

### 1 引入案例

> 从菜单中选出卡路里低于400的菜品，并排序，返回对应的菜品的名字

代码实现：
`study.wyy.java8.stream.service.DishService`


### 2 Stream介绍

#### Stream几个概念

- Sequence of elements
    source里面的元素，比如引入案例中的dish
    
- source: 入门案例的菜单menu

- Data processing operations：数据的操作过程
 入门案例中的filer，sort等
 
- pipelining；流式的工作模式，采用builder的设计模式，链式调用


#### 如何创建Stream

`study.wyy.java8.stream.Test.CreateStreamDemo`


### 3 Stream API

`study.wyy.java8.stream.Test.StreamFilterTest`
`study.wyy.java8.stream.Test.StreamMapTest`
`study.wyy.java8.stream.Test.StreamFindTest`
`study.wyy.java8.stream.Test.StreamReduceTest`
`study.wyy.java8.stream.Test.StreamMatchTest`    