import java.util.HashMap;

/**
 * @author Chris Chen
 * @date 2019/5/9 下午9:03
 */
public class HashMapTest {
    public static void main(String[] args) {
        HashMap scores = new HashMap();
        scores.put("Chinese", 80);
        scores.put("English", 90);
        scores.put(null, 0);
        scores.forEach((key, value) ->{
            System.out.println(key + ": "+ value);
        });
        /*
        比较标准，equals(), hashCode()
         */
        boolean ans = scores.containsKey(null);
        System.out.println(ans);
        /*
        比较标准，equals()
         */
        ans = scores.containsValue(80);
        System.out.println(ans);
    }

}
/*
输出的顺序可能和输入的不一样，key为null的key-value最多只能有一个
null: 0
English: 90
Chinese: 80
 */
