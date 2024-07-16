package week11.kim.week11_1.lim;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * [진우의 달 여행 (Small)]
 *
 * 우주비행이 꿈이였던 진우는 음식점 '매일매일싱싱'에서 열심히 일한 결과 달 여행에 필요한 자금을 모두 마련하였다! 지구와 우주사이는 N X M 행렬로 나타낼 수 있으며 각 원소의 값은 우주선이 그 공간을 지날 때 소모되는 연료의 양이다.
 *
 * 	[예시]
 *
 * 		지구
 * 		5 8 5 1
 * 		3 5 8 4
 * 		9 77 65 5
 * 		2 1 5 2
 * 		5 98 1 5
 * 		4 95 67 58
 * 		달
 *
 * 진우는 여행경비를 아끼기 위해 조금 특이한 우주선을 선택하였다. 진우가 선택한 우주선의 특징은 아래와 같다.
 *
 * 	1. 지구 -> 달로 가는 경우 우주선이 움직일 수 있는 방향은 아래와 같다.
 * 		현재위치 바로 아래와 아래쪽 양 대각선
 *
 * 	2. 우주선은 전에 움직인 방향으로 움직일 수 없다. 즉, 같은 방향으로 두번 연속으로 움직일 수 없다.
 *
 * 진우의 목표는 연료를 최대한 아끼며 지구의 어느위치에서든 출발하여 달의 어느위치든 착륙하는 것이다.
 *
 * 최대한 돈을 아끼고 살아서 달에 도착하고 싶은 진우를 위해 달에 도달하기 위해 필요한 연료의 최소값을 계산해 주자.
 *
 *
 * 입력
 * 첫줄에 지구와 달 사이 공간을 나타내는 행렬의 크기를 나타내는 N, M (2≤ N, M ≤ 6)이 주어진다.
 *
 * 다음 N줄 동안 각 행렬의 원소 값이 주어진다. 각 행렬의 원소값은 100 이하의 자연수이다.
 *
 *
 * 출력
 * 달 여행에 필요한 최소 연료의 값을 출력한다.
 *
 *
 * 예제 입력 1
 * 6 4
 * 5 8 5 1
 * 3 5 8 4
 * 9 77 65 5
 * 2 1 5 2
 * 5 98 1 5
 * 4 95 67 58
 *
 * 예제 출력 1
 * 29
*/

// 10_1 김현균 3
public class Ex4 {
    // 지구와 달 사이 공간을 나타내는 행렬의 크기
    int N, M;
    // 각 위치에서 소모되는 연료의 양을 저장하는 배열
    int[][] map;
    // dp[i][j][k]는 (i, j) 위치에서
    // k 방향으로 움직일 때 필요한 최소 연료의 양을 저장하는 배열
    int[][][] dp;

    public static void main(String[] args) throws Exception {
        new Ex4().solve();
    }

    public void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        // 입력으로부터 행렬의 크기를 받아옴
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        // map과 dp 배열 초기화
        map = new int[N][M];
        dp = new int[N][M][3];

        // 입력으로부터 각 위치에서 소모되는 연료의 양을 받아와 map에 저장하고,
        // dp 배열을 최대값으로 초기화
        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(input[j]);
                Arrays.fill(dp[i][j], Integer.MAX_VALUE);
            }
        }

        // 첫 번째 행의 dp 값을 map의 값으로 설정
        for (int j = 0; j < M; j++) {
            dp[0][j][0] = map[0][j];
            dp[0][j][1] = map[0][j];
            dp[0][j][2] = map[0][j];
        }

        // 두 번째 행부터 마지막 행까지
        // 각 위치에서 세 가지 방향 각각에 대해
        // 최소의 연료를 사용하는 방법을 계산하고 dp 배열에 저장
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (j - 1 >= 0) {
                    dp[i][j][0] = Math.min(dp[i][j][0],
                            Math.min(dp[i - 1][j - 1][1], dp[i - 1][j - 1][2]) + map[i][j]);
                }
                dp[i][j][1] = Math.min(dp[i][j][1],
                        Math.min(dp[i - 1][j][0], dp[i - 1][j][2]) + map[i][j]);
                if (j + 1 < M) {
                    dp[i][j][2] = Math.min(dp[i][j][2],
                            Math.min(dp[i - 1][j + 1][0], dp[i - 1][j + 1][1]) + map[i][j]);
                }
            }
        }

        // 마지막 행의 dp 값 중에서 가장 작은 값을 찾아서 출력
        int answer = Integer.MAX_VALUE;
        for (int j = 0; j < M; j++) {
            for (int k = 0; k < 3; k++) {
                answer = Math.min(answer, dp[N - 1][j][k]);
            }
        }

        System.out.println(answer);
    }
}