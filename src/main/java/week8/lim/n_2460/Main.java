package week8.lim.n_2460;

// 문제: https://www.acmicpc.net/problem/2460

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int maxPeople = 0; // 최대 사람 수를 저장할 변수
        int currentPeople = 0; // 현재 기차 안에 있는 사람 수

        for (int i = 0; i < 10; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int peopleOut = Integer.parseInt(st.nextToken()); // 내린 사람 수
            int peopleIn = Integer.parseInt(st.nextToken()); // 탄 사람 수

            currentPeople = currentPeople - peopleOut + peopleIn; // 기차 내 사람 수 업데이트
            if (currentPeople > maxPeople) { // 최대 사람 수 업데이트
                maxPeople = currentPeople;
            }
        }

        System.out.println(maxPeople); // 결과 출력
        br.close(); // 자원 해제
    }
}
