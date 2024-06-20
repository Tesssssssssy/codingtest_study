package week7.lim.groom_datastructure.adas;

// 문제: https://level.goorm.io/exam/152116/%ED%98%84%EB%8C%80%EB%AA%A8%EB%B9%84%EC%8A%A4-%EC%98%88%EC%84%A0-adas-%EC%8B%9C%EC%8A%A4%ED%85%9C/quiz/1

import java.util.*;
import java.io.*;

public class Adas {
    /**
     *  너비 우선 탐색(BFS) 알고리즘과 우선순위 큐(Priority Queue)를 사용.
     *      - PriorityQueue를 사용하여 우선순위에 따라 노드를 탐색하고, 방문한 노드에 대한 위험 점수를 계산.
     *      - Node 클래스는 좌표, 우선순위 및 정렬 인덱스를 관리하여 우선순위 큐에서 적절한 순서로 노드를 처리.
     *
     *  초기화
     *      - 시작점(S) 위치를 찾고, 우선순위 큐에 이를 추가.
     *        이 큐는 각 노드의 방문 우선순위(우선순위 순서: E, P, 0)와 좌표를 고려하여 정렬됨.
     *  BFS 실행
     *      - 우선순위에 따라 노드를 방문하고, 각 노드에 대해 주변 노드를 탐색하여 큐에 추가.
     *        또한 방문한 각 노드에 대해 위험 점수를 계산.
     *  위험 점수 계산
     *      - 일반 점(0)과 P-점에서 주변 3x3 영역을 조사하여 P-점의 수를 세고,
     *        이를 통해 위험 점수를 갱신.
     *  종료 조건
     *      - E점에 도착하면 탐색 종료.
     */
    static int W, H; // 격자의 폭과 높이
    static char[][] grid; // 격자를 나타내는 2차원 배열
    static boolean[][] visited; // 방문 여부를 확인하는 2차원 배열
    static int[] dx = {-1, 0, 1, 0}; // x축 이동을 위한 배열 (상, 하 이동)
    static int[] dy = {0, -1, 0, 1}; // y축 이동을 위한 배열 (좌, 우 이동)

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] size = br.readLine().split(" ");
        W = Integer.parseInt(size[0]); // 격자의 폭 입력
        H = Integer.parseInt(size[1]); // 격자의 높이 입력
        grid = new char[W][H];
        visited = new boolean[W][H];

        int startX = 0, startY = 0; // 시작 좌표 초기화
        for (int i = 0; i < W; i++) {
            grid[i] = br.readLine().toCharArray();
            for (int j = 0; j < H; j++) {
                if (grid[i][j] == 'S') { // 시작점 'S' 위치 파악
                    startX = i;
                    startY = j;
                }
            }
        }

        int riskScore = simulate(startX, startY); // 시뮬레이션 실행
        System.out.println(Math.max(0, riskScore)); // 위험 점수가 음수면 0으로 처리
    }

    static int simulate(int startX, int startY) {
        PriorityQueue<Node> queue = new PriorityQueue<>(); // 우선순위 큐 사용
        queue.add(new Node(startX, startY, 0, 0)); // 시작점을 큐에 추가
        visited[startX][startY] = true; // 시작점 방문 처리
        int riskScore = 0; // 위험 점수 초기화

        while (!queue.isEmpty()) {
            Node current = queue.poll(); // 우선순위가 가장 높은 노드를 가져온다
            if (grid[current.x][current.y] == 'E')
                break; // 도착점 E에 도달하면 반복 종료

            // 현재 노드에 대한 위험 점수 계산
            if (grid[current.x][current.y] == '0' || grid[current.x][current.y] == 'P') {
                int pCount = countP(current.x, current.y); // 주변 P-점 개수 계산
                if (grid[current.x][current.y] == '0') {
                    riskScore += pCount;
                } else {
                    riskScore += (pCount - 3);
                }
            }

            // 인접한 노드를 큐에 추가
            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i]; // 현재 노드의 x 좌표에서 상, 하, 좌, 우로 이동
                int ny = current.y + dy[i]; // 현재 노드의 y 좌표에서 상, 하, 좌, 우로 이동
                // nx, ny가 격자 범위 내에 있고, 아직 방문하지 않은 노드라면
                if (nx >= 0 && nx < W && ny >= 0 && ny < H && !visited[nx][ny]) {
                    visited[nx][ny] = true; // 해당 노드를 방문 처리
                    // 다음 노드의 우선순위 설정: 'E'는 1로 가장 높고, 'P'는 2, 그 외 ('0')는 3으로 설정
                    int priority = (grid[nx][ny] == 'E' ? 1 : grid[nx][ny] == 'P' ? 2 : 3);
                    // 우선순위 큐에 노드 추가
                    queue.add(new Node(nx, ny, priority, nx * H + ny));
                    /*
                        [nx * H + ny]를 사용하는 이유는 각 노드의 고유한 인덱스를 생성하기 위함.
                        이 인덱스는 노드의 위치를 일차원적으로 표현할 때 사용되며,
                        우선순위 큐에서 같은 우선순위를 가진 노드들 사이에서 순서를 결정하는 데 도움을 줌.
                    */
                }
            }
        }
        return riskScore;
    }

    // 주변 3x3 격자 내의 P-점 개수를 계산하는 함수
    static int countP(int x, int y) {
        int count = 0; // 주변에 있는 'P' 타일의 수를 세기 위한 변수
        // 중심점 (x, y)를 기준으로 주변 3x3 격자를 검사
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue; // 중심점은 세지 않음
                int nx = x + i, ny = y + j;
                // nx, ny가 격자 범위 내에 있고, 해당 위치가 'P'인 경우
                if (nx >= 0 && nx < W && ny >= 0 && ny < H && grid[nx][ny] == 'P') {
                    count++; // 'P' 타일의 수를 증가
                }
            }
        }
        return count; // 주변에 있는 'P' 타일의 총 수 반환
    }

    // 노드를 나타내는 클래스 (우선순위 큐에서 사용)
    static class Node implements Comparable<Node> {
        int x, y, priority, index; // 좌표, 우선순위, 인덱스

        Node(int x, int y, int priority, int index) {
            this.x = x;
            this.y = y;
            this.priority = priority;
            this.index = index;
        }

        // 노드 비교를 위한 메소드 (우선순위와 좌표에 따라 정렬)
        @Override
        public int compareTo(Node other) {
            // 두 노드의 우선순위를 비교
            if (this.priority != other.priority)
                return Integer.compare(this.priority, other.priority);
            // 우선순위가 같은 경우, 노드의 위치 인덱스를 비교
            return Integer.compare(this.index, other.index);
        }
    }
}