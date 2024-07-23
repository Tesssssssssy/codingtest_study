package week12.week12_1.lim;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [랜선 자르기]
 *
 * 집에서 시간을 보내던 오영식은 박성원의 부름을 받고 급히 달려왔다. 박성원이 캠프 때 쓸 N개의 랜선을 만들어야 하는데 너무 바빠서 영식이에게 도움을 청했다.
 *
 * 이미 오영식은 자체적으로 K개의 랜선을 가지고 있다. 그러나 K개의 랜선은 길이가 제각각이다. 박성원은 랜선을 모두 N개의 같은 길이의 랜선으로 만들고 싶었기 때문에 K개의 랜선을 잘라서 만들어야 한다. 예를 들어 300cm 짜리 랜선에서 140cm 짜리 랜선을 두 개 잘라내면 20cm는 버려야 한다. (이미 자른 랜선은 붙일 수 없다.)
 *
 * 편의를 위해 랜선을 자르거나 만들 때 손실되는 길이는 없다고 가정하며, 기존의 K개의 랜선으로 N개의 랜선을 만들 수 없는 경우는 없다고 가정하자. 그리고 자를 때는 항상 센티미터 단위로 정수길이만큼 자른다고 가정하자. N개보다 많이 만드는 것도 N개를 만드는 것에 포함된다. 이때 만들 수 있는 최대 랜선의 길이를 구하는 프로그램을 작성하시오.
 *
 *
 * 입력
 * 첫째 줄에는 오영식이 이미 가지고 있는 랜선의 개수 K, 그리고 필요한 랜선의 개수 N이 입력된다. K는 1이상 10,000이하의 정수이고, N은 1이상 1,000,000이하의 정수이다. 그리고 항상 K ≦ N 이다. 그 후 K줄에 걸쳐 이미 가지고 있는 각 랜선의 길이가 센티미터 단위의 정수로 입력된다. 랜선의 길이는 2^31-1보다 작거나 같은 자연수이다.
 *
 *
 * 출력
 * 첫째 줄에 N개를 만들 수 있는 랜선의 최대 길이를 센티미터 단위의 정수로 출력한다.
 *
 *
 * 예제 입력 1
 * 4 11
 * 802
 * 743
 * 457
 * 539
 *
 * 예제 출력 1
 * 200
 *
 *
 * 힌트
 * 802cm 랜선에서 4개, 743cm 랜선에서 3개, 457cm 랜선에서 2개, 539cm 랜선에서 2개를 잘라내 모두 11개를 만들 수 있다.
*/

public class Ex3 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int K = Integer.parseInt(st.nextToken()); // 이미 가지고 있는 랜선의 개수를 입력
        int N = Integer.parseInt(st.nextToken()); // 필요한 랜선의 개수를 입력

        long[] lanCables = new long[K]; // 각 랜선의 길이를 저장할 배열을 선언
        long maxLength = 0; // 랜선의 최대 길이를 저장할 변수를 선언
        for (int i = 0; i < K; i++) {
            lanCables[i] = Long.parseLong(br.readLine()); // 각 랜선의 길이를 입력받아 배열에 저장
            if (lanCables[i] > maxLength) {
                maxLength = lanCables[i]; // 랜선의 최대 길이를 갱신
            }
        }

        long start = 1; // 이분 탐색의 시작점을 1로 설정
        long end = maxLength; // 이분 탐색의 끝점을 랜선의 최대 길이로 설정
        long result = 0; // 최대한 긴 랜선의 길이를 저장할 변수를 선언

        while (start <= end) {
            long mid = (start + end) / 2; // 중간값을 계산
            long count = 0; // 필요한 랜선의 수를 저장할 변수를 선언

            for (long length : lanCables) {
                count += length / mid; // 각 랜선을 나누어 줄 때 필요한 랜선의 수를 계산
            }

            if (count >= N) { // 필요한 랜선의 수가 N 이상이면
                result = mid; // 최대 길이를 갱신
                start = mid + 1; // 최대 길이를 늘리기 위해 시작점을 늘림
            } else { // 필요한 랜선의 수가 N 미만이면
                end = mid - 1; // 최대 길이를 줄이기 위해 끝점을 줄임
            }
        }

        // 최대한 긴 랜선의 길이를 출력
        System.out.println(result);
    }
}