package week8.lim.n_11721;

// 문제: https://www.acmicpc.net/problem/11721

import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] N = br.readLine().split("");

        System.out.print(N[0]);
        for (int i = 1; i < N.length; i++) {
            if (i % 10 == 0)
                System.out.println();
            System.out.print(N[i]);
        }
    }
}