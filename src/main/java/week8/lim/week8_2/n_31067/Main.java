package week8.lim.week8_2.n_31067;

// 문제: https://www.acmicpc.net/problem/31067

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] NK = br.readLine().split(" ");
        int N = Integer.parseInt(NK[0]);
        int K = Integer.parseInt(NK[1]);

        String[] trackStr = br.readLine().split(" ");
        int[] track = new int[N];
        for (int i = 0; i < N; i++) {
            track[i] = Integer.parseInt(trackStr[i]);
        }

        int count = 0;
        boolean possible = true;

        // 처음에 이중 for문 썼다가 i=1 시작 + 이전 값과 비교하는 방식으로 수정.
        for (int i = 1; i < N; i++) {
            if (track[i] <= track[i - 1]) {
                // 이전 값이 다음 값보다 크면 최소한 그 만큼을 다음 값에 더해주어야 함.
                int neededIncrease = (track[i - 1] - track[i]) + 1;
                if (neededIncrease > K) { // 그래서 그 값이 K보다 크면 증가 트랙 못 만드는 것.
                    possible = false;
                    break;
                } else {
                    track[i] += K;
                    count++;
                }
            }
        }

        if (possible) {
            System.out.println(count);
        } else {
            System.out.println(-1);
        }
    }
}
