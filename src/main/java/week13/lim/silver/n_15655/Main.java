package week13.lim.silver.n_15655;

// 문제: https://www.acmicpc.net/problem/15655

import java.io.*;
import java.util.*;

public class Main {
    /**
     * - N개의 자연수 중에서 M개를 고른 수열
     * - 고른 수열은 오름차순이어야 한다.
     *
     * [출력]
     * 중복되는 수열을 여러 번 출력하면 안되며, 각 수열은 공백으로 구분해서 출력해야 한다.
     * 수열은 사전 순으로 증가하는 순서로 출력해야 한다.
     */

    static int N, M;
    static int[] numbers;
    static int[] sequence;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        numbers = new int[N];
        sequence = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        // 주어진 수를 사전 순으로 정렬
        Arrays.sort(numbers);

        // 백트래킹
        backtrack(0, 0);

        System.out.print(sb.toString());
    }

    private static void backtrack(int depth, int start) {
        if (depth == M) {
            for (int num : sequence) {
                sb.append(num).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = start; i < N; i++) {
            sequence[depth] = numbers[i];
            backtrack(depth + 1, i + 1);
            // 다음 선택은 현재 선택한 수의 다음 수부터 시작
        }
    }
}