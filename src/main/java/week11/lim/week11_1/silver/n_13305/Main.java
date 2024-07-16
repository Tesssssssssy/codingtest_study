package week11.lim.week11_1.silver.n_13305;

// 문제: https://www.acmicpc.net/problem/13305

import java.io.*;

public class Main {
    /**
     *  1. N개의 도시 / 도시들은 일직선 도로 위에 있다.
     *  2. 왼쪽 -> 오른쪽 도시로 이동
     *  3. 인접한 두 도시 사이의 도로들은 길이가 다를 수 있다.
     *  4. 처음에는 기름을 넣고 출발해야 함.
     *  5. 1km 마다 1L 기름 사용
     *  6. 도시 마다 주유소의 리터 당 가격 다를 수 있다.
     *  7. 각 도시에 있는 주유소의 기름 가격과, 각 도시를 연결하는 도로의 길이를 입력으로 받아
     *     제일 왼쪽 도시에서 제일 오른쪽 도시로 이동하는 최소의 비용 출력
     *
     *  [입력]
     *  4        - 도시의 개수 N
     *  2 3 1    - 도로의 길이 (왼쪽 도로부터)
     *  5 2 4 1  - 주유소의 리터당 가격 (왼쪽 도시부터)
     *
     *  |5| - 2 - |2| - 3 - |4| - 1 - |1|
     *
     *  [풀이]
     *  최소 비용인데 이동하면서 최선의 선택을 하면서 가는 거니까 그리디로 풀자.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 도시의 개수 읽기
        String[] roadLengths = br.readLine().split(" "); // 각 도로의 길이
        String[] fuelCosts = br.readLine().split(" "); // 각 도시의 연료 가격

        long minCost = 0; // 최소 비용을 저장할 변수
        long minPrice = Long.MAX_VALUE; // 발견된 최소 가격

        // 제일 왼쪽 도시에서 제일 오른쪽 도시까지 이동
        for (int i = 0; i < N - 1; i++) {
            long currentPrice = Long.parseLong(fuelCosts[i]); // 현재 도시의 연료 가격
            int distanceToNextCity = Integer.parseInt(roadLengths[i]); // 현재 도시에서 다음 도시까지의 거리

            // 현재 도시의 연료 가격이 지금까지 발견된 최소 가격보다 낮다면, 갱신
            if (currentPrice < minPrice) {
                minPrice = currentPrice;
            }

            // 최소 가격으로 다음 도시까지 필요한 연료를 계산하여 비용을 더함
            minCost += minPrice * distanceToNextCity;
        }

        System.out.println(minCost);
    }
}
