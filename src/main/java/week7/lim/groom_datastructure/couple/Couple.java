package week7.lim.groom_datastructure.couple;

// 문제: https://level.goorm.io/exam/159545/0%EC%BB%A4%ED%94%8C/quiz/1

import java.io.*;

public class Couple {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String people = br.readLine();
        String[] peoplePtsStr = br.readLine().split(" ");

        int answer = 0;

        for (int i = 0; i < peoplePtsStr.length; i++) {
            answer += Integer.parseInt(peoplePtsStr[i]);
        }

        System.out.println(answer);
    }
}
