package week7.lim.groom_datastructure.couple.practice;

// 문제: https://level.goorm.io/exam/159545/0%EC%BB%A4%ED%94%8C/quiz/1

import java.io.*;

public class CouplePrac {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] scoreStr = br.readLine().split(" ");

        int sum = 0;

        for (int i = 0; i < N; i++) {
            sum += Integer.parseInt(scoreStr[i]);
        }

        System.out.println(sum);
    }
}
