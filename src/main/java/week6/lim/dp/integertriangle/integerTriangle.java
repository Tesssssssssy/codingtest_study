package week6.lim.dp.integertriangle;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/43105

public class integerTriangle {
    public static int solution(int[][] triangle) {
        int n = triangle.length;

        // DP 배열 초기화
        int[][] dp = new int[n][n];

        // 삼각형의 마지막 행을 DP 배열의 마지막 행으로 초기화
        for (int i = 0; i < n; i++) {
            dp[n-1][i] = triangle[n-1][i];
        }

        // 아래에서 위로 올라가면서 DP 배열을 채움
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = triangle[i][j] + Math.max(dp[i+1][j], dp[i+1][j+1]);
            }
        }

        // DP 배열의 꼭대기 값이 최대 합
        return dp[0][0];
    }

    public static void main(String[] args) {
        int[][] triangle = {
                {7},
                {3, 8},
                {8, 1, 0},
                {2, 7, 4, 4},
                {4, 5, 2, 6, 5}
        };
        System.out.println(solution(triangle));  // 30
    }
}
