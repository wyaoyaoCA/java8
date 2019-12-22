package study.wyy.java8.stream.Test;

import org.junit.Test;
import study.wyy.java8.stream.modle.Trader;
import study.wyy.java8.stream.modle.Transaction;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ：wyy
 * @date ：Created in 2019-12-22 16:51
 * @description：Stream练习
 * @modified By：
 * @version: $
 */
public class StreamExercise {

    private Trader raoul = new Trader("Raoul","Cambridge");
    private Trader mario = new Trader("Mario","Milan");
    private Trader alan = new Trader("Alan","Cambridge");
    private Trader brian = new Trader("Brian","Cambridge");

    private List<Transaction> transactionList = Arrays.asList(
            new Transaction(brian,2011,300),
            new Transaction(raoul,2012,1000),
            new Transaction(raoul,2011,400),
            new Transaction(mario,2012,710),
            new Transaction(mario,2012,700),
            new Transaction(alan,2012,950)
    );

    /**
     * 练习一：获取2011年的交易，并按照value进行排序
     */

    @Test
    public void test1(){
        List<Transaction> result = transactionList.stream()
                // 过滤出2011年的交易
                .filter(transaction -> 2011 == (transaction.getYear()))
                // 进行排序
                //.sorted(Comparator.comparing(transaction -> transaction.getValue()))
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());
        System.out.println(result);
    }

    /**
     * 练习二 获取交易中业务员的工作的城市
     */
    @Test
    public void test2(){
        List<String> result = transactionList.stream()
                // 将交易数据中的城市映射到流中
                .map(transaction -> transaction.getTrader().getCity())
                .distinct().collect(Collectors.toList());
        System.out.println(result);

    }

    /**
     * 练习三：找出在剑桥工作的业务员，按照name排序
     *
     */

    @Test
    public void test3(){
        List<Trader> res = transactionList.stream()
                .filter(transaction -> "Cambridge".equals(transaction.getTrader().getCity()))
                .map(transaction -> transaction.getTrader())
                .distinct()
                .sorted(Comparator.comparing(Trader::getCity))
                .collect(Collectors.toList());

        res.stream().forEach(System.out::println);

    }

    /**
     * 练习四：将业务员的名字拼接成一个字符串,排个序
     */

    @Test
    public void test4(){

        String res = transactionList.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("",(name1, name2) -> name1 + name2);

        System.out.println(res);
    }

    /**
     * 有没有trader在米兰
     */

    @Test
    public void test5(){

        boolean liveInMilan = transactionList.stream()
                .map(transaction -> transaction.getTrader())
                .distinct()
                .anyMatch(trader -> trader.getCity().equals("Milan"));

        System.out.println(liveInMilan==true?"有":"没有");

    }

    /**
     * 练习6 最高的value？
     */
    @Test
    public void test6(){
        OptionalInt max = transactionList.stream()
                .map(Transaction::getValue)
                .mapToInt(i -> i.intValue())
                .max();
        System.out.println(max.getAsInt());

        System.out.println("===========================================");

        Optional<Integer> max2 = transactionList.stream()
                .map(Transaction::getValue)
                //.reduce((i, j) -> i > j ? i : j);
                /*.reduce(new BinaryOperator<Integer>() {
                    @Override
                    public Integer apply(Integer a, Integer b) {
                        return Integer.max(a,b);
                    }
                });*/
                .reduce(Integer::max);
        System.out.println(max2.get());

    }





}
