package week3.lim.dp.waytoschool;

public class school2 {
    /**
     *  점화식: board[i][j] = board[i - 1][j] + board[i][j - 1]
     */
    static int MOD = 1_000_000_007;

    public static int solution(int m, int n, int[][] puddles) {
        int[][] dp = new int[n+1][m+1];

        // 물에 잠긴 지역을 -1로 설정
        for (int[] puddle : puddles) {
            dp[puddle[1]][puddle[0]] = -1;
        }

        // 시작 위치 초기화
        dp[1][1] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (dp[i][j] == -1) {
                    dp[i][j] = 0; // 물에 잠긴 지역은 경로 개수를 0으로 설정
                    continue;
                }
                if (i > 1) {
                    dp[i][j] = (dp[i][j] + dp[i-1][j]) % MOD;
                }
                if (j > 1) {
                    dp[i][j] = (dp[i][j] + dp[i][j-1]) % MOD;
                }
            }
        }

        return dp[n][m];
    }

    public static void main(String[] args) {
        int m1 = 4;
        int n1 = 3;
        int[][] puddles = {{2, 2}};
        System.out.println(solution(m1, n1, puddles));  // 4
    }
}
