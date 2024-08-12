package week14.lim.silver.n_7795.practice;

// 문제: https://www.acmicpc.net/problem/7795

import java.io.*;
import java.util.*;

public class Main {
    /**
     *  [풀이] 투 포인터
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

            int[] arrA =  new int[N];
            int[] arrB =  new int[M];

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

            int j = 0;
            int cnt = 0;

            for (int i = 0; i < N; i++) {
                while (j < M && arrA[i] > arrB[j]) {
                    j++;
                }
                cnt += j;
            }

            sb.append(cnt).append("\n");
        }
        System.out.println(sb);
    }
}
