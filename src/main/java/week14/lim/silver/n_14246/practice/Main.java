package week14.lim.silver.n_14246.practice;

// 문제: https://www.acmicpc.net/problem/14246

import java.io.*;
import java.util.*;

public class Main {
    /**
     *  n개의 자연수로 이루어진 수열이 주어질 때,
     *  특정 구간 [i, j] (i <= j) 의 합이 k보다 큰 모든 쌍 (i, j)의 개수를 출력하시오.
     *
     *  [입력]
     *  첫째 줄에는 자연수의 개수 n이 주어진다. (1 <= n <= 100 000)
     *  다음 줄에는 자연수 n개가 주어진다. (자연수 <= 100 000)
     *  그 다음 줄에는 자연수 k가 주어진다. (1 <= k <= 1 000 000 000)
     *
     *  [출력]
     *  특정 구간 [i, j]의 합이 k보다 큰 모든 쌍 (i, j)의 개수를 출력하시오.
     *
     *  [풀이]
     *  투 포인터
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int k = Integer.parseInt(br.readLine());

        long cnt = 0;

        for (int left = 0; left < n; left++) {
            long sum = 0;
            for (int right = left; right < n; right++) {
                sum += arr[right];

                if (sum > k) {
                    cnt += n - right;

                    break;
                }
            }
        }

        System.out.println(cnt);
    }
}
