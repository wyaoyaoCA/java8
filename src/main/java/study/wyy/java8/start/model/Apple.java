package study.wyy.java8.start.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：wyy
 * @date ：Created in 2019-12-21 09:15
 * @description：
 * @modified By：
 * @version: $
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Apple {

    /**
     * 颜色
     */
    private String colol;

    /**
     * 重量
     */
    private Long weight;
}
