package week2.lim.Sort.k_number;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/42748

import java.util.Arrays;

public class KthNumber {
    public static int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];

        for (int idx = 0; idx < commands.length; idx++) {
            int i = commands[idx][0];
            int j = commands[idx][1];
            int k = commands[idx][2];

            // 1. 배열 자르기: 주의할 점은 array의 인덱스가 0부터 시작하지만, 문제에서 주어진 i, j는 1부터 시작하는 인덱스이므로
            //    i-1부터 j까지 자르기 위해서는 i-1과 j를 사용합니다.
            int[] temp = Arrays.copyOfRange(array, i - 1, j);

            // 2. 배열 정렬하기
            Arrays.sort(temp);

            // 3. k번째 수 찾기: 마찬가지로 k도 1부터 시작하는 인덱스이므로 k-1을 사용합니다.
            answer[idx] = temp[k - 1];
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] array = {1, 5, 2, 6, 3, 7, 4};
        int[][] commands = {{2, 5, 3}, {4, 4, 1}, {1, 7, 3}};
        int[] results = solution(array, commands);

        System.out.println(Arrays.toString(results));  // [5, 6, 3]
    }
}