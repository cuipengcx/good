package com.good.base;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @package: com.jk
 * @description: Lambda相关练习
 * @author: cuiP
 * @date: 2018/2/5 15:20
 * @version: V1.0.0
 */
public class LambdaTest {


    /**
     * 编写一个求和函数，计算流中所有数的和。例int addUp(Stream numbers)
     * reduce的第一个参数表示初始值为0；
     * reduce的第二个参数为需要进行的归约操作，它接收一个拥有两个参数的Lambda表达式，以上代码acc参数代表当前的数值总和,element代表下一个元素，reduce会把流中的元素两两输给Lambda表达式，最后将计算出累加之和。
     * 也就是说每次acc+element的返回值都会赋给acc
     * 在上述求和例子中，计算过程是这样的 初始值为0
     *  0 + 1 = 1
     *  1 + 2 = 3
     *  3 + 3 = 6
     * 以上三行就是 acc + elment = acc ,其中acc的初始值为reduce的第一个参数(在本例中初始值为0)
     *
     * 上面的方法中我们自己定义了Lambda表达式实现求和运算，如果当前流的元素为数值类型，那么可以使用Integer提供了sum函数代替自定义的Lambda表达式，如：
     * int age = list.stream().reduce(0, Integer::sum);
     * Integer类还提供了min、max等一系列数值操作，当流中元素为数值类型时可以直接使用。
     *
     * 注: 上面的Integer::sum如果不理解的话，这是java8中引用的方法，是一种简写语法，属于语法糖。
     * 一般格式为类名(或者是类的实例对象) :: 方法名（注意这里只是方法名，没有括号）,这里引用了Integer里的sum函数(java8里新增的)，下面是Integer里的sum函数源码
     */
    @Test
    public void addUp(){
        Integer sum = Stream.of(1, 2, 3).reduce(0, Integer::sum);
        System.out.println(sum);
    }

    /**
     * flatMap 聚合流: 将多个小流汇聚成大流
     */
    @Test
    public void testFlatMap(){
        List <Integer> together = Stream.of(Arrays.asList(1,2),Arrays.asList(3,4))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        together.forEach(System.out::println);
    }

    /**
     * 利用stream().forEach()循环处理List;
     */
    @Test
    public void testForEach(){
        List<Long> subDeptList = new ArrayList<>();
        subDeptList.add(1L);
        subDeptList.add(2L);
        subDeptList.add(3L);

        subDeptList.forEach(System.out::println);
    }

    /**
     * lambda对list的复制
     */
    @Test
    public void filterListTest(){
        List<Long> subDeptList = new ArrayList<>();
        subDeptList.add(1L);
        subDeptList.add(2L);
        subDeptList.add(3L);

        List<Long> ids = subDeptList.stream().map(id -> id).collect(Collectors.toList());
        System.out.println(ids.toString());
    }

    /**
     * lambda对list的筛选过滤
     */
    @Test
    public void copyListTest(){
        List<Long> subDeptList = new ArrayList<>();
        subDeptList.add(1L);
        subDeptList.add(2L);
        subDeptList.add(3L);

        List<Long> ids = subDeptList.stream().filter(id -> id < 3).collect(Collectors.toList());
        System.out.println(ids.toString());
    }
}
