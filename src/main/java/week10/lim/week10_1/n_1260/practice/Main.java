package week10.lim.week10_1.n_1260.practice;

// 문제: https://www.acmicpc.net/problem/1260

import java.io.*;
import java.util.*;

public class Main {
    /**
     *  1. N (정점의 개수) / M (간선의 개수) / V (탐색 시작 정점 번호) 입력
     *  2. M개 줄만큼 두 정점 입력
     *  3. DFS / BFS 탐색 후 각 결과 출력
     *  4. 방문할 수 있는 정점이 여러 개인 경우, 정점 번호가 작은 것을 먼저 방문
     *     더 이상 방문할 수 있는 점이 없는 경우 종료
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>()); // 인접 리스트 생성
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a); // 양방향 연결
        }

        for (int i = 1; i <= N; i++) {
            Collections.sort(graph.get(i));
        }

        // 탐색 결과 담을 리스트 초기화
        List<Integer> dfsResult = new ArrayList<>();
        List<Integer> bfsResult = new ArrayList<>();

        // dfs, bfs 모두 사용될 방문 표시 배열 초기화
        boolean[] visited = new boolean[N + 1];

        dfs(V, graph, visited, dfsResult);

        // dfs 탐색 후 bfs에서 또 사용해야 하니까 false로 초기화
        Arrays.fill(visited, false);

        bfs(V, graph, visited, bfsResult);

        // 탐색 결과 출력
        for (int res1 : dfsResult) {
            System.out.print(res1 + " ");
        }

        System.out.println();

        for (int res2: bfsResult) {
            System.out.print(res2 + " ");
        }
    }

    private static void dfs(int v, List<List<Integer>> graph, boolean[] visited, List<Integer> dfsResult) {
        // 깊이 우선 탐색(dfs)는 방문할 수 있는 노드들을 우선적으로 쭉 방문한다. (재귀)

        // 시작 노드 방문 처리
        visited[v] = true;
        // 방문한 노드 결과 리스트에 추가
        dfsResult.add(v);

        for (int neighbor : graph.get(v)) {
            if (!visited[neighbor]) {
                // 방문하지 않은 노드라면 재귀 호출로 방문
                dfs(neighbor, graph, visited, dfsResult);
            }
        }
    }

    private static void bfs(int v, List<List<Integer>> graph, boolean[] visited, List<Integer> bfsResult) {
        // 너비 우선 탐색(bfs)는 인접한 노드들을 모두 방문한다.
        Queue<Integer> queue = new LinkedList<>();
        visited[v] = true;
        queue.add(v);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            bfsResult.add(node);
            for (int neighbor : graph.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
    }
}
