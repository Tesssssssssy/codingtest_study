package week13.lim.silver.n_15649;

// 문제: https://www.acmicpc.net/problem/15649

import java.io.*;
import java.util.*;

public class Main {
    /**
     * - N, M 이 주어졌을 때,
     * 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램
     * - 1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열
     * <p>
     * [입력]
     * 자연수 N, M
     * <p>
     * [출력]
     * 한 줄에 하나씩 문제의 조건 만족하는 수열 출력
     * - 중복하는 수열을 여러 번 출력하면 안됨.
     * - 각 수열은 공백으로 구분해서 출력해야 함.
     * - 수열은 사전 순으로 증가하는 순서로 출력해야 함.
     */
    static int N, M;
    static boolean[] visited; // 방문 여부를 체크할 배열
    static int[] sequence; // 현재까지 선택된 수열을 저장할 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N + 1];
        sequence = new int[M];

        backtrack(0);
    }

    /*
        재귀적으로 수열을 생성.
        깊이가 M에 도달하면 수열을 출력하고 종료. (DFS)

        1부터 N 까지의 숫자를 순회하면서 방문하지 않은 숫자를 선택하고,
        이를 현재 수열에 추가한 후 다음 깊이로 재귀 호출.
        호출이 끝나면 해당 숫자를 다시 방문 가능 상태로 되돌림.
     */
    public static void backtrack(int depth) {
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
                /*
                    해당 깊이를 index 로 해서 i 저장
                    이 부분에서 실제 값으로 매핑
                 */

                backtrack(depth + 1);
                visited[i] = false;
            }
        }
    }
}