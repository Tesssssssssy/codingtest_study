package week6.lim.dp.practice;

public class pibonacciEx2 {
    /**
     *  DP 알고리즘 기법은 이미 계산된 결과(하위 문제)는 별도의 메모리 영역에 저장하여 다시 계산하지 않도록 설계함으로써
     *  메모리를 적절히 사용하여 수행 시간 효율성을 비약적으로 향상시키는 방법.
     *
     *  DP 구현 방법은 일반적으로 두 가지 방식
     *      - Top-down(하향식)
     *      - Bottom-up(상향식)으로 구성
     *
     *  Top-down (하향식) 풀이
     *      - 상위 문제를 해결하기 위해서 하위 문제를 재귀적으로 호출하여  하위 문제를 해결함으로써 상위 문제를 해결하는 방식이다.
     *      - 이 때 해결해놓은 하위 문제를 저장해 놓기 위해 Memoization이 사용된다.
     *          - Memoization은 DP구현 방법 중 하나로, 한 번 계산한 결과를 메모리 공간에 메모하는 기법이다.
     *          - 이를 사용하면 모든 부분 문제가 한 번씩만 계산된다고 보장할 수 있기 때문에 함수 호출 횟수가 엄청나게 감소함을 예상할 수 있다.
     *
     *      - 피보나치 함수를 만들 때 Top-down은 재귀 호출을 사용하여 구현
     *
     */
    static int[] dp;

    // Top-down
    static int fibo(int x) {
        if( x ==1 || x==2)
            return 1;

        if(dp[x] != 0)
            return dp[x];

        dp[x] = fibo(x-1) + fibo(x-2);

        return dp[x];
    }

    public static void main(String[] args) {
        int n = 6;
        dp  = new int[n+1];
        System.out.println(fibo(n));    // 8
    }
}
