package week1.lim.StackQueue.hatesamenum;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/12906

import java.util.Arrays;
import java.util.Stack;

public class hateSameNum2 {
    static int[] solution(int[] arr) {
        Stack<Integer> stack = new Stack<>();

        for (int num : arr) {
            // 스택이 비어있거나 스택의 최상위 원소가 현재 숫자와 다를 때만 스택에 숫자 추가
            if (stack.isEmpty() || stack.peek() != num) {
                stack.push(num);
            }
        }

        // 스택에 저장된 결과를 배열로 전환
        int[] answer = new int[stack.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = stack.get(i);
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 1, 3, 3, 0, 1, 1};
        int[] arr2 = {4, 4, 4, 3, 3};

        System.out.println(Arrays.toString(solution(arr1)));  // [1, 3, 0, 1]
        System.out.println(Arrays.toString(solution(arr2)));  // [4, 3]
    }
}
