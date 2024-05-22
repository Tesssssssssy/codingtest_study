package week3.lim.binarysearch.steppingstone;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/43236

import java.util.Arrays;

public class steppingstone1 {
    public static int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks); // 바위 위치를 정렬합니다.
        int minDist = 1; // 최소 거리는 1부터 시작
        int maxDist = distance; // 최대 거리는 도착지점까지의 거리
        int answer = 0; // 반환할 값

        while (minDist <= maxDist) {
            int midDist = (minDist + maxDist) / 2;
            int lastPos = 0; // 마지막으로 확인된 위치 (시작은 0)
            int removeCount = 0; // 제거한 바위 수

            for (int rock : rocks) {
                if (rock - lastPos < midDist) { // 중간 거리보다 작은 경우 바위 제거
                    removeCount++;
                } else { // 그렇지 않으면, 이 위치를 새로운 출발점으로 설정
                    lastPos = rock;
                }
            }

            // 마지막 바위와 도착지점 사이의 거리도 검사
            if (distance - lastPos < midDist) {
                removeCount++;
            }

            if (removeCount > n) { // 너무 많은 바위를 제거해야 하는 경우
                maxDist = midDist - 1;
            } else { // 유효한 상태
                answer = midDist; // 현재 midDist 값을 잠정적인 답으로 저장
                minDist = midDist + 1;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int distance1 = 25;
        int[] rocks1 = {2, 14, 11, 21, 17};
        int n1 = 2;
        System.out.println(solution(distance1, rocks1, n1));  // 4
    }
}
