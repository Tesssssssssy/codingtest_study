package week6.lim.bfsdfs.gamemap;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/1844

import java.util.LinkedList;
import java.util.Queue;

public class gameMap1 {
    /**
     * dx와 dy 배열은 각각 x, y 좌표에서 이동할 수 있는 방향을 정의.
     *
     * distance 배열은 각 위치까지 이동하는 데 필요한 최소 거리를 저장.
     *
     * queue는 BFS를 위한 대기열로, 초기에 시작 위치를 추가.
     *
     * BFS 루프 내에서 현재 위치에서 상, 하, 좌, 우로 이동 가능한지 검사하고,
     * 이동할 수 있는 위치에 대해 거리를 갱신하며 큐에 추가.
     *
     * BFS 수행 후, 목적지인 (n-1, m-1) 위치의 distance 값이 0이면 도달하지 못한 것이므로 -1을 반환,
     * 그렇지 않으면 해당 거리 값을 반환.
     */
    public static int solution(int[][] maps) {
        int answer = 0;

        // 맵의 크기
        int n = maps.length;
        int m = maps[0].length;

        // 이동 가능한 방향 정의 (동, 서, 남, 북)
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        // 방문 및 거리 기록 배열
        int[][] distance = new int[n][m];

        // BFS를 위한 큐
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0}); // 시작점 (1,1)을 (0,0)으로 인덱스 조정하여 추가
        distance[0][0] = 1; // 시작 위치 거리 초기화

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            // 4가지 방향으로의 이동 시도
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 맵의 범위를 벗어나지 않고, 이동할 수 있는 위치이며, 아직 방문하지 않았다면
                if (nx >= 0 && nx < n && ny >= 0 && ny < m
                                && maps[nx][ny] == 1
                                && distance[nx][ny] == 0) {
                    distance[nx][ny] = distance[x][y] + 1; // 거리 업데이트
                    queue.offer(new int[]{nx, ny}); // 큐에 추가
                }
            }
        }

        // 도착 지점까지의 거리 반환, 도달하지 못했다면 -1 반환
        answer = distance[n - 1][m - 1] == 0 ? -1 : distance[n - 1][m - 1];

        return answer;
    }

    public static void main(String[] args) {
        int[][] maps1 = {
                {1, 0, 1, 1, 1},
                {1, 0, 1, 0, 1},
                {1, 0, 1, 1, 1},
                {1, 1, 1, 0, 1},
                {0, 0, 0, 0, 1}
        };
        System.out.println(solution(maps1));  // 11

        int[][] maps2 = {
                {1, 0, 1, 1, 1},
                {1, 0, 1, 0, 1},
                {1, 0, 1, 1, 1},
                {1, 1, 1, 0, 0},
                {0, 0, 0, 0, 1}
        };
        System.out.println(solution(maps2));  // -1
    }
}
