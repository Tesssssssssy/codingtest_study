package week8.lim.n_2588;

// 문제: https://www.acmicpc.net/problem/2588

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int firstNumber = Integer.parseInt(br.readLine()); // 첫 번째 수 읽기
        String secondNumber = br.readLine(); // 두 번째 수를 문자열로 읽기

        // 두 번째 수의 각 자리 숫자를 추출
        // 문자의 ASCII 코드에서 '0'의 ASCII 코드를 빼면 해당 숫자를 얻을 수 있다.
        int secondNum_1st = secondNumber.charAt(2) - '0'; // 일의 자리
        int secondNum_2nd = secondNumber.charAt(1) - '0'; // 십의 자리
        int secondNum_3rd = secondNumber.charAt(0) - '0'; // 백의 자리

        // 첫 번째 수와 두 번째 수의 각 자리 수를 곱한 결과 출력
        System.out.println(firstNumber * secondNum_1st);    // 첫 번째 수와 일의 자리 수를 곱한 결과
        System.out.println(firstNumber * secondNum_2nd);     // 첫 번째 수와 십의 자리 수를 곱한 결과
        System.out.println(firstNumber * secondNum_3rd); // 첫 번째 수와 백의 자리 수를 곱한 결과

        // 두 수를 전체적으로 곱한 결과
        int secondFullNumber = Integer.parseInt(secondNumber); // 두 번째 수 전체를 정수로 변환
        System.out.println(firstNumber * secondFullNumber);
    }
}