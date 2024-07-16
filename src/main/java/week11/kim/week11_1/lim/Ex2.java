package week11.kim.week11_1.lim;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [음식물 피하기]
 *
 * 코레스코 콘도미니엄 8층은 학생들이 3끼의 식사를 해결하는 공간이다. 그러나 몇몇 비양심적인 학생들의 만행으로 음식물이 통로 중간 중간에 떨어져 있다. 이러한 음식물들은 근처에 있는 것끼리 뭉치게 돼서 큰 음식물 쓰레기가 된다.
 *
 * 이 문제를 출제한 선생님은 개인적으로 이러한 음식물을 실내화에 묻히는 것을 정말 진정으로 싫어한다. 참고로 우리가 구해야 할 답은 이 문제를 낸 조교를 맞추는 것이 아니다.
 *
 * 통로에 떨어진 음식물을 피해가기란 쉬운 일이 아니다. 따라서 선생님은 떨어진 음식물 중에 제일 큰 음식물만은 피해 가려고 한다.
 *
 * 선생님을 도와 제일 큰 음식물의 크기를 구해서 “10ra"를 외치지 않게 도와주자.
 *
 *
 * 입력
 * 첫째 줄에 통로의 세로 길이 N(1 ≤ N ≤ 100)과 가로 길이 M(1 ≤ M ≤ 100) 그리고 음식물 쓰레기의 개수 K(1 ≤ K ≤ N×M)이 주어진다.  그리고 다음 K개의 줄에 음식물이 떨어진 좌표 (r, c)가 주어진다.
 *
 * 좌표 (r, c)의 r은 위에서부터, c는 왼쪽에서부터가 기준이다. 입력으로 주어지는 좌표는 중복되지 않는다.
 *
 *
 * 출력
 * 첫째 줄에 음식물 중 가장 큰 음식물의 크기를 출력하라.
 *
 *
 * 예제 입력 1
 * 3 4 5
 * 3 2
 * 2 2
 * 3 1
 * 2 3
 * 1 1
 *
 * 예제 출력 1
 * 4
 *
 *
 * 힌트
 * 	# . . .
 * 	. # # .
 * 	# # . .
 * 위와 같이 음식물이 떨어져있고 제일큰 음식물의 크기는 4가 된다. (인접한 것은 붙어서 크게 된다고 나와 있음. 대각선으로는 음식물 끼리 붙을수 없고 상하좌우로만 붙을수 있다.)
*/


// 10_2 백송연 3
public class Ex2 {
    private int[] dx = {-1, 0, 1, 0};
    private int[] dy = {0, 1, 0, -1};

    private int[][] arr;
    private boolean[][] visited;
    private int N, M, K, max = 0;

    public static void main(String[] args) throws Exception {
        Ex2 solution = new Ex2();
        solution.solve();
    }

    public void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N+1][M+1];
        visited = new boolean[N+1][M+1];

        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            // 음식물이 떨어진 위치 표시
            arr[x][y] = 1;
        }

        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= M; j++) {
                if(arr[i][j] == 1 && !visited[i][j]) {
                    // DFS를 통해 가장 큰 음식물 덩어리의 크기를 찾음
                    max = Math.max(max, dfs(i, j));
                }
            }
        }

        System.out.println(max);
    }

    private int dfs(int x, int y) {
        visited[x][y] = true;
        // 현재 노드를 포함하여 카운트 시작
        int count = 1;

        // 상하좌우 탐색
        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx > 0 && ny > 0 && nx <= N && ny <= M) {
                if(arr[nx][ny] == 1 && !visited[nx][ny]) {
                    // 인접한 음식물이 있으면 재귀적으로 탐색하며 카운트 증가
                    count += dfs(nx, ny);
                }
            }
        }

        // 음식물 덩어리의 크기 반환
        return count;
    }
}