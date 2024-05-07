package week1.lim.Hash.phonebook;

import java.util.Arrays;

public class phonebook {
    public static boolean solution(String[] phone_book) {
        Arrays.sort(phone_book);

        // 정렬된 목록에서 인접한 요소끼리 접두어 관계인지 검사
        for (int i = 0; i < phone_book.length - 1; i++) {
            if (phone_book[i + 1].startsWith(phone_book[i])) {
                return false;
            }
        }

        // 접두어 관계가 없으면 true 반환
        return true;
    }

    public static void main(String[] args) {
        String[] phone_book1 = {"119", "97674223", "1195524421"};
        String[] phone_book2 = {"123","456","789"};
        String[] phone_book3 = {"12","123","1235","567","88"};

        System.out.println(solution(phone_book1)); // false
        System.out.println(solution(phone_book2)); // true
        System.out.println(solution(phone_book3)); // false
    }
}
