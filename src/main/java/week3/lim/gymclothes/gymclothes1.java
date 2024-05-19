package week3.lim.gymclothes;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/42862

public class gymclothes1 {
    public static int solution(int n, int[] lost, int[] reserve) {
        // 체육복을 가진 학생들의 수 배열을 생성하고 초기값은 모든 학생이 1벌을 가진 것으로 설정
        int[] student = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            student[i] = 1;
        }

        // 도난당한 학생의 체육복 수를 감소
        for (int l : lost) {
            student[l]--;
        }

        // 여벌의 체육복을 가져온 학생의 체육복 수를 증가
        for (int r : reserve) {
            student[r]++;
        }

        // 체육복을 잃어버린 학생들에게 여벌 체육복을 빌려줌
        for (int i = 1; i <= n; i++) {
            if (student[i] == 0) { // 체육복이 없는 학생이라면
                if (i > 1 && student[i - 1] > 1) { // 왼쪽 학생에게서 빌릴 수 있다면
                    student[i]++;
                    student[i - 1]--;
                } else if (i < n && student[i + 1] > 1) { // 오른쪽 학생에게서 빌릴 수 있다면
                    student[i]++;
                    student[i + 1]--;
                }
            }
        }

        // 체육수업을 들을 수 있는 학생의 수 계산
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            if (student[i] > 0) {
                answer++;
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