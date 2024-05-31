package week5.lim.Hash.phonebook;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/42577

import java.util.Arrays;

public class phoneBook1 {
    public static boolean solution(String[] phone_book) {
        boolean answer = true;

        Arrays.sort(phone_book);

        for (String str : phone_book) {
            System.out.println(str);
        }

        // 인접한 요소끼리 접두어 관계인지 확인
        for (int i = 0; i < phone_book.length - 1; i++) {
            if (phone_book[i + 1].startsWith(phone_book[i])) {
                return false;
            }
        }

        return answer;
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
