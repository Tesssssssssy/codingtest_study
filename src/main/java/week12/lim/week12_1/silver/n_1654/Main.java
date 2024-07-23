package week12.lim.week12_1.silver.n_1654;

// 문제: https://www.acmicpc.net/problem/1654

import java.io.*;

public class Main {
    /**
     *  1. N개의 랜선을 만들어야 함.
     *  2. K개의 랜선을 갖고 있는데 길이가 제각각이다.
     *  3. 랜선을 모두 N개의 같은 길이의 랜선으로 만들고 싶었기에
     *     K개의 랜선을 잘라서 만들어야 한다.
     *  4. ex.) 300cm -> 140cm 랜선 2개를 잘라내면 20cm는 버려야 함.
     *  5. 기존의 K개의 랜선으로 N개의 랜선을 만드는 경우는 없다.
     *  6. 정수 길이만큼 자를 수 있다.
     *  7. N개 보다 많이 만드는 것도 N개를 만드는 것에 포함.
     *  8. N개를 만들 수 있는 랜선의 최대 길이를 cm 단위의 정수로 출력.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] firstLine = br.readLine().split(" ");
        int K = Integer.parseInt(firstLine[0]); // 이미 갖고 있는 랜선 개수
        int N = Integer.parseInt(firstLine[1]); // 필요한 랜선의 개수

        long[] LANs = new long[K];
        long longestLan = 0;

        for (int i = 0; i < K; i++) {
            LANs[i] = Long.parseLong(br.readLine());
            longestLan = Math.max(longestLan, LANs[i]);
        }

        /*
            문제의 조건에 따라 long 자료형으로 하지 않으면 제출시 틀림!
                - K는 1이상 10,000이하의 정수이고, N은 1이상 1,000,000이하의 정수이다.
                - 랜선의 길이는 231-1보다 작거나 같은 자연수이다.
        */
        long left = 1;
        long right = longestLan;
        long answer = 0;
        while (left <= right) {
            long mid = (left + right) / 2;
            long count = 0;

            for (long LAN : LANs) {
                count += LAN / mid;
            }

            if (count >= N) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(answer);
    }
}
