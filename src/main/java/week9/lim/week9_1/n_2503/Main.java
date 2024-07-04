package week9.lim.week9_1.n_2503;

// 문제: https://www.acmicpc.net/problem/2503

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] questions = new int[N][3];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            questions[i][0] = Integer.parseInt(st.nextToken()); // 질문 수
            questions[i][1] = Integer.parseInt(st.nextToken()); // 스트라이크 개수
            questions[i][2] = Integer.parseInt(st.nextToken()); // 볼 개수
        }

        int possibleAnswers = 0;

        // 모든 가능한 수에 대해 검사
        // 123: 최소값, 987: 최대값
        for (int candidate = 123; candidate <= 987; candidate++) {
            if (isValidNumber(candidate, questions)) {
                possibleAnswers++;
            }
        }

        System.out.println(possibleAnswers);
    }

    private static boolean isValidNumber(int candidate, int[][] questions) {
        String candidateStr = String.valueOf(candidate);
        if (candidateStr.charAt(0) == candidateStr.charAt(1) ||
                candidateStr.charAt(1) == candidateStr.charAt(2) ||
                candidateStr.charAt(2) == candidateStr.charAt(0) ||
                candidateStr.contains("0")) {
            return false; // 중복되는 숫자 또는 0이 포함된 경우 (1~9만 포함해야 하니까)
        }

        for (int[] question : questions) {
            String questionStr = String.valueOf(question[0]);
            int strikes = 0;
            int balls = 0;

            for (int i = 0; i < 3; i++) {
                if (questionStr.charAt(i) == candidateStr.charAt(i)) {
                    strikes++; // 위치와 문자가 같으면 스트라이크
                } else if (candidateStr.contains(String.valueOf(questionStr.charAt(i)))) {
                    balls++; // 문자만 일치하면 볼
                }
            }

            if (strikes != question[1] || balls != question[2]) {
                return false;
                /*
                    <조건에 맞지 않는 경우>
                        영수가 스트라이크, 볼 개수를 입력에서 말해줬는데
                        계산 결과 도출된 스트라이크, 볼 개수와 일치하지 않았을 경우 방지
                 */
            }
        }

        return true; // 모든 조건을 만족
    }
}