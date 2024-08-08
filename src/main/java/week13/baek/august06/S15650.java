package week13.baek.august06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S15650 {
    static int n, m;
    static boolean[] visited;
    static int[] result;

    public static void dfs(int depth, int now){
        if(depth == m){
            for (int i = 0; i < m; i++) {
                System.out.print(result[i] + " ");
            }
            System.out.println();
            return;
        }

        for (int i = now; i < n; i++) {
            if(!visited[i]){
                visited[i] = true;
                result[depth] = i+1;
                dfs(depth+1, i+1);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        visited = new boolean[n];
        result = new int[m];
        dfs(0, 0);
    }
}
