package week9.lim.n_11727;

// 문제: https://www.acmicpc.net/problem/11727

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());

        if (n == 1) {
            System.out.println(1);
            return;
        }

        int[] dp = new int[n + 1];
        dp[1] = 1; // 세로로 한 개의 2×1 타일로 채울 수 있다.
        dp[2] = 3;
        /*
            - 2×1 타일 두 개를 가로로 놓는 방법
            - 1×2 타일 두 개를 세로로 놓는 방법
            - 하나의 2×2 타일을 놓는 방법
         */

        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] + 2 * dp[i - 2]) % 10007;
            // 주어진 문제에서는 모듈러 연산 필요
            // 2 * dp[i - 2])는 2×1 타일 두 개나 2×2 타일 하나를 추가하는 경우 의미
        }

        System.out.println(dp[n]);
    }
}
/*
    dp[1]의 경우:
        2×1 크기의 직사각형은 길이가 2인데 너비가 1이므로,
        실제적으로는 1×2 타일 하나로만 채울 수 있다.
        1×2 타일은 길이가 2이고 너비가 1인 타일이므로, 딱 맞게 직사각형을 채울 수 있다.
        2×1 타일을 사용할 경우, 직사각형의 방향과 일치하여 타일 한 개로 꽉 채울 수 있다.
        따라서, dp[1]의 값은 1입니다. 다른 조합이 없기 때문.

    dp[2]의 경우:
        2×2 크기의 직사각형을 채우는 방법은 다음과 같다:

        두 개의 1×2 타일을 세로로 두어서 직사각형을 채울 수 있다.
        두 개의 2×1 타일을 가로로 두어서 직사각형을 채울 수 있다.
        하나의 2×2 타일로 직사각형 전체를 채울 수 있다.
*/