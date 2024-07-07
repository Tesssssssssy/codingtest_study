package week9.lim.week9_2.n_20300;

// 문제: https://www.acmicpc.net/problem/20300

import java.io.*;
import java.util.*;

public class Main {
    /**
     *  1. PT를 한 번 받을 때 운동기구를 최대 두 개까지만 선택 가능.
     *  2. N개의 운동기구를 한 번씩 사용 / PT를 받을 때마다 이전에 사용하지 않았던 운동기구를 선택.
     *  3. PT를 받을 때 운동기구를 되도록이면 두 개를 사용.
     *  4. PT를 한 번 받을 때의 근손실 정도가 M을 넘지 않도록.
     *  5. M의 최솟값을 출력.
     *     운동기구를 두 개 사용해서 PT를 받을 때의 근손실 정도는 두 운동기구의 근손실 정도의 합.
     *
     *   [풀이]
     *      - 총 기구의 개수를 짝수 / 홀수일 때로 나누어서 판단
     *      - 정렬
     *          - 첫 번째는 무조건 작아야 하고, 마지막 수랑 짝지어주기 위해서
     *          - 어떤 경우의 수도 제일 최대값에 제일 최소값을 더해주면 나올 수 있는 경우 중 가장 작기 때문
     *             (제일 작은 값 2개를 더하면 제일 적은 것 같지만 뒤의 수들이 크니까 뒤의 수들을 합하면 더 큰값이 생겨서 안됨)
     *      - 짝수일 경우
     *          - 짝수>
     *            loss[0] + loss[N-1] + loss[1] + loss[N-2] + ...
     *          - 홀수>
     *            맨 마지막 수를 max값을 두고, 짝수 방식과 마찬가지로 0번째와 맨 마지막 수의 전 수를 더해서 비교하며
     *            제일 큰 값을 구한다.
     *
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] lossStr = br.readLine().split(" ");
        long[] loss = new long[N];

        for (int i = 0; i < N; i++) {
            loss[i] = Long.parseLong(lossStr[i]);
        }

        Arrays.sort(loss);

        long max = Long.MIN_VALUE; // 0으로 해도 통과
        if (loss.length % 2 == 0) { // 근손실 값이 짝수 개인 경우
            for (int i = 0; i < (N / 2) + 1; i++) {
                long sum = loss[i] + loss[N - i - 1];
                max = Math.max(sum, max);
            }
        } else { // 근손실 값이 홀수 개인 경우
            max = loss[N - 1];
            for (int i = 0; i < (N / 2) + 1; i++) {
                long sum = loss[i] + loss[N - i - 2];
                max = Math.max(sum, max);
            }
        }

        System.out.println(max);
    }
}
