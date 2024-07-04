package week9.lim.week9_2.n_2798;

// 문제: https://www.acmicpc.net/problem/2798

import java.io.*;
import java.util.*;

public class Main {
    /**
     *  1. 카드의 합이 21을 넘지 않는 한도 내에서 카드의 합 최대로.
     *  2. 각 카드에는 양의 정수
     *  3. N장의 카드가 있고 숫자 M을 외친다.
     *  4. 이제 제한 시간 안에 N장의 카드 중 3장 골라야.
     *  5. 고른 카드의 합은 M을 넘지 않으면서 M과 최대한 가깝게 만들어야 함.
     *  6. N장의 카드에 써져 있는 숫자가 주어졌을 때, M을 넘지 않으면서 M에 최대한 가까운 카드 3장의 합 출력
     *
     *  [정리]
     *  브루트포스 방식 사용 -> O(N^3)의 시간복잡도이고 1,000,000회이지만 현대 컴퓨터에서 빠르게 처리 가능
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] cards = new int[N];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }

        int similarMax = 0;

        // 3중 반복문을 사용하여 모든 카드 3장 조합의 합을 확인 <브루트 포스>
        for (int i = 0; i < N - 2; i++) {
            for (int j = i + 1; j < N - 1; j++) {
                for (int k = j + 1; k < N; k++) {
                    int sum = cards[i] + cards[j] + cards[k];
                    // 합이 M 이하이고, 이전에 찾은 값보다 크면 결과 업데이트
                    if (sum <= M && sum > similarMax) {
                        similarMax = sum;
                    }
                }
            }
        }

        // 결과 출력
        System.out.println(similarMax);
    }
}
