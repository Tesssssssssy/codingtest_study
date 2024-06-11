package week3.kim.week3_1.greedy;

/**
 * [체육복]
 *
 * 점심시간에 도둑이 들어, 일부 학생이 체육복을 도난당했습니다.
 * 다행히 여벌 체육복이 있는 학생이 이들에게 체육복을 빌려주려 합니다.
 * 학생들의 번호는 체격 순으로 매겨져 있어, 바로 앞번호의 학생이나 바로 뒷번호의 학생에게만 체육복을 빌려줄 수 있습니다.
 * 예를 들어, 4번 학생은 3번 학생이나 5번 학생에게만 체육복을 빌려줄 수 있습니다.
 * 체육복이 없으면 수업을 들을 수 없기 때문에 체육복을 적절히 빌려 최대한 많은 학생이 체육수업을 들어야 합니다.
 *
 * 전체 학생의 수 n, 체육복을 도난당한 학생들의 번호가 담긴 배열 lost,
 * 여벌의 체육복을 가져온 학생들의 번호가 담긴 배열 reserve가 매개변수로 주어질 때,
 * 체육수업을 들을 수 있는 학생의 최댓값을 return 하도록 solution 함수를 작성해주세요.
 *
 * 제한사항
 * 전체 학생의 수는 2명 이상 30명 이하입니다.
 * 체육복을 도난당한 학생의 수는 1명 이상 n명 이하이고 중복되는 번호는 없습니다.
 * 여벌의 체육복을 가져온 학생의 수는 1명 이상 n명 이하이고 중복되는 번호는 없습니다.
 * 여벌 체육복이 있는 학생만 다른 학생에게 체육복을 빌려줄 수 있습니다.
 * 여벌 체육복을 가져온 학생이 체육복을 도난당했을 수 있습니다.
 * 이때 이 학생은 체육복을 하나만 도난당했다고 가정하며, 남은 체육복이 하나이기에 다른 학생에게는 체육복을 빌려줄 수 없습니다.
 *
 * 입출력 예
 * n	lost	reserve	    return
 * 5	[2, 4]	[1, 3, 5]	5
 * 5	[2, 4]	[3]		    4
 * 3	[3]	    [1]		    2
 *
 * 입출력 예 설명
 * 예제 #1
 * 1번 학생이 2번 학생에게 체육복을 빌려주고,
 * 3번 학생이나 5번 학생이 4번 학생에게 체육복을 빌려주면 학생 5명이 체육수업을 들을 수 있습니다.
 *
 * 예제 #2
 * 3번 학생이 2번 학생이나 4번 학생에게 체육복을 빌려주면 학생 4명이 체육수업을 들을 수 있습니다.
*/

public class Ex1 {
    public static void main(String[] args) {
        Ex1 ex1 = new Ex1();

        int n1 = 5;
        int[] lost1 = {2, 4};
        int[] reserve1 = {1, 3, 5};

        // 5 반환
        System.out.println(ex1.solution(n1, lost1, reserve1));

        int n2 = 5;
        int[] lost2 = {5, 3};
        int[] reserve2 = {4, 2};

        // 4 반환
        System.out.println(ex1.solution(n2, lost2, reserve2));

        int n3 = 3;
        int[] lost3 = {3};
        int[] reserve3 = {1};

        // 2 반환
        System.out.println(ex1.solution(n3, lost3, reserve3));
    }


    public int solution(int n, int[] lost, int[] reserve) {
        // 학생들의 체육복 상태를 저장하는 배열
        int[] students = new int[n];

        // 체육수업을 들을 수 있는 학생 수
        int answer = n;

        // 체육복을 잃어버린 학생들의 상태 -1
        for (int l : lost)
            students[l-1]--;

        // 여벌 체육복을 가진 학생들의 상태 1
        for (int r : reserve)
            students[r-1]++;

        // 체육복이 없는 학생을 찾기
        for (int i = 0; i < students.length; i++) {
            if(students[i] == -1) {
                // 체육복이 없는 학생 > 앞번호 학생에게 체육복을 빌릴 수 있는지 확인
                if(i - 1 >= 0 && students[i-1] == 1) {
                    students[i]++;
                    students[i-1]--;
                }
                // 체육복이 없는 학생 > 뒷번호 학생에게 체육복을 빌릴 수 있는지 확인
                else if(i + 1 < students.length && students[i+1] == 1) {
                    students[i]++;
                    students[i+1]--;
                }
                // 체육복을 빌릴 수 없는 학생이 있다면,
                // 체육수업을 들을 수 있는 학생 수에서 1을 뺌
                else
                    answer--;
            }
        }

        // 체육수업을 들을 수 있는 학생의 최대 수를 반환
        return answer;
    }


    // 부트캠프 풀이
//    public int solution(int n, int[] lost, int[] reserve) {
//        int answer = 0;
//        answer = n - lost.length;
//        boolean[] result1 = new boolean[reserve.length];
//        boolean[] result2 = new boolean[lost.length];
//
//        //정렬부터
//        Arrays.sort(lost);
//        Arrays.sort(reserve);
//
//        for (int i = 0; i < reserve.length; i++) {
//            for (int j = 0; j < lost.length; j++) {
//                if (!result1[i] && !result2[j] &&
//                        reserve[i] == lost[j]) {
//                    result1[i] = true;
//                    result2[j] = true;
//                    answer ++;
//                }
//            }
//        }
//
//        for (int i = 0; i < reserve.length; i++) {
//            for (int j = 0; j < lost.length; j++) {
//                if (!result1[i] && !result2[j] &&
//                        lost[j] <= reserve[i]+1 && lost[j] >= reserve[i]-1) {
//                    result1[i] = true;
//                    result2[j] = true;
//                    answer++;
//                }
//            }
//        }
//        return answer;
//    }
}