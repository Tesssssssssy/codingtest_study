package week10.baek.july9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B8595 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String str = br.readLine();

        long count = 0;  // count를 long으로 변경하여 오버플로우 방지
        StringBuilder num = new StringBuilder();  // String 대신 StringBuilder 사용
        for (int i = 0; i < n; i++) {
            char c = str.charAt(i);
            // 알파벳인 경우
            if (Character.isLetter(c)) {
                if (num.length() > 0 && num.length() <= 6) {
                    count += Long.parseLong(num.toString());  // num을 long으로 파싱
                }
                num.setLength(0);  // num 초기화
            } else {
                // 숫자라면
                num.append(c);
            }
        }

        // 마지막 숫자를 처리
        if (num.length() > 0 && num.length() <= 6) {
            count += Long.parseLong(num.toString());
        }

        System.out.println(count);
    }
}