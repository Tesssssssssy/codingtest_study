package week15.lim.programmers_Lv2.n_132265.practice;

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

        // 첫 번째 조각에 올라갈 토핑
        Set<Integer> firstPiece = new HashSet<>();

        // 두 번째 조각에 올라갈 토핑
        // 시작을 무조건 두 번째 조각에 모든 토핑이 올라가 있다고 가정
        Map<Integer, Integer> secondPiece = new HashMap<>();
        for (int i = 0; i < topping.length; i++) {
            secondPiece.put(topping[i], secondPiece.getOrDefault(topping[i], 0) + 1);
        }

        // 두 번째 조각에서 토핑을 하나씩 첫 번째 조각으로 이동해 비교
        for (int i = 0; i < topping.length - 1; i++) {
            // 첫 번째 토핑
            int movedTopping = topping[i];

            // 두 번째 조각에서 첫 번째 토핑 제거
            secondPiece.put(movedTopping, secondPiece.get(movedTopping) - 1);
            if (secondPiece.get(movedTopping) == 0) {
                secondPiece.remove(movedTopping);
            }

            // 첫 번째 조각에 첫 번째 토핑 추가
            firstPiece.add(movedTopping);

            // 첫 번째 조각의 크기와 두 번째 조각의 크기가 같으면
            // 토핑의 개수가 같으므로 answer++
            if (firstPiece.size() == secondPiece.size()) {
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