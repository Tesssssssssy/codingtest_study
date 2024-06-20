package week7.lim.groom_search.ant;

// 문제: https://level.goorm.io/exam/49060/%EA%B0%9C%EB%AF%B8-%EC%A7%91%ED%95%A9%EC%9D%98-%EC%A7%80%EB%A6%84/quiz/1
// 참고: https://adjh54.tistory.com/384

import java.io.*;
import java.util.*;

public class Ant {
    /**
     *  < 투 포인터 방식을 사용한 '슬라이딩 윈도우' 알고리즘 >
     *
     *  1. 개미 위치들을 배열에 저장하고 오름차순으로 정렬.
     *  2. left 포인터와 right 포인터를 배열의 시작에 위치시킴.
     *  3. right 포인터를 배열의 끝까지 이동시키면서,
     *     현재 left와 right 사이의 거리가 D 이하가 될 때까지 이동.
     *  4. 만약 left와 right 사이의 거리가 D 이하이면,
     *     이 범위 안에 있는 모든 개미는 제거할 필요가 없다.
     *     그 범위 밖에 있는 개미들만 제거하면 되므로, 전체 개미 수에서 right - left + 1을 빼줌.
     *  5. left를 하나 증가시키고, 다시 right를 이동시켜 반복.
     *  6. 이 과정을 left가 배열 끝에 도달할 때까지 반복하며,
     *     각 단계에서 계산된 제거해야 할 최소 개미 수를 기록.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫 번째 줄에서 D 값을 읽음
        String[] firstLine = br.readLine().split(" ");
        int D = Integer.parseInt(firstLine[1]); // 개미 집합의 최대 지름 D

        // 두 번째 줄에서 개미의 위치를 읽음
        String[] positionsStr = br.readLine().split(" ");
        int[] positions = new int[positionsStr.length];

        // 문자열 배열에서 정수 배열로 변환
        for (int i = 0; i < positions.length; i++) {
            positions[i] = Integer.parseInt(positionsStr[i]);
        }

        // 개미 위치를 오름차순으로 정렬
        Arrays.sort(positions);

        // 최소 제거 개미 수를 최대 개미 수로 초기화
        int minRemove = positions.length;

        // 투 포인터 초기화
        int left = 0;
        int right = 0;

        // right 포인터를 배열 끝까지 이동시키면서 조건을 검사
        while (right < positions.length) {
            // 현재 left와 right 사이의 거리가 D 이하인 경우, right 포인터를 증가시키며 범위 확장
            while (right < positions.length && positions[right] - positions[left] <= D) {
                right++;
            }
            // 현재 left부터 right-1까지의 집합은 지름 D 이하를 만족
            // 전체 개미 수에서 현재 범위의 개미 수를 빼 제거할 개미 수를 계산
            minRemove = Math.min(minRemove, positions.length - (right - left));
            // left 포인터를 증가시켜 새로운 범위 탐색 시작
            left++;
        }

        // 최소로 제거해야 하는 개미 수 출력
        System.out.println(minRemove);
    }
}
