package week11.kim.week11_2.kim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [오르막 수]
 *
 * 오르막 수는 수의 자리가 오름차순을 이루는 수를 말한다. 이때, 인접한 수가 같아도 오름차순으로 친다.
 *
 * 예를 들어, 2234와 3678, 11119는 오르막 수이지만, 2232, 3676, 91111은 오르막 수가 아니다.
 *
 * 수의 길이 N이 주어졌을 때, 오르막 수의 개수를 구하는 프로그램을 작성하시오. 수는 0으로 시작할 수 있다.
 *
 *
 * 입력
 * 첫째 줄에 N (1 ≤ N ≤ 1,000)이 주어진다.
 *
 *
 * 출력
 * 첫째 줄에 길이가 N인 오르막 수의 개수를 10,007로 나눈 나머지를 출력한다.
 *
 *
 * 예제 입력 1
 * 1
 *
 * 예제 출력 1
 * 10
 *
 * 예제 입력 2
 * 2
 *
 * 예제 출력 2
 * 55
 *
 * 예제 입력 3
 * 3
 *
 * 예제 출력 3
 * 220
*/

public class Ex3 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 수의 길이를 입력
        int N = Integer.parseInt(br.readLine());

        // 동적 프로그래밍 테이블을 선언
        // 각 칸은 해당 자릿수에서 끝나는 숫자를 나타냄
        int[][] dp = new int[N+1][10];
        // 1 > 9
        // 2 > 00 01 02
        //      11 12 13
        //      22 23 24
        //      99
        // 3 > 000 001
        //      011 012
        for (int i = 0; i <= 9; i++) {
            // 1자리 오르막 수는 모두 1개
            dp[1][i] = 1;
        }

        for (int i = 2; i <= N; i++) {
            for (int j = 0; j <= 9; j++) {
                for (int k = 0; k <= j; k++) {
                    // i자리 오르막 수 중 j로 끝나는 수의 개수를 계산
                    dp[i][j] += dp[i-1][k];
                    // 문제의 요구사항에 따라
                    // 계산 결과를 10007로 나눈 나머지를 저장
                    dp[i][j] %= 10007;
                }
            }
        }

        int answer = 0;
        for (int i = 0; i <= 9; i++) {
            // N자리 오르막 수의 총 개수를 계산
            answer += dp[N][i];
            // 문제의 요구사항에 따라 계산 결과를 10007로 나눈 나머지를 저장
            answer %= 10007;
        }

        System.out.println(answer); // 계산된 오르막 수의 개수를 출력합니다.
    }
}