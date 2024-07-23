package week12.week12_1.kim;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * [징검다리]
 *
 * 승택이는 강을 건너려 한다.
 *
 * 승택이는 수영을 못하기 때문에, 강에 놓인 징검다리를 밟고 건너갈 것이다.
 *
 * 승택이는 수영은 못하지만 제자리뛰기는 정말 잘한다. 원하는 어느 곳으로든지 점프해서 바로 갈 수가 있다.
 *
 * 승택이는 이제 강의 한쪽 변 앞에 서 있다.
 *
 * 강엔 1번부터 시작해 2번, 3번, ... , N번 징검다리가 차례대로 놓여 있다.
 *
 * 강의 폭이 넓은 탓에 징검다리의 수는 엄청나게 많다.
 *
 * 이 징검다리를 모두 밟고 싶지는 않았던 승택이는 제자리뛰기 실력을 발휘해 적절한 개수의 징검다리만을 밟고 가기로 했다.
 *
 * 물론 강 건너편으로 바로 점프하는 것도 가능하지만, 더 재미있게 강을 건너기 위해 승택이는 다음과 같은 규칙을 정했다.
 *
 * 	1. 첫 징검다리는 점프해서 아무 것이나 밟을 수 있다. 이 점프가 첫 점프이다.
 * 	2. 두 번째 점프부터는 이전에 점프한 거리보다 1 이상 더 긴 거리를 뛰어야만 한다.
 * 	3. N번 징검다리는 반드시 밟아야 한다.
 * 	4. N번 징검다리를 밟은 후 강 건너로 이동할 땐 점프를 하지 않으므로 위의 규칙이 적용되지 않는다.
 *
 * 승택이가 위의 규칙을 지키며 강을 건널 때, 밟을 수 있는 징검다리의 최대 수는 몇 개일까?
 *
 *
 * 입력
 * 첫째 줄에 테스트 케이스의 수 T가 주어진다.
 *
 * 각 테스트 케이스는 정수 한 개로 이루어져 있으며, 징검다리의 총 수 N을 의미한다. (1 ≤ N ≤ 1016)
 *
 *
 * 출력
 * 각 테스트 케이스마다 한 줄에 승택이가 밟을 수 있는 최대 징검다리 수를 출력한다.
 *
 *
 * 예제 입력 1
 * 4
 * 1
 * 2
 * 100
 * 1000000
 *
 * 예제 출력 1
 * 1
 * 1
 * 13
 * 1413
*/

public class Ex3 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 테스트 케이스의 수 입력
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int t = 0; t < T; t++) {
            // 각 테스트 케이스에서 징검다리의 총 수 입력
            long N = Long.parseLong(br.readLine());

            // 이분 탐색의 시작점을 1로 설정
            long start = 1;
            // 이분 탐색의 끝점을 징검다리의 총 수의 제곱근으로 설정
            // (삼각수) > 1부터 k까지 자연수의 합
            //      > 삼각수 = k(k+1) / 2 >> k는 점프 횟수
            //          N >= k(k+1) / 2 >> k의 최대값 근의 공식으로 구하기
            long end = (long) Math.sqrt(2 * N);
            System.out.println("end = " + end);
            // 승택이가 밟을 수 있는 최대 징검다리 수를 저장할 변수 선언
            long result = 0;

            while (start <= end) {
                // 중간값 계산
                long mid = (start + end) / 2;
                // 점프한 거리의 합을 계산
                // (등차수열 합 공식)
                long sum = mid * (mid + 1) / 2;

                // 점프한 거리의 합이 N보다 작거나 같으면
                if (sum <= N) {
                    // 승택이가 밟을 수 있는 최대 징검다리 수를 갱신
                    result = mid;
                    // 징검다리 수를 더 늘려서 다시 시도
                    start = mid + 1;
                }
                // 점프한 거리의 합이 N보다 크면
                else {
                    // 징검다리 수를 줄여서 다시 시도
                    end = mid - 1;
                }
            }

            // 각 테스트 케이스의 결과를 저장
            sb.append(result).append("\n");
        }

        // 모든 테스트 케이스의 결과를 출력
        System.out.print(sb.toString());
    }
}