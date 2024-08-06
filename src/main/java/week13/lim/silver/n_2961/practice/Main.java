package week13.lim.silver.n_2961.practice;

// 문제: https://www.acmicpc.net/problem/2961

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] sour;
    static int[] bitter;
    static int minDiff = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        sour = new int[N];
        bitter = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            sour[i] = Integer.parseInt(st.nextToken());
            bitter[i] = Integer.parseInt(st.nextToken());
        }

        backtrack(0, 1, 0);

        System.out.println(minDiff);
    }

    private static void backtrack(int depth, int totalSour, int totalBitter) {
        if (depth > 0) { // 재료를 1개라도 사용한 경우
            int diff = Math.abs(totalSour - totalBitter);
            minDiff = Math.min(minDiff, diff);
        }

        for (int i = depth; i < N; i++) {
            backtrack(i + 1, totalSour * sour[i], totalBitter + bitter[i]);
        }
    }
}
