package week1.lim.Hash.phonebook;

import java.util.HashMap;
import java.util.Map;

public class phoneBook2 {
    public static boolean solution(String[] phone_book) {
        HashMap<String, Integer> map = new HashMap<>();

        for (String number : phone_book) {
            map.put(number, 1);
        }

        for (Map.Entry<String, Integer> entrySet : map.entrySet()) {
            System.out.println(entrySet.getKey() + " : " + entrySet.getValue());
        }

        for (String number : phone_book) {
            for (int i = 1; i < number.length(); i++) {
                if (map.containsKey(number.substring(0, i))) {
                    System.out.println(map.containsKey(number.substring(0, i)) + ", " + i);
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        String[] phone_book1 = {"119", "97674223", "1195524421"};
        // false
        System.out.println(solution(phone_book1));

        String[] phone_book2 = {"123", "456", "789"};
        // true
        System.out.println(solution(phone_book2));

        String[] phone_book3 = {"12", "123", "1235", "567", "88"};
        // false
        System.out.println(solution(phone_book3));
    }
}
