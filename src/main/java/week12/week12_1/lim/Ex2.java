package week12.week12_1.lim;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [이상한 술집]
 *
 * 프로그래밍 대회 전날, 은상과 친구들은 이상한 술집에 모였다. 이 술집에서 막걸리를 시키면 주전자의 용량은 똑같았으나 안에 들어 있는 막걸리 용량은 랜덤이다.  즉 한 번 주문에 막걸리 용량이 802ml 이기도 1002ml가 나오기도 한다.  은상은 막걸리 N 주전자를 주문하고, 자신을 포함한 친구들 K명에게 막걸리를 똑같은 양으로 나눠주려고 한다.  그런데 은상과 친구들은 다른 주전자의 막걸리가 섞이는 것이 싫어서, 분배 후 주전자에 막걸리가 조금 남아 있다면 그냥 막걸리를 버리기로 한다.  (즉, 한 번 주문한 막걸리에 남은 것을 모아서 친구들에게 다시 주는 경우는 없다.  예를 들어 5명이 3 주전자를 주문하여 1002, 802, 705 ml의 막걸리가 각 주전자에 담겨져 나왔고, 이것을 401ml로 동등하게 나눴을 경우 각각 주전자에서 200ml, 0m, 304ml 만큼은 버린다.) 이럴 때 K명에게 최대한의 많은 양의 막걸리를 분배할 수 있는 용량 ml는 무엇인지 출력해주세요.
 *
 *
 * 입력
 * 첫째 줄에는 은상이가 주문한 막걸리 주전자의 개수 N, 그리고 은상이를 포함한 친구들의 수 K가 주어진다. 둘째 줄부터 N개의 줄에 차례로 주전자의 용량이 주어진다. N은 10000이하의 정수이고, K는 1,000,000이하의 정수이다. 막걸리의 용량은 231 -1 보다 작거나 같은 자연수 또는 0이다. 단, 항상 N ≤ K 이다. 즉, 주전자의 개수가 사람 수보다 많을 수는 없다.
 *
 * 출력
 * 첫째 줄에 K명에게 나눠줄 수 있는 최대의 막걸리 용량 ml 를 출력한다.
 *
 *
 * 예제 입력 1
 * 2 3
 * 702
 * 429
 *
 * 예제 출력 1
 * 351
 *
 * 예제 입력 2
 * 4 11
 * 427
 * 541
 * 774
 * 822
 *
 * 예제 출력 2
 * 205
 *
 *
 * 힌트
 * 2번째 예제에서 205ml로 나눌 경우 2,2,3,4 가 된다. 하지만 206ml라고 하면 각각 2, 2, 3, 3 으로 나눌 수 있기 때문에 나누는 용량을 조금 줄여야한다.
*/

public class Ex2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 막걸리 주전자의 개수를 입력
        int K = Integer.parseInt(st.nextToken()); // 은상이를 포함한 친구들의 수를 입력

        long[] makgeolli = new long[N]; // 각 주전자의 막걸리 용량을 저장할 배열을 선언
        long maxVolume = 0; // 막걸리의 최대 용량을 저장할 변수를 선언
        for (int i = 0; i < N; i++) {
            makgeolli[i] = Long.parseLong(br.readLine()); // 각 주전자의 막걸리 용량을 입력받아 배열에 저장
            if (makgeolli[i] > maxVolume) {
                maxVolume = makgeolli[i]; // 막걸리의 최대 용량을 갱신
            }
        }

        long start = 1; // 이분 탐색의 시작점을 1로 설정
        long end = maxVolume; // 이분 탐색의 끝점을 막걸리의 최대 용량으로 설정
        long result = 0; // 최대한 많은 양의 막걸리를 분배할 수 있는 용량을 저장할 변수를 선언

        while (start <= end) {
            long mid = (start + end) / 2; // 중간값을 계산
            long count = 0; // 필요한 학생의 수를 저장할 변수를 선언

            for (long volume : makgeolli) {
                count += volume / mid; // 각 주전자의 막걸리를 나누어 줄 때 필요한 학생의 수를 계산
            }

            if (count >= K) { // 필요한 학생의 수가 K 이상이면
                result = mid; // 최대 용량을 갱신
                start = mid + 1; // 최대 용량을 늘리기 위해 시작점을 늘림
            } else { // 필요한 학생의 수가 K 미만이면
                end = mid - 1; // 최대 용량을 줄이기 위해 끝점을 줄임
            }
        }

        // 최대한 많은 양의 막걸리를 분배할 수 있는 용량을 출력
        System.out.println(result);
    }
}