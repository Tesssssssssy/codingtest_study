package week18.lim.n_12899;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/12899

public class Solution {
    /**
     *  124 나라가 있습니다.
     *  124 나라에서는 10진법이 아닌 다음과 같은 자신들만의 규칙으로 수를 표현합니다.
     *
     *  124 나라에는 자연수만 존재합니다.
     *  124 나라에는 모든 수를 표현할 때 1, 2, 4만 사용합니다.
     *  예를 들어서 124 나라에서 사용하는 숫자는 다음과 같이 변환됩니다.
     *
     *  10진법	 124 나라	10진법	124 나라
     *  1	        1	      6	      14
     *  2	        2	      7	      21
     *  3	        4	      8	      22
     *  4	       11	      9	      24
     *  5	       12	      10	  41
     *
     *  자연수 n이 매개변수로 주어질 때,
     *  n을 124 나라에서 사용하는 숫자로 바꾼 값을 return 하도록 solution 함수를 완성해 주세요.
     */
    public static String solution(int n) {
        StringBuilder answer = new StringBuilder();
        // 결과를 역순으로 저장하기 위해 StringBuilder를 사용

        /*
            나머지와 몫을 이용한 변환
                10진수 n을 3으로 나눈 나머지를 이용해 124 나라의 숫자를 결정.
                3으로 나눈 나머지가 1이면 1, 2이면 2, 0이면 4가 됨.
                나머지가 0일 때는 몫에서 1을 빼고, 나머지로 4를 사용.
                이는 124 나라에서 "124"를 표현하기 위한 규칙 때문.

            반복문
                n이 0이 될 때까지 반복하여 124 나라 숫자를 구한다.
                각 반복에서 n을 3으로 나눈 나머지에 따라 결과에 해당하는 숫자를 추가.
        */

        while (n > 0) {
            // n이 0보다 큰 동안 계속해서 3으로 나눈 나머지를 구하고, 그에 맞는 124 나라 숫자를 answer에 추가

            int remainder = n % 3;
            n /= 3;

            if (remainder == 0) {
                answer.append("4");
                n--; // 몫에서 1을 뻰다.

            } else if (remainder == 1) {
                answer.append("1");

            } else if (remainder == 2) {
                answer.append("2");
            }
        }

        return answer.reverse().toString();
        // 거꾸로 저장했으므로, 결과를 반전시켜 반환
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