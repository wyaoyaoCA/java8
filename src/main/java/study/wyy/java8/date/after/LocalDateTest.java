package study.wyy.java8.date.after;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.temporal.ChronoField;

/**
 * @author ：wyy
 * @date ：Created in 2020-01-09 20:37
 * @description：
 * @modified By：
 * @version: $
 */
public class LocalDateTest {

    private final Logger logger = LoggerFactory.getLogger(LocalDateTest.class);
    @Test
    public void test1(){
        LocalDate localDate = LocalDate.of(2019, 2, 14);
        System.out.println(localDate.getYear()); // 2019 int
        System.out.println(localDate.getMonth()); // FEBRUARY Month
        System.out.println(localDate.getMonthValue()); // 2 int
        System.out.println(localDate.getDayOfMonth()); // 14
        System.out.println(localDate.getDayOfYear()); // 45  31+14
        System.out.println(localDate.getDayOfMonth()); // 14
        System.out.println(localDate.getDayOfWeek()); // THURSDAY DayOfWeek
    }

    /**
     * 获取当前日期
     */
    public void test2(){
        LocalDate.now();
    }

    /**
     * get方法
     */
    @Test
    public void test3(){
        LocalDate now = LocalDate.of(2019, 2, 14);
        logger.info("现在的时间: {}",now); //2020-01-09
        // TemporalField：接口 ChronoField 实现类 enum
        logger.info("今天是今年的第几天 => 第{}天",now.get(ChronoField.DAY_OF_YEAR));
        logger.info("今天是这个月的第几天 => 第{}天",now.get(ChronoField.DAY_OF_MONTH));
        logger.info("今天是这个周的第几天 => 第{}天",now.get(ChronoField.DAY_OF_WEEK));
        logger.info("今天哪一年 => {}年",now.get(ChronoField.YEAR));
        logger.info("今天是几月 => {}月",now.get(ChronoField.MONTH_OF_YEAR));
        logger.info("今天是今年的第几周 => 第{}周",now.get(ChronoField.ALIGNED_WEEK_OF_YEAR));
        logger.info("今天是这个月的第几周 => 第{}周",now.get(ChronoField.ALIGNED_WEEK_OF_MONTH));

        /**
         * ALIGNED_DAY_OF_WEEK_IN_MONTH ：以这个月的1号为周一计算，所以14号则就是周天（7）
         */
        logger.info("今天是周几(以这个月的1号为周一计算) => 周{}",now.get(ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH));

        /**
         * ALIGNED_DAY_OF_WEEK_IN_YEAR：以1月1号为周一计算
         */
        logger.info("今天是周几(以1月1号为周一计算) => 周{}",now.get(ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR));

    }

}
