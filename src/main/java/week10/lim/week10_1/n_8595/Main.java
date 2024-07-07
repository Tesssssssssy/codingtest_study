package week10.lim.week10_1.n_8595;

// 문제: https://www.acmicpc.net/problem/8595

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();  // 첫 줄 (단어의 길이) 읽고 무시
        String input = br.readLine();  // 실제 문자열 입력 받음

        // 정규 표현식을 사용하여 입력 문자열에서 모든 숫자 찾기
        Matcher matcher = Pattern.compile("\\d+").matcher(input);
        long sum = 0;

        while (matcher.find()) {
            // 찾은 숫자를 long으로 변환하여 누적 합산
            sum += Long.parseLong(matcher.group());
        }

        System.out.println(sum);  // 합산된 결과 출력
    }
}

