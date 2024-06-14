package week6.lim.graph.rank;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/49191

public class rank1 {
    /**
     *  각 선수를 정점으로 하고, A 선수가 B 선수를 이긴다는 경기 결과를
     *  방향 간선으로 나타낼 수 있는 방향 그래프를 생각할 수 있다.
     *  그래프를 통해 플로이드-워셜 알고리즘을 사용하여 모든 선수 간의 승패 관계를 파악할 수 있다.
     *  플로이드-워셜 알고리즘을 사용하면 각 선수가 다른 선수에게 이기거나 진 결과를 전체적으로 확인할 수 있다.
     *
     *  시작점 : s, 끝점 : e, 중간점 : k => dist[s][e] = min(dist[s][e], dist[s][k] + dist[k][e])
     *  -> 플로이드-워셜 알고리즘은 이 논리를 사용
     *     k, s, e는 3중 for문을 통해 모든 경우의 수를 비교
     *      => O(V^3)의 시간복잡도를 갖게 됨
     *
     *  그래프 초기화
     *      - graph 배열을 선언하여 선수들 간의 승패 관계를 저장.
     *      인덱스 i에서 j로의 값이 true면 i 선수가 j 선수를 이겼다는 의미.
     *  플로이드-워셜 알고리즘 실행
     *      - 모든 선수 쌍에 대하여 간접적인 승리 관계를 파악하여 그래프를 업데이트.
     *  확정된 순위 계산
     *      - 각 선수에 대해, 그 선수와 직접적 또는 간접적으로 승패 관계가 확정된 선수의 수를 세어
     *      전체 선수 수 n-1과 같은 경우 순위를 확정할 수 있음을 확인.
     *  결과 반환
     *      - 순위를 확정할 수 있는 선수의 수를 반환.
     *
     */
    public static int solution(int n, int[][] results) {
        int answer = 0;
        // 정확한 순위를 매길 수 있는 선수의 수

        boolean[][] graph = new boolean[n + 1][n + 1];
        // 선수들간의 승패를 저장할 그래프

        // 결과 입력받기, A가 B를 이긴다면 graph[A][B] = true
        for (int[] result : results) {
            int winner = result[0];
            int loser = result[1];
            graph[winner][loser] = true;
        }

        // 플로이드-워셜 알고리즘으로 각 선수간 도달 가능성 계산
        // k: 중간 지점, i: 출발 지점, j: 도착 지점
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (graph[i][k] && graph[k][j]) {
                        // i가 k를 이기고 k가 j를 이긴다면 i는 j를 이길 수 있음

                        graph[i][j] = true;
                    }
                }
            }
        }

        /*
            각 선수에 대해,
            자신이 이긴 선수와 자신에게 진 선수의 총 수가 n-1이면 순위를 확정할 수 있다.
            (5명 중 4위에게 지면 나는 자동으로 5위가 되니까)
         */
        for (int i = 1; i <= n; i++) {
            int known = 0;
            for (int j = 1; j <= n; j++) {
                if (graph[i][j] || graph[j][i]) {
                    // i가 j를 이기거나 j가 i를 이기는 관계가 확인된 경우

                    known++;
                }
            }
            if (known == n - 1) {
                // 모든 선수와의 관계가 명확하면

                answer++;  // 순위 확정 가능한 선수 수 증가
            }
        }

        return answer;  // 결과 반환
    }

    public static void main(String[] args) {
        int n1 = 5;
        int[][] results1 = {{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}};
        System.out.println(solution(n1, results1)); // 2
    }
}
