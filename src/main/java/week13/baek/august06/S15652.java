package week13.baek.august06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S15652 {
    static int n, m;
    static int[] result;
    static StringBuilder sb = new StringBuilder();

    public static void dfs(int depth, int now) {
        if (depth == m) {
            for (int i = 0; i < m; i++) {
                sb.append(result[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = now; i <= n; i++) {
            result[depth] = i;
            dfs(depth + 1, i);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        result = new int[m];
        for (int i = 0; i < n; i++) {
            result[0] = i+1;
            dfs(1, i+1);
        }

        System.out.print(sb.toString());
    }
}
