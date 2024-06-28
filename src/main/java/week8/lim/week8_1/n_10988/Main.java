package week8.lim.week8_1.n_10988;

// 문제: https://www.acmicpc.net/problem/10988

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String words = br.readLine();

        // StringBuilder로 문자열 생성 후 즉시 뒤집기
        String wordsReversed = new StringBuilder(words).reverse().toString();

        // 원본과 뒤집은 문자열 비교하여 결과 출력
        int answer = words.equals(wordsReversed) ? 1 : 0;

        System.out.println(answer);
    }
}