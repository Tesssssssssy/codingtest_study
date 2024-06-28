package week8.lim.week8_1.n_1316;

// 문제: https://www.acmicpc.net/problem/1316

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] words = new String[N];
        int answer = 0; // 그룹 단어의 개수를 세는 변수

        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
        }

        for (String word : words) {
            if (isGroupWord(word)) {
                answer++;
            }
        }

        System.out.println(answer);
    }

    // 그룹 단어인지 판별하는 메소드
    private static boolean isGroupWord(String word) {
        Set<Character> seen = new HashSet<>(); // 이미 나온 문자를 저장하는 Set
        char lastChar = '\0'; // 직전에 확인한 문자를 저장하는 변수

        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            if (currentChar != lastChar) { // 새로운 문자가 나타나면
                if (seen.contains(currentChar)) { // 이미 나타난 문자라면 그룹 단어가 아님
                    return false;
                }
                seen.add(currentChar); // 문자를 Set에 추가
                lastChar = currentChar; // 마지막 문자 업데이트
            }
        }
        return true; // 모든 문자가 조건을 만족하면 그룹 단어임
    }
}