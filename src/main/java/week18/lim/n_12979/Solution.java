package week18.lim.n_12979;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/12979

public class Solution {
    public static int solution(int N, int[] stations, int W) {
        /*
            기지국의 영향 범위 계산: 각 기지국이 커버하는 범위를 계산하고, 해당 범위 밖의 구간을 파악.
            커버되지 않은 구간 계산: 각 기지국 사이의 커버되지 않은 구간을 계산하고,
                               필요한 최소한의 기지국을 추가.
            구간마다 필요한 기지국 수 계산: 커버되지 않은 구간의 길이에 따라 추가로 필요한 기지국 수를 계산.
        */

        int answer = 0;
        int current = 1;  // 현재 커버되지 않은 첫 번째 아파트 번호
        int range = 2 * W + 1;  // 하나의 기지국이 커버할 수 있는 아파트 수

        for (int station : stations) {
            // 각 기지국에 대해 그 기지국이 커버하는 범위 밖의 구간을 확인

            if (current < station - W) {
                // 현재 위치에서 다음 기지국의 시작점까지 커버되지 않는 구간 계산
                // current 부터 station - W - 1까지는 커버되지 않은 구간

                int leftUncovered = station - W - current;
                // 이 구간을 커버하기 위해 필요한 최소 기지국 수 계산해서 answer 에 추가
                answer += (leftUncovered + range - 1) / range;
            }

            // 다음 기지국 이후의 커버되지 않은 구간으로 이동
            current = station + W + 1;
        }

        // 마지막 기지국 이후로 커버되지 않은 구간이 있을 경우 처리
        if (current <= N) {
            int leftUncovered = N - current + 1;
            answer += (leftUncovered + range - 1) / range;
        }

        return answer;
    }

    public static void main(String[] args) {
        int N1 = 11;
        int[] stations1 = {4, 11};
        int W1 = 1;
        System.out.println(solution(N1, stations1, W1)); // 3

        int N2 = 16;
        int[] stations2 = {9};
        int W2 = 2;
        System.out.println(solution(N2, stations2, W2)); // 3
    }
}
