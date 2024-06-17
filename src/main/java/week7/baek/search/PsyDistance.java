package week7.baek.search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class PsyDistance {
    static List<List<Integer>> bridge;

    public static int[] bfs(int start, int n){
        int[] distances = new int[n];
        Arrays.fill(distances, -1);  // -1로 초기화 (경로가 없음을 나타냄)
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(start);
        distances[start] = 0;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int neighbor : bridge.get(current)) {
                if (distances[neighbor] == -1) {  // 방문하지 않은 노드만
                    distances[neighbor] = distances[current] + 1;
                    queue.offer(neighbor);
                }
            }
        }

        return distances;

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        int N = Integer.parseInt(input.split(" ")[0]);
        int M = Integer.parseInt(input.split(" ")[1]);
        int K = Integer.parseInt(input.split(" ")[2]);

        //인접 리스트로 그래프 표현 - 방향 있음
        bridge = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            bridge.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            input = br.readLine();
            int from = Integer.parseInt(input.split(" ")[0]);
            int to = Integer.parseInt(input.split(" ")[1]);
//            System.out.println("bridge = " + bridge[i][0] + " " + bridge[i][1]);
            bridge.get(from - 1).add(to - 1);
        }

        int[] psyDistance = new int[N];
        for (int i = 0; i < N; i++) {
            psyDistance = bfs(K - 1, N);
        }

        for (int i = 0; i < N; i++) {
            psyDistance[i] += Math.abs(i - (K-1));
        }

        int maxDistance = Integer.MAX_VALUE;
        int index = 0;
        for (int i = 0; i < N; i++) {
            if(maxDistance < psyDistance[i]){
                maxDistance = psyDistance[i];
                index = i;
            }
        }

        System.out.println(index+1);
    }
}
