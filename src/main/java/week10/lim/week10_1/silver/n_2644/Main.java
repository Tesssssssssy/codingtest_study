package week10.lim.week10_1.silver.n_2644;

// 문제: https://www.acmicpc.net/problem/2644

import java.io.*;
import java.util.*;

public class Main {
    /**
     *  1. 부모-자식: 1촌
     *     나-할아버지: 2촌
     *     나-아버지형제: 3촌
     *  2. 주어진 두 사람의 촌수를 계산 후 출력
     *
     *  [왜 BFS?]
     *  - 촌수 문제 -> 기본적으로 그래프 문제
     *  - 두 사람 사이의 촌수 계산 -> 그래프에서 두 노드 사이의 최단 경로를 찾는 문제와 동일
     *  - 가중치가 없으므로 BFS가 최단 경로를 찾는데 이상적
     *    (BFS는 각 간선의 비용이 동일할 때 정확하고 효율적으로 최단 경로를 찾을 수 있다.)
     *  - 부모-자식 관계는 양방향으로 고려
     *    -> BFS가 각 노드에서 인접한 모든 노드로 확장되면서 탐색을 진행하는 방식과 잘 맞는다.
     *
     *  [예제 의미]
     *  총 9명
     *  7번과 3번의 촌수 계산
     *  7개의 부모-자식 관계 제공
     *  1번 - 2, 3번의 부모
     *  2번 - 7, 8, 9번의 부모
     *  4번 - 5, 6번의 부모
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine()); // 전체 사람의 수
        String[] wantToKnow = br.readLine().split(" ");
        int person1 = Integer.parseInt(wantToKnow[0]);
        int person2 = Integer.parseInt(wantToKnow[1]);
        int m = Integer.parseInt(br.readLine()); // 부모 자식들 간의 관계의 수

        // 그래프 초기화
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        // 관계 입력
        for (int i = 0; i < m; i++) {
            String[] parentsChildren = br.readLine().split(" ");
            int parent = Integer.parseInt(parentsChildren[0]);
            int child = Integer.parseInt(parentsChildren[1]);
            graph.get(parent).add(child);
            graph.get(child).add(parent); // 양방향 연결
        }

        // BFS 실행
        System.out.println(BFS(graph, person1, person2, n));
    }

    // BFS로 최단 촌수 계산
    private static int BFS(List<List<Integer>> graph, int start, int end, int n) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        int[] distance = new int[n + 1]; // 해당 사람까지의 촌수 저장

        queue.add(start);
        visited[start] = true;
        distance[start] = 0;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int neighbor : graph.get(current)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                    distance[neighbor] = distance[current] + 1;

                    if (neighbor == end) {
                        return distance[neighbor]; // 목표 인물을 찾으면 즉시 반환
                    }
                }
            }
        }

        return -1; // 연결되지 않은 경우
    }
}