package week11.lim.week11_1.silver.n_1743;

// 문제: https://www.acmicpc.net/problem/1743

import java.io.*;
import java.util.*;

public class Main {
    /**
     *  1. 몇몇 비양심적인 학생들의 만행으로 음식물이 통로 중간 중간에 떨어져 있다.
     *  2. 이러한 음식물들은 근처에 있는 것끼리 뭉치게 돼서 큰 음식물 쓰레기가 된다.
     *  3. 생님은 떨어진 음식물 중에 제일 큰 음식물만은 피해 가려고 한다.
     *  4. 선생님을 도와 제일 큰 음식물의 크기를 구해서 “10ra"를 외치지 않게 도와주자.
     *
     *  [입력]
     *  세로 길이 N / 가로 길이 M / 음쓰 개수 K
     *  K줄 만큼 음쓰 좌표 (r, c)
     *
     *  ex.)
     *      # . . .
     *      . # # .
     *      # # . .
     *
     *      위와 같이 음식물이 떨어져있고 제일큰 음식물의 크기는 4가 된다.
     *      (인접한 것은 붙어서 크게 된다고 나와 있음. 대각선으로는 음식물 끼리 붙을수 없고 상하좌우로만 붙을수 있다.)
     *
     *  [출력]
     *  음쓰 중 가장 큰 음쓰 크기 출력
     *
     *  [풀이]
     *  DFS를 사용하여 각 음식물 군집의 크기를 계산하고, 그 중 가장 큰 크기를 찾아 출력.
     *  주어진 통로의 크기와 음식물이 떨어진 위치를 기반으로 음식물이 떨어져 있는 그리드를 설정.
     *  -> 각 좌표를 방문하면서 연결된 음식물의 크기를 DFS로 계산하고, 그 중 최대 크기를 찾아 출력.
     */
    static int N, M, K;
    static boolean[][] grid;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0}; // 상하좌우 이동을 위한 배열
    static int[] dy = {0, 0, 1, -1}; // 상하좌우 이동을 위한 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        grid = new boolean[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1; // 배열 인덱스는 0부터 시작
            int c = Integer.parseInt(st.nextToken()) - 1;
            grid[r][c] = true; // 음식물이 떨어진 위치를 true로 설정
        }

        int maxFoodSize = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (grid[i][j] && !visited[i][j]) { // 음식물이 있고 방문하지 않았다면
                    maxFoodSize = Math.max(maxFoodSize, dfs(i, j)); // DFS를 실행하고 최대 크기를 갱신
                }
            }
        }

        System.out.println(maxFoodSize); // 가장 큰 음식물의 크기 출력
    }

    // DFS를 사용하여 음식물 군집의 크기를 계산
    public static int dfs(int x, int y) {
        visited[x][y] = true;
        int count = 1; // 현재 위치의 음식물을 포함하여 카운트 시작

        for (int i = 0; i < 4; i++) { // 상하좌우 네 방향으로 탐색
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < N && ny >= 0 && ny < M) { // 그리드 범위 내에 있고
                if (grid[nx][ny] && !visited[nx][ny]) { // 음식물이 있고 방문하지 않았다면
                    count += dfs(nx, ny); // 재귀적으로 크기를 더함
                }
            }
        }

        return count; // 연결된 음식물의 총 크기 반환
    }
}
