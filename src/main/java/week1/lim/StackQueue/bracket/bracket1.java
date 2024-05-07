package week1.lim.StackQueue.bracket;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/12909

public class bracket1 {
    /**
     *  queue, stack을 사용하지 않고 탐색으로 풀이
     */
    static boolean solution(String s) {
        int openCount = 0;
        int closeCount = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                openCount++;
            } else if (s.charAt(i) == ')') {
                closeCount++;
            }
            if (openCount < closeCount) {
                return false;
            }
        }
        if (openCount == closeCount) {
            return true;
        }
        return false;
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
