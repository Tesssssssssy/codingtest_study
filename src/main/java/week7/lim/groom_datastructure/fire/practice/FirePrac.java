package week7.lim.groom_datastructure.fire.practice;

// 문제: https://level.goorm.io/exam/49051/%EB%B6%88%EC%9D%B4%EC%95%BC/quiz/1

import java.util.*;
import java.io.*;

public class FirePrac {
    /**
     *  1. 연구실 크기 R, C 입력받기 (R x C)
     *  2. 문자열 입력 받기
     *  3. 불이 번지는 시간 저장할 2차원 배열 필요
     *  4. 불이 번지는 칸 저장할 자료구조 필요
     *  5. 방문할 수 있는 노드들을 방문하면서 탐색해야 하므로
     *     너비 우선 탐색(BFS)으로 진행
     *  6. '#'는 벽 / '&'는 구름이 위치 / '@' 불길 시작
     *  7. '&'에 도착하면 종료
     *  8. 주변에 불이 났으면 반환하고 불이 안났으면 -1 반환
     */

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        char[][] map = new char[R][C];
        int[][] fireSpreadTime = new int[R][C];
        Queue<int[]> fireQueue = new LinkedList<>();
        int cloudX = -1;
        int cloudY = -1;

        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
            Arrays.fill(fireSpreadTime[i], -1);

            for (int j = 0; j < C; j++) {
                if (map[i][j] == '@') {
                     fireQueue.add(new int[]{i, j});
                     fireSpreadTime[i][j] = 0;
                } else if (map[i][j] == '&') {
                    cloudX = i;
                    cloudY = j;
                }
            }
        }

        // BFS를 통해 불이 번지는 시뮬레이션 실행
        while (!fireQueue.isEmpty()) {
            int[] pos = fireQueue.poll();
            int x = pos[0], y = pos[1];

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx >= 0 && nx < R && ny >= 0 && ny < C && map[nx][ny] != '#' && fireSpreadTime[nx][ny] == -1) {
                    fireSpreadTime[nx][ny] = fireSpreadTime[x][y] + 1;
                    fireQueue.offer(new int[]{nx, ny});
                }
            }
        }

        int minTime = Integer.MAX_VALUE;
        for (int d = 0; d < 4; d++) {
            int nx = cloudX + dx[d];
            int ny = cloudY + dy[d];
            if (nx >= 0 && nx < R && ny >= 0 && ny < C && fireSpreadTime[nx][ny] != -1) {
                minTime = Math.min(minTime, fireSpreadTime[nx][ny]);
            }
        }

        if (minTime == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(minTime);
        }

    }
}
