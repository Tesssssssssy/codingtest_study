package week11.lim.week11_1.silver.n_17484;

// 문제: https://www.acmicpc.net/problem/17484
// 참고: https://dingdingmin-back-end-developer.tistory.com/entry/%EB%B0%B1%EC%A4%80-17485%EC%9E%90%EB%B0%94-java-%EC%A7%84%EC%9A%B0%EC%9D%98-%EB%8B%AC-%EC%97%AC%ED%96%89

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    /**
     *  1. 지구와 우주 사이는 N x M 행렬로 표현
     *  2. 각 원소의 값은 우주선이 그 공간을 지날 때 소모되는 연료의 양
     *  3. 지구 -> 달로 가는 경우 우주선은 왼아, 아, 우아로 이동 가능
     *  4. 우주선은 전에 움직인 방향으로 움직일 수 없다. (같은 방향으로 두번 연속 이동 불가)
     *  5. 연료를 최대한 아끼며 지구의 어디에서든 출발해서 달의 어느 위치든 착륙하는 것 목표
     *  6. 달에 도달하기 위해 필요한 연료의 최소값 출력
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        int[][] arr = new int[N][M];
        int[][][] dp = new int[N][M][3];
        /*
            n x m 크기의 배열과 n x m x 3 크기의 dp 배열 선언
            dp 배열은 각 위치에서 가능한 세 가지 방향의 최소 비용을 저장

            한 점에서의 값과 어느 뱡향에서 왔는가를 담을 배열이 필요.
            dp [n][m][3]이라고 선언.
        */

        for (int i = 0; i < N; i++) {
            String[] fuel = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(fuel[j]);
            }
        }
        // 각 행에 대해 연료 비용을 배열에 저장

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Arrays.fill(dp[i][j], 1000001);
            }
        }
        // dp 배열을 큰 값(1000001)으로 초기화하여 최소 비용을 추적 가능하게 함

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < 3; j++) {
                dp[0][i][j] = arr[0][i];
            }
        }
        // 첫 번째 행의 모든 위치에 대해 초기 연료 비용 설정

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (j == 0) {
                    // 첫 번째 열 처리
                    dp[i][j][0] = Math.min(dp[i - 1][j + 1][1], dp[i - 1][j + 1][2]) + arr[i][j];
                    dp[i][j][1] = dp[i-1][j][0] + arr[i][j];
                } else if (j == M - 1) {
                    // 마지막 열 처리
                    dp[i][j][1] = dp[i-1][j][2] + arr[i][j];
                    dp[i][j][2] = Math.min(dp[i - 1][j - 1][1], dp[i - 1][j - 1][0]) + arr[i][j];
                } else {
                    // 그 외의 열 처리
                    dp[i][j][0] = Math.min(dp[i - 1][j + 1][1], dp[i - 1][j + 1][2]) + arr[i][j];
                    dp[i][j][1] = Math.min(dp[i - 1][j][0], dp[i - 1][j][2]) + arr[i][j];
                    dp[i][j][2] = Math.min(dp[i - 1][j - 1][0], dp[i - 1][j - 1][1]) + arr[i][j];
                }
            }
        }
        // 각 행과 열에 대해 동적 프로그래밍을 사용하여 최소 비용 계산

        int result = Integer.MAX_VALUE;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < 3; j++) {
                result = Math.min(result, dp[N - 1][i][j]);
            }
        }
        // 마지막 행의 모든 가능한 위치에서 최소 비용을 찾아 결과를 계산

        System.out.println(result);
    }
}
