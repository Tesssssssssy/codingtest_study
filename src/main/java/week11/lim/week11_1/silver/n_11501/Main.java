package week11.lim.week11_1.silver.n_11501;

// 문제: https://www.acmicpc.net/problem/11501

import java.io.*;

public class Main {
    /**
     *  1. 주식 하나를 산다.
     *  2. 원하는 만큼 가지고 있는 주식을 판다.
     *  3. 아무것도 안한다.
     *  4. 날짜 별로 주식의 가격을 알 떄, 최대 이익이 얼마가 되는지 계산 후 출력
     *
     *  [예제]
     *  3일이고 날짜별로 주가가 10, 7, 6일 때
     *  주가가 계속 감소하므로 최대 이익은 0이 됨.
     *
     *  3일이고 날짜별로 주기가 3, 5, 9일 때
     *  처음 두 날에 주식을 하나씩 사고, 마지막날 다 팔면 이익이 10이 됨.
     *
     *  [주의]
     *  날짜가 1,000,000일이 있고
     *  모든 날에 주식을 1원에 사서 마지막 날에 10,000원에 팔 수 있다면
     *  이익은 각 날짜마다 9,999원이고 전체 이익은 9,999 x 1,000,000 = 9,999,000,000 원이 된다.
     *  -> 이렇게 큰 수를 사용하려면 32bit int 타입의 최대 범위인 약 21억을 넘는다
     *  => 64bit long 타입을 사용해야 조건 충족 가능.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            int days = Integer.parseInt(br.readLine()); // 날짜 수
            String[] int_stockPrices = br.readLine().split(" ");

            long[] long_stockPrice = new long[days];
            for (int i = 0; i < days; i++) {
                long_stockPrice[i] = Long.parseLong(int_stockPrices[i]);
            }

            long maxProfit = 0;
            long maxFuturePrice = 0;

            /*
                뒤에서부터 최대 가격을 찾아가면서 이익 계산
                미래의 주가를 알고 있기 때문에 뒤에부터 시작하면 각 날자에 대해 그 이후의 최대 주가를 쉽게 파악할 수 있다.
                일종의 DP 풀이 방식
                각 날짜에 대한 최적의 결정이 그 이전의 날짜에 대한 최적의 결정에 영향을 미치는 방식으로 문제 해결.
            */
            for (int i = days - 1; i >= 0; i--) {
                if (long_stockPrice[i] > maxFuturePrice) {
                    maxFuturePrice = long_stockPrice[i];
                }
                maxProfit += maxFuturePrice - long_stockPrice[i];
            }

            sb.append(maxProfit).append("\n");
        }

        System.out.print(sb);
    }
}
