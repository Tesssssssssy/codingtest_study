package week7.lim.구름_유형T_탐색.주차시스템;

// 문제: https://level.goorm.io/exam/152115/%ED%98%84%EB%8C%80%EB%AA%A8%EB%B9%84%EC%8A%A4-%EC%98%88%EC%84%A0-%EC%A3%BC%EC%B0%A8%EC%8B%9C%EC%8A%A4%ED%85%9C/quiz/1

import java.util.*;
import java.io.*;

public class Parking1 {
    /**
     * <DFS 사용한 재귀 호출 방식 사용하면 RUN TIME ERROR>
     *
     *  주차장 정보 입력: 각 칸의 상태(0, 1, 2)를 입력 받음.
     *  BFS로 구역 탐색: 각 칸을 시작점으로 하여 BFS를 실행.
     *                방문하지 않은 칸이면서 자동차(1)가 없는 칸에서 시작.
     *  점수 계산: 구역을 탐색하면서 점수를 계산.
     *          빈 칸(0)은 +1점, 장애인 전용 칸(2)은 -2점.
     *  최대 점수 갱신: 각 구역의 점수를 계산한 후 최대 점수를 갱신.
     *  결과 출력: 모든 구역을 탐색한 후, 가장 높은 점수가 음수인 경우 0을 출력.
     *                             그렇지 않은 경우 최대 점수를 출력.
     */
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();  // 첫 번째 줄 입력을 읽습니다.
        StringTokenizer st = new StringTokenizer(input);
        int n = Integer.parseInt(st.nextToken());  // 세로 크기
        int m = Integer.parseInt(st.nextToken());  // 가로 크기

        int[][] parkingLot = new int[n][m];  // 주차장 상태를 저장할 2차원 배열
        boolean[][] visited = new boolean[n][m];  // 방문 여부를 확인할 배열

        // 주차장 정보 입력 받기
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            // 각 행의 주차 상태를 입력 받습니다.

            for (int j = 0; j < m; j++) {
                parkingLot[i][j] = Integer.parseInt(st.nextToken());
                // 각 칸의 주차 상태를 저장합니다.
            }
        }

        // BFS를 사용하여 구역을 탐색하고 점수를 계산
        int maxScore = 0;  // 최대 점수를 저장할 변수
        int[] dx = {-1, 1, 0, 0};  // x 방향 이동
        int[] dy = {0, 0, -1, 1};  // y 방향 이동

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && parkingLot[i][j] != 1) {
                    // 방문하지 않은 빈 칸(0) 또는 장애인 구역(2)에서 시작

                    int score = 0;  // 현재 구역의 점수를 계산할 변수
                    Queue<int[]> queue = new LinkedList<>();
                    queue.offer(new int[]{i, j});
                    visited[i][j] = true;

                    while (!queue.isEmpty()) {
                        int[] current = queue.poll();
                        int x = current[0];
                        int y = current[1];

                        // 점수 계산
                        if (parkingLot[x][y] == 0) score += 1;  // 빈 칸은 점수 +1
                        else if (parkingLot[x][y] == 2) score -= 2;  // 장애인 구역은 점수 -2

                        // 주변 칸 탐색
                        for (int k = 0; k < 4; k++) {
                            int nx = x + dx[k];
                            int ny = y + dy[k];
                            if (nx >= 0 && nx < n && ny >= 0 && ny < m
                                    && !visited[nx][ny] && parkingLot[nx][ny] != 1) {
                                visited[nx][ny] = true;
                                queue.offer(new int[]{nx, ny});
                            }
                        }
                    }

                    // 최대 점수 갱신
                    maxScore = Math.max(maxScore, score);
                }
            }
        }

        // 최대 점수가 음수이면 0 출력, 아니면 최대 점수 출력
        System.out.println(Math.max(maxScore, 0));
    }
}
