package week7.lim.groom_datastructure.adas.practice;

// 문제: https://level.goorm.io/exam/152116/%ED%98%84%EB%8C%80%EB%AA%A8%EB%B9%84%EC%8A%A4-%EC%98%88%EC%84%A0-adas-%EC%8B%9C%EC%8A%A4%ED%85%9C/quiz/1

import java.util.*;
import java.io.*;

public class AdasPrac {
    /**
     * 1. 좌표 평면 위아래 W, 좌표 평면 좌우 H 입력받기
     * 2. 좌표 입력받기 (char)
     * 3. 4개 방향으로 이동할 수 있다. (인점한 노드들)
     *    인점한 노드들을 우선순위 큐에 넣는다.
     * 4. 우선순위가 높은 노드를 먼저 방문한다. (너비 우선 탐색(BFS))
     *      - 우선순위: E(도착점), P, 0
     *      - 우선순위가 같다면 a가 작은 것이 우선, a도 같다면 b가 작은 것이 우선
     * 5. 노드 S와 E는 위험 점수 증가 x
     * 6. 일반 노드(0)는 해당 점 중심으로 3x3 크기에서 자신을 제외한 P-점 개수만큼 위험 점수 증가
     *    P- 노드는 (일반 노드와 동일하게 계산한 위험 점수 - 3) 만큼 위험 점수 증가
     * 7. 위험 점수가 음수이면 0으로 간주.
     * 8. 노드 E에 도착하면 종료
     */
    static int W, H;
    static char[][] grid;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] size = br.readLine().split(" ");
        W = Integer.parseInt(size[0]);
        H = Integer.parseInt(size[1]);
        grid = new char[W][H];
        visited = new boolean[W][H];

        int startX = 0;
        int startY = 0;
        for (int i = 0; i < W; i++) {
            grid[i] = br.readLine().toCharArray();
            for (int j = 0; j < H; j++) {
                if (grid[i][j] == 'S') {
                    startX = i;
                    startY = j;
                }
            }
        }

        int riskScore = simulate(startX, startY);
        System.out.println(Math.max(0, riskScore));
    }

    static int simulate(int startX, int startY) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(startX, startY, 0, 0));
        visited[startX][startY] = true;

        int riskScore = 0;

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            if (grid[current.x][current.y] == 'E') {
                break;
            }
            if (grid[current.x][current.y] == '0' || grid[current.x][current.y] == 'P') {
                int pCount = countP(current.x, current.y);
                if (grid[current.x][current.y] == '0') {
                    riskScore += pCount;
                } else {
                    riskScore += (pCount - 3);
                }
            }

            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];
                if (nx >= 0 && nx < W && ny >= 0 && ny < H && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    int priority = (grid[nx][ny] == 'E' ? 1 : grid[nx][ny] == 'P' ? 2 : 3);
                    pq.add(new Node(nx, ny, priority, nx * H + ny));
                }
            }
        }
        return riskScore;
    }
    static int countP(int x, int y) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }

                int nx = x + i;
                int ny = y + j;

                if (nx >= 0 && nx < W && ny >= 0 && ny < H && grid[nx][ny] == 'P') {
                    count++;
                }
            }
        }
        return count;
    }

    static class Node implements Comparable<Node> {
        int x, y, priority, index;

        public Node(int x, int y, int priority, int index) {
            this.x = x;
            this.y = y;
            this.priority = priority;
            this.index = index;
        }

        @Override
        public int compareTo(Node o) {
            if (this.priority != o.priority) {
                return Integer.compare(this.priority, o.priority);
            }
            return Integer.compare(this.index, o.index);
        }
    }
}
