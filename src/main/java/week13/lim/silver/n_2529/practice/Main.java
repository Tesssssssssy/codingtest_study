package week13.lim.silver.n_2529.practice;

// 문제: https://www.acmicpc.net/problem/2529

import java.io.*;
import java.util.*;

public class Main {
    static int k;
    static ArrayList<String> results = new ArrayList<>();
    static boolean[] used = new boolean[10];
    static char[] signs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        signs = br.readLine().replace(" ", "").toCharArray();

        backtrack("", 0);

        Collections.sort(results);
        System.out.println(results.get(results.size() - 1));
        System.out.println(results.get(0));
    }

    private static void backtrack(String current, int depth) {
        if (depth == k + 1) {
            results.add(current);
            return;
        }

        for (int i = 0; i <= 9; i++) {
            if (!used[i]) {
                if (depth == 0 || checkCondition(current.charAt(depth - 1) - '0', i, signs[depth - 1])) {
                    used[i] = true;
                    backtrack(current + i, depth + 1);
                    used[i] = false;
                }
            }
        }
    }

    private static boolean checkCondition(int previous, int current, char sign) {
        if (sign == '<' && previous < current) {
            return true;
        } else if (sign == '>' && previous > current) {
            return true;
        }
        return false;
    }
}