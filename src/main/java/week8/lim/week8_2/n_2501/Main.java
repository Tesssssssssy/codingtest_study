package week8.lim.week8_2.n_2501;

// 문제: https://www.acmicpc.net/problem/2501

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);

        // N의 약수들을 저장할 리스트
        ArrayList<Integer> numList = new ArrayList<>();

        // 1부터 N까지의 수를 순회하며 N의 약수를 찾음
        for (int i = 1; i <= N; i++) {
            if (N % i == 0) {
                numList.add(i);
            }
        }

        // 약수 리스트를 오름차순으로 정렬
        Collections.sort(numList);

        // 만약 K가 약수의 개수보다 크면 0을 출력
        if (K <= numList.size()) {
            System.out.println(numList.get(K - 1));
        } else {
            System.out.println(0);
        }
    }
}
