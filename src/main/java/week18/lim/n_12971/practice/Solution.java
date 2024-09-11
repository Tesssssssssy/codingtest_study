package week18.lim.n_12971.practice;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/12971

public class Solution {
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

        if (n == 1)
            return sticker[0];

        // 첫 번째 원소를 선택하는 경우
        int[] dp1 = new int[n];
        dp1[0] = sticker[0];
        dp1[1] = sticker[0];

        for (int i = 2; i < n - 1; i++) {
            dp1[i] = Math.max(dp1[i - 1], dp1[i - 2] + sticker[i]);
        }

        // 첫 번째 원소를 선택하지 않는 경우
        int[] dp2 = new int[n];
        dp2[0] = 0;
        dp2[1] = sticker[1];

        for (int i = 2; i < n; i++) {
            dp2[i] = Math.max(dp2[i - 1], dp2[i - 2] + sticker[i]);
        }

        return Math.max(dp1[n - 2], dp2[n - 1]);
    }

    public static void main(String[] args) {
        int[] sticker1 = {14, 6, 5, 11, 3, 9, 2, 10};
        System.out.println(solution(sticker1)); // 36

        int[] sticker2 = {1, 3, 2, 5, 4};
        System.out.println(solution(sticker2)); // 8
    }
}
