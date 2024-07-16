package week11.kim.week11_1.baek;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [치킨 쿠폰]
 *
 * 강민이는 치킨 한 마리를 주문할 수 있는 치킨 쿠폰을 n장 가지고 있다.
 * 이 치킨집에서는 치킨을 한 마리 주문할 때마다 도장을 하나씩 찍어 주는데,
 * 도장을 k개 모으면 치킨 쿠폰 한 장으로 교환할 수 있다.
 *
 * 강민이가 지금 갖고 있는 치킨 쿠폰으로 치킨을 최대 몇 마리나 먹을 수 있는지 구하여라.
 * 단, 치킨을 주문하기 위해서는 반드시 치킨 쿠폰을 갖고 있어야 한다.
 *
 *
 * 입력
 * 여러 줄에 걸쳐서 자연수 n과 k가 주어진다.
 *
 *
 * 출력
 * 각 입력마다 한 줄에 정답을 출력한다.
 *
 *
 * 제한
 * 1 < k ≤ n ≤ 1,000,000,000
 *
 *
 * 예제 입력 1
 * 4 3
 * 10 3
 * 100 5
 *
 * 예제 출력 1
 * 5
 * 14
 * 124
*/

public class Ex1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;

        // 입력이 끝날 때까지 반복
        while ((input = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(input, " ");
            int n = Integer.parseInt(st.nextToken()); // 치킨 쿠폰 수
            int k = Integer.parseInt(st.nextToken()); // 도장으로 쿠폰을 얻는 데 필요한 수

            int total = n; // 총 치킨 수
            int stamp = n; // 도장 수

            // 도장이 k개 이상이면 쿠폰으로 교환
            while (stamp >= k) {
                total += stamp / k; // 쿠폰으로 얻은 치킨 수를 더함
                stamp = stamp / k + stamp % k; // 새로운 도장 수 계산
            }

            System.out.println(total); // 총 치킨 수 출력
        }
    }
}