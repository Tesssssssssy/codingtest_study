package week12.week12_1.baek;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [수 찾기]
 *
 * N개의 정수 A[1], A[2], …, A[N]이 주어져 있을 때, 이 안에 X라는 정수가 존재하는지 알아내는 프로그램을 작성하시오.
 *
 *
 * 입력
 * 첫째 줄에 자연수 N(1 ≤ N ≤ 100,000)이 주어진다. 다음 줄에는 N개의 정수 A[1], A[2], …, A[N]이 주어진다. 다음 줄에는 M(1 ≤ M ≤ 100,000)이 주어진다. 다음 줄에는 M개의 수들이 주어지는데, 이 수들이 A안에 존재하는지 알아내면 된다. 모든 정수의 범위는 -2^31 보다 크거나 같고 2^31보다 작다.
 *
 *
 * 출력
 * M개의 줄에 답을 출력한다. 존재하면 1을, 존재하지 않으면 0을 출력한다.
 *
 *
 * 예제 입력 1
 * 5
 * 4 1 5 2 3
 * 5
 * 1 3 7 9 5
 *
 * 예제 출력 1
 * 1
 * 1
 * 0
 * 0
 * 1
*/

public class Ex4 {
    public static void main(String[] args) throws Exception {
        Ex4 ex4 = new Ex4();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫 번째 줄에서 자연수 N을 입력받음
        int N = Integer.parseInt(br.readLine());

        // 두 번째 줄에서 N개의 정수를 입력받아 배열에 저장
        int[] A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        // 배열을 정렬
        Arrays.sort(A);

        // 세 번째 줄에서 자연수 M을 입력받음
        int M = Integer.parseInt(br.readLine());

        // 네 번째 줄에서 M개의 수를 입력받아 배열에 저장
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            int num = Integer.parseInt(st.nextToken());
            // 이분 탐색을 통해 num이 배열 A에 존재하는지 확인
            if (ex4.binarySearch(A, num)) {
                sb.append(1).append("\n");
            } else {
                sb.append(0).append("\n");
            }
        }

        // 결과를 출력
        System.out.print(sb.toString());
    }

    // 이분 탐색 수행
    boolean binarySearch(int[] array, int key) {
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (array[mid] == key) {
                return true;
            } else if (array[mid] < key) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return false;
    }
}