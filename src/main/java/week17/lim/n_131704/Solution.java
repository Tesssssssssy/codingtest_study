package week17.lim.n_131704;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/131704

import java.util.*;

public class Solution {
    public static int solution(int[] order) {
        Stack<Integer> stack = new Stack<>();
        // 보조 컨테이너 벨트

        int index = 0; // order 배열을 순차적으로 접근하기 위한 인덱스

        for (int boxNumber = 1; boxNumber <= order.length; boxNumber++) {
            /*
                기본 컨테이너 벨트에서 상자를 꺼내면서 바로 트럭에 실을 수 없으면, 보조 컨테이너 벨트(스택)에 넣는다.
                보조 컨테이너 벨트의 맨 위 상자를 트럭에 실을 수 있을 때는 꺼내서 싣는다.
                더 이상 처리할 수 없는 상자가 나오면 루프를 종료한다.
            */

            // 상자를 순차적으로 보조 컨테이너 벨트에 추가
            stack.push(boxNumber);

            // 현재 보조 컨테이너 벨트의 맨 위 상자가 트럭에 실어야 하는 상자와 일치할 때, 실음
            while (!stack.isEmpty() && stack.peek() == order[index]) {
                stack.pop();
                index++;
            }
        }

        return index; // 최종적으로 트럭에 실린 상자의 개수 반환
    }

    public static void main(String[] args) {
        int[] order1 = {4, 3, 1, 2, 5};
        System.out.println(solution(order1)); // 2

        int[] order2 = {5, 4, 3, 2, 1};
        System.out.println(solution(order2)); // 5
    }
}
