package week15.lim.programmers_Lv2.n_132265;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/132265

import java.util.*;

public class Solution {
    /**
     *  롤케이크를 두 조각으로 잘라서 동생과 한 조각씩 나눠 먹으려고 합니다.
     *  롤케이크에는 여러가지 토핑들이 일렬로 올려져
     *  철수와 동생은 롤케이크를 공평하게 나눠먹으려 하는데,
     *  그들은 롤케이크의 크기보다 롤케이크 위에 올려진 토핑들의 종류에 더 관심이 많습니다.
     *  잘린 조각들의 크기와 올려진 토핑의 개수에 상관없이
     *  각 조각에 동일한 가짓수의 토핑이 올라가면 공평하게 롤케이크가 나누어진 것으로 생각합니다.
     *
     *  롤케이크에 올려진 토핑들의 번호를 저장한 정수 배열 topping이 매개변수로 주어질 때,
     *  롤케이크를 공평하게 자르는 방법의 수를 return.
     */
    public static int solution(int[] topping) {
        int answer = 0;

        // 첫 번째 조각과 두 번째 조각의 토핑 종류를 추적하기 위한 Set
        Set<Integer> set1 = new HashSet<>();
        Map<Integer, Integer> map2 = new HashMap<>();

        // 처음에는 모든 토핑이 두 번째 조각에 있다고 가정하고, map2를 채운다.
        for (int t : topping) {
            map2.put(t, map2.getOrDefault(t, 0) + 1);
        }

        // 롤케이크를 자를 위치를 결정하기 위해 순회
        for (int i = 0; i < topping.length - 1; i++) {
            int currentTopping = topping[i];

            // 첫 번째 조각에 현재 토핑을 추가
            set1.add(currentTopping);

            // 두 번째 조각에서 현재 토핑 하나 제거
            map2.put(currentTopping, map2.get(currentTopping) - 1);
            if (map2.get(currentTopping) == 0) {
                map2.remove(currentTopping);
            }

            // 두 조각의 토핑 종류 수가 동일하면 공평한 나눔이므로 answer 증가
            if (set1.size() == map2.size()) {
                answer++;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] topping1 = {1, 2, 1, 3, 1, 4, 1, 2};
        int[] topping2 = {1, 2, 3, 1, 4};

        System.out.println(solution(topping1)); // 2
        System.out.println(solution(topping2)); // 0
    }
}