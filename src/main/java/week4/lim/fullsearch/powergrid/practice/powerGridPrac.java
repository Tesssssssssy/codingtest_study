package week4.lim.fullsearch.powergrid.practice;

import java.util.ArrayList;
import java.util.List;

public class powerGridPrac {
    /**
     *  <혼자 풀기>
     *  1. 입력받은 wires에서 인접리스트들을 양방향으로 연결
     *  2. 방문한 노드 표시
     *  3. 노드 방문 후 연결 끊고 dfs로 네트워크 개수 확인
     *  4. 나머지 네트워크 개수 확인해서 두 네트워크 개수 뺀 절댓값 반환
     */
    public static int solution(int n, int[][] wires) {
        int answer = 0;

        List<List<Integer>> neighbors = new ArrayList<>();
//        boolean[] visited = new boolean[n + 1];
        // 여기 말고 아래 방문할 때마다 생성해서 표시해주어야 함.
        // 여기에다 하면 각 노드마다 방문한 노드 표시해주지 못함.

        int minDiff = Integer.MAX_VALUE;

        // 방문할 인접 리스트 초기화
        for (int i = 0; i <= n; i++) {
            neighbors.add(new ArrayList<>());
        }

        // 양방향 간선 연결
        for (int[] wire : wires) {
            neighbors.get(wire[0]).add(wire[1]);
            neighbors.get(wire[1]).add(wire[0]);
        }

        // 간선을 하나씩 빼서 방문하면서 연결 끊고 네트워크 개수 세기
        for (int[] wire : wires) {
            boolean[] visited = new boolean[n + 1];

            int nodeA = wire[0];
            int nodeB = wire[1];

            // 연결 끊기
            neighbors.get(nodeA).remove((Integer) nodeB);
            neighbors.get(nodeB).remove((Integer) nodeA);
            // neighbors.get(nodeA).remove(nodeB);
            // -> Inbound 에러 발생

            int networkA = dfs(nodeA, neighbors, visited);
            int networkB = n - networkA;

            int diffAbs = Math.abs(networkA - networkB);
            minDiff = Math.min(minDiff, diffAbs);

            // 끊었던 연결 복구
            neighbors.get(nodeA).add(nodeB);
            neighbors.get(nodeB).add(nodeA);
        }

        answer = minDiff;

        return answer;
    }

    private static int dfs(int node, List<List<Integer>> neighbors, boolean[] visited) {
        visited[node] = true;
        int network = 1;

        for (int neighbor : neighbors.get(node)) {
            if (!visited[neighbor]) {
                network += dfs(neighbor, neighbors, visited);
            }
        }

        return network;
    }

    public static void main(String[] args) {
        int n1 = 9;
        int[][] wires1 = {{1,3},{2,3},{3,4},{4,5},{4,6},{4,7},{7,8},{7,9}};
        System.out.println(solution(n1, wires1));  // 3

        int n2 = 4;
        int[][] wires2 = {{1, 2}, {2, 3}, {3, 4}};
        System.out.println(solution(n2, wires2));  // 0

        int n3 = 7;
        int[][] wires3 = {{1,2},{2,7},{3,7},{3,4},{4,5},{6,7}};
        System.out.println(solution(n3, wires3));  // 1
    }
}
