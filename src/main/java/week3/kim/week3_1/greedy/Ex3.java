package week3.kim.week3_1.greedy;

import java.util.Stack;

/**
 * [큰 수 만들기]
 *
 * 어떤 숫자에서 k개의 수를 제거했을 때 얻을 수 있는 가장 큰 숫자를 구하려 합니다.
 *
 * 예를 들어, 숫자 1924에서 수 두 개를 제거하면 [19, 12, 14, 92, 94, 24] 를 만들 수 있습니다.
 * 이 중 가장 큰 숫자는 94 입니다.
 *
 * 문자열 형식으로 숫자 number와 제거할 수의 개수 k가 solution 함수의 매개변수로 주어집니다.
 * number에서 k 개의 수를 제거했을 때 만들 수 있는 수 중 가장 큰 숫자를 문자열 형태로 return 하도록 solution 함수를 완성하세요.
 *
 * 제한 조건
 * number는 2자리 이상, 1,000,000자리 이하인 숫자입니다.
 * k는 1 이상 number의 자릿수 미만인 자연수입니다.
 *
 * 입출력 예
 * number		k	return
 * "1924"		2	"94"
 * "1231234"	3	"3234"
 * "4177252841"	4	"775841"
*/

public class Ex3 {
    public static void main(String[] args) {
        Ex3 ex3 = new Ex3();

        // "94" 반환
        System.out.println(ex3.solution("1924", 2));
        // "3234" 반환
        System.out.println(ex3.solution("1231234", 3));
        // "775841" 반환
        System.out.println(ex3.solution("4177252841", 4));
    }

    public String solution(String number, int k) {
        String answer = "";
        // 스택의 맨 위에 있는 숫자보다 큰 숫자가 들어오면 작은 숫자를 제거
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < number.length(); i++) {
            // 스택이 비어있지 않고,
            // 아직 제거할 숫자가 남아있으며,
            // 스택의 맨 위에 있는 숫자보다 현재 숫자가 크다면
            // 스택의 맨 위에 있는 숫자를 제거하고 제거할 숫자의 개수를 줄임
            while (!stack.isEmpty() && k > 0 && stack.peek() < number.charAt(i)) {
                stack.pop();

                k--;
            }

            // 현재 숫자를 스택에 추가
            stack.push(number.charAt(i));
        }

        // 제거할 숫자가 남아있다면
        // 스택의 맨 위에 있는 숫자를 제거
        for (int i = 0; i < k; i++) {
            stack.pop();
        }

        // 스택에 있는 숫자를 문자열로 변환하여 반환
        StringBuilder sb = new StringBuilder();

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        answer = sb.reverse().toString();

        return answer;
    }
}