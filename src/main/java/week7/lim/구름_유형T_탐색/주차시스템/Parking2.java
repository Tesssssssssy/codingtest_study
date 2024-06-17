package week7.lim.구름_유형T_탐색.주차시스템;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Parking2 {
    /**
     *  <DFS 사용한 버전 (재귀 호출)>
     *
     *  주차장 정보 입력: 각 칸의 상태(0, 1, 2)를 입력 받음.
     *  DFS로 구역 탐색: 각 칸을 시작점으로 하여 DFS를 실행.
     *                방문하지 않은 칸이면서 자동차(1)가 없는 칸에서 시작.
     *  점수 계산: 구역을 탐색하면서 점수를 계산.
     *          빈 칸(0)은 +1점, 장애인 전용 칸(2)은 -2점.
     *  최대 점수 갱신: 각 구역의 점수를 계산한 후 최대 점수를 갱신.
     *  결과 출력: 모든 구역을 탐색한 후, 가장 높은 점수가 음수인 경우 0을 출력.
     *                             그렇지 않은 경우 최대 점수를 출력.
     */
    static int n, m;
    static int[][] parkingLot;
    static boolean[][] visited;
    static int maxScore = 0;
    static int[] dx = {-1, 1, 0, 0};  // x 방향 이동
    static int[] dy = {0, 0, -1, 1};  // y 방향 이동

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());  // 세로 크기
        m = Integer.parseInt(st.nextToken());  // 가로 크기

        parkingLot = new int[n][m];  // 주차장 상태를 저장할 2차원 배열
        visited = new boolean[n][m];  // 방문 여부를 확인할 배열

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                parkingLot[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && parkingLot[i][j] != 1) {
                    int score = dfs(i, j);  // 현재 구역의 점수를 계산
                    maxScore = Math.max(maxScore, score);  // 최대 점수 갱신
                }
            }
        }

        System.out.println(Math.max(maxScore, 0));  // 결과 출력
    }

    private static int dfs(int x, int y) {
        visited[x][y] = true;
        int score = 0;

        if (parkingLot[x][y] == 0) score += 1;
        else if (parkingLot[x][y] == 2) score -= 2;

        for (int k = 0; k < 4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];

            if (nx >= 0 && nx < n && ny >= 0 && ny < m && !visited[nx][ny] && parkingLot[nx][ny] != 1) {
                score += dfs(nx, ny);
            }
        }
        return score;
    }
}

