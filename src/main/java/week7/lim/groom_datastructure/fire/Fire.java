package week7.lim.groom_datastructure.fire;

// 문제: https://level.goorm.io/exam/49051/%EB%B6%88%EC%9D%B4%EC%95%BC/quiz/1

import java.util.*;
import java.io.*;

public class Fire {
    /**
     * BFS(너비 우선 탐색)를 사용하여 해결할 수 있는데, 구름이의 위치에서 불이 번지는 시간을 계산하는 것이 핵심.
     * 주어진 지도에서 구름이의 위치를 기준으로 불이 번지는 패턴을 파악하고, 불이 구름이와 인접해 있는지를 확인하는 것이 필요.
     *
     * 입력 데이터 처리
     *  - RxC 크기의 지도를 입력받고, 구름이의 위치와 불의 초기 위치를 파악.
     *
     * BFS 초기화
     *  - 불이 있는 위치를 모두 BFS 큐에 삽입하고, 해당 위치의 번짐 시간을 0으로 설정.
     *  - 구름이의 위치도 별도로 저장.
     *
     * BFS 실행
     *  - 큐에서 위치를 하나씩 꺼내어 그 위치에서 사방으로 이동할 수 있는지 확인.
     *  - 불이 이동할 수 있는 조건:
     *      - 이동할 위치가 지도 범위 내에 있어야 하며,
     *      - 벽('#')이 아니어야 하며,
     *      - 이미 불이 번진 적이 없는 위치여야 함.
     *  - 이동 가능한 위치에 대해 큐에 삽입하고, 해당 위치까지의 불이 번지는 시간을 저장.
     *
     * 구름이의 안전 여부 판단
     *  - 구름이의 위치에서 상하좌우를 검사하여 불이 번지는 시간을 확인.
     *  - 구름이의 주변 네 칸 중 하나라도 불이 번진 시간이 있다면,
     *    가장 작은 시간이 구름이가 안전하게 자료를 챙길 수 있는 시간.
     *  - 만약 주변 네 칸 모두 불이 번질 수 없는 경우에는 -1을 출력.
     */
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken()); // 행
        int C = Integer.parseInt(st.nextToken()); // 열

        char[][] map = new char[R][C];           // 연구실 지도
        int[][] fireSpreadTime = new int[R][C];  // 불이 번지는 시간을 저장하는 배열
        Queue<int[]> fireQueue = new LinkedList<>(); // 불이 번지는 위치를 저장하는 큐
        int cloudX = -1, cloudY = -1;
        /*
            구름이의 위치 초기화 -1, -1로 하는 이유
                이후 코드에서 구름이의 위치를 실제로 찾지 못했을 경우에 대한 예외 처리를 쉽게 할 수 있다.
                만약 입력에 구름이의 위치가 실제로 주어지지 않았다면 이러한 초기값이 그대로 유지되어,
                이상한 위치에서 계산을 시도하는 것을 방지할 수 있다.
         */

        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
            Arrays.fill(fireSpreadTime[i], -1);
            /*
                불이 도달하지 않은 위치는 -1로 초기화
                    어떤 위치에 불이 번지지 않았는지 쉽게 파악할 수 있다.
                    초기값을 0이나 다른 값으로 설정하면
                    추가적인 상태를 체크하는 배열이 필요하거나 다른 방식으로 처리해야 할 수도 있다.
             */
            for (int j = 0; j < C; j++) {
                if (map[i][j] == '@') {
                    fireQueue.offer(new int[]{i, j}); // 불 시작 위치를 큐에 추가
                    fireSpreadTime[i][j] = 0; // 시작 위치의 불 번짐 시간은 0
                } else if (map[i][j] == '&') {
                    cloudX = i; // 구름이의 위치 저장
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

                // 범위를 벗어나지 않고, 벽이 아니며, 아직 불이 번지지 않은 위치일 경우
                if (nx >= 0 && nx < R && ny >= 0 && ny < C && map[nx][ny] != '#' && fireSpreadTime[nx][ny] == -1) {
                    fireSpreadTime[nx][ny] = fireSpreadTime[x][y] + 1; // 번짐 시간 업데이트
                    fireQueue.offer(new int[]{nx, ny}); // 새 위치를 큐에 추가
                }
            }
        }

        // 구름이 주변의 불 번짐 시간 중 최소값을 찾음
        int minTime = Integer.MAX_VALUE;
        for (int d = 0; d < 4; d++) {
            int nx = cloudX + dx[d];
            int ny = cloudY + dy[d];
            // 범위를 벗어나지 않고 불이 번진 시간이 있는 경우 최소 시간 갱신
            if (nx >= 0 && nx < R && ny >= 0 && ny < C && fireSpreadTime[nx][ny] != -1) {
                minTime = Math.min(minTime, fireSpreadTime[nx][ny]);
            }
        }

        // 구름이가 탈출할 수 있는 최소 시간을 출력
        if (minTime == Integer.MAX_VALUE) {
            System.out.println(-1); // 불이 구름이 주변에 번지지 않은 경우
        } else {
            System.out.println(minTime); // 구름이 주변에 불이 번진 최소 시간
        }
    }
}