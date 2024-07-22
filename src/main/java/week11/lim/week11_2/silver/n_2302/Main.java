package week11.lim.week11_2.silver.n_2302;

// 문제: https://www.acmicpc.net/problem/2302

import java.io.*;
import java.util.*;

public class Main {
    /**
     *  1. 극장의 좌석은 한 줄 / 왼쪽부터 차례로 1 - N번
     *  2. 입장권 번호가 5번이면 5번 좌석에 앉아야 함.
     *  3. 단, 자신의 바로 왼쪽 또는 오른쪽 좌석으로 자리를 옮길 수 있다.
     *  4. VIP 회원들은 반드시 자신의 좌석에만 앉아야 함. (이동 불가)
     *  5. 오늘 모든 좌석 sold out
     *  6. VIP 회원들의 좌석번호가 주어졌을 때
     *     사람들이 좌석에 앉는 서로 다른 방법의 가짓수 출력
     *
     *  [아이디어]
     *      ex.) 1, 2, 3이 있고 고정 좌석이 2 하나라면,
     *          가능한 경우의 수는
     *          1, 2, 3 -> 1가지 (자기 자리에 앉는 경우)
     *
     *          1, 2, 3인데 고정 좌석이 1 하나라면,
     *          가능한 경우의 수는
     *          1, 2, 3
     *          1, 3, 2 -> 2가지 (2-3 자리 교환 + 자기 자리에 앉는 경우)
     *
     *          고려할 경우
     *          1. 고정 좌석이 없을 경우
     *          2. 고정 좌석이 있다면
     *             자기 자리에 앉는 경우
     *             옆 자리와 이동할 경우 (이동이 가능하다면)
     *
     *  [풀이]
     *  주어진 좌석들 사이에서 고정 좌석을 기준으로 자유롭게 배치 가능한 섹션을 계산해서
     *  각 섹션의 가능한 좌석 배치를 계산하는 방식
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

//        int[] vipSeats = new int[M];
        int[] vipSeats = new int[M + 2];
        /*
            여기서 vipSeats 배열은 고정 좌석을 저장하기 위한 배열.
            M은 고정 좌석의 개수이며, M+2를 사용하는 이유는 배열의 시작과 끝에 '가상의 고정 좌석'을 추가하기 위해서임.
            이 가상의 고정 좌석은 계산을 용이하게 하기 위해 사용됨.
         */

        for (int i = 1; i <= M; i++) {
            vipSeats[i] = Integer.parseInt(br.readLine());
        }

        vipSeats[0] = 0;       // 시작 지점 가상 고정 좌석
        vipSeats[M + 1] = N + 1; // 마지막 지점 가상 고정 좌석

        /*
            ex.)
            N=9 / M=2 / 4, 7이 고정 좌석이라면
               vipSeats[1] = 4, vipSeats[2] = 7이고

               vipSeats[0] = 0, vipSeats[3] = 10이 됨.
               -> vipSeats
                  | 0 | 1 | 2 | 3  |
                  | 0 | 4 | 7 | 10 |

            vipSeats[0] = 0
                시작 지점 바로 앞에 고정 좌석이 있는 것처럼 설정,
                첫 번째 실제 좌석 (1번 좌석) 바로 앞에 가상의 고정 좌석이 있다고 가정.
                첫 번째 실제 고정 좌석과 첫 번째 좌석 사이의 구간을 계산할 때, 일관된 방식으로 첫 구간을 처리할 수 있다.
                예를 들어, 첫 번째 고정 좌석이 4번 좌석이라면,
                         1번부터 3번 좌석까지의 구간을 계산하기 위해 시작 지점 0을 사용할 수 있다.
            vipSeats[M + 1] = N + 1
                마지막 좌석 바로 뒤에 고정 좌석이 있는 것처럼 설정,
                마지막 실제 좌석 (N번 좌석) 바로 뒤에 가상의 고정 좌석이 있다고 가정
                마지막 실제 고정 좌석과 마지막 좌석 사이의 구간을 계산할 때도 일관된 방식으로 마지막 구간을 처리할 수 있다.
                예를 들어, 마지막 고정 좌석이 7번 좌석이고 전체 좌석이 9개라면,
                         8번부터 9번 좌석까지의 구간을 계산하기 위해 끝 지점 N+1을 사용할 수 있다.

            이렇게 가상의 고정 좌석을 설정함으로써,
            실제 고정 좌석 사이 뿐만 아니라,
            고정 좌석과 행의 시작 또는 끝 사이의 구간까지도 동일한 방법으로 처리할 수 있다.
        */

//        Arrays.sort(vipSeats);
        /*
            고정 좌석 번호가 순서대로 입력되지 않을 수 있기 때문에,
            배열을 정렬하여 각 섹션을 올바르게 계산할 수 있도록 한다.
            -> 정렬 없어도 통과 ok
        */

        int[] dp = new int[N + 1];
        dp[0] = 1; // 0개의 좌석은 1가지 방법
        dp[1] = 1; // 1개의 좌석은 1가지 방법
        if (N >= 2) {
            dp[2] = 2; // 2개의 좌석은 2가지 방법
        }
        /*
            dp[i] 배열은 i개의 연속된 자유 좌석에서 가능한 좌석 배치의 수를 저장.
            dp[0] = 1은 자리가 없을 때도 하나의 '빈' 경우로 취급.
            dp[1] = 1과 dp[2] = 2는 각각 1개와 2개의 좌석이 있을 때의 배치 경우의 수 의미.
        */


        for (int i = 3; i <= N; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        /*
            동적 계획법 (피보나치 수열 참고)
                이 공식은 각 좌석 구간에서의 가능한 좌석 배치 수를 계산.
                피보나치 수열과 같은 패턴을 따르는데,
                i번째 좌석의 경우의 수는 바로 앞 좌석(i-1)의 경우의 수와 그 앞 좌석(i-2)의 경우의 수를 합한 값.
        */

        long result = 1; // 가능한 가짓수가 최대 2^31 - 1 이므로 long 타입 사용

        for (int i = 1; i <= M + 1; i++) {
            int sectionLength = vipSeats[i] - vipSeats[i - 1] - 1; // 섹션의 길이
            result *= dp[sectionLength]; // 가능한 배치의 수 곱하기
        }
        /*
            각 고정 좌석 사이의 구간(섹션)의 길이를 계산하고,
            해당 길이에 대한 가능한 좌석 배치 수(dp[sectionLength])를 모두 곱하여 최종 결과를 도출.
            이 방식으로 모든 섹션의 배치 가능성을 고려하여 전체 경우의 수를 계산.

            N=9 / M=2 / 4, 7이 고정 좌석이라면
            vipSeats[1] = 4, vipSeats[2] = 7,
            vipSeats[0] = 0, vipSeats[3] = 10.
            -> vipSeats
               | 0 | 1 | 2 | 3  |
               | 0 | 4 | 7 | 10 |

            고정 좌석을 기준으로 섹션을 나누어서 길이를 통해 섹션 별 경우의 수를 구하고
            이를 곱해서 최종 경우의 수를 계산한다.

            i=1,
            sectionLength = vipSeats[1] - vipSeats[0] - 1
                          = 4 - 0 - 1
                          = 3
            result *= dp[3] = 1 * 3 = 3

            i=2,
            sectionLength = vipSeats[2] - vipSeats[1] - 1
                          = 7 - 4 - 1
                          = 2
            result *= dp[2] = 3 * 2 = 6

            i=3,
            sectionLength = vipSeats[3] - vipSeats[2] - 1
                          = 10 - 7 - 1
                          = 2
            result *= dp[2] = 6 * 2 = 12

            최종 답: 12
        */

        System.out.println(result);
    }
}