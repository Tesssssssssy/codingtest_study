package week5.lim.StackQueue.process;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/42587

import java.util.LinkedList;
import java.util.Queue;

public class process1 {
    public static int solution(int[] priorities, int location) {
        // 프로세스의 우선순위와 원래 위치를 저장하는 큐 생성.
        Queue<int[]> queue = new LinkedList<>();

        // 각 프로세스를 큐에 삽입.
        for (int i = 0; i < priorities.length; i++) {
            queue.add(new int[]{priorities[i], i});
        }

        int executionOrder = 0;
        // 실행 순서를 기록하는 변수

        while (!queue.isEmpty()) {
            // 큐의 첫 번째 프로세스를 꺼냄.
            int[] current = queue.poll();
            boolean hasHigherPriority = false;

            // 큐에 현재 프로세스보다 높은 우선순위의 프로세스가 있는지 확인.
            for (int[] process : queue) {
                if (process[0] > current[0]) {
                    hasHigherPriority = true;
                    break;
                }
            }

            if (hasHigherPriority) {
                // 높은 우선순위의 프로세스가 있다면 현재 프로세스를 다시 큐에 삽입.
                queue.add(current);
            } else {
                // 높은 우선순위의 프로세스가 없다면 현재 프로세스를 실행.
                executionOrder++;

                // 실행한 프로세스가 우리가 찾고 있는 위치의 프로세스인 경우 실행 순서를 반환.
                if (current[1] == location) {
                    return executionOrder;
                }
            }
        }

        return executionOrder;
        // 실행 순서를 찾지 못한 경우
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
