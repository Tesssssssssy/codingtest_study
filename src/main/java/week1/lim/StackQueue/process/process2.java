package week1.lim.StackQueue.process;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/42587

import java.util.Collections;
import java.util.PriorityQueue;

public class process2 {
    /**
     *  송연님 풀이
     */

    static Integer solution(int[] priorities, int location) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for(Integer i : priorities) {
            pq.add(i);
        }

        System.out.println(pq);     // [3, 2, 2, 1]

        int count = 0;

        while(!pq.isEmpty()) {
            for (int i = 0; i < priorities.length; i++) {
                // 배열을 돌면서 우선순위 큐의 가장 큰 값을 찾음

                if (pq.peek() == priorities[i]) {
                    // pq.peek()=3 != priorities[0] = 2 -> 아무 일도 안 일어남
                    // pq.peek()=3 != priorities[2] = 3

                    count++;
                    // count = 1

                    // 가장 큰 수여서 큐에서 나가야 할 뿐만 아니라, 찾고 있던 원소였다면 반복문 탈출
                    if (i == location) { // i = 2 == location = 2
                        return count;   // return count = 1
                    }

                    // 가장 큰 수이지만 찾고 있던 원소가 아니라면 다시 배열 탐색
                    // 다시 큐에 집어 넣는다.
                    pq.poll();
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] priorities = {2, 1, 3, 2};
        int location = 2;

        System.out.println(solution(priorities, location));     // 1
    }
}