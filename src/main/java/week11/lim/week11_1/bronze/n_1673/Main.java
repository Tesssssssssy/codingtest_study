package week11.lim.week11_1.bronze.n_1673;

// 문제: https://www.acmicpc.net/problem/1673

import java.io.*;

public class Main {
    /**
     *  1. 치킨 한 마리 주문할 수 있는 치킨 쿠폰 n장
     *  2. 한 마리 주문할 때마다 도장을 찍어주는데
     *     도장을 k개 모으면 치킨 쿠폰 한 장으로 교환 가능
     *  3. 지금 갖고 있는 치킨 쿠폰으로 치킨을 최대 몇 마리나 먹을 수 있는지 출력
     *
     *  [풀이]
     *  그리디 알고리즘
     *
     *  [예제]
     *  10 3
     *  10장의 쿠폰으로 10마리 구매
     *  -> 10장의 도장 생김
     *  도장 기존 3 + 10 = 13개
     *
     *  -> 도장 3개부터 쿠폰 1개로 교환 가능하므로
     *  10 / 3 = 3
     *  -> 10마리 + 쿠폰 3장(3마리) = 13마리 / 도장 1개
     *  -> 3마리를 또 구매했으므로 쿠폰 3개 추가
     *  -> 도장 1개 + 3개 = 4개
     *  -> 4 / 3 = 1이므로 1마리 추가 구매 가능 (쿠폰 1개 생성)
     *
     *  => 13마리 + 1마리 = 14마리
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;

        while ((line = br.readLine()) != null) {
            if (line.isEmpty())
                break; // 입력이 끝났다면 종료

            String[] input = line.split(" ");
            int n = Integer.parseInt(input[0]);
            int k = Integer.parseInt(input[1]);

            int totalChickens = 0;
            int stamps = 0;
            int coupons = n;

            while (coupons > 0) {
                // 치킨 구매
                totalChickens += coupons;
                stamps += coupons; // 치킨을 살 때마다 도장을 얻음
                coupons = 0; // 사용한 쿠폰은 0으로

                // 도장을 쿠폰으로 교환
                coupons += stamps / k;
                stamps = stamps % k; // 교환 후 남은 도장
            }

            System.out.println(totalChickens);
        }
    }
}
