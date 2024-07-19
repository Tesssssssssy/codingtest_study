package week11.lim.week11_2.silver.n_1965;

// 문제: https://www.acmicpc.net/problem/1965

import java.io.*;

public class Main {
    /**
     *  1. 정육면체 상자가 일렬로 늘어서 있다.
     *  2. 상자마다 크기가 있는데, 앞 상자의 크기가 뒤 상자의 크기보다 작으면
     *     앞 상자를 뒤 상자 안에 넣을 수 있다.
     *     ex.) 1, 5, 2, 3, 7  5개의 상자가 있다면
     *          1 상자를 5 상자에 넣고, 다시 5 상자를 7상자에 넣을 수 있다.
     *  3. 이렇게 상자를 넣을 수 있는 방법은 여러 가지가 있을 수 있다.
     *     ex.) 1, 5, 2, 3, 7  5개의 상자가 있을 때
     *          크기 순으로 1, 2, 3, 7을 선택하면 총 4개의 상자가 한 개의 상자에 들어간다.
     *  4. 한 번에 넣을 수 있는 최대의 상자 개수 출력
     *
     *  [풀이]
     *  1. 가장 긴 증가하는 부분 수열(Longest Increasing Subsequence, LIS) 문제
     *  2. 최대로 겹쳐 넣을 수 있는 상자의 개수를 찾는 건,
     *     해당 배열에서 가장 긴 증가하는 부분 수열의 길이를 찾는 것과 동일
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] inputStr = br.readLine().split(" ");

        int[] boxes = new int[n];
        for (int i = 0; i < n; i++) {
            boxes[i] = Integer.parseInt(inputStr[i]);
        }

        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
        }

        /*
            상자의 크기가 증가하는 순서대로 증가하는 부분 수열을 구하려면,
            각 상자 i에 대해 그 앞에 있는 모든 상자 j (0 ≤ j < i)를 검사해서,
            boxes[j]가 boxes[i]보다 작을 경우에만 dp[i]를 업데이트해야 함.
        */
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (boxes[j] < boxes[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int result = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            result = Math.max(dp[i], result);
        }

        System.out.println(result);
    }
}
/*
    1. 문제 이해와 분석:
        문제에서 요구하는 것은 주어진 상자들 중에서 서로 넣을 수 있는 최대의 상자 수를 찾는 것이다.
        이는 "가장 긴 증가하는 부분 수열" (Longest Increasing Subsequence, LIS) 문제의 한 변형으로 볼 수 있다.
        주어진 상자들의 크기에 따라 다른 상자 안에 들어갈 수 있는 조건이 '증가하는 순'임을 확인할 수 있다.

    2. 최적 부분 구조 및 중복되는 부분 문제 확인:
        최적 부분 구조:
            문제의 해결 방법은 각 상자를 끝으로 하는 가장 긴 부분 수열을 찾아 그 중 최대값을 구하는 것이다.
            이 때, 특정 상자까지 고려했을 때의 최대 상자 수는 이전 상자들의 결과에 의존한다. 이러한 구조는 동적 계획법을 사용하기에 적합한 예이다.

        중복되는 부분 문제:
            각 상자를 살펴보면서 이전 상자들과 비교할 때,
            이전 상자들의 결과(즉, 그 상자를 끝으로 하는 최대 부분 수열의 길이)를 여러 번 사용하게 된다.
            이는 중복 계산을 막기 위해 동적 계획법을 사용해야 하는 좋은 이유를 제공한다.

    3. 접근 방법:
        DP 배열 정의
            dp[i]를 정의하여 i번째 상자를 마지막으로 사용했을 때의 최대 상자 수를 저장한다.

        점화식
            각 상자 i에 대해, 모든 이전 상자 j (j < i)를 검토하여,
            만약 상자 j의 크기가 상자 i보다 작다면, dp[i] = max(dp[i], dp[j] + 1)로 업데이트한다.

        초기값
            각 상자는 최소 자기 자신만 포함할 수 있으므로, 모든 dp[i]의 초기값은 1이다.

        결과 계산
            모든 dp[i] 값 중 최대값이 문제의 정답이다.

    이와 같은 분석을 통해, 문제의 구조와 조건이 동적 계획법을 적용하기에 적합하다고 판단할 수 있다.
*/