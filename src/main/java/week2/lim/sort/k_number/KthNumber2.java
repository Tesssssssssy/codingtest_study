package week2.lim.sort.k_number;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/42748
// 참고: https://velog.io/@imok-_/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-K%EB%B2%88%EC%A7%B8%EC%88%98-java

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class KthNumber2 {
    public static int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        ArrayList<Integer> list = new ArrayList<Integer>();

        for (int i = 0; i < commands.length; i++) {
            for (int j = commands[i][0]; j <= commands[i][1]; j++) {
                list.add(array[j-1]);
                Collections.sort(list); // ArrayList 오름차순 정렬
            }
            answer[i] = list.get(commands[i][2] - 1);
            list.clear();
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