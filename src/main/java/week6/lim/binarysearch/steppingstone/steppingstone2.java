package week6.lim.binarysearch.steppingstone;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/43236
// 참고: https://hy-ung.tistory.com/49

import java.util.Arrays;

public class steppingstone2 {
    public static int solution(int goal, int[] rocks, int n) {
        Arrays.sort(rocks); // 바위 위치를 오름차순으로 정렬

        int left = 0; // 이분 탐색의 하한값
        int right = goal; // 이분 탐색의 상한값

        int result = Integer.MIN_VALUE; // 가능한 최소 거리의 최대값을 저장하는 변수. 초기값은 최소 가능 값으로 설정
        while (left <= right) {
            int mid = (left + right) / 2; // 현재 검토 중인 거리
            int cnt = 0; // 제거된 바위의 수
            int prev = 0; // 이전 바위의 위치 또는 시작 위치

            for (int i = 0; i < rocks.length; i++) {
                if (rocks[i] - prev < mid) {
                    // 현재 바위와 이전 바위 사이의 거리가 mid보다 작으면, 이 바위를 제거
                    cnt++;
                } else {
                    // 그렇지 않다면, 이전 위치를 현재 바위의 위치로 갱신
                    prev = rocks[i];
                }

                // 제거해야 하는 바위의 수가 허용치를 초과하면 반복 중단
                if (cnt > n) {
                    break;
                }
            }

            // 마지막 바위와 도착 지점 사이의 거리 검사
            if (goal - prev < mid && cnt <= n) {
                cnt++;
            }

            if (cnt > n) {
                // 바위를 너무 많이 제거해야 하면, mid 값을 줄여서 조건을 완화
                right = mid - 1;
            } else {
                // 가능한 경우, 결과값 갱신 및 mid 값을 증가시켜 최대 최소 거리 탐색
                left = mid + 1;
                result = mid;
            }
        }
        return result; // 계산된 최소 거리의 최대값 반환
    }

    public static void main(String[] args) {
        int distance1 = 25;
        int[] rocks1 = {2, 14, 11, 21, 17};
        int n1 = 2;
        System.out.println(solution(distance1, rocks1, n1));  // 4
    }
}
