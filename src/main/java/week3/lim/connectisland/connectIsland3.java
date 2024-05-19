package week3.lim.connectisland;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/42861

import java.util.Arrays;

public class connectIsland3 {
    /**
     *  크루스칼 알고리즘 -> 자바로 구현
     *      - Union-Find 자료구조를 사용하여 각 섬의 연결 상태를 관리.
     *      - 비용을 기준으로 간선을 오름차순 정렬.
     *      - 간선을 하나씩 검사하면서, 연결된 두 섬이 서로 다른 집합에 속해 있다면, 그 간선을 최종 결과에 추가하고 두 섬을 하나의 집합으로 합친다.
     *      - 모든 섬이 연결될 때까지 이 과정을 반복.
     */

    // Union-Find 구조

    // 주어진 원소의 루트 노드를 찾습니다. 경로 압축을 사용하여 효율을 높인다.
    public static int find(int[] parent, int i) {
        if (parent[i] == i) {
            return i;
        }
        return parent[i] = find(parent, parent[i]);
    }

    // 두 원소가 속한 집합을 합칩니다.
    public static void union(int[] parent, int x, int y) {
        int rootX = find(parent, x);
        int rootY = find(parent, y);
        if (rootX != rootY) {
            parent[rootY] = rootX;
        }
    }

    public static int solution(int n, int[][] costs) {
        Arrays.sort(costs, (a, b) -> a[2] - b[2]);
        // 배열을 비용(cost[2])을 기준으로 오름차순 정렬

        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i; // 각 노드의 부모를 자기 자신으로 초기화
        }

        int minCost = 0;
        for (int[] cost : costs) {
            // 정렬된 costs를 순회하면서 각 간선을 처리.
            // 간선의 두 노드가 서로 다른 집합에 속해 있다면 해당 간선을 MST에 추가하고, 비용을 총합에 더한다.

            int start = cost[0];
            int end = cost[1];
            int weight = cost[2];

            // 사이클이 형성되지 않을 경우에만 간선 추가
            if (find(parent, start) != find(parent, end)) {
                union(parent, start, end);
                minCost += weight;
            }
        }

        return minCost;
    }

    public static void main(String[] args) {
        int n1 = 4;
        int[][] costs1 = {{0,1,1},{0,2,2},{1,2,5},{1,3,1},{2,3,8}};
        System.out.println(solution(n1, costs1));   // 4
    }
}
