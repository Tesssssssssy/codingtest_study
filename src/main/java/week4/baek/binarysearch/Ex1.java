package week4.baek.binarysearch;

import java.util.Arrays;

public class Ex1 {
    public static long solution(int n, int[] times) {
        long answer = 0;
        Arrays.sort(times);

        long left = 0;
        //모든 사람이 가장 느린 심사대에서 심사를 받았을 경우
        long right = times[times.length-1] * (long)n;

        //이분 탐색 진행
        while(left <= right) {
            long mid = (left + right) / 2;
            long complete = 0;
            //mid 시간동안 몇 명을 심사할 수 있는 지 검사
            for (int i = 0; i < times.length; i++)
                complete += mid / times[i];

            //mid 시간동안 모두 검사하지 못하면, mid보다 커야하므로 left를 mid+1로 이동
            if (complete < n)
                left = mid + 1;

            //모두 검사할 수 있으면, 이게 정답일 수도 있고, 최솟값이 있을 수도 있음
            else {
                right = mid - 1;
                answer = mid;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] times = {7, 10};
        System.out.println(solution(6, times));
    }
}
