package week4.kim.week4_1.graph;

/**
 * [순위]
 *
 * n명의 권투선수가 권투 대회에 참여했고 각각 1번부터 n번까지 번호를 받았습니다.
 * 권투 경기는 1대1 방식으로 진행이 되고, 만약 A 선수가 B 선수보다 실력이 좋다면 A 선수는 B 선수를 항상 이깁니다.
 * 심판은 주어진 경기 결과를 가지고 선수들의 순위를 매기려 합니다.
 * 하지만 몇몇 경기 결과를 분실하여 정확하게 순위를 매길 수 없습니다.
 *
 * 선수의 수 n, 경기 결과를 담은 2차원 배열 results가 매개변수로 주어질 때
 * 정확하게 순위를 매길 수 있는 선수의 수를 return 하도록 solution 함수를 작성해주세요.
 *
 * 제한사항
 * 선수의 수는 1명 이상 100명 이하입니다.
 * 경기 결과는 1개 이상 4,500개 이하입니다.
 * results 배열 각 행 [A, B]는 A 선수가 B 선수를 이겼다는 의미입니다.
 * 모든 경기 결과에는 모순이 없습니다.
 *
 * 입출력 예
 * n	results					                    return
 * 5	[[4, 3], [4, 2], [3, 2], [1, 2], [2, 5]]	2
 *
 * 입출력 예 설명
 * 2번 선수는 [1, 3, 4] 선수에게 패배했고 5번 선수에게 승리했기 때문에 4위입니다.
 * 5번 선수는 4위인 2번 선수에게 패배했기 때문에 5위입니다.
 */

public class Ex2 {
    public static void main(String[] args) {
        Ex2 ex2 = new Ex2();

        // 권투선수의 수
        int n = 5;
        // 경기 결과(좌 > 승, 우 > 패)
        int[][] results = {{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}};

        // 2
        System.out.println(ex2.solution(n, results));
    }

    public int solution(int n, int[][] results) {
        // 선수들 간의 승패 관계를 나타내는 그래프
        boolean[][] graph = new boolean[n + 1][n + 1];

        // 경기 결과를 바탕으로 승패 관계를 그래프에 입력
        for (int[] result : results) {
            int winner = result[0];
            int loser = result[1];

            // 승자가 패자를 이겼다는 관계를 그래프에 표시
            graph[winner][loser] = true;
        }

        /*
        * 플로이드-워셜 알고리즘
        *
        * 음수 사이클이 없는 그래프내의 각 모든 정점에서 각 모든 정점에 까지의 최단거리를 모두 구할 수 있는 알고리즘이다.
        * 다익스트라 알고리즘과는 다르게 그래프에 음수 사이클만 존재하지 않으면,
        * 음의 가중치를 갖는 간선이 존재해도 상관이 없다는 것이다.
        *
        *   ※음수 사이클 : 사이클의 모든 경로를 지나 원래 지점으로 돌아 왔을때, 최종적인 비용이 음수가 되는 경우.
        */

        // 플로이드-워셜 알고리즘을 사용
        // 각 선수 간의 승패 관계를 전체적으로 파악
        for (int mid = 1; mid <= n; mid++) {
            for (int start = 1; start <= n; start++) {
                for (int end = 1; end <= n; end++) {
                    if (graph[start][mid] && graph[mid][end]) {
                        // 중간 선수를 통해 승패 관계를 유추
                        graph[start][end] = true;
                    }
                }
            }
        }

        // 정확한 순위를 매길 수 있는 선수의 수
        int answer = 0;

        // 각 선수에 대해 승패 관계를 파악하여 순위를 매길 수 있는지 확인
        for (int i = 1; i <= n; i++) {
            // i번 선수와 승패 관계가 명확한 선수의 수
            int count = 0;

            for (int j = 1; j <= n; j++) {
                // i번 선수가 j번 선수를 이기거나, j번 선수에게 진 경우
                if (graph[i][j] || graph[j][i]) {
                    // 승패 관계가 명확한 선수의 수를 증가
                    count++;
                }
            }

            // 모든 선수와의 승패 관계가 명확한 경우
            if (count == n - 1) {
                // 순위를 매길 수 있는 선수의 수를 증가
                answer++;
            }
        }

        return answer;
    }
}