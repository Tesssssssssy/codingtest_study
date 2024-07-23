package week12.lim.week12_1.silver.n_6236;

// 문제: https://www.acmicpc.net/problem/6236

import java.io.*;

public class Main {
    /**
     *  - 앞으로 N일 동안 자신이 사용할 금액을 계산하였고,
     *    돈을 펑펑 쓰지 않기 위해 정확히 M 번만 통장에서 돈을 빼서 쓰기로.
     *  - K 원을 인출하며, 통장에서 뺀 돈으로 하루를 보낼 수 있으면 그대로 사용하고,
     *    모자라게 되면 남은 금액은 통장에 집어넣고 다시 K원을 인출.
     *  - M이라는 숫자를 좋아하기 때문에,
     *    정확히 M번을 맞추기 위해서 남은 금액이 그날 사용할 금액보다 많더라도
     *    남은 금액은 통장에 집어넣고 다시 K원을 인출할 수 있다.
     *  - 돈을 아끼기 위해 인출 금액 K를 최소화.
     *  - 현우가 필요한 최소 금액 K를 계산하는 프로그램.
     *
     *  [입력]
     *  N / M. (1 ≤ N ≤ 100,000, 1 ≤ M ≤ N)
     *  총 N개의 줄에 현우가 i번째 날에 이용할 금액. (1 ≤ 금액 ≤ 10000)
     *
     *  [출력]
     *  통장에서 인출해야 할 최소 금액 K를 출력
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        int[] days = new int[N];
        int maxDaily = 0;
        long totalSum = 0;

        for (int i = 0; i < N; i++) {
            days[i] = Integer.parseInt(br.readLine());
            maxDaily = Math.max(maxDaily, days[i]);
            totalSum += days[i];
        }

        long left = maxDaily; // 최소 K는 어느 하루에 필요한 최대 금액
        long right = totalSum; // 최대 K는 모든 금액을 한 번에 인출하는 경우
        long answer = totalSum; // 결과를 저장할 변수 초기화

        while (left <= right) {
            long mid = (left + right) / 2;
            long currentSum = 0;
            int count = 1; // 최소 한 번은 인출

            for (int moneyNeeded : days) {
                if (currentSum + moneyNeeded > mid) {
                    count++;  // 새로 인출
                    currentSum = 0;
                    if (count > M) {
                        break;
                    }
                }
                currentSum += moneyNeeded;
            }

            if (count <= M) {
                answer = mid;  // 가능한 경우, mid 저장
                right = mid - 1; // 더 작은 값 탐색
            } else {
                left = mid + 1; // 큰 값 탐색
            }
        }

        System.out.println(answer);
    }
}
