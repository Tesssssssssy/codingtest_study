package week12.baek.july23.baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S2343 {
    public static void main(String[] args) throws IOException {
        // 입력을 받기 위해 BufferedReader 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 강의 수 N과 블루레이 수 M을 입력받음
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 강의 길이를 담을 배열과 합계를 초기화
        st = new StringTokenizer(br.readLine());
        int[] lect = new int[N];
        long sum = 0;
        long low = 0;  // 블루레이 크기의 최솟값을 초기화 (0에서 시작)
        long high = 0; // 블루레이 크기의 최댓값을 초기화 (합계에서 시작)

        // 각 강의 길이를 배열에 저장하고, 합계를 계산하며, 최대 강의 길이로 low 초기화
        for (int i = 0; i < N; i++) {
            lect[i] = Integer.parseInt(st.nextToken());
            sum += lect[i];
            low = Math.max(low, lect[i]); // 최댓값 찾기
        }

        // high는 모든 강의 길이의 합으로 설정
        high = sum;

        // 이진 탐색을 수행하여 최소 블루레이 크기 찾기
        while (low <= high) {
            long mid = (low + high) / 2; // 중간값 계산
            int count = 1; // 필요한 블루레이 개수
            long sumTime = 0; // 현재 블루레이에 담긴 강의 시간 합계

            // 모든 강의를 하나씩 확인
            for (int i = 0; i < N; i++) {
                sumTime += lect[i];
                // 현재 블루레이에 담을 수 없는 경우 새로운 블루레이 사용
                if (sumTime > mid) {
                    sumTime = lect[i];
                    count++;
                }
            }

            // 필요한 블루레이 개수가 M보다 작거나 같은 경우
            if (count <= M) {
                high = mid - 1; // 블루레이 크기를 줄이기 위해 high 조정
            } else {
                low = mid + 1; // 블루레이 크기를 늘리기 위해 low 조정
            }
        }

        // 최소 블루레이 크기 출력
        System.out.println(low);
    }
}
