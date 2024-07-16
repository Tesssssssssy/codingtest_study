package week11.baek.july16.baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S1463 {
    static Integer[] dp;
    static int recur(int N) {

        if (dp[N] == null) {
            // 6으로 나눠지는 경우
            if (N % 6 == 0) {
                dp[N] = Math.min(recur(N - 1), Math.min(recur(N / 3), recur(N / 2))) + 1;
            }
            // 3으로만 나눠지는 경우
            else if (N % 3 == 0) {
                dp[N] = Math.min(recur(N / 3), recur(N - 1)) + 1;
            }
            // 2로만 나눠지는 경우
            else if (N % 2 == 0) {
                dp[N] = Math.min(recur(N / 2), recur(N - 1)) + 1;
            }
            // 2와 3으로 나누어지지 않는 경우
            else {
                dp[N] = recur(N - 1) + 1;
            }
        }
        return dp[N];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        dp = new Integer[n+1];
        dp[0] = dp[1] = 0;

        System.out.println(recur(n));

    }
}
