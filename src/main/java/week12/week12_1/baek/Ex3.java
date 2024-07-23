package week12.week12_1.baek;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [게임]
 *
 * 김형택은 지금 몰래 Spider Solitaire(스파이더 카드놀이)를 하고 있다. 형택이는 이 게임을 이길 때도 있었지만, 질 때도 있었다. 누군가의 시선이 느껴진 형택이는 게임을 중단하고 코딩을 하기 시작했다. 의심을 피했다고 생각한 형택이는 다시 게임을 켰다. 그 때 형택이는 잠시 코딩을 하는 사이에 자신의 게임 실력이 눈에 띄게 향상된 것을 알았다.
 *
 * 이제 형택이는 앞으로의 모든 게임에서 지지 않는다. 하지만, 형택이는 게임 기록을 삭제 할 수 없기 때문에, 자신의 못하던 예전 기록이 현재 자신의 엄청난 실력을 증명하지 못한다고 생각했다.
 *
 * 게임 기록은 다음과 같이 생겼다.
 *
 * 	게임 횟수 : X
 * 	이긴 게임 : Y (Z%)
 * 	Z는 형택이의 승률이고, 소수점은 버린다. 예를 들어, X=53, Y=47이라면, Z=88이다.
 *
 * X와 Y가 주어졌을 때, 형택이가 게임을 최소 몇 번 더 해야 Z가 변하는지 구하는 프로그램을 작성하시오.
 *
 *
 * 입력
 * 각 줄에 정수 X와 Y가 주어진다.
 *
 *
 * 출력
 * 첫째 줄에 형택이가 게임을 최소 몇 판 더 해야하는지 출력한다. 만약 Z가 절대 변하지 않는다면 -1을 출력한다.
 *
 *
 * 제한
 * 	1 ≤ X ≤ 1,000,000,000
 * 	0 ≤ Y ≤ X
 *
 *
 * 예제 입력 1
 * 10 8
 *
 * 예제 출력 1
 * 1
 *
 * 예제 입력 2
 * 100 80
 *
 * 예제 출력 2
 * 6
 *
 * 예제 입력 3
 * 47 47
 *
 * 예제 출력 3
 * -1
 *
 * 예제 입력 4
 * 99000 0
 *
 * 예제 출력 4
 * 1000
 *
 * 예제 입력 5
 * 1000000000 470000000
 *
 * 예제 출력 5
 * 19230770
*/

public class Ex3 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long X = Long.parseLong(st.nextToken()); // 총 게임 횟수를 입력받음
        long Y = Long.parseLong(st.nextToken()); // 이긴 게임 횟수를 입력받음

        long Z = (Y * 100) / X; // 현재 승률을 계산

        if (Z >= 99) { // 승률이 99% 이상이면 승률을 올릴 수 없으므로 -1을 출력
            System.out.println(-1);
            return;
        }

        long start = 1; // 이분 탐색의 시작점을 1로 설정
        long end = X; // 이분 탐색의 끝점을 총 게임 횟수로 설정
        long result = -1; // 최소 게임 횟수를 저장할 변수를 선언

        while (start <= end) {
            long mid = (start + end) / 2; // 중간값을 계산
            long newZ = ((Y + mid) * 100) / (X + mid); // 새로운 승률을 계산

            if (newZ > Z) { // 새로운 승률이 현재 승률보다 크면
                result = mid; // 최소 게임 횟수를 갱신
                end = mid - 1; // 최소 게임 횟수를 줄이기 위해 끝점을 줄임
            } else { // 새로운 승률이 현재 승률보다 크지 않으면
                start = mid + 1; // 최소 게임 횟수를 늘리기 위해 시작점을 늘림
            }
        }

        // 최소 게임 횟수를 출력
        System.out.println(result);
    }
}