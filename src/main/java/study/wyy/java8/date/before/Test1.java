package study.wyy.java8.date.before;

/**
 * @author ：wyy
 * @date ：Created in 2020-01-07 20:44
 * @description：System
 * @modified By：
 * @version: $
 */
public class Test1 {

    public static void main(String[] args) {
        // 返回当前时间与1970年1月1日0时0分0秒之间的时间差（毫秒值）
        long timeMillis = System.currentTimeMillis();
        System.out.println(timeMillis);
    }
}
