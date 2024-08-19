package week15.lim.programmers_Lv2.n_131127.practice;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/131127

import java.util.*;

public class Solution {
    /**
     *  일정한 금액을 지불하면 10일 동안 회원 자격을 부여
     *  회원을 대상으로 매일 한 가지 제품을 할인하는 행사
     *  할인하는 제품은 하루에 하나씩만 구매할 수 있습니다
     *  자신이 원하는 제품과 수량이 할인하는 날짜와 10일 연속으로 일치할 경우에 맞춰서 회원가입을 하려 함.
     *
     *  예를 들어, 정현이가 원하는 제품이 바나나 3개, 사과 2개, 쌀 2개, 돼지고기 2개, 냄비 1개이며,
     *  XYZ 마트에서 14일간 회원을 대상으로 할인하는 제품이 날짜 순서대로
     *  치킨, 사과, 사과, 바나나, 쌀, 사과, 돼지고기, 바나나, 돼지고기, 쌀, 냄비, 바나나, 사과, 바나나인 경우에 대해 알아봅시다.
     *  첫째 날부터 열흘 간에는 냄비가 할인하지 않기 때문에 첫째 날에는 회원가입을 하지 않습니다.
     *  둘째 날부터 열흘 간에는 바나나를 원하는 만큼 할인구매할 수 없기 때문에 둘째 날에도 회원가입을 하지 않습니다.
     *  셋째 날, 넷째 날, 다섯째 날부터 각각 열흘은 원하는 제품과 수량이 일치하기 때문에 셋 중 하루에 회원가입을 하려 합니다.
     *
     *  회원등록시 정현이가 원하는 제품을 모두 할인 받을 수 있는
     *  회원등록 날짜의 총 일수를 return 하는 solution 함수를 완성하시오.
     *  가능한 날이 없으면 0을 return
     *
     *  [풀이]
     *  시간 복잡도는 O(n), 공간 복잡도는 O(m) (n은 discount 배열의 길이, m은 want 배열의 길이).
     *  매우 효율적
     */
    public static int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;

        // 원하는 제품의 품목과 개수 저장
        Map<String, Integer> wantMap = new HashMap<>();
        for (int i = 0; i < want.length; i++) {
            wantMap.put(want[i], number[i]);
        }

        // 현재 확인하고 있는 10일의 할인 품목과 개수 저장
        Map<String, Integer> currentMap = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            currentMap.put(discount[i], currentMap.getOrDefault(discount[i], 0) + 1);
        }

        // currentMap에 저장되어 있는 초기 10일과 wantMap 비교
        if (checkMap(wantMap, currentMap)) {
            answer++;
        }

        // 슬라이딩 윈도우로 이후의 10일 확인
        for (int i = 10; i < discount.length; i++) {
            // 맨 앞 제품 제거
            String oldProduct = discount[i - 10];
            currentMap.put(oldProduct, currentMap.get(oldProduct) - 1);
            if (currentMap.get(oldProduct) == 0) {
                currentMap.remove(oldProduct);
            }

            // 새 제품 추가
            String newProduct = discount[i];
            currentMap.put(newProduct, currentMap.getOrDefault(newProduct, 0) + 1);

            // 새 제품부터 다음 10일 비교
            if (checkMap(wantMap, currentMap)) {
                answer++;
            }
        }

        return answer;
    }

    private static boolean checkMap(Map<String, Integer> wantMap, Map<String, Integer> currentMap) {
        for (String key : wantMap.keySet()) {
            if (!currentMap.containsKey(key) || !currentMap.get(key).equals(wantMap.get(key))) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        String[] want1 = {"banana", "apple", "rice", "pork", "pot"};
        int[] number1 = {3, 2, 2, 2, 1};
        String[] discount1 = {"chicken", "apple", "apple", "banana", "rice", "apple",
                "pork", "banana", "pork", "rice", "pot", "banana", "apple", "banana"};

        System.out.println(solution(want1, number1, discount1)); // 3

        String[] want2 = {"apple"};
        int[] number2 = {10};
        String[] discount2 = {"banana", "banana", "banana", "banana", "banana",
                "banana", "banana", "banana", "banana", "banana"};

        System.out.println(solution(want2, number2, discount2)); // 0
    }
}