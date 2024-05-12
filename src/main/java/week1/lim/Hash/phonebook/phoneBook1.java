package week1.lim.Hash.phonebook;

import java.util.Arrays;

public class phoneBook1 {
    public static boolean solution(String[] phone_book) {
        /*
                문자열 배열을 **사전 순**으로 정렬
                -> 문자열이 숫자처럼 오름차순으로 정렬되는 것이 아니라
                   각 문자열의 각 문자를 순서대로 비교하여 정렬하는 방식
                   -> 각 문자열의 첫 번째 문자부터 비교
                     -> 첫 번째 문자가 같으면 두 번재 문자를 비교하고, 그 다음 문자들도 계속 비교
                       -> 문자열의 한 쪽이 끝날 때까지 또는 서로 다른 문자가 나타날 때까지 비교
                         -> 두 문자열 중 하나가 다른 하나의 접두어로 되어 있고 모든 문자가 같을 때 짧은 문자열이 먼저 온다.

                 "119"는 "1195524421"의 접두어이고
                 또한 "119"는 "97674223" 보다 사전 순으로 먼저 온다 (첫 문자 '1'이 '9'보다 앞서기 때문)
                 "1195524421"은 "97674223"과 비교할 때, 첫 번째 문자 '1'이 '9'보다 앞서므로 앞선다.

         */
        Arrays.sort(phone_book);
        for (String str : phone_book) System.out.println(str);
        /*
            119
            1195524421
            97674223
        */

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
