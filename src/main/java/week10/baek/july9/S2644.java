package week10.baek.july9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class S2644 {
    static int[][] graph;
    static boolean[] visited;
    static int result = -1;

    public static void dfs(int b, int count, int n){
        visited[n] = true;

        if(n == b) {
            result = count;
            return;
        }

        for (int i = 1; i< graph.length; i++) {
            if(!visited[i] && graph[n][i] == 1){
                dfs( b, count+1, i);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int m = Integer.parseInt(br.readLine());

        graph = new int[n+1][n+1];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            graph[x][y] = 1;
            graph[y][x] = 1;
        }

        visited = new boolean[n+1];
        dfs(b,0, a);

        System.out.println(result);
    }
}
