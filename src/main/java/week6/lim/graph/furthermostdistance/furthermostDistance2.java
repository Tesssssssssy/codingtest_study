package week6.lim.graph.furthermostdistance;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/49189
// 참고: https://suhyeokeee.tistory.com/179

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class furthermostDistance2 {
    static Queue<Integer> q = new LinkedList<>();
    // 큐를 선언하여 BFS를 실행할 때 사용

    static int arr[];
    // 각 노드까지의 최단 거리를 저장할 배열

    static ArrayList<Integer>[] list;
    // 그래프의 연결 상태를 나타내는 인접 리스트

    static boolean visited[];
    // 노드 방문 여부를 체크할 배열
    // 양방향 그래프이므로 큐에서 무한 반복을 방지하기 위해 방문 체크

    // solution 함수는 노드의 수 n과 간선 정보 edge를 입력받아 결과를 반환
    public static int solution(int n, int[][] edge) {
        int ans = 0;
        // 반환할 결과 값을 저장할 변수
        arr = new int[n + 1];
        // n+1 크기의 배열을 선언 (노드 번호가 1부터 시작하므로)
        visited = new boolean[n + 1];
        // 방문 체크 배열 초기화
        list = new ArrayList[n + 1];
        // 각 노드의 인접 리스트를 저장할 배열

        // 인접 리스트 초기화
        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        // edge 배열을 이용하여 인접 리스트 구성
        for (int i = 0; i < edge.length; i++) {
            int a = edge[i][0];
            int b = edge[i][1];
            list[a].add(b);
            // a와 b가 연결되어 있음
            list[b].add(a);
            // b와 a도 연결되어 있음 (양방향)
        } // 연결관계 표현

        // BFS 실행을 위해 시작 노드인 1을 큐에 추가하고 방문 표시
        q.add(1);
        visited[1] = true;
        while (!q.isEmpty()) {
            int a = q.poll();
            // 큐에서 노드를 하나 꺼냄

            // 꺼낸 노드의 모든 인접 노드를 확인
            for (int b : list[a]) {
                if (visited[b]) {
                    // 이미 방문한 노드면 스킵
                    continue;
                }
                q.add(b);
                // 방문하지 않았다면 큐에 추가
                visited[b] = true;
                // 방문 표시
                arr[b] = arr[a] + 1;
                // a 노드에서 b 노드로 거리는 a의 거리에 1을 더한 것
            }
        }

        // arr 배열을 정렬하여 가장 먼 거리 찾기
        Arrays.sort(arr);
        int cnt = 0;
        int max = arr[n];  // 최대 거리 값
        // 배열의 끝에서부터 최대 거리와 같은 값의 개수를 세기
        for (int i = n; i >= 1; i--) {
            if (max == arr[i]) {
                cnt++;
            } else {
                break;  // 최대값이 아닌 경우 루프 종료
            }
        }

        return cnt;  // 가장 멀리 떨어진 노드의 개수 반환
    }

    public static void main(String[] args) {
        int n1 = 6;
        int[][] vertex1 = {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};
        System.out.println(solution(n1, vertex1));  // 3
    }
}
