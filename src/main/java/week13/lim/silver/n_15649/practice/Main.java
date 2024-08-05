package week13.lim.silver.n_15649.practice;

// 문제: https://www.acmicpc.net/problem/15649

import java.io.*;
import java.util.*;

public class Main {
    /**
     * - 1부터 N까지 자연수 중 중복 없이 M개를 고른 수열
     */

    static boolean[] visited;
    static int N, M;
    static int[] sequence;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N + 1];
        sequence = new int[M];

        backtrack(0);
    }

    private static void backtrack(int depth) { // DFS
        if (depth == M) {
            for (int i = 0; i < M; i++) {
                System.out.print(sequence[i] + " ");
            }
            System.out.println();
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                sequence[depth] = i;
                backtrack(depth + 1);
                visited[i] = false;
            }
        }
    }
}
