package week17.lim.n_12900;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/12900

public class Solution {
    /**
     *  [풀이]
     *  DP
     *
     *  문제 분석:
     *      n이 1일 때 가능한 타일 배치 방법은 1가지. (타일을 세로로 놓기)
     *      n이 2일 때 가능한 타일 배치 방법은 2가지. (타일을 세로로 2개 놓기, 가로로 2개 놓기)
     *
     *  이후 n에 대한 해결 방법은 이전 두 개의 값을 이용하여 구할 수 있다:
     *      f(n) = f(n-1) + f(n-2)
     *
     *  위 식에서:
     *      f(n-1)은 맨 왼쪽에 세로로 타일을 놓은 경우 남은 타일 배치 방법의 수를 의미.
     *      f(n-2)는 맨 왼쪽에 가로로 타일을 놓은 경우 남은 타일 배치 방법의 수를 의미.
     */
    public static int solution(int n) {
        // 나머지를 계산하기 위한 값
        int MOD = 1_000_000_007;

        // n이 1일 때와 2일 때의 경우를 바로 반환
        if (n == 1)
            return 1;
        if (n == 2)
            return 2;

        // DP 배열 초기화
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;

        // 동적 프로그래밍을 이용해 값을 계산
        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % MOD;
        }

        // n에 대한 결과 반환
        return dp[n];
    }

    public static void main(String[] args) {
        int n1 = 4;
        System.out.println(solution(n1)); // 5
    }
}
