package study.wyy.java8.date.before;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import study.wyy.java8.stream.client.DishClient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author ：wyy
 * @date ：Created in 2020-01-08 20:47
 * @description：
 * @modified By：
 * @version: $
 */
public class CalendarTest {

    private final Logger logger = LoggerFactory.getLogger(DishClient.class);
    /**
     * Claendar: 是一个抽象类 日历类
     *      Calendar类是一个抽象类，可以为在某一特定时刻和一组之间的转换的方法calendar fields如YEAR ， MONTH ， DAY_OF_MONTH ， HOUR ，等等，
     *      以及用于操纵该日历字段，如获取的日期下个星期
     * 1 如何实例化
     *      1) new 其子类: GregorianCalendar
     *      2）静态方法 getInstance
     * 2 方法
     *      1）get方法
     *      2）set方法
     *      .....
     */

    /**
     * 如何实例化
     *      getInstance
     */
    @Test
    public void test1(){
        Calendar instance = Calendar.getInstance();
        // class java.util.GregorianCalendar 也是GregorianCalendar这个子类
        // 当前日期和时间进行初始化
        System.out.println(instance.getClass());

        System.out.println(instance);
    }

    /**
     * 测试get方法： 返回给定日历字段的值。
     *      日历字段：Calendar定义了一堆日历字段都是静态
     *   下面是一些常用的，具体可以参考文档自己慢慢实验
     */
    @Test
    public void testGet(){
        Calendar calendar = Calendar.getInstance();

        // 1 获取指定时间是该月的第几天zxzz
        logger.info("今天是这个月的第几天 => 第{}天",calendar.get(Calendar.DAY_OF_MONTH));
        // 2 获取指定时间是该周的第几天（注意：是从周天开始计算的，周三就是第四天）
        logger.info("今天是这个周的第几天 => 第{}天",calendar.get(Calendar.DAY_OF_WEEK));
        // 3 获取指定时间在该月的第几个周
        logger.info("今天是这个月的第几周 => 第{}个周",calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH));
        logger.info("今天是这个月的第几周 => 第{}个周",calendar.get(Calendar.WEEK_OF_MONTH));
        // 4 获取指定时间是在该年的第几天
        logger.info("今天是今年的第几天 => 第{}天",calendar.get(Calendar.DAY_OF_YEAR));
        // 5 获取指定时间是在该年的第几个周
        logger.info("今天是今年的第几个周 => 第{}个周",calendar.get(Calendar.WEEK_OF_YEAR));
        // 6 获取指定时间是当前天的第几个小时
        logger.info("当前时间是今天的第几个小时 => 第{}个小时",calendar.get(Calendar.HOUR_OF_DAY));
        logger.info("：当前时间是今天的第{}个小时的第{}分钟",calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE));


        logger.info("今年有多少个周 => 今年有{}个周",calendar.getWeeksInWeekYear());

        logger.info("今年有多少天？=> 今年有{}天",calendar.getActualMaximum(Calendar.DAY_OF_YEAR));
        logger.info("这个月有多少天? => 这个月有{}天",calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

        logger.info("今年是哪一年 => {}年",calendar.getWeekYear());
        logger.info("今年是哪一年 => {}年",calendar.get(Calendar.YEAR));


        // 6 通过get转为date
        Date time = calendar.getTime();

    }


    /***
     * 测试add方法
     *  void add(int field, int amount)
     *      根据日历的规则，将指定的时间量添加或减去给定的日历字段。
     */
    @Test
    public void testAdd(){
        Calendar calendar = Calendar.getInstance();
        // 注意月份要+1
        logger.info("{}年{}月{}日",calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH)+1,calendar.get(Calendar.DATE));


        logger.info("计算两年后...........");
        calendar.add(Calendar.YEAR,2);

        logger.info("两年后 => {}年{}月{}日",calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH)+1,calendar.get(Calendar.DATE));

        logger.info("计算两周后...........");
        calendar = Calendar.getInstance();
        calendar.add(Calendar.WEEK_OF_YEAR,2);
        logger.info("两周后 => {}年{}月{}日",calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH)+1,calendar.get(Calendar.DATE));

    }

    /***
     * 测试set方法
     *  set(int year, int month, int date)
     *  set(int year, int month, int date, int hourOfDay, int minute)
     *  set(int year, int month, int date, int hourOfDay, int minute, int second)
     *  setTime(Date date) 使用给定的 Date设置此日历的时间。
     */
    @Test
    public void TestSet() throws ParseException {
        // 1 设置日历字段
        Calendar calendar = Calendar.getInstance();
        // 三个参数分别为年月日
        calendar.set(2048,02,02);
        logger.info("{}年{}月{}日",calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DATE));
        // 2 还可以设置到具体的时间，可以设置小时，分钟，秒
        calendar.set(2048,02,02,14,32,12);
        logger.info("{}年{}月{}日{}时{}分{}秒",
                calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DATE),
                calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),calendar.get(Calendar.SECOND)
        );

        Date parse = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2019-02-02 09:12:34");
        calendar.setTime(parse);
        logger.info("{}年{}月{}日{}时{}分{}秒",
                calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH)+1,calendar.get(Calendar.DATE),
                calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),calendar.get(Calendar.SECOND)
        );


        // 设置时区
        // sun.util.calendar.ZoneInfo[id="Asia/Shanghai",offset=28800000,dstSavings=0,useDaylight=false,transitions=19,lastRule=null]
        System.out.println(TimeZone.getDefault());
        calendar.setTimeZone(TimeZone.getDefault());
    }

}

