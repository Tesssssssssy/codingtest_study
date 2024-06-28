package week8.lim.week8_2.n_2941;

// 문제: https://www.acmicpc.net/problem/2941

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        String[] croatianAlphabets = {"dz=", "c=", "c-", "d-", "lj", "nj", "s=", "z="};

        for (String alphabet : croatianAlphabets) {
            input = input.replace(alphabet, "1");
        }

        System.out.println(input.length());
    }
}
