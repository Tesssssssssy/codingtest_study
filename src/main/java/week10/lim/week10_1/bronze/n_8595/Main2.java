package week10.lim.week10_1.bronze.n_8595;

// 문제: https://www.acmicpc.net/problem/8595

import java.io.*;
import java.util.*;

public class Main2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();

        long ans = 0;

        StringTokenizer st = new StringTokenizer(br.readLine().replaceAll("[a-zA-Z]", " "));
        // 문자들은 모두 공백으로 대체

        while (st.hasMoreTokens()) {
            // 남아있는 숫자들의 합을 구한다.
            ans += Integer.parseInt(st.nextToken());
        }

        System.out.println(ans);
    }
}
