package week15.lim.programmers_Lv2.n_17677.practice;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/17677

import java.util.HashMap;
import java.util.Map;

public class Solution {
    /**
     *  자카드 유사도는 집합 간의 유사도를 검사하는 여러 방법 중의 하나로 알려져 있다.
     *  두 집합 A, B 사이의 자카드 유사도 J(A, B)는 두 집합의 교집합 크기를 두 집합의 합집합 크기로 나눈 값으로 정의된다.
     *
     *  예를 들어 집합 A = {1, 2, 3}, 집합 B = {2, 3, 4}라고 할 때,
     *  교집합 A ∩ B = {2, 3}, 합집합 A ∪ B = {1, 2, 3, 4}이 되므로,
     *  집합 A, B 사이의 자카드 유사도 J(A, B) = 2/4 = 0.5가 된다.
     *  집합 A와 집합 B가 모두 공집합일 경우에는 나눗셈이 정의되지 않으니 따로 J(A, B) = 1로 정의한다.
     *
     *  자카드 유사도는 원소의 중복을 허용하는 다중집합에 대해서 확장할 수 있다.
     *  다중집합 A는 원소 "1"을 3개 가지고 있고, 다중집합 B는 원소 "1"을 5개 가지고 있다고 하자.
     *  이 다중집합의 교집합 A ∩ B는 원소 "1"을 min(3, 5)인 3개, 합집합 A ∪ B는 원소 "1"을 max(3, 5)인 5개 가지게 된다.
     *  다중집합 A = {1, 1, 2, 2, 3}, 다중집합 B = {1, 2, 2, 4, 5}라고 하면,
     *  교집합 A ∩ B = {1, 2, 2}, 합집합 A ∪ B = {1, 1, 2, 2, 3, 4, 5}가 되므로,
     *  자카드 유사도 J(A, B) = 3/7, 약 0.42가 된다.
     *
     *  이를 이용하여 문자열 사이의 유사도를 계산하는데 이용할 수 있다.
     *  문자열 "FRANCE"와 "FRENCH"가 주어졌을 때, 이를 두 글자씩 끊어서 다중집합을 만들 수 있다.
     *  각각 {FR, RA, AN, NC, CE}, {FR, RE, EN, NC, CH}가 되며,
     *  교집합은 {FR, NC}, 합집합은 {FR, RA, AN, NC, CE, RE, EN, CH}가 되므로,
     *  두 문자열 사이의 자카드 유사도 J("FRANCE", "FRENCH") = 2/8 = 0.25가 된다.
     *
     *  [출력]
     *  입력으로 들어온 두 문자열의 자카드 유사도를 출력한다.
     *  유사도 값은 0에서 1 사이의 실수이므로, 이를 다루기 쉽도록 65536을 곱한 후에 소수점 아래를 버리고 정수부만 출력한다.
     */
    public static int solution(String str1, String str2) {
        Map<String, Integer> map1 = makeMultiSet(str1);
        Map<String, Integer> map2 = makeMultiSet(str2);

        int intersection = 0;
        int union = 0;

        for (String key : map1.keySet()) {
            if (map2.containsKey(key)) {
                intersection += Math.min(map1.get(key), map2.get(key));
            }
            union += map1.get(key);
        }

        for (String key : map2.keySet()) {
            union += map2.get(key);
        }

        union -= intersection;

        double jaccard = union == 0 ? 1.0 : (double) intersection / union;

        return (int) (jaccard * 65536);
    }

    private static Map<String, Integer> makeMultiSet(String str) {
        Map<String, Integer> multiSet = new HashMap<>();
        str = str.toLowerCase();

        for (int i = 0; i < str.length() - 1; i++) {
            String pair = str.substring(i, i + 2);
            if (pair.matches("[a-z]{2}")) {
                multiSet.put(pair, multiSet.getOrDefault(pair, 0) + 1);
            }
        }

        return multiSet;
    }

    public static void main(String[] args) {
        String str1_1 = "FRANCE";
        String str2_1 = "french";
        System.out.println(solution(str1_1, str2_1));

        String str1_2 = "handshake";
        String str2_2 = "shake hands";
        System.out.println(solution(str1_2, str2_2));

        String str1_3 = "aa1+aa2";
        String str2_3 = "AAAA12";
        System.out.println(solution(str1_3, str2_3));

        String str1_4 = "E=M*C^2";
        String str2_4 = "e=m*c^2";
        System.out.println(solution(str1_4, str2_4));
    }
}
