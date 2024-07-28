package week9.baek.july7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class S10826 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        if (n == 0) System.out.println(0);
        else if (n == 1) System.out.println(1);
        else {
            BigInteger[] dp = new BigInteger[10001];
//            dp[0] = new BigInteger("0");
            dp[0] = BigInteger.ZERO;

//            dp[1] = new BigInteger("1");
            dp[1] = BigInteger.ONE;

            for (int i = 2; i <= n; i++) {
                dp[i] = dp[i-2].add(dp[i-1]);
            }
            System.out.print(dp[n]);
        }
    }
}
