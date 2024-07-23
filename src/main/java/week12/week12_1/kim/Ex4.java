package week12.week12_1.kim;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * [정수 제곱근]
 *
 * 정수가 주어지면, 그 수의 정수 제곱근을 구하는 프로그램을 작성하시오.
 *
 *
 * 입력
 * 첫째 줄에 정수 n이 주어진다. (0 ≤ n < 2^63)
 *
 *
 * 출력
 * 첫째 줄에 q2 ≥ n인 가장 작은 음이 아닌 정수 q를 출력한다.
 *
 *
 * 예제 입력 1
 * 122333444455555
 *
 * 예제 출력 1
 * 11060446
*/

public class Ex4 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 정수 n을 입력받음
        long n = Long.parseLong(br.readLine());

        /*
        *
        * long q = (long)Math.sqrt(n);
        *
        * if(Math.pow(q, 2) < n) q++;
        *
        * System.out.println(q);
        *
        */

        // 이분 탐색의 시작점을 0으로 설정
        long start = 0;
        // 이분 탐색의 끝점을 최대 가능한 정수의 제곱근으로 설정
        long end = n;
        // q^2 ≥ n인 가장 작은 음이 아닌 정수 q를 저장할 변수를 선언
        long result = 0;

        while (start <= end) {
            // 중간값을 계산
            long mid = (start + end) / 2;

            /*
            * mid * mid 는 long을 넘어갈 수 있어서
            * Math.pow 사용해서 값을 구해야 함 (변환값이 double임)
            * */

            if (Math.pow(mid, 2) >= n) { // 중간값의 제곱이 n보다 크거나 같으면
                result = mid; // q의 후보로 저장합니다.
                end = mid - 1; // 더 작은 값을 찾기 위해 끝점을 줄입니다.
            } else { // 중간값의 제곱이 n보다 작으면
                start = mid + 1; // 더 큰 값을 찾기 위해 시작점을 늘립니다.
            }
        }

        System.out.println(result); // q^2 ≥ n인 가장 작은 음이 아닌 정수 q를 출력합니다.
    }
}