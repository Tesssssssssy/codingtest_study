package week6.lim.greedy.lifeboat;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/42885

import java.util.Arrays;

public class lifeboat1 {
    public static int solution(int[] people, int limit) {
        Arrays.sort(people);  // 사람 몸무게를 오름차순으로 정렬
        int left = 0;  // 가장 가벼운 사람의 인덱스
        int right = people.length - 1;  // 가장 무거운 사람의 인덱스
        int answer = 0;  // 사용된 구명보트의 수

        while (left <= right) {
            if (people[left] + people[right] <= limit) {
                // 두 사람의 무게 합이 limit 이하이면 함께 태우기
                left++;
            }
            right--;  // 가장 무거운 사람은 항상 보트를 타야 함
            answer++;  // 보트 하나 추가
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
