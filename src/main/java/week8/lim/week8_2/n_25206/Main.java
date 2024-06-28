package week8.lim.week8_2.n_25206;

// 문제: https://www.acmicpc.net/problem/25206

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // 등급에 따른 과목 평점을 매핑하는 맵
        Map<String, Double> standard = new HashMap<>();
        standard.put("A+", 4.5);
        standard.put("A0", 4.0);
        standard.put("B+", 3.5);
        standard.put("B0", 3.0);
        standard.put("C+", 2.5);
        standard.put("C0", 2.0);
        standard.put("D+", 1.5);
        standard.put("D0", 1.0);
        standard.put("F", 0.0);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        double totalPoints = 0.0;
        double totalScore = 0.0;

        for (int i = 0; i < 20; i++) {
            String[] input = br.readLine().split(" ");
            String courseName = input[0];
            double score = Double.parseDouble(input[1]);
            String grade = input[2];

            // 등급이 P인 과목은 계산에서 제외
            if (grade.equals("P")) {
                continue;
            }

            // 과목 평점 계산
            double point = standard.get(grade);
            totalPoints += score * point;
            totalScore += score;
        }

        // 전공평점 계산
        double gpa = totalPoints / totalScore;

        // 결과 출력 (소수점 여섯째 자리까지)
        System.out.printf("%.6f\n", gpa);
    }
}
