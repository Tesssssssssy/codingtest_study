package week18.lim.n_12899.practice;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/12899

public class Solution {
    public static String solution(int n) {
        StringBuilder sb = new StringBuilder();

        while (n > 0) {
            int remainder = n % 3;
            n /= 3;

            if (remainder == 0) {
                sb.append("4");
                n--;
            } else if (remainder == 1) {
                sb.append("1");
            } else if (remainder == 2) {
                sb.append("2");
            }
        }

        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(solution(1)); // 1
        System.out.println(solution(2)); // 2
        System.out.println(solution(3)); // 3
        System.out.println(solution(4)); // 11
    }
}
/*
    예시 1: n = 4
        첫 번째 반복:
            n = 4
            n % 3 = 1 (나머지: 1)
            n / 3 = 1 (몫: 1)
            결과: "1"

        두 번째 반복:
            n = 1
            n % 3 = 1 (나머지: 1)
            n / 3 = 0 (몫: 0)
            결과: "11"

        위 과정에서 얻은 결과는 "11"이 됨.

    예시 2: n = 10
        첫 번째 반복:
                n = 10
                n % 3 = 1 (나머지: 1)
                n / 3 = 3 (몫: 3)
                결과: "1"

        두 번째 반복:
            n = 3
            n % 3 = 0 (나머지: 0)
            n / 3 = 1 (몫: 1)
            결과: "4"

        세 번째 반복:
            n = 1
            n % 3 = 1 (나머지: 1)
            n / 3 = 0 (몫: 0)
            결과: "41"

        이 과정에서 얻은 결과는 "41"이 됨.

    핵심 이유
        거꾸로 저장
            변환 과정에서 가장 먼저 구한 나머지가 가장 낮은 자리(1의 자리)에 해당하고,
            이후 반복해서 구한 나머지가 점점 높은 자리에 해당하게 된다.
            그래서 처음에 나오는 숫자가 실제로는 결과 문자열의 끝부분에 위치하게 된다.
*/