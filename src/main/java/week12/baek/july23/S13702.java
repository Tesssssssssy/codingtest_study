package week12.baek.july23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S13702 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        long[] amount = new long[n];
        long max = 0;
        for (int i = 0; i < n; i++) {
            amount[i] = Long.parseLong(br.readLine());
            max = Math.max(max, amount[i]);
        }

        long low = 1;
        long high = max;
        long result = 0;

        while (low <= high) {
            long mid = (low + high) / 2;

            long count = 0;
            // 각 주전자마다 몇 명에게 줄 수 있는지 계산
            for (long amt : amount) {
                count += amt / mid;
            }

            // 모두에게 나눠 줄 수 있는지 확인
            // 나눠 줄 수 있으면 -> 더 키우기
            if (count >= k) {
                result = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println(result);
    }
}
