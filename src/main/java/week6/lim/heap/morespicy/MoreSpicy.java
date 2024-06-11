package week6.lim.heap.morespicy;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/42626

import java.util.PriorityQueue;

public class MoreSpicy {
    /**
     *  1. 입력받은 스코빌 배열 PriorityQueue에 넣어서 자동 정렬
     *  2. 가장 작은 값 2개 선택 후
     *     새로운 값 = 가장 작은 값 + ( 두 번째로 작은 값 * 2 )
     *  3. 이 때 반환할 answer++
     *  4. 새로운 값을 다시 PriorityQueue에 넣는다.
     *  5. 모든 값이 K 이상이면 answer return
     */
    public static int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        for (int scov : scoville) {
            priorityQueue.offer(scov);
        }

        //while (priorityQueue.peek() < K) { // test 1, 3, 8, 14 - 실패(런타임 에러)
        while (priorityQueue.size() > 1 && priorityQueue.peek() < K) {
            int min1 = priorityQueue.poll();
            int min2 = priorityQueue.poll();
            int newValue = min1 + (min2 * 2);
            answer++;
            priorityQueue.offer(newValue);
        }

        if (priorityQueue.peek() < K) {
            return -1;
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] scoville = {1, 2, 3, 9, 10, 12};
        int K = 7;

        System.out.println(solution(scoville, K));  // 2
    }
}
