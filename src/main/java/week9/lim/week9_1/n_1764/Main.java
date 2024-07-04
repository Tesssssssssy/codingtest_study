package week9.lim.week9_1.n_1764;

// 문제: https://www.acmicpc.net/problem/1764

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        Set<String> strangers = new HashSet<>();
        Set<String> duplicates = new TreeSet<>();
        // TreeSet을 사용하여 중복된 이름을 사전순으로 관리

        // 첫 번째 그룹의 이름을 읽고 저장
        for (int i = 0; i < N; i++) {
            String name = br.readLine();
            strangers.add(name);
        }

        // 두 번째 그룹의 이름을 읽으면서 중복 확인
        for (int j = 0; j < M; j++) {
            String name = br.readLine();
            if (strangers.contains(name)) { // 듣지도 못한 애들 중 보지도 못한 애가 있으면
                duplicates.add(name);   // 중복에 추가
            }
        }

        System.out.println(duplicates.size()); // 중복된 이름의 수 출력
        for (String name : duplicates) {
            System.out.println(name); // 중복된 이름을 사전순으로 출력
        }
    }
}