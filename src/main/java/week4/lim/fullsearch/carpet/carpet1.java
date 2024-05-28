package week4.lim.fullsearch.carpet;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/42842

import java.util.Arrays;

public class carpet1 {
    /**
     *  total 변수는 전체 격자 수(brown + yellow)를 저장.
     *
     *  for 반복문은 가능한 세로 길이 height를 3부터 total의 제곱근까지 반복.
     *  3 이하는 갈색 격자 최소 개수 8개를 만족하지 못함.
     *
     *  내부 if 문에서 total % height == 0은 세로 길이가 전체 격자 수의 약수인지 검사.
     *
     *  이후 width를 계산하고, (width - 2) * (height - 2) == yellow 조건은
     *  노란색 격자의 수가 가로 길이와 세로 길이에 의해 정확히 표현되는지 확인.
     *
     *  조건을 만족하는 가로, 세로 길이가 있으면 배열로 반환.
     */
    public static int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        // 가로, 세로 크기를 저장할 배열

        int total = brown + yellow;
        // 전체 격자의 수

        // 전체 격자의 수에 대해 가능한 세로 길이를 검토
        for (int height = 3; height <= (int) Math.sqrt(total); height++) {
            if (total % height == 0) {
                // 세로 길이가 전체 격자 수의 약수인 경우

                int width = total / height;
                // 가로 길이 계산

                if ((width - 2) * (height - 2) == yellow) {
                    // brown 계산 공식을 사용해 검증

                    answer[0] = width; // 가로 길이
                    answer[1] = height; // 세로 길이
                    break; // 조건에 맞는 크기를 찾으면 반복 종료
                }
            }
        }

        return answer; // 계산된 가로, 세로 길이 반환
    }

    public static void main(String[] args) {
        int brown1 = 10;
        int yellow1 = 2;
        System.out.println(Arrays.toString(solution(brown1, yellow1))); // [4, 3]

        int brown2 = 8;
        int yellow2 = 1;
        System.out.println(Arrays.toString(solution(brown2, yellow2))); // [3, 3]

        int brown3 = 24;
        int yellow3 = 24;
        System.out.println(Arrays.toString(solution(brown3, yellow3))); // [8, 6]
    }
}
