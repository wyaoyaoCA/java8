package study.wyy.java8.generic.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author ：wyy
 * @date ：Created in 2019-12-23 22:00
 * @description：
 * @modified By：
 * @version: $
 */
public class GenericTest1 {


    @Test
    public void test1(){
        List list = new ArrayList();
        // 存放学生成绩
        list.add(89);
        list.add(86);
        list.add(78);

        // 问题1 : 类型不安全
        list.add("Tom");

        for (Object o:list) {
            // 问题2 ： 强转时，可能出现classCastException
            int a = (Integer) o;
        }

        /**
         * 这些问题在编译期是发现不了的，这个是很不方便的，只有运行的时候才会知道！！！！
         */
    }

    /**
     * 使用泛型
     */

    @Test
    public void test2(){
        List<Integer> list = new ArrayList();
        // 存放学生成绩
        list.add(89);
        list.add(86);
        list.add(78);

        // 编译时，就会进行类型检查，保证数据的安全
        //list.add("Tom");

        for (Integer o:list) {
            // 避免了类型转换
            int a = o;
        }
        // 这里返回的迭代器也是带有泛型，就是Integer
        Iterator<Integer> iterator = list.iterator();

        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
