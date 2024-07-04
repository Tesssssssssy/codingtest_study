package week9.lim.week9_2.n_2512;

// 문제: https://www.acmicpc.net/problem/2512

import java.io.*;
import java.util.*;

public class Main {
    /**
     *  1. 정해진 총액 이하에서 가능한 한 최대의 총 예산 배정
     *  2. 모든 요청이 배정될 수 있는 경우, 요청한 금액을 그대로 배정
     *  3. 모든 요청이 배정될 수 없는 경우에는 특정한 정수 상한액을 계산하여 그 이상인 예산요청에는 모두 상한액을 배정.
     *     상한액 이하의 예산요청에 대해서는 요청한 금액을 그대로 배정
     *  4. 485이고 4개 지방의 예산요청이 각각 120, 110, 140, 150이라고 하자.
     *     이 경우, 상한액을 127로 잡으면,
     *     위의 요청들에 대해서 각각 120, 110, 127, 127을 배정하고 그 합이 484로 가능한 최대.
     *  5. 위의 조건을 모두 만족하도록 예산을 배정.
     *
     *  [정리]
     *  이진 탐색 사용
     *      이진 탐색 초기화: 최소 상한액을 0으로, 최대 상한액을 예산 요청 중 최댓값으로 설정.
     *      이진 탐색 실행: 상한액을 조절하면서 국가 예산 내에서 가능한 최대의 예산을 할당.
     *      상한액 결정: 이진 탐색을 통해 각 지방에 배정할 수 있는 최대 예산의 상한액을 찾는다.
     *
     *  시간 복잡도: O(N log(maxRequest)).
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 지방의 수
        int[] requests = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int maxRequest = 0;
        for (int i = 0; i < N; i++) {
            requests[i] = Integer.parseInt(st.nextToken());
            maxRequest = Math.max(maxRequest, requests[i]);
        }

        int M = Integer.parseInt(br.readLine()); // 총 예산

        // 이진 탐색을 위한 초기 설정
        int low = 0;
        int high = maxRequest;
        int result = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            long sum = 0;

            for (int request : requests) {
                if (request > mid) {
                    sum += mid; // 상한액 적용
                } else {
                    sum += request; // 요청 금액 그대로 적용
                }
            }

            if (sum > M) { // 현재 설정된 상한액 mid로 인해 예산 초과가 발생
                high = mid - 1;
                // 가능한 상한액을 낮춰야 하므로,
                // 이진 탐색의 상한 범위 high를 현재 중간값 mid - 1로 조정
            } else { // 현재 상한액 mid로 설정했을 때 예산 범위 내에 있음을 의미.
                low = mid + 1;
                // 상한액을 더 높여도 되는지 확인하기 위해,
                // 이진 탐색의 하한 범위 low를 mid + 1로 조정

                result = mid; // 현재까지 검토된 조건들 중에서 예산 범위 내에서 가능한 최대 상한액
            }
        }

        System.out.println(result); // 최대 상한액 출력
    }
}
