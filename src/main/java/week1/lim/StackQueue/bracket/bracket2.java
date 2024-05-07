package week1.lim.StackQueue.bracket;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/12909

import java.util.Stack;

public class bracket2 {
    /**
     *  stack을 사용한 풀이
     */
    static boolean solution(String s) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push('(');
            } else if (s.charAt(i) == ')') {
                if (stack.isEmpty()) {
                    return false;
                }
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String str = "()()";
        String str2 = "(())()";
        String str3 = ")()(";
        String str4 = "(()(";
        System.out.println(solution(str));  // true
        System.out.println(solution(str2)); // true
        System.out.println(solution(str3)); // false
        System.out.println(solution(str4)); // false
    }
}
