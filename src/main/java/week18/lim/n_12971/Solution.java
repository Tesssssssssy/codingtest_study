package week18.lim.n_12971;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/12971

import java.util.*;

public class Solution {
    /**
     *  N개의 스티커가 원형으로 연결되어 있습니다.
     *  다음 그림은 N = 8인 경우의 예시입니다.
     *
     *               10     14
     *          2                 6
     *          9                 5
     *               3      11
     *
     *  원형으로 연결된 스티커에서 몇 장의 스티커를 뜯어내어 뜯어낸 스티커에 적힌 숫자의 합이 최대가 되도록 하고 싶습니다.
     *  단 스티커 한 장을 뜯어내면 양쪽으로 인접해있는 스티커는 찢어져서 사용할 수 없게 됩니다.
     *
     *  예를 들어 위 그림에서 14가 적힌 스티커를 뜯으면 인접해있는 10, 6이 적힌 스티커는 사용할 수 없습니다.
     *  스티커에 적힌 숫자가 배열 형태로 주어질 때,
     *  스티커를 뜯어내어 얻을 수 있는 숫자의 합의 최댓값을 return 하는 solution 함수를 완성해 주세요.
     *  원형의 스티커 모양을 위해 배열의 첫 번째 원소와 마지막 원소가 서로 연결되어 있다고 간주합니다.
     */
    public static int solution(int sticker[]) {
        /*
            접근 방법
                두 가지 경우로 나누기
                    첫 번째 원소를 선택하는 경우
                    첫 번째 원소를 선택하지 않는 경우

                DP 배열 정의
                    dp[i]: i번째 스티커까지 뜯었을 때 얻을 수 있는 최대 합.
                    이 배열을 사용해 각 경우에 대해 최대 합을 계산.

                최종 결과
                    두 가지 경우에서 계산된 최대 합 중 더 큰 값을 선택.
        */

        int n = sticker.length;

        // 스티커가 하나일 경우 바로 반환
        if (n == 1)
            return sticker[0];

        // 첫 번째 스티커를 포함하는 경우
        int[] dp1 = new int[n];
        dp1[0] = sticker[0];
        dp1[1] = sticker[0];

        for (int i = 2; i < n - 1; i++) {
            dp1[i] = Math.max(dp1[i - 1], dp1[i - 2] + sticker[i]);
        }

        // 첫 번째 스티커를 포함하지 않는 경우
        int[] dp2 = new int[n];
        dp2[0] = 0;
        dp2[1] = sticker[1];

        for (int i = 2; i < n; i++) {
            dp2[i] = Math.max(dp2[i - 1], dp2[i - 2] + sticker[i]);
        }

        // 두 경우의 최대값 비교
        return Math.max(dp1[n - 2], dp2[n - 1]);
    }

    public static void main(String[] args) {
        int[] sticker1 = {14, 6, 5, 11, 3, 9, 2, 10};
        System.out.println(solution(sticker1)); // 36

        int[] sticker2 = {1, 3, 2, 5, 4};
        System.out.println(solution(sticker2)); // 8
    }
}
