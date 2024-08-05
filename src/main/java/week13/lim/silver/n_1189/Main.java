package week13.lim.silver.n_1189;

// 문제: https://www.acmicpc.net/problem/1189

import java.io.*;
import java.util.*;

public class Main {
    /**
     *  - 한수는 현재 왼쪽 아래점에 있고 집은 오른쪽 위에 있다.
     *  - 집에 돌아가는 방법은 다양하다.
     *  - 한번 지나친 곳을 다시 방문하지 않는다.
     *
     *  - T는 가지 못하는 부분
     *
     *  [입력]
     *  R (행) / C (열) / K (거리)
     *
     *  [출력]
     *  거리가 K인 가짓수
     */

    static int R, C, K;
    static char[][] map;
    static boolean[][] visited;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new char[R][];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        /*
            시작점: 왼쪽 하단 (R-1, 0)
            목표: 오른쪽 상단 (0, C-1)
        */
        backtrack(R-1, 0, 1);
        // 시작점을 카운트 하므로 거리를 1로 시작

        System.out.println(answer);
    }

    private static void backtrack(int x, int y, int distance) {
        if (x == 0 && y == C-1 && distance == K) {
            answer++;  // 목표 지점에 도달하면 정답 카운트
            return;
        }

        if (distance >= K)
            return;  // 거리가 K 이상이면 더 이상 진행 x

        // 상하좌우 탐색
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < R && ny >= 0 && ny < C
                    && !visited[nx][ny] && map[nx][ny] != 'T') {
                backtrack(nx, ny, distance + 1);
            }
        }

        visited[x][y] = false;
    }
}