package week8.lim.n_14652;

// 문제: https://www.acmicpc.net/problem/14652

import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] arrStr = br.readLine().split(" ");
        int[] arr = new int[arrStr.length];

        for (int i = 0; i < arrStr.length; i++) {
            arr[i] = Integer.parseInt(arrStr[i]);
        }

        int N = arr[0];
        int M = arr[1];
        int ticketNum = arr[2];
        int cnt = 0;

        // 첫 칸부터 좌석 번호가 하나씩 늘어나는 걸 cnt로 찾기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (ticketNum == cnt) {
                    System.out.println(i + " " + j);
                }
                cnt++;
            }
        }
    }
}