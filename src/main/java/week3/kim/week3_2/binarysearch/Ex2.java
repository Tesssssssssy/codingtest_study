package week3.kim.week3_2.binarysearch;

import java.util.Arrays;

/**
 * [징검다리]
 *
 * 출발지점부터 distance만큼 떨어진 곳에 도착지점이 있습니다. 그리고 그사이에는 바위들이 놓여있습니다. 바위 중 몇 개를 제거하려고 합니다.
 * 예를 들어, 도착지점이 25만큼 떨어져 있고, 바위가 [2, 14, 11, 21, 17] 지점에 놓여있을 때
 * 바위 2개를 제거하면 출발지점, 도착지점, 바위 간의 거리가 아래와 같습니다.
 *
 * 제거한 바위의 위치	각 바위 사이의 거리	거리의 최솟값
 * [21, 17]	        [2, 9, 3, 11]	    2
 * [2, 21]	        [11, 3, 3, 8]	    3
 * [2, 11]	        [14, 3, 4, 4]	    3
 * [11, 21]	        [2, 12, 3, 8]	    2
 * [2, 14]	        [11, 6, 4, 4]	    4
 * 위에서 구한 거리의 최솟값 중에 가장 큰 값은 4입니다.
 *
 * 출발지점부터 도착지점까지의 거리 distance,
 * 바위들이 있는 위치를 담은 배열 rocks,
 * 제거할 바위의 수 n이 매개변수로 주어질 때,
 * 바위를 n개 제거한 뒤 각 지점 사이의 거리의 최솟값 중에 가장 큰 값을 return 하도록 solution 함수를 작성해주세요.
 *
 * 제한사항
 * 도착지점까지의 거리 distance는 1 이상 1,000,000,000 이하입니다.
 * 바위는 1개 이상 50,000개 이하가 있습니다.
 * n 은 1 이상 바위의 개수 이하입니다.
 *
 * 입출력 예
 * distance	rocks			    n	return
 * 25		[2, 14, 11, 21, 17]	2	4
*/

public class Ex2 {
    public static void main(String[] args) {
        Ex2 ex2 = new Ex2();

        // 도착지점까지의 거리
        int distance = 25;
        // 바위들의 위치
        int[] rockPositions = {2, 14, 11, 21, 17};
        // 제거할 바위의 수
        int n = 2;

        // 4 반환
        System.out.println(ex2.solution(distance, rockPositions, n));
    }


    public int solution(int distance, int[] rocks, int n) {
        // 바위 위치를 오름차순으로 정렬
        Arrays.sort(rocks);

        // 이분 탐색의 시작 범위
        int left = 0;
        // 이분 탐색의 끝 범위
        int right = distance;
        // 이분 탐색의 중간값
        int mid;
        // 최종 답안
        int answer = 0;

        // 이분 탐색을 수행
        while (left <= right) {
            // 중간값을 계산
            mid = (left + right) / 2;
            // 이전 바위의 위치
            int prev = 0;
            // 제거된 바위의 수
            int removeCount = 0;

            // 바위들을 순회하며 제거할 바위를 결정
            for (int rock : rocks) {
                // 바위 사이의 거리가 mid보다 작으면 바위를 제거
                if (rock - prev < mid) {
                    removeCount++;
                }
                // 바위를 유지
                else {
                    prev = rock;
                }
            }

            // 마지막 바위와 도착지점 사이의 거리를 확인
            if (distance - prev < mid) {
                removeCount++;
            }

            // 제거된 바위의 수가 n 이하인 경우,
            // 답안을 업데이트하고 범위를 조정
            if (removeCount <= n) {
                answer = Math.max(answer, mid);
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        // 계산된 최대 거리의 최솟값을 반환
        return answer;
    }
}