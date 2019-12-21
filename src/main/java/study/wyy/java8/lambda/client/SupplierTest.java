package study.wyy.java8.lambda.client;

import org.junit.Test;
import study.wyy.java8.lambda.model.Apple;

import java.util.function.Supplier;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author ：wyy
 * @date ：Created in 2019-12-21 11:28
 * @description：supplier演示
 * @modified By：
 * @version: $
 */

public class SupplierTest {


    @Test
    public void testCreateApple(){
        Apple apple = createApple(() -> new Apple("red", 140L));
        assertThat(apple,notNullValue());
        assertThat(apple.getColol(),equalTo("red"));
        assertThat(apple.getWeight(),equalTo(140L));

    }


    private Apple createApple(Supplier<Apple> supplier){
        return supplier.get();
    }

}
