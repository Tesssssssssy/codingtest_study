package week18.lim.n_72411.practice;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/72411

import java.util.*;

public class Solution {
    public static String[] solution(String[] orders, int[] course) {
        // 결과 담을 리스트
        List<String> result = new ArrayList<>();

        for (int size : course) {
            Map<String, Integer> combinations = new HashMap<>();

            // 모든 주문에 대한 조합 생성
            for (String order : orders) {
                char[] items = order.toCharArray();
                Arrays.sort(items);
                findCombinations(items, size,0, "", combinations);
            }

            // 가장 많이 주문된 조합 찾기
            int maxOrder = 2;
            for (int cnt : combinations.values()) {
                maxOrder = Math.max(maxOrder, cnt);
            }

            // 가장 많이 주문된 조합 리스트에 추가
            for (Map.Entry<String, Integer> combi : combinations.entrySet()) {
                if (combi.getValue() == maxOrder) {
                    result.add(combi.getKey());
                }
            }
        }

        Collections.sort(result);
        return result.toArray(new String[0]);
    }


    private static void findCombinations(char[] items, int size, int start, String current,
                                         Map<String, Integer> combinations) {
        if (current.length() == size) {
            combinations.put(current, combinations.getOrDefault(current, 0) + 1);
            return;
        }

        for (int i = start; i < items.length; i++) {
            findCombinations(items, size, i + 1, current + items[i], combinations);
        }
    }

    public static void main(String[] args) {
        String[] orders1 = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
        int[] course1 = {2, 3, 4};
        System.out.println(Arrays.toString(solution(orders1, course1)));
        // ["AC", "ACDE", "BCFG", "CDE"]

        String[] orders2 = {"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"};
        int[] course2 = {2, 3, 5};
        System.out.println(Arrays.toString(solution(orders2, course2)));
        // ["ACD", "AD", "ADE", "CD", "XYZ"]

        String[] orders3 = {"XYZ", "XWY", "WXA"};
        int[] course3 = {2, 3, 4};
        System.out.println(Arrays.toString(solution(orders3, course3)));
        // ["WX", "XY"]
    }
}