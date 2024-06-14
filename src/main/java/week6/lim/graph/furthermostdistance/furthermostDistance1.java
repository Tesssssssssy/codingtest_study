package week6.lim.graph.furthermostdistance;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/49189

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class furthermostDistance1 {
    /**
     *    그래프 초기화
     *      - 노드 수 n에 맞게 graph 리스트를 초기화하고 각 노드마다 연결된 노드 목록을 저장.
     *    BFS 준비
     *      - BFS를 위해 큐(queue)를 사용하고,
     *        방문 여부를 확인할 visited 배열과 각 노드까지의 거리를 저장할 distances 배열을 초기화.
     *    BFS 실행
     *      - BFS를 통해 모든 노드를 방문하며 각 노드까지의 최단 거리를 계산.
     *    최대 거리 계산 및 카운트
     *      - 계산된 거리 중 최대 값을 찾고, 해당 거리를 가진 노드의 수를 세어 반환.
     *
     *    최악의 경우에도 시간 복잡도는 O(V + E)로 주어진 범위 내에서 처리 가능.
     */
    public static int solution(int n, int[][] edge) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());  // 각 노드 번호에 대응하는 인접 리스트 초기화
        }

        // 그래프 구성
        for (int[] link : edge) {
            graph.get(link[0]).add(link[1]);  // 노드 link[0]의 인접 리스트에 link[1] 추가
            graph.get(link[1]).add(link[0]);  // 노드 link[1]의 인접 리스트에 link[0] 추가 (양방향)
        }

        // BFS 초기 설정
        Queue<Integer> queue = new LinkedList<>();  // BFS를 위한 큐 생성
        boolean[] visited = new boolean[n + 1];  // 각 노드의 방문 여부를 저장할 배열
        int[] distances = new int[n + 1];  // 1번 노드로부터 각 노드까지의 거리를 저장할 배열

        queue.add(1);  // 1번 노드를 큐에 추가
        visited[1] = true;  // 1번 노드를 방문했다고 표시
        distances[1] = 0;  // 1번 노드의 거리는 0

        // BFS 실행
        while (!queue.isEmpty()) {
            int current = queue.poll();  // 큐에서 다음 노드를 꺼냄
            for (int neighbor : graph.get(current)) {  // 현재 노드의 인접 노드를 순회
                if (!visited[neighbor]) {  // 방문하지 않은 노드에 대해
                    visited[neighbor] = true;  // 방문했다고 표시
                    queue.add(neighbor);  // 큐에 추가
                    distances[neighbor] = distances[current] + 1;  // 거리는 이전 노드 거리에 1을 더한 값
                }
            }
        }

        // 최대 거리 찾기
        int maxDistance = 0;
        for (int i = 1; i <= n; i++) {
            if (distances[i] > maxDistance) {
                maxDistance = distances[i];  // 가장 먼 거리 업데이트
            }
        }

        // 최대 거리를 가지는 노드의 개수 계산
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (distances[i] == maxDistance) {
                count++;  // 최대 거리와 같은 거리를 가진 노드 수 카운트
            }
        }

        return count;  // 결과 반환
    }

    public static void main(String[] args) {
        int n1 = 6;
        int[][] vertex1 = {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};
        System.out.println(solution(n1, vertex1));  // 3
    }
}