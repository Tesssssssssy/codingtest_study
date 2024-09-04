package week17.lim.n_118667;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/118667

public class Solution {
    /**
     *  두 큐의 합 계산:
     *      두 큐의 원소 합을 각각 계산하여, 전체 합의 절반을 목표 값으로 설정.
     *
     *  투 포인터 사용:
     *      두 큐를 하나의 배열로 연결하여 슬라이딩 윈도우 방식으로 접근.
     *      두 포인터(인덱스)를 사용하여 큐에서 원소를 추출하고 삽입하는 작업을 시뮬레이션.
     *
     *  가능한 경우 확인:
     *      목표 값을 넘지 않는 선에서 포인터를 이동시켜 작업을 최소화.
     *      만약 두 큐의 합이 동일해질 수 없는 경우, 예를 들어 전체 합이 홀수라면 -1을 반환.
     */
    public static int solution(int[] queue1, int[] queue2) {
        long sum1 = 0, sum2 = 0;
        long totalSum = 0;

        // 두 큐의 합을 계산
        for (int num : queue1)
            sum1 += num;

        for (int num : queue2)
            sum2 += num;

        totalSum = sum1 + sum2;

        // 전체 합이 홀수면 두 큐의 합을 같게 할 수 없음
        if (totalSum % 2 != 0)
            return -1;

        long target = totalSum / 2;
        int n = queue1.length;
        int[] combined = new int[2 * n];

        System.arraycopy(queue1, 0, combined, 0, n);
        System.arraycopy(queue2, 0, combined, n, n);

        /*
            슬라이딩 윈도우 구현:
                두 큐를 하나의 배열(combined)로 합친 후,
                투 포인터를 사용해 left와 right를 이동시키며 최소 작업 횟수를 계산.
                currentSum이 목표 합인 target과 같아지면 해당 작업 횟수를 반환.
        */

        int left = 0, right = n;
        long currentSum = sum1;
        int minOperations = 0;

        while (left < 2 * n && right < 2 * n) {
            if (currentSum == target) {
                return minOperations;

            } else if (currentSum < target) {
                currentSum += combined[right++];

            } else {
                currentSum -= combined[left++];
            }

            minOperations++;
        }

        return -1; // 조건을 만족하는 합을 만들 수 없는 경우
    }

    public static void main(String[] args) {
        int[] queue1_1 = {3, 2, 7, 2};
        int[] queue2_1 = {4, 6, 5, 1};
        System.out.println(solution(queue1_1, queue2_1)); // 2

        int[] queue1_2 = {1, 2, 1, 2};
        int[] queue2_2 = {1, 10, 1, 2};
        System.out.println(solution(queue1_2, queue2_2)); // 7

        int[] queue1_3 = {1, 1};
        int[] queue2_3 = {1, 5};
        System.out.println(solution(queue1_3, queue2_3)); // -1
    }
}
