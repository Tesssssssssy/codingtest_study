package week4.kim.week4_1.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * [가장 먼 노드]
 *
 * n개의 노드가 있는 그래프가 있습니다.
 * 각 노드는 1부터 n까지 번호가 적혀있습니다.
 * 1번 노드에서 가장 멀리 떨어진 노드의 갯수를 구하려고 합니다.
 * 가장 멀리 떨어진 노드란 최단경로로 이동했을 때 간선의 개수가 가장 많은 노드들을 의미합니다.
 *
 * 노드의 개수 n, 간선에 대한 정보가 담긴 2차원 배열 vertex가 매개변수로 주어질 때,
 * 1번 노드로부터 가장 멀리 떨어진 노드가 몇 개인지를 return 하도록 solution 함수를 작성해주세요.
 *
 * 제한사항
 * 노드의 개수 n은 2 이상 20,000 이하입니다.
 * 간선은 양방향이며 총 1개 이상 50,000개 이하의 간선이 있습니다.
 * vertex 배열 각 행 [a, b]는 a번 노드와 b번 노드 사이에 간선이 있다는 의미입니다.
 *
 * 입출력 예
 * n	vertex							                            return
 * 6	[[3, 6], [4, 3], [3, 2], [1, 3], [1, 2], [2, 4], [5, 2]]	3
 *
 * 입출력 예 설명
 * 1번 노드에서 가장 멀리 떨어진 노드는 4,5,6번 노드입니다.
 */

public class Ex1 {
    public static void main(String[] args) {
        Ex1 ex1 = new Ex1();

        // 노드의 개수
        int n = 6;
        // 간선 정보
        int[][] vertex = {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};

        // 3
        System.out.println(ex1.solution(n, vertex));
    }

    public int solution(int n, int[][] edge) {
        // 방문 여부를 체크하는 배열
        boolean[] visited = new boolean[n + 1];
        // 각 노드까지의 거리를 저장하는 배열
        int[] dist = new int[n + 1];
        // 그래프를 표현하는 인접 리스트
        List<List<Integer>> graph = new ArrayList<>();

        // 그래프 초기화
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        // 간선 정보를 바탕으로 그래프를 구성
        for (int[] e : edge) {
            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);
        }

        // BFS를 위한 큐
        Queue<Integer> queue = new LinkedList<>();

        // 시작 노드를 큐에 추가
        queue.add(1);
        // 시작 노드를 방문 처리
        visited[1] = true;

        // 가장 멀리 떨어진 노드까지의 거리
        int maxDist = 0;

        // BFS 실행
        while (!queue.isEmpty()) {
            // 큐에서 노드 하나 꺼냄
            int node = queue.poll();

            // 해당 노드에 인접한 노드들을 확인
            for (int adjNode : graph.get(node)) {
                // 방문하지 않은 노드라면
                if (!visited[adjNode]) {
                    // 거리를 업데이트
                    dist[adjNode] = dist[node] + 1;

                    // 방문 처리
                    visited[adjNode] = true;

                    // 큐에 추가
                    queue.add(adjNode);

                    // 최대 거리 업데이트
                    maxDist = Math.max(maxDist, dist[adjNode]);
                }
            }
        }

        // 가장 멀리 떨어진 노드의 개수
        int count = 0;

        // 모든 노드의 거리를 확인하며 최대 거리와 같은 노드의 개수 확인
        for (int d : dist) {
            if (d == maxDist) {
                count++;
            }
        }

        return count;
    }
}