package week11.lim.week11_1.silver.n_1012;

// 문제: https://www.acmicpc.net/problem/1012

import java.io.*;
import java.util.*;

public class Main {
    /**
     *  0. 지렁이는 배추근처에 서식하며 해충을 잡아 먹음으로써 배추를 보호한다.
     *  1. 어떤 배추에 배추흰지렁이가 한 마리라도 살고 있으면
     *     이 지렁이는 인접한 다른 배추로 이동할 수 있어, 그 배추들 역시 해충으로부터 보호받을 수 있다.
     *  2. 한 배추의 상하좌우 네 방향에 다른 배추가 위치한 경우에 서로 인접해있는 것.
     *  3. 배추를 재배하는 땅은 고르지 못해서 배추를 군데군데 심어 놓았다.
     *  4. 배추들이 모여있는 곳에는 배추흰지렁이가 한 마리만 있으면 되므로
     *     서로 인접해있는 배추들이 몇 군데에 퍼져있는지 조사하면 총 몇 마리의 지렁이가 필요한지 알 수 있다.
     *
     *  [풀이]
     *  모든 칸을 순회하면서 방문하지 않은 배추가 있는 칸에서 DFS를 시작.
     *  DFS를 통해 해당 배추와 연결된 모든 배추를 방문 처리.
     *  하나의 DFS 실행이 끝나면 하나의 군집이 완성된 것이므로 지렁이 개수를 하나 증가.
     */
    static int M, N, K; // M: 가로, N: 세로, K: 배추 위치 개수
    static int[][] ground;
    static boolean[][] visited;
    static int[] dx = {0, 1, 0, -1}; // 좌표 이동을 위한 배열 (상, 하, 좌, 우)
    static int[] dy = {-1, 0, 1, 0}; // 좌표 이동을 위한 배열 (상, 하, 좌, 우)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수
        StringBuilder sb = new StringBuilder();

        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            M = Integer.parseInt(st.nextToken()); // 가로길이
            N = Integer.parseInt(st.nextToken()); // 세로길이
            K = Integer.parseInt(st.nextToken()); // 배추 위치 개수

            ground = new int[N][M];
            visited = new boolean[N][M];

            // 배추 위치 입력 받기
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                ground[y][x] = 1;
            }

            // 지렁이가 필요한 개수 계산
            int worms = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (ground[i][j] == 1 && !visited[i][j]) {
                        dfs(i, j);
                        worms++;
                    }
                }
            }
            sb.append(worms).append("\n");
        }

        System.out.print(sb);
    }

    // DFS 메서드
    public static void dfs(int x, int y) {
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
                if (ground[nx][ny] == 1 && !visited[nx][ny]) {
                    dfs(nx, ny);
                }
            }
        }
    }
}
