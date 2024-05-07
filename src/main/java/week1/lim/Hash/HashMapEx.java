package week1.lim.Hash;

import java.util.HashMap;

public class HashMapEx {
    public static void main(String[] args) {
        HashMap<String, String> h1 = new HashMap<String, String>();
        HashMap<String, String> h2 = new HashMap<String, String>();

        h1.put("aaa", "1111");
        h1.put("bbb", "2222");
        h1.put("ccc", "3333");
        h1.putIfAbsent("aaa", "0000");
        h1.putIfAbsent("ddd", "4444");
        h2.putAll(h1);
        System.out.println("h1 : " + h1);
        System.out.println("h2 : " + h2);

        System.out.println("[1]: " + h1.containsKey("aaa"));
        System.out.println("[2]: " + h1.containsValue("1111"));
        System.out.println("[3]: " + h1.isEmpty());
        System.out.println("[4]: " + h1.size());
        System.out.println("[5]: " + h1);
        System.out.println("[6]: " + h1.remove("aaa", "1111"));
        System.out.println("[7]: " + h1.put("bbb", "0000"));
        System.out.println("[8]: " + h1.replace("ccc", "0000"));
        System.out.println("h1 : " + h1);
        System.out.println("h2 : " + h2);


        for (String key: h1.keySet()) {
            String value = h1.get(key);
            System.out.println("Key:" + key + ", Value:" + value);
        }
    }

    /*
        h1 : {aaa=1111, ccc=3333, bbb=2222, ddd=4444}
        h2 : {aaa=1111, ccc=3333, bbb=2222, ddd=4444}
        [1]: true
        [2]: true
        [3]: false
        [4]: 4
        [5]: {aaa=1111, ccc=3333, bbb=2222, ddd=4444}
        [6]: true
        [7]: 2222
        [8]: 3333
        h1 : {ccc=0000, bbb=0000, ddd=4444}
        h2 : {aaa=1111, ccc=3333, bbb=2222, ddd=4444}
        Key:ccc, Value:0000
        Key:bbb, Value:0000
        Key:ddd, Value:4444
    */
}
