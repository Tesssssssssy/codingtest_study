package week6.lim.binarysearch.immigration;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/43238
// 참고: https://born2bedeveloper.tistory.com/40

import java.util.Arrays;

public class immigration2 {
    /**
     *   이분 탐색을 할 때는
     *      1. 내가 찾아야 할 정답의 범위를 left ~ right로 정한다.
     *      2. 정답을 mid로 간주한 후, 해당 정답이 유효한지 살펴본다.
     *      3. 2번 여부를 따지며 left와 right의 범위를 좁힌다.
     *      4. left > right가 되면 반복을 끝내고 답을 반환한다.
     */
    public static long solution(int n, int[] times) {
        long answer = 0;
        Arrays.sort(times);

        long left = 0;
        long right = times[times.length-1] * (long)n; // 모든 사람이 가장 느리게 심사받음

        while (left <= right) {
            long mid = (left + right) / 2;
            long complete = 0;

            for (int i = 0; i < times.length; i++)
                complete += mid / times[i];

            if (complete < n) // 해당 시간에는 모든 사람이 검사받을 수 없다.
                left = mid + 1;
            else {
                right = mid - 1;
                answer = mid; // 모두 검사받았으나, 더 최솟값이 있을 수 있다.
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        int n1 = 6;
        int[] times1 = {7, 10};
        System.out.println(solution(n1, times1));   // 28
    }
}
