package week11.lim.week11_2.silver.n_2156;

// 문제: https://www.acmicpc.net/problem/2156

import java.io.*;

public class Main {
    /**
     *  1. 포도주 잔이 일렬로 놓여 있다.
     *  2. 1) 포도주 잔을 선택하면 그 잔에 들어있는 포도주는 모두 마셔야 한다.
     *        마신 후에는 원래 위치에 다시 놓아야 한다.
     *     2) 연속으로 놓여 있는 3잔을 모두 마실 수 없다.
     *  3. 될 수 있는 대로 많은 양의 포도주를 맛봐야 한다.
     *  4. 1-n 번호의 n개의 포도주가 놓여 있고, 양이 주어질 때,
     *     가장 많은 양의 포도주를 마실 때의 포도주의 양 출력
     *  5. ex.)
     *     6개의 포도주
     *     6, 10, 13, 9, 8, 1 (양)
     *     v  v       v  v
     *     -> 총 포도주의 양 33으로 최대
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] wine = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            wine[i] = Integer.parseInt(br.readLine());
        }

        if (n == 1) {
            System.out.println(wine[1]);
            return;
        }

        int[] dp = new int[n + 1];
        // i번째 포도주 잔까지 고려했을 때 마실 수 있는 최대 포도주의 양.

        dp[1] = wine[1]; // 첫 번째 포도주만 마실 경우
        dp[2] = wine[1] + wine[2]; // 첫 번째와 두 번째 포도주를 마실 경우

        for (int i = 3; i <= n; i++) {
            dp[i] = Math.max(dp[i-1], Math.max(dp[i-2] + wine[i], dp[i-3] + wine[i-1] + wine[i]));
        }
        /*
            i번째 포도주를 마시지 않는 경우: dp[i] = dp[i-1]
                문제는 i-1번째 포도주 잔까지의 최대 포도주 양을 구하는 문제로 단순화됨.
                즉, 이 경우의 최대 포도주 양은 dp[i-1]과 동일.
                여기서 dp[i-1]은 이미 계산된 값으로, i-1번째 포도주 잔까지 마실 때 얻을 수 있는 최대 포도주 양을 의미.

            i번째 포도주를 마시고, i-1번째 포도주는 마시지 않는 경우: dp[i] = dp[i-2] + wine[i]
                이 경우 최대 포도주 양은 i-2번째 포도주 잔까지 마신 양에 i번째 포도주 잔의 양을 더한 것.
                이는 dp[i-2] + wine[i]와 같으며,
                dp[i-2]는 i-2번째 포도주 잔까지 마실 때 얻을 수 있는 최대 포도주 양을 나타냄.

            i번째 포도주와 i-1번째 포도주를 모두 마시고, i-2번째는 마시지 않는 경우: dp[i] = dp[i-3] + wine[i-1] + wine[i]
                규칙에 따라 연속된 세 잔을 마실 수 없으므로, i-2번째 포도주는 마시지 않아야 함.
                따라서 이 경우 최대 포도주 양은 i-3번째 포도주 잔까지 마신 양에 i-1번째와 i번째 포도주 잔의 양을 더한 것.
                이는 dp[i-3] + wine[i-1] + wine[i]와 같으며,
                dp[i-3]는 i-3번째 포도주 잔까지 마실 때 얻을 수 있는 최대 포도주 양을 나타냄.

            이 중 최대 값을 선택해야 함.
        */

        System.out.println(dp[n]);
    }
}
