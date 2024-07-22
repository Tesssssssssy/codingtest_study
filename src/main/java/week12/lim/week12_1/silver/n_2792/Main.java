package week12.lim.week12_1.silver.n_2792;

// 문제: https://www.acmicpc.net/problem/2792

import java.io.*;

public class Main {
    /**
     *  1. 각 보석은 M가지 다른 색상 중 한 색상
     *  2. 모든 보석을 N명의 학생들에게 나누어주려고 한다.
     *     보석을 받지 못하는 학생이 있어도 된다.
     *  3. 한 아이가 너무 많은 보석을 가져가면 다른 아이들이 질투함.
     *     질투심 = 가장 많은 보석을 가져간 학생이 가지고 있는 보석의 개수
     *  4. 질투심이 최소가 되게 보석을 나누어 주어야 함.
     *  5. 빨간 보석 4개 (RRRR) / 파란 보석 7개 (BBBBBBB)
     *     -> 5명에게 나누어주는 경우
     *        RR / RR / BB / BB / BBB
     *        -> 질투심 3, 이 값보다 작게 나누어 줄 수 없다.
     *  6. 질투심이 최소가 되게 보석을 나누어주는 방법을 계산하고
     *     질투심 값 출력
     *
     *  [입력]
     *  N (아이들의 수) / M (색상의 수)
     *  M개 줄에 보석 개수
     *
     *  [풀이]
     *  이진탐색
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]); // 학생 수
        int M = Integer.parseInt(input[1]); // 색상 수

        int[] jewels = new int[M];
        for (int i = 0; i < M; i++) {
            jewels[i] = Integer.parseInt(br.readLine());
        }

        int lowestJewel = 1;
        int highestJewel = 0;
        for (int jewel : jewels) {
            if (jewel > highestJewel) {
                highestJewel = jewel;
            }
        }

        while (lowestJewel < highestJewel) {
            int mid = (lowestJewel + highestJewel) / 2;
            long sum = 0; // 필요한 학생 수 계산

            for (int jewel : jewels) {
                if (jewel % mid == 0)
                    sum += jewel / mid;
                else
                    sum += jewel / mid + 1;
                // 보석을 학생들에게 나누어 줄 때 나누어 떨어지지 않는 경우를 처리 (올림 처리)
            }
            // mid 는 이진 탐색을 통해 결정되는 "가장 많은 보석을 가진 학생이 받을 수 있는 최대 보석의 개수" 의미

            if (sum > N) {
                lowestJewel = mid + 1; // 더 많은 보석을 한 학생에게 주어야 하므로, 질투심 범위를 높임
            } else {
                highestJewel = mid; // 더 적은 보석을 한 학생에게 줄 수 있으므로, 질투심 범위를 낮춤
            }
        }

        System.out.println(lowestJewel); // 최소 질투심 출력
        br.close();
    }
}
/*
    목표 변수의 성격 파악
        질투심은 "가장 많은 보석을 받는 학생이 가진 보석의 개수"로 정의된다.
        이는 구간에서 최대값(minimax 문제)을 최소화하는 문제이다.
        구간에서 최대값을 최소화하고자 할 때 이진 탐색이 효과적인 접근 방법이 될 수 있다.

    값의 범위가 명확
        질투심의 가능한 최소값은 1이며, 최대값은 가장 많은 보석을 가진 색상의 보석 수이다.
        이 범위 내에서 최적의 값을 찾아야 하며,
        이진 탐색은 이러한 "정렬된" 범위 또는 "순서가 있는" 범위 내에서 값을 찾을 때 매우 효율적이다.

    결정 함수의 존재
        이진 탐색은 "결정 문제"(yes/no 문제)를 통해 최적의 값을 찾는 방식이다.
        이 문제에서 결정 함수는 주어진 maxJealousy 값에 대해 모든 보석을 N명의 학생에게 나눌 수 있는지 여부를 검사한다.
        만약 나눌 수 있다면, 질투심을 줄일 수 있는 가능성이 있으므로 탐색 범위를 더 낮은 쪽으로 조정할 수 있다.

    효율성
        N과 M의 범위가 매우 크기 때문에,
        각 색상 별로 보석을 학생 수에 따라 나누어보며 질투심을 최소화하는 직접적인 방법은 비효율적이다.
        이진 탐색을 사용하면 로그 시간 복잡도를 갖는 탐색을 통해 빠르게 최적의 해답을 찾을 수 있다.
*/