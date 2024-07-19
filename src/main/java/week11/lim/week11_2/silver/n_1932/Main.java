package week11.lim.week11_2.silver.n_1932;

// 문제: https://www.acmicpc.net/problem/1932
// 참고(프로그래머스 문제): week3/lim/dp/integertriangle/integerTriangle.java

import java.io.*;

public class Main {
    /**
     *              7
     *            3   8
     *          8   1   0
     *        2   7   4   4
     *      4   5   2   6   5
     *     ->  크기가 5인 정수 삼각형의 한 모습
     *
     *     맨 위층 7부터 시작해서 아래에 있는 수 중 하나를 선택하여 아래층으로 내려올 때,
     *     이제까지 선택된 수의 합이 최대가 되는 경로에 있는 수의 합 출력
     *
     *     아래층에 있는 수는 현재 층에서
     *     선택된 수의 대각선 왼쪽 또는 대각선 오른쪽에 있는 것 중에서만 선택할 수 있다.
     *
     *     [풀이]
     *     트리의 맨 아래부터 거꾸로 계산한다.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] triangle = new int[n][n];
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j <= i; j++) {
                triangle[i][j] = Integer.parseInt(input[j]);
                if (i == n - 1) { // 마지막 행 초기화
                    dp[i][j] = triangle[i][j];
                }
            }
        }

        // 아래에서 위로 올라가면서 DP 배열을 채움
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = triangle[i][j] + Math.max(dp[i+1][j], dp[i+1][j+1]);
            }
        }

        System.out.println(dp[0][0]);
    }
}
