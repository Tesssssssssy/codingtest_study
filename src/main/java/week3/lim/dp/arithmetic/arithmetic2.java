package week3.lim.dp.arithmetic;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/1843
// 참고: https://velog.io/@ddongh1122/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EC%82%AC%EC%B9%99%EC%97%B0%EC%82%B0

public class arithmetic2 {
    /**
     *  DP[a][b][0] : 숫자 배열의 인덱스 a~b 범위의 연산의 최소값
     *  DP[a][b][1] : 숫자 배열의 인덱스 a~b 범위의 연산의 최대값
     *  -> 위 문제에서 답은 DP[a][arr.length / 2][1]이 된다
     *
     *  다음은 DP배열을 채워나가는 과정이다.
     *      DP[a][b][0] 케이스(a~b 범위 최소값)
     *      해당 범위에 있는 모든 연산자에 대해 아래를 반복하여 가장 작은값을 넣어준다.
     *
     *      1. 연산자가 + 일 경우 (연산자의 인덱스 i)
     *         - DP[a][i][0] + DP[i+1][b][0] (연산자 좌,우 기준 각각 최솟값 + 최솟값)
     *
     *      2. 연산자가 - 일 경우
     *         - DP[a][i][0] - DP[i+1][b][1] (연산자 좌,우 기준 각각 최솟값 - 최대값)
     *
     *      위 과정을 a~b의 길이를 0부터 증가시켜가며 DP 테이블을 채워주면 결과를 구할 수 있다.
     */
    public static int solution(String[] arr) {
        int[] num = new int[arr.length / 2 + 1];
        int[] op = new int[arr.length / 2];      //0 : +, 1: -

        int ind1 = 0;
        int ind2 = 0;

        for (String cur : arr) {
            if (cur.equals("+"))
                op[ind2++] = 0;
            else if (cur.equals("-"))
                op[ind2++] = 1;
            else
                num[ind1++] = Integer.parseInt(cur);
        }

        //DP 시작
        //dp[a][b][0] : a~b까지 연산 최소값
        //dp[a][b][1] : a~b까지 연산 최대값
        int[][][] dp = new int[arr.length / 2 + 1][arr.length / 2 + 1][2];

        for (int i = 0; i < arr.length / 2 + 1; i++)
            dp[i][i][0] = dp[i][i][1] = num[i];

        for (int len = 1; len < arr.length / 2 + 1; len++) {
            for (int s = 0; s < arr.length / 2 + 1; s++) {
                if (s + len >= arr.length / 2 + 1) break;
                int e = s + len;
                int max = Integer.MIN_VALUE;
                int min = Integer.MAX_VALUE;
                for (int i = s; i < e; i++) {
                    //덧셈 연산일 때
                    if (op[i] == 0) {
                        max = Math.max(max, dp[s][i][1] + dp[i+1][e][1]);
                        min = Math.min(min, dp[s][i][0] + dp[i+1][e][0]);
                    }
                    //뺄셈 연산일 떄
                    else {
                        max = Math.max(max, dp[s][i][1] - dp[i+1][e][0]);
                        min = Math.min(min, dp[s][i][0] - dp[i+1][e][1]);
                    }
                }
                dp[s][e][0] = min;
                dp[s][e][1] = max;
            }
        }
        return dp[0][arr.length / 2][1];
    }

    public static void main(String[] args) {
        String[] arr1 = {"1", "-", "3", "+", "5", "-", "8"};
        String[] arr2 = {"5", "-", "3", "+", "1", "+", "2", "-", "4"};

        System.out.println(solution(arr1)); // 1
        System.out.println(solution(arr2)); // 3
    }
}
