package week3.lim.dp.practice;

public class pibonacciEx3 {
    /**
     *  Bottom-up (상향식)
     *      - 하위에서부터 문제를 해결해나가면서 먼저 계산했던 문제들의 값을 활용해서 상위 문제를 해결해나가는 방식으로
     *      - DP의 전형적인 형태는 Bottom-up이다. 여기서 사용되는 문제 결과 값 저장용 리스트는 DP 테이블이라고 부른다.
     *
     *      - Bottom-up 방식은 반복문을 사용해서 구현
     */
    static int[] dp;

    static int fibo(int x) {
        dp[1] =1;
        dp[2] =1;
        for (int i=3; i<x+1; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[x];
    }

    public static void main(String[] args) {
        int n = 6;
        dp  = new int[n+1];

        System.out.println(fibo(n));    // 8
    }
}
