package week9.lim.n_10448;

// 문제: https://www.acmicpc.net/problem/10448

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        // 삼각수를 저장할 HashSet
        Set<Integer> triangularNumbers = new HashSet<>();
        int maxK = 1000; // K의 최대 가능 값
        int n = 1;
        // 가능한 모든 삼각수를 계산하고 저장
        while (true) {
            int triangular = n * (n + 1) / 2;
            if (triangular > maxK) {
                break; // 삼각수가 maxK을 초과하면 반복 중단
            }
            triangularNumbers.add(triangular); // 삼각수 추가
            n++;
        }

        // 각 테스트 케이스에 대해 수행
        for (int t = 0; t < T; t++) {
            int K = Integer.parseInt(br.readLine());
            boolean found = false;

            /*
                일단 3개의 삼각수의 합으로 표현될 수 있는지를 알아야 하므로
                2개의 삼각수의 합으로 가능한지 먼저 확인
                그리고 이후에 2개의 삼각수의 합과 나머자 1개의 삼각수의 합으로 최종 판단

                먼저 두 삼각수의 합을 저장할 HashSet
                - 모든 가능한 삼각수 쌍의 합을 twoSumSet에 저장
                - K 값을 두 삼각수의 합과 하나의 삼각수의 차로 표현할 수 있는지 빠르게 확인하기 위함
            */
            Set<Integer> twoSumSet = new HashSet<>();
            for (Integer a : triangularNumbers) {
                for (Integer b : triangularNumbers) {
                    if (a + b <= K) { // 합이 K 이하인 경우만 저장
                        twoSumSet.add(a + b);
                    }
                }
            }

            /*
                두 삼각수의 합과 하나의 삼각수의 합이 K가 되는지 검사
                미리 계산된 두 삼각수의 합과 하나의 삼각수를 이용하여
                K 값을 구성할 수 있는지 검사
            */
            for (Integer sum : twoSumSet) {
                if (triangularNumbers.contains(K - sum)) { // (K - sum)이 삼각수인지 검사
                    bw.write("1\n"); // 조건을 만족하면 1 출력
                    found = true;
                    break;
                }
            }

            if (!found) {
                bw.write("0\n"); // 조건을 만족하는 조합이 없으면 0 출력
            }
        }

        br.close();
        bw.flush(); // 버퍼에 남아있는 데이터를 출력
        bw.close();
    }
}