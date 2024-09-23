package week19.lim.n_67258;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/67258

import java.util.*;

public class Solution {
    public static int[] solution(String[] gems) {
        // 모든 보석의 종류를 Set으로 파악
        Set<String> gemTypes = new HashSet<>(Arrays.asList(gems));
        int totalGems = gemTypes.size(); // 보석의 종류 개수

        // 보석의 구간을 찾기 위한 변수들 (투포인터 사용)
        Map<String, Integer> gemMap = new HashMap<>();
        int start = 0, end = 0;
        int minStart = 0, minLength = Integer.MAX_VALUE;

        while (end < gems.length) {
            // 보석을 맵에 추가 (end 포인터 이동)
            gemMap.put(gems[end], gemMap.getOrDefault(gems[end], 0) + 1);
            end++;

            // 모든 보석 종류를 포함할 경우 start 포인터를 이동하며 최소 구간을 찾는다.
            while (gemMap.size() == totalGems) {
                // 현재 구간이 더 짧다면 최소 구간 갱신
                if (end - start < minLength) {
                    minLength = end - start;
                    minStart = start;
                }

                /*
                    start 포인터를 오른쪽으로 이동하며 구간을 줄인다.
                    모든 보석을 포함하는 상태에서 start 포인터를 오른쪽으로 이동시키며 구간을 줄인다.
                    이때 보석의 개수가 0이 되면 그 보석을 맵에서 제거.
                */
                gemMap.put(gems[start], gemMap.get(gems[start]) - 1);
                if (gemMap.get(gems[start]) == 0) {
                    gemMap.remove(gems[start]);
                }

                start++;
            }
        }

        return new int[] {minStart + 1, minStart + minLength};
    }

    public static void main(String[] args) {
        String[] gems1 = {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"};
        System.out.println(Arrays.toString(solution(gems1))); // [3, 7]

        String[] gems2 = {"AA", "AB", "AC", "AA", "AC"};
        System.out.println(Arrays.toString(solution(gems2))); // [1, 3]

        String[] gems3 = {"XYZ", "XYZ", "XYZ"};
        System.out.println(Arrays.toString(solution(gems3))); // [1, 1]

        String[] gems4 = {"ZZZ", "YYY", "NNNN", "YYY", "BBB"};
        System.out.println(Arrays.toString(solution(gems4))); // [1, 5]
    }
}
