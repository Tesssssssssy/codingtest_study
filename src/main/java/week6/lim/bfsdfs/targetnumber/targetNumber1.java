package week6.lim.bfsdfs.targetnumber;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/43165

import java.util.LinkedList;
import java.util.Queue;

public class targetNumber1 {
    /**
     *  queue를 이용한 풀이
     */

    public static int solution(int[] numbers, int target) {
        int answer = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);

        for (int i = 0; i < numbers.length; i++) {
            int size = queue.size();
            for (int j = 0; j < size; j++) {
                Integer sum = queue.poll();
                queue.add(sum + numbers[i]);
                queue.add(sum - numbers[i]);
            }
        }

        while (!queue.isEmpty()) {
            Integer sum = queue.poll();

            if (sum == target) {
                answer += 1;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] numbers1 = {1, 1, 1, 1, 1};
        int target1 = 3;
        System.out.println(solution(numbers1, target1));  // 5

        int[] numbers2 = {4, 1, 2, 1};
        int target2 = 4;
        System.out.println(solution(numbers2, target2));  // 2
    }
}
