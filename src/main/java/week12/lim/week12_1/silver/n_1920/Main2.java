package week12.lim.week12_1.silver.n_1920;

import java.io.*;
import java.util.*;

public class Main2 {
    /**
     * - N개의 정수 A[1], A[2], …, A[N]이 주어져 있을 때,
     * 이 안에 X라는 정수가 존재하는지 알아내는 프로그램
     *
     * [입력]
     * - 자연수 N(1 ≤ N ≤ 100,000)
     * - 다음 줄에는 N개의 정수 A[1], A[2], …, A[N]
     * - 다음 줄에는 M(1 ≤ M ≤ 100,000)
     * - 다음 줄에는 M개의 수
     *
     * 이 수들이 A안에 존재하는지 알아내면 된다.
     * 모든 정수의 범위는 -231 보다 크거나 같고 231보다 작다.
     *
     * [출력]
     * M개의 줄에 답을 출력한다.
     * (존재하면 1을, 존재하지 않으면 0을 출력)
     *
     * [풀이]
     * 이진 탐색 사용
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] array = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }

        // 배열 정렬
        Arrays.sort(array);

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            int target = Integer.parseInt(st.nextToken());
            // 이진 탐색으로 target 존재 여부 확인
            if (binarySearch(array, target)) {
                sb.append(1).append('\n');
            } else {
                sb.append(0).append('\n');
            }
        }

        System.out.print(sb);
    }

    // 이진 탐색 메소드
    private static boolean binarySearch(int[] array, int target) {
        int low = 0;
        int high = array.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (array[mid] == target) {
                return true;
            } else if (array[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return false;
    }
}
