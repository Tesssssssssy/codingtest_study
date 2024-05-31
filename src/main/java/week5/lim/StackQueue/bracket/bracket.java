package week5.lim.StackQueue.bracket;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/12909

import java.util.Stack;

public class bracket {
    public static boolean solution(String s) {
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
        System.out.println(solution(str));  // true

        String str2 = "(())()";
        System.out.println(solution(str2)); // true

        String str3 = ")()(";
        System.out.println(solution(str3)); // false

        String str4 = "(()(";
        System.out.println(solution(str4)); // false
    }
}
