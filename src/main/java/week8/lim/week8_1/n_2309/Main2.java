package week8.lim.week8_1.n_2309;

import java.io.*;
import java.util.*;

public class Main2 {
    static int[] dorfs;
    static boolean[] visited;
    static boolean found = false;

    public static void dfs(int idx, int depth, int sum, String path) {
        if (found) return; // 여기서 바로 탐색 끝내면..?
        if (depth == 7) {
            if (sum == 100) {
                String[] s = path.trim().split(" ");
                int[] result = new int[s.length];
                for (int i = 0; i < s.length; i++) {
                    result[i] = Integer.parseInt(s[i]);
                }
                Arrays.sort(result);
                for (int j = 0; j < 7; j++) {
                    System.out.println(result[j]);
                }
                found = true; // 찾았으니까 찾았다 표시
            }
            return;
        }

        for (int i = idx; i < 9; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(i + 1, depth + 1, sum + dorfs[i], path + dorfs[i] + " ");
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        dorfs = new int[9];
        visited = new boolean[9];

        for (int i = 0; i < 9; i++) {
            dorfs[i] = Integer.parseInt(br.readLine().trim());
        }

        dfs(0, 0, 0, "");

        br.close();
    }
}
