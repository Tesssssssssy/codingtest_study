package week10.lim.week10_1.gold.n_11729;

// 문제: https://www.acmicpc.net/problem/11729

import java.io.*;

public class Main {
    /**
     *  1. 3개의 장대가 있고 첫 번쨰 장대에는 반경이 서로 다른 n개의 원판이 있다.
     *  2. 각 원판은 반경이 큰 순서대로 쌓여있다.
     *  3. 첫 번째 -> 세 번째 장대로 옮긴다.
     *  4. 한번에 한 개의 원판만 다른 탑으로 옮길 수 있다.
     *  5. 쌓아 놓은 원판은 항상 위의 것이 아래의 것보다 작아야 한다.
     *  6. 필요한 이동 순서를 출력 (최소 이동 횟수)
     *
     *  [하노이탑 공식]
     *   하노이탑의 가장 큰 규칙: "작은 원판 위에 큰 원판은 올 수 없다"
     *
     *   가장 큰 원판을 C 로 옮기기 위해서는 n-1 개의 원판이 A 에서 B 로 가야한다.
     *   그리고 A 에 있는 가장 큰 원판이 C 로 이동. (1회)
     *   B 에 있는 (n-1)개의 원판을 C 로 이동.
     *
     *   Hanoi(n) = 2 × Hanoi(n-1) + 1
     *   (n 개의 원판을 이동시키기 위해 Hanoi(n-1) 횟수만큼 이동한 횟수가 2번이고,
     *    가장 아래 원판은 1번 이동)
     *
     *    [시간 초과]
     *    System.out.println을 사용하니까 시간 초과 에러
     *    -> StringBuilder 혹은 BufferWriter 사용해야 함.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());  // 원판의 수 입력 받기
        bw.write((int) (Math.pow(2, N) - 1) + "\n");  // 최소 이동 횟수는 2^N - 1

        move(N, 1, 3, 2, bw);  // 원판 N개를 1번 장대에서 3번 장대로 이동
        br.close();
        bw.flush();  // 버퍼에 남은 데이터 출력
        bw.close();
    }

    /**
     * 하노이 탑 재귀 함수
     *
     * @param n      현재 원판의 개수
     * @param start  시작 장대
     * @param end    목표 장대
     * @param mid    보조 장대
     * @param bw     BufferedWriter 객체
     */
    private static void move(int n, int start, int end, int mid, BufferedWriter bw) throws IOException {
        if (n == 1) {
            bw.write(start + " " + end + "\n");
        } else {
            move(n - 1, start, mid, end, bw);
            bw.write(start + " " + end + "\n");
            move(n - 1, mid, end, start, bw);
        }
    }
}