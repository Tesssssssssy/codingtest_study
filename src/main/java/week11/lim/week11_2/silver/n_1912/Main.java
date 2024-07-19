package week11.lim.week11_2.silver.n_1912;

// 문제: https://www.acmicpc.net/problem/1912

import java.io.*;
import java.util.*;

public class Main {
    /**
     *  1. n개의 정수로 이루어진 임의의 수열이 주어짐.
     *  2. 이 중 연속된 몇 개의 수를 선택해서 구할 수 있는 합 중 가장 큰 합을 구하려고 한다.
     *     단, 수는 한 개 이상 선택해야 한다.
     *  3. ex.)
     *      10, -4, 3, 1, 5, 6, -35, 12, 21, -1 -> 수열
     *      여기서 정답은 12 + 21인 33이 정답이 된다.
     *
     *  [
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] progression = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            progression[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[n];
        dp[0] = progression[0]; // 첫 번째 요소는 수열의 첫 번째 요소로 초기화
        int maxSum = dp[0]; // 최대 합을 저장하는 변수, 초기값은 dp[0]

        // 동적 계획법으로 최대 부분 배열 합 구하기
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(dp[i-1] + progression[i], progression[i]); // i 번째에서 끝나는 최대 부분 배열 합 계산
            maxSum = Math.max(maxSum, dp[i]); // 최대값 갱신
        }

        System.out.println(maxSum); // 최대 부분 배열의 합 출력
    }
}
