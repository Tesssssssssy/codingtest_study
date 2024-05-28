package week4.lim.fullsearch.carpet.practice;

import java.util.Arrays;

public class carpetPrac1 {
    /**
     *  <혼자 풀이>
     *  1. 가운데 노란색 격자가 있으려면 무조건 가로, 세로가 3이상이어야 함.
     *  2. brown, yellow를 더해서 총 격자 수 구하고
     *     총 격자수의 약수를 구해서 경우의 수를 만들고
     *     그 경우의 수 내에서 카펫의 가로, 세로 크기를 반환한다.
     */
    public static int[] solution(int brown, int yellow) {
        int[] answer = new int[2];

        int total = brown + yellow;

        for (int height = 3; height <= Math.sqrt(total); height++) {
            int row = total / height;
            int col = total / row;

            if (total % height == 0 && row >= col) {
                if ((row - 2) * (col - 2) == yellow) {
                    answer[0] = row;
                    answer[1] = col;
                }
            }
        }

        return answer;
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
