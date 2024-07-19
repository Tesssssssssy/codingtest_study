package week11.baek.july19.baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S1912 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        // DP 배열 초기화
        int[] dp = new int[n];
        dp[0] = nums[0];
        int max = dp[0];

        for (int i = 1; i < n; i++) {
            //지금까지의 연속합 + 현재 값 vs 현재 값
            dp[i] = Math.max(dp[i-1] + nums[i], nums[i]);
            if(dp[i] > max) max = dp[i];
        }

        System.out.println(max);
    }
}
