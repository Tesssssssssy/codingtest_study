package week17.lim.n_12900.practice;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/12900

public class Solution {
    static int MOD = 1_000_000_007;

    public static int solution(int n) {
        /*
            DP로 n이 늘어날수록 방법의 수를 계산한다.

            n = 1 -> 세로 2 가로 1  (세로로 배치하는 경우 1) : 1
            n = 2 -> 세로 2 가로 2  (가로로 2개 배치 1, 세로로 2개 배치 1) : 2

            f(n) = f(n-1) + f(n-2)
        */

        int[] dp = new int[n + 1];

        if (n == 1)
            return 1;
        if (n == 2)
            return 2;

        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % MOD;
        }

        return dp[n];
    }

    public static void main(String[] args) {
        int n1 = 4;
        System.out.println(solution(n1)); // 5
    }
}
