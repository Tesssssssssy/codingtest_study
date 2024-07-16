package week11.lim.week11_1.silver.n_1463;

// 문제: https://www.acmicpc.net/problem/1463

import java.io.*;

public class Main {
    /**
     *  1. X가 3으로 나누어 떨어지면, 3으로 나눈다.
     *     X가 2로 나누어 떨어지면, 2로 나눈다.
     *     1을 뺀다.
     *  2. 정수 N이 주어졌을 때,
     *     위와 같은 연산 세 개를 적절히 사용해서 1을 만들려고 한다.
     *     연산을 사용하는 횟수의 최솟값을 출력.
     *
     *  [풀이]
     *  동적계획법
     *      i-1에서 1을 더하는 연산 (1을 뺀다는 의미)
     *      i/2에서 1을 더하는 연산 (만약 i가 2로 나누어 떨어진다면)
     *      i/3에서 1을 더하는 연산 (만약 i가 3으로 나누어 떨어진다면)
     *
     *      => 각 숫자에 대해 이 중 최소값을 dp 배열에 저장하며, 최종적으로 dp[N]을 출력.
     *
     *  [주의]
     *  무조건 큰 수로 나누는 방법이 최소 보장 x
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[N + 1];

        // 1을 만드는 경우 연산 횟수는 0이므로 dp[1]은 0으로 초기화.
        dp[1] = 0;

        for (int i = 2; i <= N; i++) {
            dp[i] = dp[i - 1] + 1; // 먼저 1을 빼는 경우를 기본으로 설정

            if (i % 3 == 0) {
                dp[i] = Math.min(dp[i], dp[i / 3] + 1); // 3으로 나누어 떨어지는 경우
            }

            if (i % 2 == 0) {
                dp[i] = Math.min(dp[i], dp[i / 2] + 1); // 2로 나누어 떨어지는 경우
            }
        }

        // N을 1로 만드는데 필요한 최소 연산 횟수 출력
        System.out.println(dp[N]);
    }
}

/*
    연산 수행: i / 3은 i를 3으로 나눈 결과.
             이 결과는 i보다 작은 값이며, 이 작은 값에 대해서 이미 최소 연산 횟수를 dp 배열에 저장해 둠.
             따라서 dp[i / 3]은 i를 3으로 나눈 결과를 1로 만드는 데 필요한 최소 연산 횟수를 나타냄.

    1 더하기: 여기서 +1은 i를 3으로 나누는 그 연산 자체를 카운트하는 것.
            즉, i에서 i / 3으로 상태가 변경되는 데 1번의 연산이 필요하다는 의미.
            따라서, dp[i / 3] + 1은 i를 3으로 나누고 그 결과를 1로 만드는 데 필요한 전체 최소 연산 횟수를 계산.
*/