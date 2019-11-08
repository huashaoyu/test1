package com.hsy.guava;

import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.*;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.*;
import com.google.common.io.ByteStreams;
import com.google.common.io.Resources;
import com.google.common.math.IntMath;
import com.google.common.primitives.Bytes;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Shorts;
import org.junit.Test;

import java.io.*;
import java.math.RoundingMode;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class GuavaTest {

    //可选类
    @Test
    public void OptionalTest(){
        Integer value1 = null;
        Integer value2 = new Integer(10);
        Optional<Integer> a = Optional.fromNullable(value1);
        Optional<Integer> b = Optional.of(value2);
        Optional<Integer> c = Optional.fromNullable(value2);
        Optional<Integer> d = a.or(b);
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
        
    }
    
    //前置条件
    @Test
    public void PreconditionsTest(){
        Preconditions.checkArgument(1 < 10, "12345");
        String s = Preconditions.checkNotNull("");
        //Object o = Preconditions.checkNotNull(null);
        ArrayList<String> strings = Preconditions.checkNotNull(new ArrayList<String>());
        System.out.println(strings);
        List<String> a = new ArrayList<String>();
        Preconditions.checkPositionIndex(1,5, "1341234124312");
        Preconditions.checkPositionIndexes(1,5,10);
//        Preconditions.checkArgument(false);
//        Preconditions.checkState(false);
    }
    
    //排序
    @Test
    public void OrderingTest(){
        List<Integer> numbers = new ArrayList<Integer>();
        numbers.add(new Integer(5));
        numbers.add(new Integer(2));
        numbers.add(new Integer(15));
        numbers.add(new Integer(51));
        numbers.add(new Integer(53));
        numbers.add(new Integer(35));
        numbers.add(new Integer(45));
        numbers.add(new Integer(32));
        numbers.add(new Integer(43));
        numbers.add(new Integer(16));

        Ordering ordering = Ordering.natural();
        System.out.println("Input List: ");
        System.out.println(numbers);

        Collections.sort(numbers,ordering);
        System.out.println("Sorted List: ");
        System.out.println(numbers);

        System.out.println("========================");
        System.out.println("List is sorted: " + ordering.isOrdered(numbers));
        System.out.println("Minimum: " + ordering.min(numbers));
        System.out.println("Maximum: " + ordering.max(numbers));
        
        Collections.sort(numbers,ordering.reverse());
        System.out.println("Reverse: " + numbers);
        
        numbers.add(null);
        numbers.add(null);
        System.out.println("Null added to Sorted List: ");
        System.out.println(numbers);
        
        Collections.sort(numbers,ordering.nullsFirst());
        System.out.println("Null first Sorted List: ");
        System.out.println(numbers);
        System.out.println("========================");

        List<String> names = new ArrayList<String>();
        names.add("Ram");
        names.add("Shyam");
        names.add("Mohan");
        names.add("Sohan");
        names.add("Ramesh");
        names.add("Suresh");
        names.add("Naresh");
        names.add("Mahesh");
        names.add(null);
        names.add("Vikas");
        names.add("Deepak");

        System.out.println("Names List: ");
        System.out.println(names);
        
        Collections.sort(names,ordering.nullsFirst().reverse());
        System.out.println("Null first then reverse sorted list: ");
        System.out.println(names);
    }
    
    @Test
    public void ObjectTest(){
        Student s1 = new Student("Mahesh", "Parashar", 1, "VI");
        Student s2 = new Student("Suresh", null, 3, null);
        System.out.println(s1.equals(s2));
        System.out.println(s1.hashCode());
        System.out.println(
                MoreObjects.toStringHelper(s1)
                    .add("Name", s1.getFirstName()+" "+s1.getLastName())
                    .add("Class", s1.getClassName())
                    .add("Roll No", s1.getRollNo())
                    .toString()
        );

        System.out.println(Objects.equal("a", "a"));
        System.out.println(Objects.equal(null,"a"));
        System.out.println(Objects.equal("a",null));
        System.out.println(Objects.equal(null,null));
    }
    
    @Test
    public void RangeTest(){
        Range<Integer> range1 = Range.closed(0, 9);
        System.out.println("[0,9] : ");
        printRange(range1);
        System.out.println("5 is present: " + range1.contains(5));
        System.out.println("(1,2,3) is present: " + range1.containsAll(Ints.asList(1,2,3)));
        System.out.println("Lower Bound: " + range1.lowerEndpoint());
        System.out.println("Upper Bound: " + range1.upperEndpoint());//原文出自【易百教程】，商业转载请联系作者获得授权，非商业请保留原文链接：https://www.yiibai.com/guava/guava_range_class.html#article-start
        
        Range<Integer> range2 = Range.open(0,9);
        System.out.print("(0,9) open : ");
        printRange(range2);
        
        Range<Integer> range3 = Range.openClosed(0,9);
        System.out.print("(0,9) openClosed : ");
        printRange(range3);

        Range<Integer> range4 = Range.closedOpen(0, 9);
        System.out.print("(0,9) closedOpen : ");
        printRange(range4);

        Range<Integer> range5 = Range.greaterThan(9);
        System.out.print("(9,infinity) : ");
        System.out.println("Lower Bound: " + range5.lowerEndpoint());
        System.out.println("Upper Bount: " + range5.hasUpperBound());

        Range<Integer> range6 = Range.closed(3, 5);
        printRange(range6);
        System.out.println("[0,9] encloses [3,5]:" + range1.encloses(range6));

        Range<Integer> range7 = Range.closed(9, 20);
        printRange(range7);

        System.out.println("[0,9] is connected [9,20]:" + range1.isConnected(range7));
        Range<Integer> range8 = Range.closed(5, 15);
        printRange(range1.intersection(range8));
        printRange(range1.span(range8));
        
        RangeMap<Integer,String> rangeMap = TreeRangeMap.create();
        rangeMap.put(Range.closed(1,10),"foo");
        rangeMap.put(Range.open(3, 6), "bar");
        rangeMap.put(Range.open(10, 20), "foo");
        System.out.println(rangeMap);
        rangeMap.remove(Range.closed(5, 11));
        System.out.println(rangeMap);
        
        RangeSet<Integer> rangeSet = TreeRangeSet.create();
        rangeSet.add(Range.closed(1,10));
        rangeSet.add(Range.closedOpen(11, 15));//不相连区间:{[1,10], [11,15)}
        rangeSet.add(Range.closedOpen(15, 20)); //相连区间; {[1,10], [11,20)}
        rangeSet.add(Range.openClosed(0, 0)); //空区间; {[1,10], [11,20)}
        System.out.println(rangeSet);
        rangeSet.remove(Range.open(5, 10)); //分割[1, 10]; {[1,5], [10,10], [11,20)}
        System.out.println(rangeSet);

        System.out.println(Range.closed(1,10));
        System.out.println(Range.closedOpen(11, 15));
        System.out.println(Range.closedOpen(15, 20));
        System.out.println(Range.openClosed(0, 0));
        
        
    }
    
    private void printRange(Range<Integer> range){
        System.out.print("[");
        for(int grade : ContiguousSet.create(range, DiscreteDomain.integers())) {
            System.out.print(grade +" ");
        }
        System.out.println("]");
    }

    
    @Test
    public void ThrowablesTest(){
        try{
            Integer a = null;
            System.out.println(10/a);
        }catch (Exception e){
            System.out.println(e);
            System.out.println(Throwables.getRootCause(e));
        }
    }
    
    @Test
    public void MultisetTest(){
        Multiset<String> multiset = HashMultiset.create();
        multiset.add("a");
        multiset.add("b");
        multiset.add("c");
        multiset.add("d");
        multiset.add("a");
        multiset.add("b");
        multiset.add("c");
        multiset.add("b");
        multiset.add("b");
        multiset.add("b");
        System.out.println("Occurrence of 'b' : "+multiset.count("b"));
        System.out.println("Total Size : "+multiset.size());
        Set<String> set = multiset.elementSet();
        System.out.print("Set [");
        for (String s : set) {
            System.out.print(s + " ");
        }
        System.out.println("]");
        Iterator<String> iterator  = multiset.iterator();
        System.out.print("MultiSet [");
        while(iterator.hasNext()){
            System.out.print(iterator.next() + " ");
        }
        System.out.println("]");
        System.out.println("MultiSet [");
        for (Multiset.Entry<String> entry : multiset.entrySet())
        {
            System.out.println("Element: "+entry.getElement() +", Occurrence(s): " + entry.getCount());
        }
        System.out.println("]");
        multiset.remove("b",2);
        System.out.println("Occurence of 'b' : "+multiset.count("b"));
    }
    
    @Test
    public void BiMap(){
        BiMap<Integer,String> empIDNameMap = HashBiMap.create();
        empIDNameMap.put(new Integer(101),"AAA");
        empIDNameMap.put(new Integer(102),"BBB");
        empIDNameMap.put(new Integer(103),"CCC");
        empIDNameMap.put(new Integer(104),"DDD");
        empIDNameMap.forcePut(new Integer(105), "DDD");
        System.out.println(empIDNameMap);
        System.out.println(empIDNameMap.inverse());
        System.out.println(empIDNameMap.inverse().get("DDD"));
    }

    @Test
    public void BiMap1(){
        BiMap<Integer,Student> empIDNameMap = HashBiMap.create();
        empIDNameMap.put(1,new Student("AA","BB",1,"1234"));
        empIDNameMap.put(2,new Student("AA","BB",1,"1234"));
        System.out.println(empIDNameMap);
    }

    @Test
    public void joinerTest(){
        String s = Joiner.on("-").skipNulls().join(Arrays.asList(1,2,3,4,5,null,6));
        String s1 = Joiner.on("-").useForNull("").join(Arrays.asList(1,2,3,4,5,null,6));
        System.out.println(s);
        System.out.println(s1);
    }
    
    @Test
    public void splitterTest(){
        Iterable<String> s = Splitter.on(",")
                .trimResults()
                .omitEmptyStrings()
                .split("the ,quick, , brown         , fox,              jumps, over, the, lazy, little dog.");
        System.out.println(s);

        System.out.println(Splitter.on(",").trimResults().omitEmptyStrings().split("a a, , a a"));
        System.out.println(Splitter.on(",").trimResults().split("a a, , a a"));
    }
    
    @Test
    public void caseFormatTest(){
        String data = "test_data";
        System.out.println(CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_CAMEL,"test-data"));
        System.out.println(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL,"test_data"));
        System.out.println(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL,"test_data"));
    }
    
    @Test
    public void BytesTest(){
        byte[] byteArray = {1,2,3,4,5,5,7,9,9};
        List<Byte> objectArray = Bytes.asList(byteArray);
        System.out.println(objectArray.toString());

        byteArray = Bytes.toArray(objectArray);
        System.out.print("[ ");
        for(int i = 0; i< byteArray.length ; i++){
            System.out.print(byteArray[i] + " ");
        }
        System.out.println("]");

        byte data = 9;
        System.out.println("5 is in list? "+ Bytes.contains(byteArray, data));
        System.out.println("Index of 9: " + Bytes.indexOf(byteArray,data));
        System.out.println("Last index of 9: " + Bytes.lastIndexOf(byteArray,data));
    }
    
    @Test
    public void shortsTest(){
        short[] shortArray = {1,2,3,4,5,6,7,8,9};
        List<Short> objectArray = Shorts.asList(shortArray);
        System.out.println(objectArray.toString());

        shortArray = Shorts.toArray(objectArray);
        System.out.print("[ ");
        for(int i = 0; i< shortArray.length ; i++){
            System.out.print(shortArray[i] + " ");
        }
        System.out.println("]");
        
        short data = 5;
        System.out.println("5 is in list? " + Shorts.contains(shortArray,data));
        System.out.println("Min: " + Shorts.min(shortArray));
        System.out.println("Max: " + Shorts.max(shortArray));
        data = 2400;
        byte[] byteArray = Shorts.toByteArray(data);
        for (int i = 0; i < byteArray.length; i++) {
            System.out.println(byteArray[i]);
        }
    }
    
    @Test
    public void intTest(){
        try{
            System.out.println(IntMath.checkedAdd(Integer.MAX_VALUE, Integer.MAX_VALUE));
        }catch(ArithmeticException e){
            System.out.println(e.getMessage());
            System.out.println("Error: " + Throwables.getStackTraceAsString(e));
            System.out.println("Error: " + Throwables.getRootCause(e));
        }
        System.out.println(IntMath.divide(100, 5, RoundingMode.UNNECESSARY));
        try{
            System.out.println(IntMath.divide(100, 3, RoundingMode.UNNECESSARY));
        }catch(ArithmeticException e){
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("Log2(2): "+IntMath.log2(2, RoundingMode.HALF_EVEN));
        System.out.println("Log10(10): "+ IntMath.log10(10, RoundingMode.HALF_EVEN));
        System.out.println("sqrt(100): "+IntMath.sqrt(IntMath.pow(10,2), RoundingMode.HALF_EVEN));
        System.out.println("gcd(100,50): "+IntMath.gcd(100,50));
        System.out.println("modulus(100,50): "+IntMath.mod(100,50));
        System.out.println("factorial(5): "+IntMath.factorial(5));
    }
    
    @Test
    public void multimapTest(){
        Multimap<String,String> multimap = ArrayListMultimap.create();
        multimap.put("lower", "a");
        multimap.put("lower", "b");
        multimap.put("lower", "c");
        multimap.put("lower", "d");
        multimap.put("lower", "e");
        multimap.put("lower", "e");
        multimap.put("upper", "A");
        multimap.put("upper", "B");
        multimap.put("upper", "C");
        multimap.put("upper", "D");
        multimap.put("upper", "D");

        List<String> lowerList = (List<String>)multimap.get("lower");
        System.out.println(lowerList.toString());
        lowerList.add("f");
        System.out.println(lowerList.toString());

        List<String> upperList = (List<String>)multimap.get("upper");
        System.out.println("Initial upper case list");
        System.out.println(upperList.toString());
        upperList.remove("D");
        System.out.println("Modified upper case list");
        System.out.println(upperList.toString());

        Map<String, Collection<String>> map = multimap.asMap();
        System.out.println("Multimap as a map");
        for (Map.Entry<String,  Collection<String>> entry : map.entrySet()) {
            String key = entry.getKey();
            Collection<String> value =  multimap.get(key);
            System.out.println(key + ":" + value);
        }

        Set<String> keys =  multimap.keySet();
        for(String key:keys){
            System.out.println(key);
        }

        Collection<String> values = multimap.values();
        System.out.println(values);

        System.out.println(multimap.size());
        System.out.println(multimap.containsEntry("lower","a"));
        System.out.println(multimap.containsEntry("lower","D"));
        System.out.println(multimap.containsKey("lower"));
        System.out.println(multimap.containsKey("a"));
    }
    
    @Test
    public void loadingCacheTest(){
        LoadingCache<String, Employee> employeeLoadingCache =
                CacheBuilder.newBuilder()
                        .maximumSize(100)
                        .expireAfterAccess(30, TimeUnit.MINUTES)
                .build(new CacheLoader<String, Employee>() {
                    @Override
                    public Employee load(String key) throws Exception {
                        return null;
                    }
                });
    }
    
    @Test
    public void iOTest(){
//        String path = this.getClass().getResource("").getPath();
//        File file = new File(path + "Employee.class");
        URL resource = Resources.getResource("Employee.class");
        File f = new File(resource.getFile());
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            OutputStream outputStream = new FileOutputStream("11.txt");
            ByteStreams.copy(inputStream,outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void resourcesTest(){
        System.out.println(Resources.getResource("application.yml"));
        System.out.println(Resources.getResource(Employee.class,"application.yml"));
    }
    
    @Test
    public void test1(){
        List<Integer> intList = Ints.asList(5, 2, 7, 1, 3, 3);
        int[] intArray = {5, 2, 7, 1, 3, 3};
        System.out.println(Ints.min(intArray));
        System.out.println(Ints.max(intArray));
    }
    
    @Test
    public void linkedHashSetTest(){
        List<Integer> classTime = new ArrayList<Integer>();
        classTime.add(1);
        classTime.add(11);
        classTime.add(111);
        classTime.add(16);
        classTime.add(12);
        classTime.add(1);
        classTime.add(1);
        classTime.add(1);
        classTime = new ArrayList<Integer>(new LinkedHashSet<Integer>(classTime));//去重保持当前排序
        System.out.println(classTime);
    }
    
    @Test
    public void test(){
        /*List<String> list = new ArrayList<String>();
        list.add("abc");
        list.add("bbc");
        list.add("cbc");
        Iterator<String> it = list.iterator();
        while(it.hasNext()){
            String str = it.next();
            System.out.println(str);
            if(str.equals("abc")){
                list.remove(str);
            }
        }
*/

        List<String> list = new ArrayList<String>();
        list.add("abc");
        list.add("bbc");
        list.add("cbc");
        Iterator<String> it = list.iterator();
        while(it.hasNext()){
            String str = it.next();
            if(str.equals("abc")){
                it.remove();
            }
        }
        System.out.println(list);
    }
    
    @Test
    public void test11(){
        Map<String,String> map = new HashMap<String, String>();
        map.put("1","1");
        map.put("2","2");
        map.put("3","3");
        map.put("4","4");
        // 传统迭代方式
        System.out.println("1111111111");
        for (Map.Entry<String,String> entry : map.entrySet()){
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
        // JDK8迭代方式
        System.out.println("22222222222");
        map.forEach((key, value) -> {
            System.out.println(key + ":" + value);
        });
        Consumer<String> con = (x) -> System.out.println(x);
        con.accept("有一个参数，无返回值的用法（Consumer函数式接口）");
    }
    
    @Test
    public void test12() throws InterruptedException {
        Date date1 = new Date();
        Thread.sleep(1000);
        Date date2 = new Date();
        System.out.println(date1);
        System.out.println(date2);
        System.out.println(date1.before(date2));
    }

}