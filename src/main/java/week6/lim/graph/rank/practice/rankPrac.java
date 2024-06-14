package week6.lim.graph.rank.practice;

public class rankPrac {
    /**
     *   <안보고 풀이>
     *   1. 플로이드-워셜 알고리즘 사용해서 승리 관계 표현
     *   2. n명 중 n-1의 관계 정리가 끝나면 그대로 종료.
     */

    public static int solution(int n, int[][] results) {
        int answer = 0;

        boolean[][] whoIsWinner = new boolean[n + 1][n + 1];

        // 승패가 기록되어 있는 results 배열로부터 누가 winner인지 표시 (true/false로)
        for (int[] result : results) {
            whoIsWinner[result[0]][result[1]] = true;
        }

        // 승패 표시했으니까 플로이드-워셜 알고리즘으로 최종 승패 관계 정리
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (whoIsWinner[i][k] && whoIsWinner[k][j]) {
                        whoIsWinner[i][j] = true;
                    }
                }
            }
        }

        // n명 중 n-1의 관계 정리가 끝나면 그대로 종료
        for (int x = 1; x <= n; x++) {
            int clear = 0;
            for (int y = 1; y <= n; y++) {
                if (whoIsWinner[x][y] || whoIsWinner[y][x]) {
                    clear++;
                }
            }
            if (clear == n - 1) {
                answer++;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int n1 = 5;
        int[][] results1 = {{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}};
        System.out.println(solution(n1, results1)); // 2
    }

}
