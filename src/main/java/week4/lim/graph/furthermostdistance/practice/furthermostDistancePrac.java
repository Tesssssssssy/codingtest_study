package week4.lim.graph.furthermostdistance.practice;

import java.util.*;

public class furthermostDistancePrac {
    /**
     *  <안보고 혼자 풀이한 버전>
     *  1. 각 노드가 방문할 수 있는 인접리스트 필요
     *  2. 입력받은 vertex 정보로 양방향 설정 필요
     *  3. 방문했다는 것을 표시할 배열 필요
     *  4. bfs로 탐색해야 하는데 이를 위해 Queue 필요
     *  5. 최대 거리와 같은 노드들 찾아서 count 세서 반환
     */

    public static int solution(int n, int[][] edges) {
        int answer = 0;

        List<List<Integer>> list = new ArrayList<>();
        boolean[] visited = new boolean[n + 1];
        int[] distance = new int[n + 1];

        // 1. 각 노드가 방문할 수 있는 인접리스트 설정
        for (int i = 0; i < edges.length; i++) {
            list.add(new ArrayList<>());
        }

        // 2. 입력받은 vertex 정보로 양방향 설정
        for (int[] edge : edges) {
            list.get(edge[0]).add(edge[1]);
            list.get(edge[1]).add(edge[0]);
        }

        // 4. bfs로 탐색하기 위해 Queue 초기화
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        visited[1] = true;
        distance[1] = 0; // 1번부터 시작하니까 1까지의 거리는 0

        int maxDistance = 0;

        while (!queue.isEmpty()) {
            Integer temp = queue.poll();
            for (int neighbor : list.get(temp)) {
                // 현재 방문한 노드에서 갈 수 있는 인접리스트 가져와서 또 탐색해야 함
                // 이게 BFS 방식

              if (!visited[neighbor]) {
                  visited[neighbor] = true;
                  queue.add(neighbor);
                  distance[neighbor] = distance[temp] + 1;
              }
            }
        }

        for (int i = 1; i <= n; i++) {
            if (distance[i] > maxDistance) {
                maxDistance = distance[i];  // 가장 먼 거리 업데이트
            }
        }

        // 최대 거리를 가지는 노드의 개수 계산
        for (int i = 1; i <= n; i++) {
            if (distance[i] == maxDistance) {
                answer++;  // 최대 거리와 같은 거리를 가진 노드 수 카운트
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int n1 = 6;
        int[][] vertex1 = {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};
        System.out.println(solution(n1, vertex1));  // 3
    }
}
