package week6.lim.binarysearch.immigration;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/43238

public class immigration1 {
    public static long solution(int n, int[] times) {
        long minTime = 1;   // 최소 시간은 1분

        long maxTime = (long)times[0] * n;  // 최대 시간 초기 설정

        for (int time : times) {
            maxTime = Math.min(maxTime, (long)time * n);    // 최대 시간 최소화
        }

        long answer = maxTime;  // 최대 시간으로 초기화

        while (minTime <= maxTime) {
            long midTime = (minTime + maxTime) / 2;
            long sum = 0;

            // midTime 동안 각 심사관이 심사할 수 있는 사람 수 계산
            for (int time : times) {
                sum += midTime / time;
                if (sum >= n)
                    break;  // 이미 n명 이상 심사할 수 있다면 반복 중단
            }

            if (sum >= n) {  // n명 이상 심사 가능한 경우
                answer = midTime;  // 정답 갱신
                maxTime = midTime - 1;  // 최대 시간 감소
            } else {  // n명 심사 불가능한 경우
                minTime = midTime + 1;  // 최소 시간 증가
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
