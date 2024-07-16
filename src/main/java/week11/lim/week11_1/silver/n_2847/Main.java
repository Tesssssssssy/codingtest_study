package week11.lim.week11_1.silver.n_2847;

// 문제: https://www.acmicpc.net/problem/2847

import java.io.*;

public class Main {
    /**
     *  1. 게임에는 총 N개의 레벨이 있고, 각 레벨을 클리어할 때 마다 점수가 주어짐.
     *  2. 플레이어의 점수는 레벨을 클리어하면서 얻은 점수의 합으로, 이 점수를 바탕으로 온라인 순위를 매김.
     *  3. 레벨을 난이도 순으로 배치했다. 하지만, 실수로 쉬운 레벨이 어려운 레벨보다 점수를 많이 받는 경우를 만들었다.
     *  4. 이 문제를 해결하기 위해 동준이는 특정 레벨의 점수를 감소시키려고 한다.
     *     이렇게해서 각 레벨을 클리어할 때 주는 점수가 증가하게 만들려고 한다.
     *  5. 각 레벨을 클리어할 때 얻는 점수가 주어졌을 때, 몇 번 감소시키면 되는지 구해서 출력.
     *
     *  [조건]
     *  - 점수는 항상 양수, 1만큼 감소시키는 것이 1번
     *  - 정답이 여러 가지인 경우에는 점수를 내리는 것을 최소한으로 하는 방법을 찾아야 함.
     *
     *  [풀이]
     *  주어진 배열을 뒤에서부터 검사하면서, 이전 레벨의 점수가 현재 레벨의 점수보다 크거나 같은 경우,
     *  이전 레벨의 점수를 현재 레벨의 점수보다 작게 만들고, 그 차이만큼 감소 횟수를 증가시킨다.
     *  -> 각 레벨의 점수를 최소한으로 감소시켜 증가 수열을 만들 수 있다.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());  // 레벨의 수
        int[] scores = new int[N];

        for (int i = 0; i < N; i++) {
            scores[i] = Integer.parseInt(br.readLine());
        }

        int count = 0;

        // 뒤에서부터 앞으로 검사
        for (int i = N - 2; i >= 0; i--) {
            if (scores[i] >= scores[i + 1]) {
                // 점수를 감소시켜야 할 경우
                int decrease = scores[i] - scores[i + 1] + 1;
                count += decrease;
                scores[i] -= decrease;
            }
        }

        System.out.println(count);
    }
}
