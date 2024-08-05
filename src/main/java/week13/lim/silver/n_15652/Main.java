package week13.lim.silver.n_15652;

// 문제: https://www.acmicpc.net/problem/15652

import java.io.*;
import java.util.*;

public class Main {
    /**
     *  - 1부터 N까지 자연수 중에서 M개를 고른 수열
     *  - 같은 수를 여러 번 골라도 된다.
     *  - 고른 수열은 비내림차순이어야 한다.
     *  - 길이가 K인 수열 A가 A1 ≤ A2 ≤ ... ≤ AK-1 ≤ AK를 만족하면, 비내림차순이라고 한다.
     *
     *  [출력]
     *  중복되는 수열을 여러 번 출력하면 안되며, 각 수열은 공백으로 구분해서 출력해야 한다.
     *  수열은 사전 순으로 증가하는 순서로 출력해야 한다.
     */

    static int N, M;
    static int[] sequence;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        sequence = new int[M];

        backtrack(0, 1);

        System.out.print(sb.toString());
    }

    private static void backtrack(int depth, int start) {
        if (depth == M) {
            for (int i = 0; i < M; i++) {
                sb.append(sequence[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = start; i <= N; i++) {
            sequence[depth] = i;
            backtrack(depth + 1, i);
            // start 를 i로 해서 현재 선택한 수 또는 그 이상을 선택할 수 있도록 수정
        }
    }
}
