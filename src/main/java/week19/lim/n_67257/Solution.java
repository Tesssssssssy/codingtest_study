package week19.lim.n_67257;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/67257

import java.util.*;

public class Solution {
    /**
     *  [풀이]
     *  연산자 추출: 수식에서 사용된 연산자를 추출하고, 그 연산자의 우선순위 조합을 구한다.
     *  수식 파싱: 숫자와 연산자를 따로 리스트에 저장한다.
     *  우선순위에 따라 계산: 각 연산자 우선순위 조합에 대해 해당 우선순위에 맞춰 계산을 수행한다.
     *  결과 비교: 모든 우선순위 조합에 대한 결과 중 절댓값이 가장 큰 결과를 반환한다.
     */
    public static long solution(String expression) {
        // 1. 연산자를 추출하고, 우선순위 조합을 만든다.
        List<Character> operations = Arrays.asList('+', '-', '*');
        List<String> priorities = new ArrayList<>();
        permutation(operations, "", 3, priorities);

        // 2. 연산식에서 숫자와 연산자를 분리한다.
        List<Long> numbers = new ArrayList<>();
        List<Character> operators = new ArrayList<>();
        parseExpression(expression, numbers, operators);

        // 3. 각 우선순위에 따라 계산하고 최대 절댓값을 구한다.
        long maxResult = 0;
        for (String priority : priorities) {
            maxResult = Math.max(maxResult, Math.abs(calculate(numbers, operators, priority)));
        }

        return maxResult;
    }

    // 연산자 우선순위의 모든 조합을 구하는 함수 (순열)
    private static void permutation(List<Character> operations, String current, int length, List<String> results) {
        if (current.length() == length) {
            results.add(current);
            return;
        }

        for (Character op : operations) {
            if (!current.contains(op.toString())) {
                permutation(operations, current + op, length, results);
            }
        }
    }

    // 수식을 숫자와 연산자로 분리하는 함수
    private static void parseExpression(String expression, List<Long> numbers, List<Character> operators) {
        StringBuilder num = new StringBuilder(); // 숫자를 담을 StringBuilder

        for (char c : expression.toCharArray()) {
            if (Character.isDigit(c)) { // 문자가 숫자일 경우
                num.append(c); // 현재 숫자를 num에 추가

            } else { // 문자가 연산자일 경우
                // 지금까지 만들어진 숫자를 numbers 리스트에 추가
                numbers.add(Long.parseLong(num.toString()));
                // num은 숫자들을 담고 있는 문자열이므로 Long.parseLong을 사용하여 숫자로 변환 후 리스트에 추가.

                num.setLength(0); // 다음 숫자를 위해 num 초기화
                operators.add(c); // 연산자를 operators 리스트에 추가
            }
        }

        // 마지막 숫자 추가 (수식이 끝난 후 남은 숫자 처리)
        numbers.add(Long.parseLong(num.toString()));
    }

    // 연산자의 우선순위를 적용하여 계산하는 함수
    private static long calculate(List<Long> numbers, List<Character> operators, String priority) {
        /*
            1. 숫자와 연산자 리스트의 복사본을 만든다.
               원본은 여러 우선순위 조합에서 사용되니까 복사본으로 계산 수행.
        */
        List<Long> numCopy = new ArrayList<>(numbers);
        List<Character> opCopy = new ArrayList<>(operators);

        // 2. 우선순위에 따라 연산을 처리한다.
        for (char op : priority.toCharArray()) {
            // 해당 연산자가 있는 곳에서 연산을 수행.
            for (int i = 0; i < opCopy.size();) {
                if (opCopy.get(i) == op) {
                    // 연산자에 해당하는 연산을 수행하고 결과를 해당 위치에 넣는다.
                    long result = applyOp(numCopy.remove(i), numCopy.remove(i), opCopy.remove(i));
                    numCopy.add(i, result);
                } else {
                    i++; // 연산자가 일치하지 않으면 다음으로 이동.
                }
            }
        }

        /*
            3. 최종 결과값을 반환. (리스트의 첫 번째 값이 최종 결과)
               모든 연산이 끝나면, 숫자 리스트 numCopy에는 최종 결과값만 남아 있다.
               return numCopy.get(0);에서 최종적으로 첫 번째 값이 결과값이므로 이를 반환.
        */
        return numCopy.get(0);
    }

    // 연산자에 따른 계산 처리
    private static long applyOp(long a, long b, char op) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            default: throw new IllegalArgumentException("Invalid operator");
        }
    }

    public static void main(String[] args) {
        String expression1 = "100-200*300-500+20";
        System.out.println(solution(expression1)); // 60420

        String expression2 = "50*6-3*2";
        System.out.println(solution(expression2)); // 300
    }
}

/*
    calculate 메소드

    예시:
        수식: "100-200*300-500+20"
        우선순위: "*+-"

        단계별 연산 과정:
            초기 상태:
                숫자 리스트: [100, 200, 300, 500, 20]
                연산자 리스트: ['-', '*', '-', '+']

            첫 번째 연산자: * (곱셈을 먼저 처리):
                200 * 300 = 60000
                숫자 리스트: [100, 60000, 500, 20]
                연산자 리스트: ['-', '-', '+']

            두 번째 연산자: + (덧셈 처리):
                500 + 20 = 520
                숫자 리스트: [100, 60000, 520]
                연산자 리스트: ['-', '-']

            세 번째 연산자: - (뺄셈 처리):
                60000 - 520 = 59480
                숫자 리스트: [100, 59480]
                연산자 리스트: ['-']
                100 - 59480 = -59380
                숫자 리스트: [-59380]
                연산자 리스트: []

        최종 결과는 -59380. 절댓값을 반환하면 59380이 됨.
*/