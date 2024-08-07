package week13.baek.august06.baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S14889 {
    static int[][] arr;
    static int n;
    static int minDifference = Integer.MAX_VALUE;
    static boolean[] visited;

    // DFS를 통해 팀 조합을 생성
    public static void dfs(int count, int start) {
        if (count == n / 2) {
            calculateDifference();
            return;
        }

        for (int i = start; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(count + 1, i + 1);
                visited[i] = false; // Backtracking
            }
        }
    }

    // 팀 간의 능력치 차이를 계산
    public static void calculateDifference() {
        int startTeamAbility = 0;
        int linkTeamAbility = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (visited[i] && visited[j]) {
                    startTeamAbility += arr[i][j] + arr[j][i];
                } else if (!visited[i] && !visited[j]) {
                    linkTeamAbility += arr[i][j] + arr[j][i];
                }
            }
        }

        int difference = Math.abs(startTeamAbility - linkTeamAbility);
        if (difference < minDifference) {
            minDifference = difference;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        arr = new int[n][n];
        visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // DFS를 통해 모든 팀 조합을 시도
        dfs(0, 0);

        System.out.println(minDifference);
    }
}
