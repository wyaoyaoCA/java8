package study.wyy.java8.date.before;

import org.junit.Test;

import java.util.Date;

/**
 * @author ：wyy
 * @date ：Created in 2020-01-07 20:50
 * @description：Date
 * @modified By：
 * @version: $
 */
public class DateTest {
    /***
     * java.util.Date
     *  1 构造器
     *      1) 无参构造: 内部使用的就是System.currentTimeMillis()
     *          public Date() {
     *              this(System.currentTimeMillis());
     *          }
     *
 *          2）有参构造
     *
 *      2 方法
     *      1）toString : 显示年月日时分秒
     *      2) getTime：返回与1970年1月1日0时0分0秒之间的时间差（毫秒值）
     *
     *
     */
   @Test
   public void test1(){
       Date date = new Date();
       // Tue Jan 07 20:53:37 CST 2020
       System.out.println(date.toString());
       System.out.println(date.getTime());
   }


    /**
     * 有参数构造
     *      1) new Date(int year, int month, int day);  -- 过时
     *      2) new Date(int year, int month, int day,int hour,int minute)  -- 过时
     *      3) new Date(int year, int month, int day,int hour,int minute, int second);  -- 过时
     *
     *      4）new Date(Long date) ： 传入一个毫秒值，同样也是和1970年1月1日0时0分0秒  没有过时
     */
    @Test
    public void test2(){
        // 过时的构造，但是这里如果使用的需要切记，是从1900开始计算的年，(int y = year + 1900;)
        // 这里输入的2019 -> 3919
        // 月份也是+1，2 -> 3(Mar)
        Date date = new Date(2019, 2, 2);
        // Sun Mar 02 00:00:00 CST 3919
        System.out.println(date.toString());

        Date date1 = new Date(2019, 2, 2, 12, 12, 12);
        // Sun Mar 02 12:12:12 CST 3919
        System.out.println(date1.toString());


        Date date2 = new Date(System.currentTimeMillis());
        System.out.println(date2);

    }
}
