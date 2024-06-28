package week8.lim.week8_1.n_2609;

// 문제: https://www.acmicpc.net/problem/2609

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int GCD = gcd(N, M);    // 최대공약수
        int LCM = N * M / GCD;  // 최소공배수

        System.out.println(GCD);
        System.out.println(LCM);
    }

    // 최대공약수 재귀 방식
    public static int gcd(int a, int b) {
        if (b == 0)
            return a;

        // GCD(a, b) = GCD(b, r)이므로 (r = a % b)
        return gcd(b, a % b);
    }
}

/*
    A=ad, B= bd 에서 a와 b는 서로소이고, d는 최대공약수이다.
    -> 두 수의 최소 공배수는 자연스럽게 a×b×d.

    만약 위 문제처럼 A와 B로 주어진다면 A = ad, B= bd 이었으므로
    a×b×d를 만족하려면 A × B 만 하면 adbd 로 d가 한 번 더 들어가 있으니
    A × B ÷ d 를 해주면 a × b × d 를 만족하면서 최소공배수가 된다.
*/