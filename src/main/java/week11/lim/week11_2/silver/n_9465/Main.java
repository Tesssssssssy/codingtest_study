package week11.lim.week11_2.silver.n_9465;

// 문제: https://www.acmicpc.net/problem/9465

import java.io.*;
import java.util.*;

public class Main {
    /**
     *  1. 스티커 2n개를 구매 / 2행 n열로 배치
     *  2. 스티커 한 장을 떼면, 그 스티커와 변을 공유하는 스티커는 모두 찢어져서 사용할 수 없게 된다.
     *     즉, 뗀 스티커의 왼쪽, 오른쪽, 위, 아래에 있는 스티커는 사용할 수 없게 된다.
     *  3. 모든 스티커를 붙일 수 없게된 상냥이는 각 스티커에 점수를 매기고,
     *     점수의 합이 최대가 되게 스티커를 떼어내려고 한다.
     *  4. 각 스티커에 점수를 매겼다. 상냥이가 뗄 수 있는 스티커의 점수의 최댓값 출력.
     *     2n개의 스티커 중에서 점수의 합이 최대가 되면서 서로 변을 공유 하지 않는 스티커 집합을 구해야 한다.
     *  5. ex.)
     *     50, 50, 100, 60인 스티커를 고르면,
     *     점수는 260이 되고 이 것이 최대 점수이다.
     *     가장 높은 점수를 가지는 두 스티커 (100과 70)은 변을 공유하기 때문에, 동시에 뗄 수 없다.
     *
     *  [풀이]
     *  조합
     *  - 해당 열의 어느 스티커도 선택하지 않는 경우.
     *  - 해당 열의 위쪽 스티커만 선택하는 경우.
     *  - 해당 열의 아래쪽 스티커만 선택하는 경우.
     *
     * [정의]
     *  - dp[j][0]: j번째 열까지 고려했을 때, j번째 열에서 스티커를 선택하지 않는 경우의 최대 점수.
     *  - dp[j][1]: j번째 열까지 고려했을 때, j번째 열의 위쪽 스티커만 선택하는 경우의 최대 점수.
     *  - dp[j][2]: j번째 열까지 고려했을 때, j번째 열의 아래쪽 스티커만 선택하는 경우의 최대 점수.
     *
     * [점화식]
     *  - dp[j][0] = max(dp[j-1][0], dp[j-1][1], dp[j-1][2]): j번째 열에서 스티커를 선택하지 않는 경우
     *  - dp[j][1] = max(dp[j-1][0], dp[j-1][2]) + sticker[j-1][0]: j번째 열의 위쪽 스티커를 선택하는 경우
     *  - dp[j][2] = max(dp[j-1][0], dp[j-1][1]) + sticker[j-1][1]: j번째 열의 아래쪽 스티커를 선택하는 경우
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());
            int[][] sticker = new int[n][2]; // 스티커 점수 배열
            int[][] dp = new int[n + 1][3]; // dp 배열

            // 스티커 점수 입력
            for (int i = 0; i < 2; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    sticker[j][i] = Integer.parseInt(st.nextToken());
                }
            }

            // DP 초기 설정
            dp[1][0] = 0;
            dp[1][1] = sticker[0][0];
            dp[1][2] = sticker[0][1];

            // DP 배열을 사용하여 최대 점수 계산
            for (int j = 2; j <= n; j++) {
                dp[j][0] = Math.max(dp[j - 1][0], Math.max(dp[j - 1][1], dp[j - 1][2]));
                dp[j][1] = Math.max(dp[j - 1][0], dp[j - 1][2]) + sticker[j - 1][0];
                dp[j][2] = Math.max(dp[j - 1][0], dp[j - 1][1]) + sticker[j - 1][1];
            }

            // 최대값을 결과 문자열에 추가
            int maxResult = Math.max(dp[n][0], Math.max(dp[n][1], dp[n][2]));
            sb.append(maxResult).append('\n');
        }
        System.out.println(sb);
    }
}
