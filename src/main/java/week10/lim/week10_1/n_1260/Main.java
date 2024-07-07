package week10.lim.week10_1.n_1260;

// 문제: https://www.acmicpc.net/problem/1260

import java.io.*;
import java.util.*;

public class Main {
    /**
     *  1. 그래프를 DFS로 탐색한 결과와 BFS로 탐색한 결과를 출력.
     *  2. 방문할 수 있는 정점이 여러 개인 경우, 정점 번호가 작은 것을 먼저 방문하고
     *     더 이상 방문할 수 있는 점이 없는 경우 종료
     *  3. 정점 번호는 1번부터 N번까지.
     *
     *  4. [입력]
     *     첫째 줄: N (정점의 개수) / M (간선의 개수) / V (탐색 시작 정점 번호)
     *     다음 M줄: 간선이 연결하는 두 정점의 번호
     *              (양방향 연결)
     *  5. [출력]
     *     첫째 줄에 DFS로 수행한 결과 출력
     *     둘째 줄에 BFS로 수행한 결과 출력
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 정점의 수
        int M = Integer.parseInt(st.nextToken()); // 간선의 수
        int V = Integer.parseInt(st.nextToken()); // 시작 정점

        // 그래프 저장 (인접 리스트)
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());  // 각 정점을 위한 리스트 초기화
        }

        /*
            간선 정보 입력
            1 ---- 2        1부터 시작
            |  \   |        DFS: 1 2 4 3
            |   \  |        BFS: 1 2 3 4
            3 ---- 4
        */
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a); // 양방향 연결
        }

        /*
            각 정점의 인접 리스트를 정렬하여 번호가 작은 것을 먼저 방문하도록 한다.
            (문제 조건: 방문할 수 있는 정점이 여러 개인 경우에는 정점 번호가 작은 것을 먼저 방문)
        */
        for (int i = 1; i <= N; i++) {
            Collections.sort(graph.get(i));
        }

        // DFS와 BFS 결과를 저장할 리스트
        List<Integer> dfsResult = new ArrayList<>();
        List<Integer> bfsResult = new ArrayList<>();

        // DFS와 BFS 방문 여부를 저장할 배열
        boolean[] visited = new boolean[N + 1];

        // DFS 탐색 (V 점부터 탐색 시작)
        dfs(V, graph, visited, dfsResult);

        // 이후에 BFS 탐색을 또 해야하므로 이미 사용한 방문 여부 배열 초기화
        Arrays.fill(visited, false);

        // BFS 탐색 (V 점부터 탐색 시작)
        bfs(V, graph, visited, bfsResult);

        for (int node : dfsResult) {
            System.out.print(node + " ");
        }

        System.out.println();

        for (int node : bfsResult) {
            System.out.print(node + " ");
        }
        System.out.println();
    }

    // DFS
    public static void dfs(int v, List<List<Integer>> graph, boolean[] visited, List<Integer> result) {
        // 현재 정점 v를 방문 처리.
        visited[v] = true;
        // 방문한 정점을 결과 리스트에 추가.
        result.add(v);

        // 현재 정점 v에 인접한 모든 정점을 순회.
        for (int neighbor : graph.get(v)) {
            // 인접한 정점 중 아직 방문하지 않은 정점에 대해
            if (!visited[neighbor]) {
                // 재귀적으로 DFS를 호출하여 해당 정점 방문.
                dfs(neighbor, graph, visited, result);
            }
        }
    }

    // BFS
    public static void bfs(int v, List<List<Integer>> graph, boolean[] visited, List<Integer> result) {
        // 탐색을 시작할 정점을 큐에 추가.
        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);
        // 시작 정점 방문 처리.
        visited[v] = true;

        while (!queue.isEmpty()) {
            // 큐에서 정점 하나 꺼내고.
            int node = queue.poll();
            // 꺼낸 정점을 결과 리스트에 추가.
            result.add(node);

            // 꺼낸 정점의 인접한 모든 정점을 순회.
            for (int neighbor : graph.get(node)) {
                // 인접 정점 중 아직 방문하지 않은 정점이 있으면
                if (!visited[neighbor]) {
                    // 해당 정점을 방문 처리하고
                    visited[neighbor] = true;
                    // 큐에 추가.
                    queue.add(neighbor);
                }
            }
        }
    }
}
