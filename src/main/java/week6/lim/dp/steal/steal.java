package week6.lim.dp.steal;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/42897

public class steal {
    /**
     *  점화식: dp[i] = Math.max(money[i]+dp[i-2], dp[i-1]);
     *
     *  현재 집을 털었을 때와 털지 않았을 때로 나눠서 최댓값을 구하면 된다.
     *  ( 도둑질은 집을 연달아서 할 수 없다는 조건을 이용 )
     */
    public static int solution(int[] money) {
        int n = money.length;
        if (n == 1)
            return money[0];

        // 첫 집을 포함, 마지막 집을 제외
        // bottom-up 방식 (dp)
        int[] dp1 = new int[n];
        dp1[0] = money[0];
        dp1[1] = money[0];

        for (int i = 2; i < n - 1; i++) {
            dp1[i] = Math.max(dp1[i-1], dp1[i-2] + money[i]);
        }

        // 마지막 집을 포함, 첫 집을 제외
        // ( = 첫 집을 털지 않을 때 )
        int[] dp2 = new int[n];
        dp2[0] = 0;  // 첫 집을 털지 않기 때문에 0으로 시작
        dp2[1] = money[1];

        for (int i = 2; i < n; i++) {
            dp2[i] = Math.max(dp2[i-1], dp2[i-2] + money[i]);
        }

        // 두 경우 중 최대값을 반환
        return Math.max(dp1[n-2], dp2[n-1]);
    }

    public static void main(String[] args) {
        int[] money1 = {1, 2, 3, 1};
        System.out.println(solution(money1));   // 4
    }
}
