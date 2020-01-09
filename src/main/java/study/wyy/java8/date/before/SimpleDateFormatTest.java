package study.wyy.java8.date.before;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ：wyy
 * @date ：Created in 2020-01-07 21:47
 * @description：日期格式化
 * @modified By：
 * @version: $
 */
public class SimpleDateFormatTest {

    /**
     * 格式化： Date -> 字符串
     */
    @Test
    public void test2(){
        SimpleDateFormat format = new SimpleDateFormat();
        String result = format.format(new Date());
        // 20-1-7 下午9:54
        System.out.println(result);
    }

    /*
    * 解析： 字符串 -> date
    *  默认解析：20-1-7 下午9:54 这种格式的
    *
    * */
    @Test
    public void test3() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat();
        Date parse = format.parse("20-1-7 下午9:54");
        System.out.println(parse);
    }


    /**
     * 如何指定解析的格式 -> 用到了SimpleDateFormat另一种构造，带参构造
     *
     *
     */
    @Test
    public void test4() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse = format.parse("2019-02-02 09:12:34");
        System.out.println(parse); // Sat Feb 02 09:12:34 CST 2019
    }

}
