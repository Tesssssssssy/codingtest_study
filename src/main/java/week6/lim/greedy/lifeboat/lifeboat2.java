package week6.lim.greedy.lifeboat;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/42885

import java.util.Arrays;

public class lifeboat2 {
    public static int solution(int[] people, int limit) {
        int answer = 0;
        Arrays.sort(people);
        int index = 0;

        for (int i = people.length - 1; i >= index; i--) {
            if (people[i] + people[index] <= limit) {
                index++;
                answer++;
            }
            else {
                answer++;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] people1 = {70, 50, 80, 50};
        int limit1 = 100;
        System.out.println(solution(people1, limit1));  // 3

        int[] people2 = {70, 80, 50};
        int limit2 = 100;
        System.out.println(solution(people2, limit2));  // 3
    }
}
