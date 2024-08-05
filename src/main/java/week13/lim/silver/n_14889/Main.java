package week13.lim.silver.n_14889;

// 문제: https://www.acmicpc.net/problem/14889

import java.io.*;
import java.util.*;

public class Main {
    /**
     *  축구를 하기 위해 모인 사람은 총 N 명이고 신기하게도 N은 짝수이다.
     *  이제 N/2명으로 이루어진 스타트 팀과 링크 팀으로 사람들을 나눠야 한다.
     *
     *  BOJ 를 운영하는 회사 답게 사람에게 번호를 1부터 N 까지로 배정했고, 아래와 같은 능력치를 조사했다.
     *  능력치 Sij 는 i번 사람과 j번 사람이 같은 팀에 속했을 때, 팀에 더해지는 능력치이다.
     *  팀의 능력치는 팀에 속한 모든 쌍의 능력치 Sij 의 합이다. Sij 는 Sji 와 다를 수도 있으며,
     *  i번 사람과 j번 사람이 같은 팀에 속했을 때, 팀에 더해지는 능력치는 Sij 와 Sji 이다.
     *
     *  N=4이고, S가 아래와 같은 경우를 살펴보자.
     *
         i\j	1	2	3	4
         1	 	    1	2	3
         2	    4	 	5	6
         3	    7	1	 	2
         4	    3	4	5
     *
     *  예를 들어, 1, 2번이 스타트 팀, 3, 4번이 링크 팀에 속한 경우에 두 팀의 능력치는 아래와 같다.
     *      스타트 팀: S12 + S21 = 1 + 4 = 5
     *      링크 팀: S34 + S43 = 2 + 5 = 7
     *      1, 3번이 스타트 팀, 2, 4번이 링크 팀에 속하면, 두 팀의 능력치는 아래와 같다.
     *
     *      스타트 팀: S13 + S31 = 2 + 7 = 9
     *      링크 팀: S24 + S42 = 6 + 4 = 10
     *
     *  축구를 재미있게 하기 위해서 스타트 팀의 능력치와 링크 팀의 능력치의 차이를 최소로 하려고 한다.
     *  위의 예제와 같은 경우에는 1, 4번이 스타트 팀,
     *  2, 3번 팀이 링크 팀에 속하면 스타트 팀의 능력치는 6,
     *  링크 팀의 능력치는 6이 되어서 차이가 0이 되고 이 값이 최소이다.
     */

    static int N;
    static int[][] S;
    static boolean[] visited;
    static int minDifference = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        S = new int[N][N];
        visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                S[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        backtrack(0, 0);
        System.out.println(minDifference);
    }

    private static void backtrack(int idx, int count) {
        if (count == N / 2) {
            calculateDifference();
            return;
        }

        for (int i = idx; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                backtrack(i + 1, count + 1);
                visited[i] = false;
            }
        }
    }

    private static void calculateDifference() {
        int startTeam = 0;
        int linkTeam = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i] && visited[j]) {
                    startTeam += S[i][j];
                }
                if (!visited[i] && !visited[j]) {
                    linkTeam += S[i][j];
                }
            }
        }

        int diff = Math.abs(startTeam - linkTeam);
        minDifference = Math.min(minDifference, diff);
    }
}