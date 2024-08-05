package week13.lim.silver.n_15650;

// 문제: https://www.acmicpc.net/problem/15650

import java.io.*;
import java.util.*;

public class Main {
    /**
     * - 1부터 N까지 자연수 중 중복 없이 M개를 고른 수열
     * - 고른 수열은 오름차순이어야.
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

        backtrack(0, 1);
        // 오름차순을 유지하기 위해 시작 인덱스 전달
    }

    private static void backtrack(int depth, int start) {
        /*
            backtrack 메소드에 start 인자를 추가하여,
            이전에 선택된 수 다음부터 수를 선택하도록 하여 오름차순을 강제
        */

        if (depth == M) {
            for (int i = 0; i < M; i++) {
                System.out.print(sequence[i] + " ");
            }
            System.out.println();
            return;
        }

        for (int i = start; i <= N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                sequence[depth] = i;
                backtrack(depth + 1, i + 1);
                visited[i] = false;
            }
        }
    }
}