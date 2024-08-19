package week15.lim.programmers_Lv2.n_12973.practice;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/12973

import java.util.Stack;

public class Solution {
    /**
     *  짝지어 제거하기는, 알파벳 소문자로 이루어진 문자열을 가지고 시작합니다.
     *  먼저 문자열에서 같은 알파벳이 2개 붙어 있는 짝을 찾습니다.
     *  그 다음, 그 둘을 제거한 뒤, 앞뒤로 문자열을 이어 붙입니다.
     *  이 과정을 반복해서 문자열을 모두 제거한다면 짝지어 제거하기가 종료됩니다.
     *
     *  문자열 S가 주어졌을 때, 짝지어 제거하기를 성공적으로 수행할 수 있는지 반환하는 함수를 완성해 주세요.
     *  성공적으로 수행할 수 있으면 1을, 아닐 경우 0을 리턴해주면 됩니다.
     *
     *  예를 들어, 문자열 S = baabaa 라면
     *  b aa baa → bb aa → aa →
     *  의 순서로 문자열을 모두 제거할 수 있으므로 1을 반환합니다.
     *
     *  [풀이]
     *  스택 사용
     */
    public static int solution(String s) {
        // 가장 최근에 문자와 다음 문자를 비교해야 하므로 Stack 사용
        Stack<Character> stack = new Stack<Character>();

        for (char c : s.toCharArray()) {
            if (!stack.isEmpty() && stack.peek() == c) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }

        return stack.isEmpty() ? 1 : 0;
    }

    public static void main(String[] args) {
        String s1 = "baabaa";
        System.out.println(solution(s1)); // 1

        String s2 = "cdcd";
        System.out.println(solution(s2)); // 0
    }
}