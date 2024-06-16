package week6.lim.dp.expressN;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/42895

import java.util.HashSet;
import java.util.Set;

public class expressN1 {
    public static int solution(int N, int number) {
        if (N == number)
            return 1;

        // dp[i]는 N을 i번 사용하여 만들 수 있는 모든 수의 집합
        Set<Integer>[] dp = new Set[9];

        for (int i = 0; i < 9; i++) {
            dp[i] = new HashSet<>();
        }

        // 기본값 설정
        dp[1].add(N);

        // dp 채우기
        for (int i = 2; i < 9; i++) {
            // N을 i번 연속해서 사용하는 경우
            int num = Integer.parseInt(String.valueOf(N).repeat(i));
            dp[i].add(num);

            // 이전 dp[j]와 dp[i-j]의 조합으로 dp[i]를 채운다
            // (dp[j]와 dp[i-j]에 저장된 숫자들을 이용하여 i에 해당하는 숫자를 만듭니다.)
            for (int j = 1; j < i; j++) {
                for (int x : dp[j]) {
                    for (int y : dp[i - j]) {
                        dp[i].add(x + y);
                        dp[i].add(x - y);
                        dp[i].add(x * y);
                        if (y != 0) {
                            dp[i].add(x / y);
                        }
                    }
                }
            }

            // 목표 숫자 확인
            if (dp[i].contains(number)) {
                return i;
            }
        }

        // 8번까지 사용해도 만들 수 없는 경우
        return -1;
    }

    public static void main(String[] args) {
        int N1 = 5;
        int number1 = 12;
        System.out.println(solution(N1, number1));  // 4

        int N2 = 2;
        int number2 = 11;
        System.out.println(solution(N2, number2));  // 3
    }
}

/*
    dp[j]와 dp[i-j] 조합의 의미:
        dp[j]: 숫자 N을 정확히 j번 사용해서 만들 수 있는 모든 결과를 저장합니다.
        dp[i-j]: 숫자 N을 정확히 i−j번 사용해서 만들 수 있는 모든 결과를 저장합니다.

    조합의 목적:
        모든 조합 탐색: dp[j]의 모든 원소와 dp[i-j]의 모든 원소를 조합하여 사칙연산을 수행함으로써,
                    N을 i번 사용하여 만들 수 있는 모든 가능한 수를 계산합니다.
        새로운 결과 생성: j번과 i−j번 사용하여 생성된 결과들을 조합함으로써, 더 많은 수를 생성할 수 있습니다.
                        이는 i번 사용하여 가능한 모든 수를 포괄적으로 찾는 데 도움을 줍니다.
*/