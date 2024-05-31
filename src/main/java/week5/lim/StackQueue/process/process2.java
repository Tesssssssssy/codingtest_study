package week5.lim.StackQueue.process;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/42587

import java.util.Collections;
import java.util.PriorityQueue;

public class process2 {
    /**
     *  PriorityQueue 사용한 풀이
     */
    public static int solution(int[] priorities, int location) {
        // 프로세스의 우선순위를 저장하는 우선순위 큐 생성
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Collections.reverseOrder());

        // 각 프로세스의 우선순위를 우선순위 큐에 삽입.
        for (int priority : priorities) {
            priorityQueue.add(priority);
        }

        int executionOrder = 0;
        // 실행 순서를 기록하는 변수

        // 큐에 프로세스가 남아있는 동안 반복.
        while (!priorityQueue.isEmpty()) {
            // 큐의 모든 프로세스를 순회.
            for (int i = 0; i < priorities.length; i++) {
                // 현재 프로세스의 우선순위가 우선순위 큐의 최고 우선순위와 같다면
                if (priorities[i] == priorityQueue.peek()) {
                    // 해당 프로세스를 실행.

                    executionOrder++;
                    // 우선순위 큐에서 최고 우선순위를 제거
                    priorityQueue.poll();

                    // 실행한 프로세스가 우리가 찾고 있는 위치의 프로세스인 경우 실행 순서 반환.
                    if (i == location) {
                        return executionOrder;
                    }
                }
            }
        }

        return executionOrder;
    }

    public static void main(String[] args) {
        int[] priorities1 = {2, 1, 3, 2};
        int location1 = 2;
        System.out.println(solution(priorities1, location1)); // 1

        int[] priorities2 = {1, 1, 9, 1, 1, 1};
        int location2 = 0;
        System.out.println(solution(priorities2, location2)); // 5
    }
}
