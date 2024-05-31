package week5.lim.StackQueue.stock;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/42584

import java.util.Arrays;
import java.util.Stack;

public class stock2 {
    public static int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < prices.length; i++) {
            // 스택이 비어있지 않고, 현재 가격이 스택의 top에 있는 가격보다 낮은 경우

            while (!stack.isEmpty() && prices[i] < prices[stack.peek()]) {
                int index = stack.pop();
                answer[index] = i - index;
            }

            stack.push(i);
            // 현재 인덱스를 스택에 푸시
        }

        // 스택에 남아 있는 인덱스 처리
        while (!stack.isEmpty()) {
            int index = stack.pop();
            answer[index] = prices.length - index - 1;
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] prices = {1, 2, 3, 2, 3};
        System.out.print(Arrays.toString(solution(prices)));  // [4, 3, 1, 1, 0]
    }
}
