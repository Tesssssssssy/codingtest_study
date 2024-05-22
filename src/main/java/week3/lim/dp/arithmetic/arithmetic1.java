package week3.lim.dp.arithmetic;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/1843

public class arithmetic1 {
    public static int solution(String[] arr) {
        int n = arr.length;

        // 숫자만 추출: 숫자는 짝수 인덱스에 위치
        int[] numbers = new int[(n / 2) + 1];

        // 연산자 추출: 연산자는 홀수 인덱스에 위치
        char[] operators = new char[n / 2];

        int numIndex = 0, opIndex = 0;

        // 입력 배열을 순회하면서 숫자와 연산자를 분리하여 저장
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {  // 숫자는 짝수 인덱스에 위치
                numbers[numIndex++] = Integer.parseInt(arr[i]);
            } else {  // 연산자는 홀수 인덱스에 위치
                operators[opIndex++] = arr[i].charAt(0);
            }
        }

        // 최대값과 최소값을 계산하기 위한 DP 테이블
        int[][] maxDp = new int[numIndex][numIndex];
        int[][] minDp = new int[numIndex][numIndex];

        // 단일 숫자의 경우, 최대값과 최소값은 그 숫자 자체이다
        for (int i = 0; i < numIndex; i++) {
            maxDp[i][i] = numbers[i];
            minDp[i][i] = numbers[i];
        }

        // 길이를 2부터 시작해서 숫자 배열의 크기까지 증가시키며 DP 테이블을 채움
        for (int len = 1; len < numIndex; len++) {  // 부분 배열의 길이
            for (int i = 0; i < numIndex - len; i++) {  // 시작 인덱스
                int j = i + len;  // 끝 인덱스
                maxDp[i][j] = Integer.MIN_VALUE;
                minDp[i][j] = Integer.MAX_VALUE;

                // 가능한 모든 중간 위치 k에서 분할하여 최대값과 최소값 계산
                for (int k = i; k < j; k++) {
                    if (operators[k] == '+') {
                        // 더하기 연산자일 때,
                        // 최대값은 두 부분의 최대값의 합,
                        // 최소값은 두 부분의 최소값의 합
                        maxDp[i][j] = Math.max(maxDp[i][j], maxDp[i][k] + maxDp[k + 1][j]);
                        minDp[i][j] = Math.min(minDp[i][j], minDp[i][k] + minDp[k + 1][j]);
                    } else if (operators[k] == '-') {
                        // 빼기 연산자일 때,
                        // 최대값은 왼쪽 최대값에서 오른쪽 최소값을 빼고,
                        // 최소값은 왼쪽 최소값에서 오른쪽 최대값을 빼서 계산
                        maxDp[i][j] = Math.max(maxDp[i][j], maxDp[i][k] - minDp[k + 1][j]);
                        minDp[i][j] = Math.min(minDp[i][j], minDp[i][k] - maxDp[k + 1][j]);
                    }
                }
            }
        }

        // 전체 배열에 대한 최대값 반환
        return maxDp[0][numIndex - 1];
    }

    public static void main(String[] args) {
        String[] arr1 = {"1", "-", "3", "+", "5", "-", "8"};
        String[] arr2 = {"5", "-", "3", "+", "1", "+", "2", "-", "4"};

        System.out.println(solution(arr1)); // 1
        System.out.println(solution(arr2)); // 3
    }
}
