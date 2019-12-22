package study.wyy.java8.stream.Test;

import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author ：wyy
 * @date ：Created in 2019-12-22 08:52
 * @description：创建Stream
 * @modified By：
 * @version: $
 */
public class CreateStreamDemo {


    /**
     * 1 通过Collections创建
     *     通过集合的stream()方法
     */
    @Test
    public void testCreateStreamFromCollections(){
        List<String> strings = Arrays.asList("html", "java", "pyhton", "php");
        Stream<String> stream = strings.stream();
        // lambda
        /*stream.forEach((codeLanguage) -> {
            System.out.println(codeLanguage);
        });*/

        // 简化的lambda
        // stream.forEach(codelanguage -> System.out.println(codelanguage));

        // 方法引用 这里可以使用方法引用，lambda中的功能已经实现了，并且函数式接口的抽象方法的的接收的形参也和我们的调用
        // 的方法一样
        stream.forEach(System.out::println);

    }

    /**
     * 2 通过Values创建
     *
     */
    @Test
    public void testCreateStreamFromValues(){
       Stream<String> stream = Stream.of("html", "java", "pyhton", "php");
        stream.forEach(System.out::println);
    }

    /**
     * 3 通过Arrays创建
     */
    @Test
    public void testCreateStreamFromArrays(){
        Stream<String> stream = Arrays.stream(new String[]{"html", "java", "pyhton", "php"});
        stream.forEach(System.out::println);
    }

    /**study/wyy/java8/stream/Test/fileTest.txt
     * 4 通过文件
     */
    @Test
    public void testCreateStreamFromFile(){
        Path path = Paths.get("/Users/wyaoyao/ideaWorkSpace/java8/src/main/java/study/wyy/java8/stream/Test/fileTest.txt");
        try {
            Stream<String> stream = Files.lines(path);
            System.out.println(stream);
            System.out.println("===========================");
            stream.forEach(s -> System.out.println(s));
        }catch (Exception e){

        }
    }

    /**
     * 5 通过迭代器
     */
    @Test
    public void testCreateStreamFromIterator(){
        // 接收一个T，返回一个T
//        UnaryOperator s = new UnaryOperator<Integer>(){
//            @Override
//            public Integer apply(Integer integer) {
//                return null;
//            }
//        };

        Stream<Integer> stream = Stream.iterate(0, (i) -> {
            return i + 1;
        });
        stream.limit(10).forEach(System.out::println);
    }

    /**
     * 6 通过Generate
     */
    @Test
    public void testCreateStreamFromGenerate(){

       // Stream<Double> stream = Stream.generate(() -> {return Math.random();});
        Stream<Double> stream = Stream.generate(Math::random);
        stream.limit(10).forEach(System.out::println);
    }

}
