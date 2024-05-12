package week1.lim.StackQueue.process;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/42587

import java.util.LinkedList;
import java.util.Queue;

public class process1 {
    /**
     *  현균님 풀이
     */

    static int solution(int[] priorities, int location) {
        Queue<Integer> queue = new LinkedList<>();

        for (int priority : priorities) {
            queue.offer(priority);
        }

        int answer = 0;

        while (!queue.isEmpty()) {
            int current = queue.poll();     // 큐에서 하나 빼고
            boolean isMaxPriority = true;   // 우선순위가 가장 좋은 프로세스인지 판단하기 위한 변수

            for (int priority : queue) {    // 우선순위가 저장되어 있는 큐를 돌면서
                if (priority > current) {   // 큐에 있는 우선순위가 현재 우선순위보다 높다면
                    isMaxPriority = false;  // 현재 큐에서 뺀 프로세스는 가장 높은 우선순위를 가진 프로세스가 아님
                    break;                  // for문 탈출하고 다시 큐가 빌 때까지 반복
                }
            }

            if (isMaxPriority) {    // 만약 우선순위가 가장 높은 프로세스라면
                answer++;           // answer = 0 + 1 = 1
                if (location == 0) {    // 만약 위치가 0이라면
                    break;              // if문 탈출
                }
            } else {
                queue.offer(current);   // 큐에서 가장 높은 우선순위가 아니라면 다시 큐에 넣는다.
            }

            location = (location - 1 + queue.size()) % queue.size();
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] priorities1 = {2, 1, 3, 2};
        int location1 = 2;
        System.out.println(solution(priorities1, location1));   // 1

        int[] priorities2 = {1, 1, 9, 1, 1, 1};
        int location2 = 0;
        System.out.println(solution(priorities2, location2));   // 5
    }
}
