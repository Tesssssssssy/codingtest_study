package week2.lim.Heap.diskcontroller;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/42627

import java.util.Arrays;
import java.util.PriorityQueue;

public class DiskController {
    public static int solution(int[][] jobs) {
        // 작업 시간을 기준으로 오름차순 정렬하는 우선순위 큐 초기화.
        // 우선순위 큐는 짧은 작업을 먼저 처리하기 위해 사용됨.
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[1] - b[1]);

        // jobs 배열을 요청 시간을 기준으로 오름차순 정렬.
        // 이는 작업 요청 시간 순서대로 처리를 시작하기 위한 사전 준비.
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);

        int count = 0; // 처리된 작업의 수 카운트.
        int idx = 0;   // 현재 처리할 작업의 인덱스 나타냄.
        int time = 0;  // 현재 시간을 나타내며, 작업이 처리되는 시간 추적.
        int totalWaitTime = 0; // 모든 작업의 대기 시간 총합.

        // 모든 작업이 처리될 때까지 반복.
        while (count < jobs.length) {
            // 현재 시간 이전에 요청된 모든 작업을 우선순위 큐에 추가.
            while (idx < jobs.length && jobs[idx][0] <= time) {
                queue.offer(jobs[idx++]);
            }

            // 큐가 비어 있다면, 현재 처리할 작업이 없으므로 다음 작업의 요청 시간으로 시간을 이동.
            if (queue.isEmpty()) {
                time = jobs[idx][0];
            } else {
                // 큐에서 다음 작업을 가져와 처리.
                int[] job = queue.poll();
                time += job[1]; // 작업 소요 시간만큼 현재 시간을 증가.
                totalWaitTime += time - job[0]; // 작업이 완료될 때까지의 전체 시간에서 요청 시간을 빼 대기 시간을 계산.
                count++; // 처리된 작업 수를 증가시킵니다.
            }
        }

        // 모든 작업의 대기 시간 총합을 작업 수로 나누어 평균 대기 시간을 계산하고 반환.
        return totalWaitTime / jobs.length;
    }

    /*
        예시 작업 리스트:
        - Job A: 요청 시간 = 0ms, 소요 시간 = 3ms
        - Job B: 요청 시간 = 1ms, 소요 시간 = 9ms
        - Job C: 요청 시간 = 2ms, 소요 시간 = 6ms

        작업 스케줄링 단계별 설명:
        초기 상태:
        - 모든 작업은 요청 시간에 따라 정렬되어 있다: A(0,3), B(1,9), C(2,6).
        - 우선순위 큐는 비어 있다.

        처음 작업 시작 (Job A):
        - 시간 = 0ms: Job A가 요청되고, 이는 바로 우선순위 큐에 추가됨.
        - 우선순위 큐에서 Job A를 꺼내 처리 시작 (가장 짧은 소요 시간을 가진 작업).
        - 시간 = 3ms: Job A가 완료됨.
        - Job A의 총 대기 시간 = 완료 시간 - 요청 시간 = 3ms - 0ms = 3ms

        다음 작업 처리 (Job C):
        - 시간 = 1ms 및 2ms에 Job B와 Job C가 각각 요청됨. 두 작업 모두 우선순위 큐에 추가됨.
        - 우선순위 큐에는 Job B(소요시간 9ms)와 Job C(소요시간 6ms)가 있고, Job C가 먼저 처리됨 (소요 시간이 짧기 때문).
        - 시간 = 3ms에서 Job C 처리 시작.
        - 시간 = 9ms: Job C가 완료됨.
        - Job C의 총 대기 시간 = 완료 시간 - 요청 시간 = 9ms - 2ms = 7ms

        마지막 작업 처리 (Job B):
        - Job B만 우선순위 큐에 남아있다.
        - 시간 = 9ms에서 Job B 처리 시작.
        - 시간 = 18ms: Job B가 완료됨.
        - Job B의 총 대기 시간 = 완료 시간 - 요청 시간 = 18ms - 1ms = 17ms

        평균 대기 시간 계산:
        - 총 대기 시간 = Job A의 대기시간 + Job B의 대기시간 + Job C의 대기시간 = 3ms + 17ms + 7ms = 27ms
        - 평균 대기 시간 = 총 대기 시간 / 작업 수 = 27ms / 3 = 9ms

        결론:
        - 위의 과정을 통해 우선순위 큐를 사용하여 각 작업을 가장 짧은 소요 시간을 기준으로 먼저 처리함으로써 전체적인 평균 대기 시간을 최소화할 수 있다.
        - 이 방식은 "최소 작업 시간 우선" 스케줄링이라고 할 수 있으며,
          대기 중인 작업 중에서 처리 시간이 가장 짧은 작업을 우선적으로 처리하여 전체 대기 시간을 줄인다.
    */

    public static void main(String[] args) {
        int[][] jobs = {{0, 3}, {1, 9}, {2, 6}};
        System.out.println(solution(jobs)); // 9
    }
}
