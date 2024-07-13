package week10.baek.july9;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class S1260 {
    static boolean[] visited;
    static BufferedWriter bw;

    public static void dfs(int V, Map<Integer, List<Integer>> list) throws IOException {
        visited[V] = true;
        bw.write(V + " ");

        List<Integer> al = list.get(V);
        for (int i = 0; i < al.size(); i++) {
            if (!visited[al.get(i)]) {
                dfs(al.get(i), list);
            }
        }
    }

    public static void bfs(int V, Map<Integer, List<Integer>> list) throws IOException {
        Queue<Integer> q = new LinkedList<>();
        q.add(V);
        visited[V] = true;

        while (!q.isEmpty()) {
            int n = q.poll();
            bw.write(n + " ");

            List<Integer> al = list.get(n);
            for (int i = 0; i < al.size(); i++) {
                if (!visited[al.get(i)]) {
                    visited[al.get(i)] = true;
                    q.add(al.get(i));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());

        Map<Integer, List<Integer>> list = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            list.put(i, new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            list.get(s).add(e);
            list.get(e).add(s);
        }

        // 정점 리스트 정렬
        for (int key : list.keySet()) {
            Collections.sort(list.get(key));
        }

        visited = new boolean[N + 1];
        dfs(V, list);
        bw.newLine();

        visited = new boolean[N + 1];
        bfs(V, list);

        bw.flush();
        bw.close();
        br.close();
    }
}
