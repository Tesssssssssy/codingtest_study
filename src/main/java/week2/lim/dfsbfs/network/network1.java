package week2.lim.dfsbfs.network;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/43162

public class network1 {
    /**
     *  DFS를 이용한 풀이
     */
    public static int solution(int n, int[][] computers) {
        boolean[] visited = new boolean[n]; // 방문한 노드를 체크할 배열
        int count = 0; // 네트워크 수

        // 모든 컴퓨터를 순회하면서 네트워크 찾기
        for (int i = 0; i < n; i++) {
            if (!visited[i]) { // 아직 방문하지 않은 컴퓨터에서 DFS 시작
                dfs(computers, visited, i);
                count++; // 네트워크 카운트 증가
            }
        }

        return count;
    }

    private static void dfs(int[][] computers, boolean[] visited, int start) {
        visited[start] = true; // 현재 노드 방문 처리

        for (int i = 0; i < computers.length; i++) {
            // 현재 노드와 연결되어 있고 아직 방문하지 않은 노드를 방문
            if (computers[start][i] == 1 && !visited[i]) {
                dfs(computers, visited, i);
            }
        }
    }

    public static void main(String[] args) {
        int[][] computers1 = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        int n1 = 3;
        System.out.println(solution(n1, computers1)); //  2

        int[][] computers2 = {{1, 1, 0}, {1, 1, 1}, {0, 1, 1}};
        int n2 = 3;
        System.out.println(solution(n2, computers2)); //  1
    }
}
