package week8.lim.n_5073;

// 문제: https://www.acmicpc.net/problem/5073

import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            StringTokenizer token = new StringTokenizer(br.readLine());

            int[] array = {Integer.parseInt(token.nextToken()), Integer.parseInt(token.nextToken()), Integer.parseInt(token.nextToken())};

            if (array[0] == 0 && array[1] == 0 && array[2] == 0) {
                break;
            }

            Arrays.sort(array); // 작은 수부터 큰 수로 정렬

            if (array[2] >= array[0] + array[1]) { // 가장 긴변의 길이가 나머지 두변 길이의 합보단 작아야 함.
                bw.write("Invalid\n");
            } else {
                int[] disArray = Arrays.stream(array).distinct().toArray();
                if (disArray.length == 1) {
                    bw.write("Equilateral\n");
                } else if (disArray.length == 2) {
                    bw.write("Isosceles\n");
                } else if (disArray.length == 3) {
                    bw.write("Scalene\n");
                }
            }
        }
        br.close();
        bw.flush();
        bw.close();
    }
}