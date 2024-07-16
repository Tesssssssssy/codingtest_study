package week11.lim.week11_1.silver.n_9375;

// 문제: https://www.acmicpc.net/problem/9375
// 프로그래머스 참고: week1/lim/Hash/clothes/clothes.java
//           문제: https://school.programmers.co.kr/learn/courses/30/lessons/42578

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCases = Integer.parseInt(br.readLine()); // 테스트 케이스 수.

        for (int t = 0; t < testCases; t++) {
            int n = Integer.parseInt(br.readLine()); // 각 테스트 케이스에서 의상 수를 입력
            HashMap<String, Integer> clothes = new HashMap<>(); // 옷의 종류별로 개수를 저장할 해시맵

            for (int i = 0; i < n; i++) {
                String line = br.readLine();
                String[] inputs = line.split(" ");
                String type = inputs[1]; // 의상의 종류를 추출

                clothes.put(type, clothes.getOrDefault(type, 0) + 1); // 해시맵에 종류별 개수를 증가시킴.
            }

            int combinations = 1; // 가능한 조합의 수 초기화

            for (int count : clothes.values()) {
                combinations *= (count + 1);
                // 각 종류의 (개수 + 1)을 곱함
                // 각 옷의 종류마다 가지고 있는 옷의 수 + 1 (옷을 입지 않는 선택을 포함)을 곱하여 가능한 모든 조합을 계산
            }

            // 모든 옷을 안 입는 경우를 제외하고 출력
            System.out.println(combinations - 1);
        }

        br.close();
    }
}
