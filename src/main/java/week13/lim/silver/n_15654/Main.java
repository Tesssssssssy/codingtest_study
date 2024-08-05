package week13.lim.silver.n_15654;

// 문제: https://www.acmicpc.net/problem/15654

import java.io.*;
import java.util.*;

public class Main {
    /**
     *  - N개의 자연수와 자연수 M이 주어졌을 때,
     *    아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램.
     *  - N개의 자연수는 모두 다른 수이다.
     *  - N개의 자연수 중에서 M개를 고른 수열
     *
     *  [입력]
     *  첫째 줄에 N과 M이 주어진다. (1 ≤ M ≤ N ≤ 8)
     *  둘째 줄에 N개의 수가 주어진다.
     *
     *  [출력]
     *  중복되는 수열을 여러 번 출력하면 안되며, 각 수열은 공백으로 구분해서 출력해야 한다.
     *  수열은 사전 순으로 증가하는 순서로 출력해야 한다.
     */

    static int N, M;
    static int[] numbers;
    static int[] sequence;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        numbers = new int[N];
        sequence = new int[M];
        visited = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        // 주어진 수를 사전 순으로 정렬
        Arrays.sort(numbers);

        // 백트래킹
        backtrack(0);

        System.out.print(sb.toString());
    }

    private static void backtrack(int depth) {
        if (depth == M) {
            for (int num : sequence) {
                sb.append(num).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                sequence[depth] = numbers[i];
                backtrack(depth + 1);
                visited[i] = false;
            }
        }
    }
}
