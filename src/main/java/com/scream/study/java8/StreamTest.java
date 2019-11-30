package com.scream.study.java8;

import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {

    @Data
    static class Person {
        private String name;
        private int age;
        private String sex;

        @Override
        public int hashCode() {
            return this.getSex().equals("男") ? 0 : 1;
        }

        @Override
        public boolean equals(final Object obj) {
            if (obj == null) {
                return false;
            }
            final Person person = (Person) obj;
            if (this == person) {
                return true;
            } else {
                return (this.getSex().equals(person.getSex()));
            }
        }

        public String getKey() {//分组条件
//            return getSex();
            return getSex() + getAge();
        }
    }

    private static List<Person> getPersonList() {
        List<Person> personList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            Person p = new Person();
            p.setName("NO." + (i + 1));
            p.setAge(i + 1);
            p.setSex(random.nextInt(10) > 5 ? "男" : "女");
            personList.add(p);
        }
        return personList;
    }

    /**
     * 自己生产stream
     */
    private static Stream<Double> getStream() {
        Random random = new Random();
        Stream<Double> doubleStream = Stream.generate(random::nextDouble);
        return doubleStream;
    }

    /**
     * 惰性计算
     * 数据处理/转换（intermedia） 操作 map (mapToInt, flatMap 等)、 filter、 distinct、 sorted、 peek、 limit、 skip、 parallel、 sequential、 unordered 等这些操作，在调用方法时并不会立即调用，而是在真正使用的时候才会生效，这样可以让操作延迟到真正需要使用的时刻。
     * <p>
     * 如果没有惰性计算，那么很明显会先输出男，然后输出 "猜猜看我是先执行还是后执行"。而实际的效果是。
     * 猜猜看我是先执行还是后执行
     * 男
     * 男
     * 男
     * 可见 惰性计算 把计算延迟到了真正需要的时候。
     */
    private static void lazyCompute() {
        List<Person> personList = getPersonList();
        Stream<Person> stream = personList.stream().filter(item -> {
            if (item.getSex().equals("男")) {
                System.out.println(item.getSex());
            }
            return item.getSex().equals("男");
        });
        System.out.println("猜猜看我是先执行还是后执行");
        List<Person> afterList = stream.collect(Collectors.toList());
    }

    /**
     * 获取 Stream 流时可以使用 parallelStream 方法代替 stream 方法以获取并行处理流，并行处理可以充分的发挥多核优势，而且不增加编码的复杂性。
     * 下面的代码演示了生成一千万个随机数后，把每个随机数乘以2然后求和时，串行计算和并行计算的耗时差异。
     */
    private static void parallelStream() {
        List<Double> doubleList = getStream().limit(10000000).collect(Collectors.toList());
        long start = System.currentTimeMillis();
        Double sum = doubleList.stream().mapToDouble(item -> item).sum();
        long end = System.currentTimeMillis();
        System.out.println("串行共用时" + (end - start) + "结果：" + sum);
        start = System.currentTimeMillis();
        sum = doubleList.parallelStream().mapToDouble(item -> item).sum();
        end = System.currentTimeMillis();
        System.out.println("并行共用时" + (end - start) + "结果：" + sum);

    }

    public static void main(String[] args) {
        List<Person> personList = getPersonList();
//        personList.stream().filter(item -> item.getSex().equals("男")).forEach(item -> System.out.println(item.getSex()));//过滤

        personList.stream().map(item -> item.getName() + item.getSex() + item.hashCode()).forEach(item -> System.out.println(item));//获取自己想要的数据
//        personList.stream().map(item -> item.getName()).forEach(name -> System.out.println(name));//collectionUtils.collect();

//        Optional<Person> optional = personList.stream().findFirst();//查找第一个元素
//        Optional<Person> optional = personList.stream().filter(person -> person.getSex().equals("男")).findFirst();过滤后查找第一个元素
//        Optional.orElse(new Person())//默认值 Optional.ofNullable(p1).orElse(p2)//优先p1 p1为空就p2
//        System.out.println(optional.orElse(new Person()).getSex());Optional.ofNullable()//赋值

//        personList.stream().distinct().forEach(item -> System.out.println(item));//去重 对象类型要重写equals方法

//        personList.stream().skip(3).forEach(item -> System.out.println(item));//跳过三个
//        personList.stream().limit(3).forEach(item -> System.out.println(item));//只取三个

//        System.out.println(personList.stream().findAny().orElse(new Person()).getName());//今天测试了一下Stream.findAny的结果，发现总是返回列表的第一个元素，结果和findFirst一样，搜了一下发现findAny并不是随机地选一个，如果是数据较少，串行地情况下，一般会返回第一个结果，如果是并行的情况，那就不能确保是第一个。


//        IntSummaryStatistics statics = personList.stream().mapToInt(item -> item.getAge()).summaryStatistics();//IntSummaryStatistics 数学统计功能，求一组数组的最大值、最小值、个数、数据和、平均数等
//        System.out.println(statics.getMin());//最小值
//        System.out.println(statics.getMax());//最大值
//        System.out.println(statics.getSum());//求和
//        System.out.println(statics.getAverage());//平均数
//        System.out.println(statics.getCount());//个数 personList.size()

//        *******************************stream()操作不会对原有集合进行任何更改，可以通过collect(Collectors.toList())返回新的list进行操作
//        List<Person> filterList = personList.stream().filter(item -> item.getSex().equals("女")).collect(Collectors.toList());//通过collect返回新的list


//        Map<String, List<Person>> groupMap = personList.stream().collect(Collectors.groupingBy(Person::getKey)); //groupingBy分组聚合功能，和数据库的 Group by 的功能一致。
//        groupMap.forEach((k, v) -> System.out.println(k + '-' + v));

//        Map<Boolean, List<Person>> sexMap = personList.stream().collect(Collectors.partitioningBy(item -> item.getSex().equals("男")));//partitioningBy按条件分组 分成true false 两个维度
//        System.out.println(sexMap.get(true));
//        System.out.println(sexMap.get(false));

//        lazyCompute();
        parallelStream();

    }


}
