package week6.lim.heap.diskcontroller;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/42627

import java.util.Arrays;
import java.util.PriorityQueue;

public class DiskController1 {
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

    public static void main(String[] args) {
        int[][] jobs = {{0, 3}, {1, 9}, {2, 6}};
        System.out.println(solution(jobs)); // 9
    }
}
