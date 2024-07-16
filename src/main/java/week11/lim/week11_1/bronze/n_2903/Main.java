package week11.lim.week11_1.bronze.n_2903;

// 문제: https://www.acmicpc.net/problem/2903

import java.io.*;

public class Main {
    /**
     *  1. 중앙 이동 알고리즘 이용
     *  2. 정사각형 이루는 점 4개 선택
     *  3. 정사각형의 각 변의 중앙에 점을 하나 추가
     *  4. 정사각형의 중심에 점을 하나 추가
     *  5. 초기 상태에서 위 과정을 한 번 거치면 총 4개의 정사각형이 새로 생김.
     *  6. 만족할 때까지 반복
     *  7. 어떤 점은 한 개 보다 많은 정사각형에 포함될 수 있다.
     *     -> 메모리 소모를 줄이기 위해 중복하는 점을 한 번만 저장
     *        과정을 N번 거친 후 점 몇 개를 저장해야 하는지 출력
     *  8. 1 <= N <= 15
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // int result = (int) Math.pow((1 << N) + 1, 2);
        // 점의 총 개수 계산 (비트 시프트 연산 사용하는 방법)

        int sideLength = (int) Math.pow(2, N) + 1;  // 2^N 후 1을 더한 값
        int result = (int) Math.pow(sideLength, 2);  // 위 결과를 제곱하여 점의 총 개수 계산

        System.out.println(result);  // 결과 출력
    }
}

/*
    이 문제는 각 단계마다 생성되는 정사각형의 각 변에 중간점을 추가하고,
    정사각형의 중앙에 하나의 점을 추가함으로써 점의 수가 어떻게 증가하는지를 계산하는 문제.
    각 단계를 거칠 때마다 정사각형의 수가 4배로 증가하며, 이로 인해 점의 수도 특정 패턴으로 증가.
    1 -> 4 -> 16 -> ....

    N단계 후의 점의 수는 다음 수식을 통해 계산:
        P(N) = (2^N + 1)^2
        이 수식은 각 단계에서 형성되는 격자의 크기를 나타냄.
        ex.)
           N=1, 2^1 + 1 = 3이므로 3^2 = 9점이 있다.
           N=2, 2^2 + 1 = 5이므로 5^2 = 25점이 있다.

    위 코드에서 1 << N 은 2^N을 계산하는 빠른 방법 (비트 시프트 연산)
    이렇게 연산된 값에 1을 더하고, 그 결과를 제곱하여 점의 총 수를 얻는다.
    이 방식은 N의 값이 15까지 허용되는 범위 내에서 빠르고 정확하게 계산됨.
*/