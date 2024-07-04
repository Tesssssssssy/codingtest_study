package week9.lim.week9_1.n_1159;

// 문제: https://www.acmicpc.net/problem/1159

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 문자별 출현 횟수 저장
        HashMap<Character, Integer> charCount = new HashMap<>();

        for (int i = 0; i < N; i++) {
            String lastName = br.readLine();
            char firstChar = lastName.charAt(0);
            // 해당 문자의 출현 횟수 업데이트
            charCount.put(firstChar, charCount.getOrDefault(firstChar, 0) + 1);
        }

        StringBuilder result = new StringBuilder();
        // HashMap의 키셋을 순회하면서 출현 횟수가 5 이상인지 확인
        for (char c : charCount.keySet()) {
            if (charCount.get(c) >= 5) { // 해당 문자의 출현 횟수가 5 이상인지 확인
                result.append(c);
            }
        }

        // 결과 출력
        if (result.length() == 0) {
            System.out.println("PREDAJA");
        } else {
            System.out.println(result);
        }
    }
}
