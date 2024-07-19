package week11.lim.week11_2.silver.n_1890;

// 문제: https://www.acmicpc.net/problem/1890

import java.io.*;

public class Main {
    /**
     *  1. N x N 게임판에 수가 적혀있다.
     *  2. 목표: 가장 왼쪽 위 칸 -> 가장 오른쪽 아래 칸으로 규칙에 맞게 점프해서 가는 것.
     *  3. 각 칸에 적혀있는 수: 현재 칸에서 갈 수 있는 거리
     *  4. 반드시 오른쪽이나 아래쪽으로만 이동 가능
     *  5. 0은 더 이상 진행을 막는 종착점
     *  6. 항상 현재 칸에 적혀있는 수만큼 오른쪽이나 아래로 가야 함.
     *  7. 한 번 점프할 때, 방향을 바꾸면 안됨.
     *     한 칸에서 오른쪽으로 점프를 하거나, 아래로 점프를 하는 두 경우만 존재
     *  8. 가장 왼쪽 위 -> 가장 오른쪽 아래 칸으로 이동할 수 있는 경로의 개수 출력
     *
     *  [동적계획법으로 풀어야하는 이유]
     *  - 최적 부분 구조
     *        각 칸에서 오른쪽 또는 아래로만 이동할 수 있으므로,
     *        특정 위치에서 목표 지점(가장 오른쪽 아래 칸)까지 도달하는 방법은 그 위치에 도달하기까지의 경로에 달려 있다.
     *        즉, 작은 문제의 해결이 전체 문제의 해결로 직결됨.
     *  - 중복되는 부분 문제
     *        예를 들어, 특정 칸으로부터 목표 지점까지의 경로 수를 계산할 때,
     *        다른 경로에서 같은 위치로부터 시작하는 계산이 반복될 수 있다.
     *        따라서, 한 번 계산한 결과를 저장하고 재활용함으로써 계산 시간을 줄일 수 있다.
     *
     *  이 문제에서는 각 칸에서 시작하여 목표 지점에 도달할 수 있는 경로의 수를 계산하는 작은 문제들로 나눌 수 있고,
     *  이러한 계산 결과를 dp 배열에 저장하여 중복 계산을 방지할 수 있다.
     *  이러한 접근 방식은 계산을 효율적으로 만들고, 큰 문제를 해결하는 데 필요한 시간을 크게 줄일 수 있다.
     *  따라서 이러한 조건들을 고려했을 때, 이 문제는 동적 계획법을 적용하기에 적합한 문제라고 볼 수 있다.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] board = new int[N][N];

        long[][] dp = new long[N][N];
        // 경로의 개수가 2^63 - 1 보다 작거나 같으므로 long 타입 사용

        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(input[j]);
            }
        }

        // DP 초기화 및 계산
        dp[0][0] = 1; // 시작점

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (dp[i][j] > 0 && board[i][j] != 0) {
                    int distance = board[i][j];
                    int new_i = i + distance; // 아래로 점프
                    int new_j = j + distance; // 오른쪽으로 점프

                    if (new_i < N)
                        dp[new_i][j] += dp[i][j]; // 범위 내라면 아래로 점프
                    if (new_j < N)
                        dp[i][new_j] += dp[i][j]; // 범위 내라면 오른쪽으로 점프
                }
            }
        }

        System.out.println(dp[N-1][N-1]);
    }
}
/*
    동적 계획법(Dynamic Programming, DP)을 사용해야 하는 문제를 판단하는 몇 가지 주요 신호.
    - 최적 부분 구조(Optimal Substructure):
        동적 계획법의 핵심은 주어진 문제를 작은 부분 문제로 나누고,
        이러한 작은 문제들의 해결을 통해 전체 문제의 해결책을 구성할 수 있는 성질을 가지고 있어야 한다.
        즉, 전체 문제의 최적 해결책이 부분 문제의 최적 해결책으로부터 구성될 수 있어야 한다.

    - 중복되는 부분 문제(Overlapping Subproblems):
        동적 계획법은 각 부분 문제가 여러 번 계산되는 상황에서 유용하다.
        즉, 문제를 해결하는 과정에서 같은 작은 문제를 반복해서 해결해야 할 때,
        이를 메모리에 저장함으로써 다시 계산하는 시간을 절약할 수 있다.
*/