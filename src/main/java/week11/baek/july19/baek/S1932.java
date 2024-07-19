package week11.baek.july19.baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S1932 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] triangle = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j <= i; j++) {
                triangle[i][j]  = Integer.parseInt(st.nextToken());
            }
        }

        //Top-down 방식으로 꼭대기에서
        // 한칸 씩(왼쪽 대각선(아래), 오른쪽 대각선(아래 오른쪽)) 내려온 합으로 값을 변경함
        int[][] dp = new int[n][n];

        dp[0][0] = triangle[0][0];
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j <=i ; j++) {
                //왼쪽 대각선(아래)
                int tmp = dp[i][j] + triangle[i+1][j];
                if(tmp > dp[i+1][j]) dp[i+1][j] = dp[i][j] + triangle[i+1][j];

                //오른쪽 대각선(아래 오른쪽)
                tmp = dp[i][j] + triangle[i+1][j+1];
                if(tmp > dp[i+1][j+1]) dp[i+1][j+1] = dp[i][j] + triangle[i+1][j+1];

            }
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if(max < dp[n-1][i]) max = dp[n-1][i];
        }

        System.out.println(max);

    }
}
