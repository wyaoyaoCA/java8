package study.wyy.java8.stream.modle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * @author ：wyy
 * @date ：Created in 2019-12-22 16:54
 * @description：交易
 * @modified By：
 * @version: $
 */
@AllArgsConstructor
@Data
@ToString
public class Transaction {

    private final Trader trader;
    private final int year;
    private final int value;
}
