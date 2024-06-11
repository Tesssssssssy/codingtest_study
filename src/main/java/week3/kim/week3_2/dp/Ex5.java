package week3.kim.week3_2.dp;

/**
 * [도둑질]
 *
 * 도둑이 어느 마을을 털 계획을 하고 있습니다. 이 마을의 모든 집들은 아래 그림과 같이 동그랗게 배치되어 있습니다.
 *
 * image.png
 *
 * 각 집들은 서로 인접한 집들과 방범장치가 연결되어 있기 때문에 인접한 두 집을 털면 경보가 울립니다.
 *
 * 각 집에 있는 돈이 담긴 배열 money가 주어질 때, 도둑이 훔칠 수 있는 돈의 최댓값을 return 하도록 solution 함수를 작성하세요.
 *
 * 제한사항
 * 이 마을에 있는 집은 3개 이상 1,000,000개 이하입니다.
 * money 배열의 각 원소는 0 이상 1,000 이하인 정수입니다.
 *
 * 입출력 예
 * money	    return
 * [1, 2, 3, 1]	4
*/

public class Ex5 {
    public static void main(String[] args) {
        Ex5 ex5 = new Ex5();

        int[] money = {1, 2, 3, 1};

        // 예상 출력: 4
        System.out.println(ex5.solution(money));
    }


    public int solution(int[] money) {
        int length = money.length;

        int[] dp = new int[length - 1];
        int[] dp2 = new int[length];

        // 첫 번째 집을 털 경우
        dp[0] = money[0];
        dp[1] = money[0];

        for (int i = 2; i < length - 1; i++) {
            dp[i] = Math.max(dp[i - 2] + money[i], dp[i - 1]);
        }

        // 첫 번째 집을 털지 않는 경우
        dp2[0] = 0;
        dp2[1] = money[1];

        for (int i = 2; i < length; i++) {
            dp2[i] = Math.max(dp2[i - 2] + money[i], dp2[i - 1]);
        }

        // 두 경우 중 더 큰 값을 반환
        return Math.max(dp[length - 2], dp2[length - 1]);
    }
}
