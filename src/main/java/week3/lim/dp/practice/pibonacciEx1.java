package week3.lim.dp.practice;

// 참고: https://loosie.tistory.com/150

public class pibonacciEx1 {
    /**
     *  단순 재귀 풀이
     *      - 점화식이 F(n) = F(n-1) + F(n-2)이기 때문에 단순 재귀 함수로 구현
     *      => 재귀함수로 구현을 하면 시간복잡도 O(2^n)을 갖게 된다. 다음 그림과 같이 중복되는 호출로 인해 굉장히 좋지 않은 효율
     */
    private static int fibo(int x) {
        if( x ==1 || x==2 )
            return 1;
        return fibo(x-1) + fibo(x-2);
    }

    public static void main(String[] args) {
        int n = 6;
        System.out.println(fibo(n));    // 8
    }
}
