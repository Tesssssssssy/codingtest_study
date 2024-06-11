package week6.lim.greedy.gymclothes;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/42862
// 참고: https://velog.io/@puclpu/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EC%B2%B4%EC%9C%A1%EB%B3%B5-Java

import java.util.Arrays;

public class gymclothes2 {
        public static int solution(int n, int[] lost, int[] reserve) {
        int answer = n - lost.length;
        // 도난 당하지 않은 학생 수

        Arrays.sort(lost);
        Arrays.sort(reserve);

        // 여벌 체육복을 가져왔지만 도난당한 학생 수
        // 다른 학생에게 체육복을 빌려줄 수 없음
        for (int i = 0; i < lost.length; i++) {
            for (int j = 0; j < reserve.length; j++) {
                if (lost[i] == reserve[j]) {
                    answer++;
                    lost[i] = -1;
                    reserve[j] = -1;
                    break;
                }
            }
        }

        // 도난당했지만 체육복을 빌릴 수 있는 학생 수
        for (int i = 0; i < lost.length; i++) {
            for (int j = 0; j < reserve.length; j++) {
                if ((lost[i] == reserve[j] - 1) || (lost[i] == reserve[j] + 1)) {
                    answer++;
                    reserve[j] = -1;
                    break;
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        int n1 = 5;
        int[] lost1 = {2, 4};
        int[] reserve1 = {1, 3, 5};
        System.out.println(solution(n1, lost1, reserve1));  // 5

        int n2 = 5;
        int[] lost2 = {2, 4};
        int[] reserve2 = {3};
        System.out.println(solution(n2, lost2, reserve2));  // 4

        int n3 = 3;
        int[] lost3 = {3};
        int[] reserve3 = {1};
        System.out.println(solution(n3, lost3, reserve3));  // 2
    }
}
