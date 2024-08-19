package week15.lim.programmers_Lv2.n_70129.practice;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/70129

import java.util.*;

public class Solution {
    /**
     *  0과 1로 이루어진 문자열 x에 대한 이진 변환
     *      - x의 모든 0 제거
     *      - x의 길이가 c라면, x를 "c를 2진법으로 표현한 문자열"로 변환
     *
     *   ex.)
     *      x = "0111010"
     *      -> x = "1111" -> "100"
     *
     *   s가 "1"이 될 때까지 계속해서 s에 이진 변환을 가했을 떄,
     *   이진 변환의 횟수와 변환 과정에서 제거된 모든 0의 개수를
     *   각각 배열에 담아 반환
     */
    public static int[] solution(String s) {
        int conversionCnt = 0;
        int removedZerosCnt = 0;

        while (!s.equals("1")) {
            conversionCnt++;
            int zerosCnt = s.length() - s.replace("0", "").length();
            removedZerosCnt += zerosCnt;

            s = Integer.toBinaryString(s.length() - zerosCnt);
        }

        return new int[]{conversionCnt, removedZerosCnt};
    }

    public static void main(String[] args) {
        String s1 = "110010101001";
        String s2 = "01110";
        String s3 = "1111111";

        System.out.println(Arrays.toString(solution(s1))); // [3, 8]
        System.out.println(Arrays.toString(solution(s2))); // [3, 3]
        System.out.println(Arrays.toString(solution(s3))); // [4, 1]
    }
}
