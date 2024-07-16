package week10.lim.week10_2.bronze.n_25641;

// 문제: https://www.acmicpc.net/problem/25641

import java.io.*;

public class Main {
    /**
     *  1. 편의상 소떡소떡을 알파벳 s와 t로만 구성된 길이 N의 문자열로 생각
     *  2. 소떡소떡을 먹기 전에 소떡소떡을 균형 잡힌 소떡소떡으로 만들려고 한다.
     *     (꼬치에 꽂힌 소세지와 떡의 개수가 같을 때 이를 균형 잡힌 소떡소떡)
     *  3. 소세지와 떡이 한 개도 꽂혀있지 않다면 균형잡힌 소떡소떡이 아니다.
     *  4. 소떡소떡의 맨 왼쪽에 있는 소세지나 떡을 떼어낼 수 있다.
     *     오른쪽은 손잡이 부분이기 때문에 오른쪽에서 떼어내는 것은 불가능.
     *  5. 만들 수 있는 길이가 최대인 균형 잡힌 소떡소떡의 모양을 출력.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 소떡소떡의 길이
        String sotteok = br.readLine(); // 소떡소떡 문자열

        int countS = 0; // 소세지 's'의 개수
        int countT = 0; // 떡 't'의 개수

        // 각 문자의 빈도 수 계산
        for (int i = 0; i < N; i++) {
            if (sotteok.charAt(i) == 's') {
                countS++;
            } else {
                countT++;
            }
        }

        // 균형 잡힌 최대 길이를 만들기 위해 앞에서 불필요한 문자 제거
        // 왼쪽에서부터 시작하여 's'와 't'의 개수가 같아질 때까지 문자를 제거
        int start = 0;
        while (countS != countT) {
            if (sotteok.charAt(start) == 's') {
                countS--;
            } else {
                countT--;
            }
            start++;
        }

        // 균형 잡힌 부분 문자열 출력
        // 's'와 't'의 개수가 같아진 시점부터 문자열 끝까지의 부분을 출력
        System.out.println(sotteok.substring(start));
    }
}
