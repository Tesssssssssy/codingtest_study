package week11.kim.week11_2.kim;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * [극장 좌석]
 *
 * 어떤 극장의 좌석은 한 줄로 되어 있으며 왼쪽부터 차례대로 1번부터 N번까지 번호가 매겨져 있다. 공연을 보러 온 사람들은 자기의 입장권에 표시되어 있는 좌석에 앉아야 한다. 예를 들어서, 입장권에 5번이 쓰여 있으면 5번 좌석에 앉아야 한다. 단, 자기의 바로 왼쪽 좌석 또는 바로 오른쪽 좌석으로는 자리를 옮길 수 있다. 예를 들어서, 7번 입장권을 가진 사람은 7번 좌석은 물론이고, 6번 좌석이나 8번 좌석에도 앉을 수 있다. 그러나 5번 좌석이나 9번 좌석에는 앉을 수 없다.
 *
 * 그런데 이 극장에는 “VIP 회원”들이 있다. 이 사람들은 반드시 자기 좌석에만 앉아야 하며 옆 좌석으로 자리를 옮길 수 없다.
 *
 * 오늘 공연은 입장권이 매진되어 1번 좌석부터 N번 좌석까지 모든 좌석이 다 팔렸다. VIP 회원들의 좌석 번호들이 주어졌을 때, 사람들이 좌석에 앉는 서로 다른 방법의 가짓수를 구하는 프로그램을 작성하시오.
 *
 * 예를 들어서, 좌석이 9개이고, 4번 좌석과 7번 좌석이 VIP석인 경우에 <123456789>는 물론 가능한 배치이다. 또한 <213465789> 와 <132465798> 도 가능한 배치이다. 그러나 <312456789> 와 <123546789> 는 허용되지 않는 배치 방법이다.
 *
 *
 * 입력
 * 첫째 줄에는 좌석의 개수 N이 입력된다. N은 1 이상 40 이하이다. 둘째 줄에는 고정석의 개수 M이 입력된다. M은 0 이상 N 이하이다. 다음 M 개의 줄에는 고정석의 번호가 작은 수부터 큰 수의 순서로 한 줄에 하나씩 입력된다.
 *
 *
 * 출력
 * 주어진 조건을 만족하면서 사람들이 좌석에 앉을 수 있는 방법의 가짓수를 출력한다. 방법의 가짓수는 2,000,000,000을 넘지 않는다. (2,000,000,000 < 231-1)
 *
 *
 * 예제 입력 1
 * 9
 * 2
 * 4
 * 7
 *
 * 예제 출력 1
 * 12
*/

public class Ex1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 좌석의 개수를 입력
        int M = Integer.parseInt(br.readLine()); // VIP 회원의 수를 입력

        // 피보나치 수열을 저장할 배열을 선언
        int[] dp = new int[N+1];
        dp[0] = dp[1] = 1; // 피보나치 수열의 첫 번째와 두 번째 항은 1
        dp[2] = 2; // 피보나치 수열의 세 번째 항은 2
        for (int i = 3; i <= N; i++) {
            // 피보나치 수열의 일반항을 계산하여 dp 배열에 저장
            dp[i] = dp[i-1] + dp[i-2];
        }

        // VIP 좌석 순서 고려해야 할 경우 > 정렬 필요

//        // VIP 회원의 좌석 번호를 저장할 배열
//        int[] vip = new int[M+1];
//        vip[0] = 0;
//        for (int i = 1; i <= M; i++) {
//            vip[i] = Integer.parseInt(br.readLine()); // VIP 회원의 좌석 번호 입력 받기
//        }

//        // VIP 회원의 좌석 번호를 기준으로 나눈 구간의 길이를 저장할 배열
//        int[] section = new int[M+2];
////        section[0] = vip[1] - 1; // 첫 번째 구간의 길이
//        for (int i = 0; i <= M; i++) {
//            if (i < M)
//                section[i] = vip[i+1] - vip[i] - 1; // 중간 구간의 길이
//            else if (i == M)
//                section[i+1] = N - vip[i]; // 마지막 구간의 길이
//        }

//        // 각 구간에서 앉을 수 있는 경우의 수를 모두 곱하여 전체 경우의 수를 구함
//        int answer = 1;
//        for (int i = 0; i <= M+1; i++) {
//            answer *= dp[section[i]]; // 각 구간에서 앉을 수 있는 경우의 수를 곱함
//        }

        int answer = 1; // 경우의 수를 저장할 변수를 선언하고 1로 초기화
        int vipSeat = 0; // 이전 VIP 좌석 번호를 저장할 변수를 선언하고 0으로 초기화
        for (int i = 0; i < M; i++) {
            int temp = Integer.parseInt(br.readLine()); // 현재 VIP 좌석 번호를 입력
            // 이전 VIP 좌석과 현재 VIP 좌석 사이의
            // 좌석에서 앉을 수 있는 경우의 수를 계산하여 answer에 곱함
            answer *= dp[temp - vipSeat - 1];
            vipSeat = temp; // 이전 VIP 좌석 번호를 업데이트
        }
        // 마지막 VIP 좌석과 마지막 좌석 사이의
        // 좌석에서 앉을 수 있는 경우의 수를 계산하여 answer에 곱함
        answer *= dp[N - vipSeat];

        System.out.println(answer); // 계산된 경우의 수를 출력
    }
}