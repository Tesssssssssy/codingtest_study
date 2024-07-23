package week12.lim.week12_1.silver.n_13702;

import java.io.*;

public class Main {
    /**
     *  1. 막걸리를 시키면 주전자의 용량은 똑같으나 안에 있는 막걸리 용량은 랜덤.
     *  2. 막걸리 N 주전자를 주문하고, 자신을 포함한 K 명에게 똑같은 양으로 나누어준다.
     *  3. 분배 후 주전자에 막걸리가 조금 남아있다면 그냥 버린다.
     *     (한 번 주문한 막걸리에 남은 걸 모아서 다시 주는 경우 x)
     *  4. ex.)
     *     5명이 3 주전자를 주문해 1002, 802, 705가 담겨져 나왔고
     *     이를 401로 동일하게 나눴을 경우
     *     각 주전자에서 200, 0, 304 만큼 남는데, 버린다.
     *  5. 이 때, K 명에게 최대한 많은 양의 막걸리르 분배할 수 있는 용량 출력
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] firstLine = br.readLine().split(" ");
        int N = Integer.parseInt(firstLine[0]); // 주전자 개수
        int K = Integer.parseInt(firstLine[1]); // 친구 수

        long[] makgeolliList = new long[N];
        long maxMakgeolli = 0;

        for (int i = 0; i < N; i++) {
            makgeolliList[i] = Long.parseLong(br.readLine());
            maxMakgeolli = Math.max(maxMakgeolli, makgeolliList[i]);
        }

        long left = 1;
        long right = maxMakgeolli;
        long result = 0;

        while (left <= right) {
            long mid = (left + right) / 2;
            int count = 0;

            for (long makgeolli : makgeolliList) {
                count += makgeolli / mid;
            }

            if (count >= K) {
                result = mid; // 가능하면 결과 갱신
                left = mid + 1; // 더 큰 값을 찾기 위해 left 갱신
            } else {
                right = mid - 1; // mid 값이 너무 크면 줄임
            }
        }

        System.out.println(result);
        br.close();
    }
}
/*
        while (left < right) {
            int mid = (left + right) / 2;
            int sum = 0;

            for (int makgeolli : makgeolliList) {
                if (makgeolli % mid == 0)
                    sum += makgeolli / mid;
                else
                    sum += makgeolli / mid + 1;
            }

            if (sum > K)
                right = mid;
            else
                left = mid + 1;
        }

        무한 루프 방지
            while (left < right) 루프 내에서 mid 값을 (left + right) / 2로 설정할 경우,
            left와 right가 1 차이나는 순간, mid는 항상 left와 같게 된다.
            이 경우, if (sum > K) 조건이 만족하지 않으면 left = mid + 1로 설정되어 left가 right보다 커지면서 루프가 종료된다.
            그러나 만약 sum > K가 계속 만족되면 right = mid를 설정하게 되고,
            right와 left의 차이가 줄어들지 않아 left가 right보다 작은 상태를 유지할 수 있다.
            이로 인해 무한 루프에 빠질 위험이 있다.
*/