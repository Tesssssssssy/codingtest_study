package week14.lim.silver.n_7795.practice;

// 문제: https://www.acmicpc.net/problem/7795

import java.io.*;
import java.util.*;

public class Main2 {
    /**
     *  [풀이] 이진 탐색
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int[] arrA = new int[N];
            int[] arrB = new int[M];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arrA[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                arrB[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(arrA);
            Arrays.sort(arrB);

            int cnt = 0;
            for (int i = 0; i < N; i++) {
                cnt += binarySearch(arrB, arrA[i]);
            }

            sb.append(cnt).append("\n");

        }

        System.out.println(sb);
    }

    private static int binarySearch(int[] arrB, int target) {
        int left = 0;
        int right = arrB.length;

        while (left < right) {
            int mid = (left + right) / 2;

            if (arrB[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}
