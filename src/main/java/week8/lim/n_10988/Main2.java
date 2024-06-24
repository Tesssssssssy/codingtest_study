package week8.lim.n_10988;

// 문제: https://www.acmicpc.net/problem/10988

import java.io.*;

public class Main2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String[] words = input.split("");  // 문자열을 문자 단위로 분리

        int answer = 1;  // 기본적으로 팰린드롬이라고 가정

        int left = 0;   // 왼쪽 시작 인덱스
        int right = words.length - 1;  // 오른쪽 시작 인덱스

        while (left < right) {  // 중간까지만 검사
            if (!words[left].equals(words[right])) {
                answer = 0;  // 한 쌍이라도 일치하지 않으면 팰린드롬이 아님
                break;
            }
            left++;   // 왼쪽 인덱스 증가
            right--;  // 오른쪽 인덱스 감소
        }

        System.out.println(answer);
    }
}
