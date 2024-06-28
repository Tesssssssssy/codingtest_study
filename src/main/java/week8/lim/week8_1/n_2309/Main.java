package week8.lim.week8_1.n_2309;

// 문제: https://www.acmicpc.net/problem/2309

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = new int[9];
        int sum = 0;

        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            sum += arr[i]; // 키 합산
        }

        Arrays.sort(arr);

        // 100을 초과하는 부분 찾기 (합에서 두 명의 키를 빼서 100 만들기) - 브루트 포스
        boolean found = false;
        for (int i = 0; i < arr.length && !found; i++) {
            for (int j = i + 1; j < arr.length && !found; j++) {
                if (sum - arr[i] - arr[j] == 100) {
                    // 두 명을 제외하고 나머지 출력
                    for (int k = 0; k < arr.length; k++) {
                        if (k != i && k != j) {
                            System.out.println(arr[k]);
                        }
                    }
                    found = true; // 답을 찾으면 루프 종료
                }
            }
        }
    }
}
