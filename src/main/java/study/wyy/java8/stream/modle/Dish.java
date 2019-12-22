package study.wyy.java8.stream.modle;

/**
 * @author ：wyy
 * @date ：Created in 2019-12-21 22:00
 * @description：dish 一道菜
 * @modified By：
 * @version: $
 */

public class Dish {

    private final String name;
    /**
     * 是不是素食
     */
    private final Boolean vegetarian;

    /**
     * 卡路里
     */
    private final Integer calories;

    private final Type type;


    /**
     * 构造方法
     * @param name
     * @param vegetarian
     * @param calories
     * @param type
     */
    public Dish(String name, Boolean vegetarian, Integer calories, Type type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }

    public String getName(){
        return name;
    }

    public Boolean isVegetarian(){
        return vegetarian;
    }

    public Integer getCalories(){
        return calories;
    }

    public Type getType(){
        return type;
    }

    public enum Type {MEAT,FISH,OTHER}


}
