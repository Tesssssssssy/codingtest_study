package week12.lim.week12_1.silver.n_11561;

// 문제: https://www.acmicpc.net/problem/11561

import java.io.*;

public class Main {
    /**
     *  1. 수영을 못하기 때문에, 강에 놓인 징검다리를 밟고 건너갈 것.
     *  2. 원하는 어느 곳으로든지 점프해서 바로 갈 수가 있다.
     *  3. 강엔 1번부터 시작해 2번, 3번, ... , N번 징검다리
     *  4. 적절한 개수의 징검다리만을 밟고 가기로 했다.
     *  5. 더 재미있게 강을 건너기 위해 승택이는 다음과 같은 규칙
     *     - 첫 징검다리는 점프해서 아무 것이나 밟을 수 있다. 이 점프가 첫 점프이다.
     *     - 두 번째 점프부터는 이전에 점프한 거리보다 1 이상 더 긴 거리를 뛰어야만 한다.
     *     - N번 징검다리는 반드시 밟아야 한다.
     *     - N번 징검다리를 밟은 후 강 건너로 이동할 땐 점프를 하지 않으므로 위의 규칙이 적용되지 않는다.
     *  6. 승택이가 위의 규칙을 지키며 강을 건널 때, 밟을 수 있는 징검다리의 최대 수는 몇 개일까?
     *
     *  [입력]
     *  테스트 케이스의 수 T
     *  각 테스트 케이스는 정수 한 개로 이루어져 있으며, 징검다리의 총 수 N을 의미
     *
     *  [출력]
     *  각 테스트 케이스마다 한 줄에 승택이가 밟을 수 있는 최대 징검다리 수를 출력
     *
     *  시간 복잡도: O(log N)
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());  // 테스트 케이스 수
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < T; i++) {
            long N = Long.parseLong(br.readLine());  // 징검다리의 총 수

            long left = 1, right = 2000000000;  // k 값의 탐색 범위
            long answer = 1;

            while (left <= right) {
                long mid = left + (right - left) / 2;
                /*
                    mid 값을 계산하여 k * (k + 1) / 2가 N보다 작거나 같은지 확인

                    left 와 right 의 평균을 구하지만, int 또는 long 오버플로우를 방지하기 위해 이렇게 표현.
                    (right - left) / 2는 right 와 left 의 차이를 반으로 나눈 것으로,
                    left 에 이 값을 더함으로써 중간 위치를 얻는다.
                */

                if (mid * (mid + 1) / 2 <= N) {
                    /*
                        선택한 mid 값에 대해, 1부터 mid 까지의 합이 N보다 작거나 같은지 확인.
                        이 합은 (mid * (mid + 1)) / 2 공식으로 계산되며, 이는 가우스 합 공식.
                        이 조건이 참이면 현재 mid 값이 징검다리를 건널 때 사용할 수 있는 점프 수로 가능한 경우.
                    */
                    answer = mid;
                    /*
                         현재 mid 값이 조건을 만족하므로, answer 를 mid 로 갱신.
                         이는 현재까지 조사된 범위 내에서 가능한 최대 징검다리 수를 저장.
                    */
                    left = mid + 1;
                    /*
                        조건을 만족하는 mid 값을 발견했으므로,
                        더 큰 값에서도 가능한지 시험하기 위해 left 경계를 조정.
                        mid 보다 1 큰 값에서 다시 시도하여 더 많은 징검다리를 밟을 수 있는지 확인.
                    */
                } else {
                    right = mid - 1;
                    /*
                        mid 값이 조건을 만족하지 않으면,
                        즉 mid 값으로는 요구하는 나무 길이 N을 만족할 수 없다면, mid 를 줄여야 함.
                        따라서 right 를 mid - 1로 조정하여 mid 보다 작은 값에서 다시 탐색을 시도.
                    */
                }
                // 가능한 경우 answer 를 업데이트하고 left 를 조정하며, 그렇지 않은 경우 right 를 조정
            }

            sb.append(answer).append("\n");
        }

        System.out.print(sb);
    }
}
/*
    각 점프의 거리가 이전 점프보다 항상 1 이상 커야 하고, N번 징검다리는 반드시 밟아야 한다는 조건
    => 점프 거리의 합이 N에 최대한 가깝게 도달해야

    승택이가 징검다리를 밟을 수 있는 최대 수를 찾기 위해, 이러한 증가하는 점프 길이를 통해 가능한 최대 징검다리의 수를 계산.
    각 점프의 거리는 1, 2, 3, ..., k 처럼 증가하며, k 점프까지의 합은 k * (k + 1) / 2이다.
    이 합이 N을 초과하지 않으면서 최대가 되는 k 값을 찾아야 한다.

    이를 이해하기 쉽게 구현하는 방법은 각 테스트 케이스에 대해 위의 공식을 이용해 k의 값을 증가시키면서 N과 비교하는 것.
    k의 값이 어느 정도까지 증가할 수 있는지를 이진 탐색을 사용해서 효율적으로 찾을 수 있다.
*/
/*
    mid = (left + right) / 2로 계산하는 방법은 흔히 사용되며 매우 직관적임.
    그러나 이 방식은 left 와 right 값이 매우 큰 경우 (특히 Java 에서 int 타입의 최대값 근처에서) 오버플로우를 일으킬 수 있다.
    오버플로우는 left 와 right 의 합이 해당 자료형의 최대 값을 초과하는 경우 발생.

    예를 들어, int 의 최대 값은 2,147,483,647 (Integer.MAX_VALUE).
    만약 left 와 right 가 이 값에 근접하다면, left + right 는 음수가 될 수 있으며,
    이는 프로그램의 예상치 못한 동작을 초래할 수 있다.

    이런 문제를 피하기 위해 다음과 같이 계산
    -> long mid = left + (right - left) / 2;

    이 방식은 right - left 가 항상 양수 또는 0이므로,
    left 에 이 값을 더하는 것은 오버플로우를 발생시키지 않는다.
    결과적으로, 이는 더 안전하고 신뢰할 수 있는 방식으로 mid 값을 계산.

    왜 이 방법이 안전한가?
    - 안정성
        (right - left) / 2는 right 와 left 사이의 거리를 2로 나눈 것으로,
        이 값은 right 또는 left 자체보다 항상 작거나 같다.
        그러므로 이 값을 left 에 더하면 right 를 초과하지 않는다.

    - 범위 제한
        left + (right - left) / 2 계산에서는 항상 left 와 right 의 중간 지점을 찾게 되므로,
        이 값은 left 와 right 사이에 존재하게 된다.
*/