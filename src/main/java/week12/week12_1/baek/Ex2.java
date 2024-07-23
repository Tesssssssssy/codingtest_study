package week12.week12_1.baek;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [용돈 관리]
 *
 * 현우는 용돈을 효율적으로 활용하기 위해 계획을 짜기로 하였다. 현우는 앞으로 N일 동안 자신이 사용할 금액을 계산하였고, 돈을 펑펑 쓰지 않기 위해 정확히 M번만 통장에서 돈을 빼서 쓰기로 하였다. 현우는 통장에서 K원을 인출하며, 통장에서 뺀 돈으로 하루를 보낼 수 있으면 그대로 사용하고, 모자라게 되면 남은 금액은 통장에 집어넣고 다시 K원을 인출한다. 다만 현우는 M이라는 숫자를 좋아하기 때문에, 정확히 M번을 맞추기 위해서 남은 금액이 그날 사용할 금액보다 많더라도 남은 금액은 통장에 집어넣고 다시 K원을 인출할 수 있다. 현우는 돈을 아끼기 위해 인출 금액 K를 최소화하기로 하였다. 현우가 필요한 최소 금액 K를 계산하는 프로그램을 작성하시오.
 *
 *
 * 입력
 * 1번째 줄에는 N과 M이 공백으로 주어진다. (1 ≤ N ≤ 100,000, 1 ≤ M ≤ N)
 *
 * 2번째 줄부터 총 N개의 줄에는 현우가 i번째 날에 이용할 금액이 주어진다. (1 ≤ 금액 ≤ 10000)
 *
 *
 * 출력
 * 첫 번째 줄에 현우가 통장에서 인출해야 할 최소 금액 K를 출력한다.
 *
 *
 * 예제 입력 1
 * 7 5
 * 100
 * 400
 * 300
 * 100
 * 500
 * 101
 * 400
 *
 * 예제 출력 1
 * 500
*/

public class Ex2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 사용할 날의 수를 입력받음
        int M = Integer.parseInt(st.nextToken()); // 인출 횟수를 입력받음

        int[] expenses = new int[N]; // 각 날의 사용 금액을 저장할 배열을 선언
        int maxExpense = 0; // 하루 최대 사용 금액을 저장할 변수를 선언
        int totalExpense = 0; // 총 사용 금액을 저장할 변수를 선언
        for (int i = 0; i < N; i++) {
            expenses[i] = Integer.parseInt(br.readLine()); // 각 날의 사용 금액을 입력받아 배열에 저장
            if (expenses[i] > maxExpense) {
                maxExpense = expenses[i]; // 하루 최대 사용 금액을 갱신
            }
            totalExpense += expenses[i]; // 총 사용 금액을 갱신
        }

        int start = maxExpense; // 이분 탐색의 시작점을 하루 최대 사용 금액으로 설정
        int end = totalExpense; // 이분 탐색의 끝점을 총 사용 금액으로 설정
        int result = totalExpense; // 인출 금액의 최소값을 저장할 변수를 선언

        while (start <= end) {
            int mid = (start + end) / 2; // 중간값을 계산
            int sum = 0; // 현재 인출 금액을 저장할 변수를 선언
            int count = 1; // 인출 횟수를 저장할 변수를 선언

            for (int expense : expenses) {
                if (sum + expense > mid) { // 현재 인출 금액으로 하루를 보낼 수 없으면
                    count++; // 인출 횟수를 증가
                    sum = expense; // 새로운 인출 금액으로 갱신
                } else {
                    sum += expense; // 현재 인출 금액으로 하루를 보낼 수 있으면 금액을 더함
                }
            }

            if (count <= M) { // 인출 횟수가 M 이하이면
                result = mid; // 인출 금액의 최소값을 갱신
                end = mid - 1; // 인출 금액을 줄이기 위해 끝점을 줄임
            } else { // 인출 횟수가 M 초과이면
                start = mid + 1; // 인출 금액을 늘리기 위해 시작점을 늘림
            }
        }

        // 인출 금액의 최소값을 출력
        System.out.println(result);
    }
}