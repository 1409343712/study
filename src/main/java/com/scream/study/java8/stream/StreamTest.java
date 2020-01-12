package com.scream.study.java8.stream;

import lombok.Data;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {

	private List<Person> personList = new ArrayList<>();

	@Before
	public void fillPersonList() {
		Person p1 = new Person("lucy", "女", 18, 90);
		Person p2 = new Person("lily", "女", 15, 59);
		Person p3 = new Person("lilei", "男", 14, 61);
		Person p4 = new Person("mingming", "男", 17, 81);
		Person p5 = new Person("hanmeimei", "女", 16, 74);
		personList.add(p1);
		personList.add(p2);
		personList.add(p3);
		personList.add(p4);
		personList.add(p5);
	}

	@Test
	public void forEach() {
		personList.stream().forEach(System.out::println);
	}

	@Test
	public void limit() {
		personList.stream().limit(1).forEach(System.out::println);
	}

	@Test
	public void skip() {
		personList.stream().skip(4).forEach(System.out::println);
	}

	@Test
	public void filter() {
		personList.stream().filter(item -> item.getScore() < 60).forEach(System.out::println);
	}

	@Test
	public void sorted() {
		personList.stream().sorted(Comparator.comparing(Person::getScore)).forEach(System.out::println);
	}

	/**
	 * 处理嵌套循环类似List<List<Integer>>
	 */
	@Test
	public void flatMap() {
		List<List<Integer>> numList = new ArrayList<>();
		numList.add(Arrays.asList(1,2,3,4,5));
		numList.add(Arrays.asList(1,2,3,4,5));
		numList.add(Arrays.asList(1,2,3,4,5));
		numList.add(Arrays.asList(1,2,3,4,5));
		Stream<Integer> numstream = numList.stream().flatMap(Collection::stream);
		System.out.println(numstream.max(Comparator.naturalOrder()).get());//最大
	}

	@Test
	public void max() {
		Person person = personList.stream().max(Comparator.comparing(Person::getScore)).get();
		System.out.println(person);
	}

	@Test
	public void min() {
		Person person = personList.stream().min(Comparator.comparing(Person::getScore)).get();
		System.out.println(person);
	}

	@Test
	public void count() {
		long size = personList.stream().count();
		System.out.println(size);
	}

	@Test
	public void anyMatch() {
		boolean anyMatch = personList.stream().anyMatch(item -> item.getScore() > 100);
		System.out.println(anyMatch);
	}

	@Test
	public void allMatch() {
		boolean anyMatch = personList.stream().allMatch(item -> item.getScore() > 50);
		System.out.println(anyMatch);
	}

	@Test
	public void noneMatch() {
		boolean anyMatch = personList.stream().noneMatch(item -> item.getScore() > 100);
		System.out.println(anyMatch);
	}

	@Test
	public void findAny() {
		//今天测试了一下Stream.findAny的结果，发现总是返回列表的第一个元素，结果和findFirst一样，搜了一下发现findAny并不是随机地选一个，如果是数据较少，串行地情况下，一般会返回第一个结果，如果是并行的情况，那就不能确保是第一个。
		System.out.println(personList.stream().findAny().orElse(new Person()).getName());
	}

	/**
	 * IntSummaryStatistics 数学统计功能，求一组数组的最大值、最小值、个数、数据和、平均数等
	 */
	@Test
	public void summaryStatics() {
		IntSummaryStatistics statics = personList.stream().mapToInt(Person::getScore).summaryStatistics();
		System.out.println(statics.getMin());//最小值
		System.out.println(statics.getMax());//最大值
		System.out.println(statics.getSum());//求和
		System.out.println(statics.getAverage());//平均数
		System.out.println(statics.getCount());//个数 personList.size()
	}

	@Test
	public void collect() {
		List<Person> filterList = personList.stream().filter(item -> item.getSex().equals("女")).collect(Collectors.toList());//通过collect返回新的list
		System.out.println(filterList);
		Map<String, List<Person>> sexMap = personList.stream().collect(Collectors.groupingBy(Person::getSex)); //groupingBy分组聚合功能，和数据库的 Group by 的功能一致。
		System.out.println(sexMap);
		Map<Boolean, List<Person>> partitionMap = personList.stream().collect(Collectors.partitioningBy(item -> item.getSex().equals("男")));//partitioningBy按条件分组 分成true false 两个维度
		System.out.println(partitionMap);
		Set<String> sexSet = personList.stream().map(Person::getSex).collect(Collectors.toSet());////通过collect返回新的set
		System.out.println(sexSet);
		String names = personList.stream().map(Person::getName).collect(Collectors.joining());//获取所以有人姓名
		System.out.println(names);

	}
	/**
	 * 惰性计算
	 * 数据处理/转换（intermedia） 操作 map (mapToInt, flatMap 等)、 filter、 distinct、 sorted、 peek、 limit、 skip、 parallel、 sequential、 unordered 等这些操作，在调用方法时并不会立即调用，而是在真正使用的时候才会生效，这样可以让操作延迟到真正需要使用的时刻。
	 * <p>
	 * 如果没有惰性计算，那么很明显会先输出男，然后输出 "猜猜看我是先执行还是后执行"。而实际的效果是。
	 * 猜猜看我是先执行还是后执行
	 * 男
	 * 男
	 * 可见 惰性计算 把计算延迟到了真正需要的时候。
	 */
	@Test
	public void lazyCompute() {
		Stream<Person> stream = personList.stream().filter(item -> {
			if (item.getSex().equals("男")) {
				System.out.println(item.getSex());
			}
			return item.getSex().equals("男");
		});
		System.out.println("猜猜看我是先执行还是后执行?");
		List<Person> afterList = stream.collect(Collectors.toList());
	}
	/**
	 * 获取 Stream 流时可以使用 parallelStream 方法代替 stream 方法以获取并行处理流，并行处理可以充分的发挥多核优势，而且不增加编码的复杂性。
	 * 下面的代码演示了生成一千万个随机数后，把每个随机数乘以2然后求和时，串行计算和并行计算的耗时差异。
	 */
	@Test
	public void parallelStream() {
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
	/**
	 * 自己生产stream
	 */
	private static Stream<Double> getStream() {
		String[] arrays = {"1","2","3","4","5"};
		Arrays.stream(arrays);
		Random random = new Random();
		Stream<Double> doubleStream = Stream.generate(random::nextDouble);
		return doubleStream;
	}
	@Data
	class Person {
		private String name;
		private String sex;
		private Integer age;
		private Integer score;

		public Person() {

		}

		public Person(String name, String sex, Integer age, Integer score) {
			this.name = name;
			this.sex = sex;
			this.age = age;
			this.score = score;
		}
	}

}
