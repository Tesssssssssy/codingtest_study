package week3.kim.week3_2.dp;

/**
 * [사칙연산]
 *
 * 사칙연산에서 더하기(+)는 결합법칙이 성립하지만, 빼기(-)는 결합법칙이 성립하지 않습니다.
 * 예를 들어 식 1 - 5 - 3은 연산 순서에 따라 다음과 같이 다른 결과를 가집니다.
 *
 * ((1 - 5) - 3) = -7
 * (1 - (5 - 3)) = -1
 * 위 예시와 같이 뺄셈은 연산 순서에 따라 그 결과가 바뀔 수 있습니다.
 * 또 다른 예로 식 1 - 3 + 5 - 8은 연산 순서에 따라 다음과 같이 5가지 결과가 나옵니다.
 *
 * (((1 - 3) + 5) - 8) = -5
 * ((1 - (3 + 5)) - 8) = -15
 * (1 - ((3 + 5) - 8)) = 1
 * (1 - (3 + (5 - 8))) = 1
 * ((1 - 3) + (5 - 8)) = -5
 * 위와 같이 서로 다른 연산 순서의 계산 결과는 [-15, -5, -5, 1, 1]이 되며, 이중 최댓값은 1입니다.
 * 문자열 형태의 숫자와, 더하기 기호("+"), 뺄셈 기호("-")가 들어있는 배열 arr가 매개변수로 주어질 때,
 * 서로 다른 연산순서의 계산 결과 중 최댓값을 return 하도록 solution 함수를 완성해 주세요.
 *
 * 제한 사항
 * arr는 두 연산자 "+", "-" 와 숫자가 들어있는 배열이며, 길이는 3 이상 201 이하 입니다.
 * arr의 길이는 항상 홀수입니다.
 * arr에 들어있는 숫자의 개수는 2개 이상 101개 이하이며, 연산자의 개수는 (숫자의 개수) -1 입니다.
 * 숫자는 1 이상 1,000 이하의 자연수가 문자열 형태로 들어있습니다.. (ex : "456")
 * 배열의 첫 번째 원소와 마지막 원소는 반드시 숫자이며, 숫자와 연산자가 항상 번갈아가며 들어있습니다.
 *
 * 입출력 예
 * arr							                    result
 * ["1", "-", "3", "+", "5", "-", "8"]			    1
 * ["5", "-", "3", "+", "1", "+", "2", "-", "4"]	3
 *
 * 입출력 예시
 * 입출력 예 #1
 * 위의 예시와 같이 (1-(3+(5-8))) = 1 입니다.
 *
 * 입출력 예 #2
 * (5-(3+((1+2)-4))) = 3 입니다.
*/

public class Ex4 {
    public static void main(String[] args) {
        Ex4 ex4 = new Ex4();

        String[] arr1 = {"1", "-", "3", "+", "5", "-", "8"};
        String[] arr2 = {"5", "-", "3", "+", "1", "+", "2", "-", "4"};

        // 1 반환
        System.out.println(ex4.solution(arr1));
        // 3 반환
        System.out.println(ex4.solution(arr2));
    }


    public int solution(String[] arr) {
        // 숫자의 개수를 계산
        int n = arr.length / 2 + 1;

        // 최대값과 최소값을 저장할 2차원 배열 초기화
        int[][] dpMax = new int[n][n];
        int[][] dpMin = new int[n][n];

        // 배열의 각 숫자에 대해 초기값을 설정
        for (int i = 0; i < n; i++) {
            dpMax[i][i] = Integer.parseInt(arr[i * 2]);
            dpMin[i][i] = Integer.parseInt(arr[i * 2]);
        }

        // 모든 가능한 부분 수식에 대해 최대값과 최소값을 계산
        for (int cnt = 1; cnt < n; cnt++) {
            for (int i = 0; i < n - cnt; i++) {
                int j = i + cnt;

                // 초기 최대값과 최소값을 설정
                dpMax[i][j] = Integer.MIN_VALUE;
                dpMin[i][j] = Integer.MAX_VALUE;

                // 가능한 모든 분할에 대해 최대값과 최소값을 계산
                for (int k = i; k < j; k++) {
                    // 연산자에 따라 계산된 최대값과 최소값을 구함
                    int maxFirst = calculate(dpMax[i][k], dpMax[k + 1][j], arr[k * 2 + 1]);
                    int minFirst = calculate(dpMin[i][k], dpMin[k + 1][j], arr[k * 2 + 1]);
                    int maxSecond = calculate(dpMax[i][k], dpMin[k + 1][j], arr[k * 2 + 1]);
                    int minSecond = calculate(dpMin[i][k], dpMax[k + 1][j], arr[k * 2 + 1]);

                    // 최대값과 최소값을 업데이트
                    dpMax[i][j] = Math.max(dpMax[i][j], Math.max(maxFirst, maxSecond));
                    dpMin[i][j] = Math.min(dpMin[i][j], Math.min(minFirst, minSecond));
                }
            }
        }

        // 전체 수식에 대한 최대 결과값을 반환
        return dpMax[0][n - 1];
    }

    // 두 숫자와 연산자를 받아 계산 결과를 반환
    private int calculate(int a, int b, String op) {
        // 더하기 연산인 경우
        if (op.equals("+")) {
            return a + b;
        }
        // 뺄셈 연산인 경우
        else {
            return a - b;
        }
    }
}
