package week6.lim.greedy.makebignumber;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/42883

public class makeBigNumber1 {
    public static String solution(String number, int k) {
        // 결과를 저장할 스택
        char[] stack = new char[number.length() - k];
        int top = 0;

        for (int i = 0; i < number.length(); i++) {
            char c = number.charAt(i);
            // 스택의 최상위 값보다 현재 값이 크면 최상위 값을 제거
            // (k>0이면서 스택이 비어있지 않고, 스택의 최상단 값이 현재 값보다 작을 때)
            while (top > 0 && stack[top - 1] < c && k > 0) {
                top--;
                k--;
            }
            // 스택이 가득 차지 않았다면 현재 값을 스택에 추가
            if (top < stack.length) {
                stack[top++] = c;
            }
        }

        // 스택의 내용을 결과 문자열로 변환
        return new String(stack);
    }

    public static void main(String[] args) {
        String number1 = "1924";
        int k1 = 2;
        System.out.println(solution(number1, k1));  // 94

        String number2 = "1231234";
        int k2 = 3;
        System.out.println(solution(number2, k2));  // 3234

        String number3 = "4177252841";
        int k3 = 4;
        System.out.println(solution(number3, k3));  // 775841
    }
}
