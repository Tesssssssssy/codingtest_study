package week18.lim.n_152996;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/152996
// 참고: https://stritegdc.tistory.com/325

import java.util.*;

public class Solution {
    /**
     *  어느 공원 놀이터에는 시소가 하나 설치되어 있습니다.
     *  이 시소는 중심으로부터 2(m), 3(m), 4(m) 거리의 지점에 좌석이 하나씩 있습니다.
     *  이 시소를 두 명이 마주 보고 탄다고 할 때,
     *  시소가 평형인 상태에서 각각에 의해 시소에 걸리는 토크의 크기가 서로 상쇄되어 완전한 균형을 이룰 수 있다면
     *  그 두 사람을 시소 짝꿍이라고 합니다.
     *
     *  즉, 탑승한 사람의 무게와 시소 축과 좌석 간의 거리의 곱이 양쪽 다 같다면 시소 짝꿍이라고 할 수 있습니다.
     *
     *  사람들의 몸무게 목록 weights이 주어질 때,
     *  시소 짝꿍이 몇 쌍 존재하는지 구하여 return 하도록 solution 함수를 완성해주세요.
     */
    public static long solution(int[] weights) {
        /*
            오름차순 정렬을 했기 때문에
            무게가 같은 경우와 (* 4, * 3), (* 3, * 2), (* 4, * 2) 경우에 대해서만 체크한다.

            또한 같은 무게인 경우 앞서 구한 count 에서 1을 뺀 값을 그대로 활용할 수 있다.
            시간초과가 나지 않기 위해서 필수.
        */
        long answer = 0;

        Arrays.sort(weights);

        int count = 0;
        for (int i = 0; i < weights.length - 1; i++) {
            // 무게가 같은 경우

            if (i > 0) {
                if (weights[i] == weights[i - 1]) {
                    count--;
                    answer += count;
                    continue;
                }
            }

            count = 0;

            // 4 : 3  /  3 : 2  /  4 : 2 경우 확인
            for (int j = i + 1; j < weights.length; j++) {
                if (weights[i] == weights[j] ||
                        weights[i] * 4 == weights[j] * 3 ||
                        weights[i] * 3 == weights[j] * 2 ||
                        weights[i] * 2 == weights[j]) {
                    count++;
                }
            }

            answer += count;
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] weights = {100, 180, 360, 100, 270};
        System.out.println(solution(weights)); // 4
    }
}
