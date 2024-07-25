package week12.baek.july23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S2792 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int arr[] = new int[M];
        int left = 1, right = 0, mid = 0, sum, answer = 0;

        // 각 색상별 보석의 개수
        for (int i = 0; i < M; i++) {
            arr[i] = Integer.parseInt(in.readLine());
            right = Math.max(right, arr[i]);
        }

        // 이분 탐색 하면서 최솟값 찾기
        while (left <= right) {
            // 질투심이 mid가 되도록 보석 나눠주기
            mid = (left + right) / 2;
            sum = 0;
            for (int i = 0; i < M; i++) {
                sum += arr[i] / mid;
                if (arr[i] % mid != 0) {
                    sum++;
                }
            }
            // 보석을 나눠줄 수 없는 경우
            if (sum > N) {
                left = mid + 1;
            }
            // 보석을 나눠줄 수 있는 경우
            else {
                right = mid - 1;
                answer = mid;
            }
        }

        // 질투심의 최솟값 출력
        System.out.println(answer);
    }
}
