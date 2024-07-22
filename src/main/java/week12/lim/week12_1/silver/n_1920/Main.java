package week12.lim.week12_1.silver.n_1920;

// 문제: https://www.acmicpc.net/problem/1920

import java.io.*;
import java.util.*;

public class Main {
    /**
     *  - N개의 정수 A[1], A[2], …, A[N]이 주어져 있을 때,
     *    이 안에 X라는 정수가 존재하는지 알아내는 프로그램
     *
     *  [입력]
     *  - 자연수 N(1 ≤ N ≤ 100,000)
     *  - 다음 줄에는 N개의 정수 A[1], A[2], …, A[N]
     *  - 다음 줄에는 M(1 ≤ M ≤ 100,000)
     *  - 다음 줄에는 M개의 수
     *
     *  이 수들이 A안에 존재하는지 알아내면 된다.
     *  모든 정수의 범위는 -231 보다 크거나 같고 231보다 작다.
     *
     *  [출력]
     *  M개의 줄에 답을 출력한다.
     *  (존재하면 1을, 존재하지 않으면 0을 출력)
     *
     *  [풀이]
     *  HashSet 사용
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // N개의 정수 입력 받기
        int N = Integer.parseInt(br.readLine());
        HashSet<Integer> set = new HashSet<>();
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            set.add(Integer.parseInt(st.nextToken()));
        }

        // M개의 정수 입력 받기
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            if (set.contains(Integer.parseInt(st.nextToken()))) {
                sb.append(1).append('\n');
            } else {
                sb.append(0).append('\n');
            }
        }

        // 결과 출력
        System.out.print(sb);
    }
}
/*
    효율적으로 해결하기 위해 HashSet을 사용하는 것이 좋다.
    HashSet은 평균적으로 O(1) 시간 복잡도로 요소의 존재 여부를 확인할 수 있기 때문에,
    이 방법은 M개의 정수 각각에 대해 빠르게 존재 여부를 판단할 수 있다.
*/