package week5.lim.StackQueue.hatesamenum;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/12906

import java.util.Arrays;
import java.util.Stack;

public class hateSameNum {
    public static int[] solution(int []arr) {
        Stack<Integer> stack = new Stack<>();

        for (int ar : arr) {
            if (stack.isEmpty() || stack.peek() != ar) {
                // stack이 비어있을 때 조건 추가하지 않으면 런타임 에러

                stack.push(ar);
            }
        }

        // stack을 배열로 변환
        int[] answer = new int[stack.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = stack.get(i);
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 1, 3, 3, 0, 1, 1};
        System.out.println(Arrays.toString(solution(arr1)));  // [1, 3, 0, 1]

        int[] arr2 = {4, 4, 4, 3, 3};
        System.out.println(Arrays.toString(solution(arr2)));  // [4, 3]
    }
}
