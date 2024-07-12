package week10.lim.week10_1.bronze.n_14659;

// 문제: https://www.acmicpc.net/problem/14659

import java.io.*;
import java.util.*;

public class Main {
    /**
     *  1. N개의 봉우리.
     *  2. 자신보다 낮은 봉우리에 서있는 적들만 처치 가능.
     *  3. 처음 출발한 봉우리보다 높은 봉우리를 만나면 그대로 공격을 포기하고 낮잠.
     *  4. 봉우리의 높이는 모두 다르고 모든 용들은 오른쪽으로만 감.
     *  5. 활잡이 중 최고의 활잡이가 최대 몇 명의 적을 처리할 수 있는지 출력.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] mountainTop = new int[N + 1];

        for (int i = 0; i < N; i++) {
            mountainTop[i] = Integer.parseInt(st.nextToken());
        }

        int maxKilled = 0;

        for (int i = 0; i < N; i++) {
            int killed = 0;
            for (int j = i + 1; j < N; j++) {
                if (mountainTop[i] > mountainTop[j]) {
                    killed++;
                } else {
                    break;
                }
            }
            maxKilled = Math.max(maxKilled, killed);
        }

        System.out.println(maxKilled);
    }
}
