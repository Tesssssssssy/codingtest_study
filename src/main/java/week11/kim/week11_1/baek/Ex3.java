package week11.kim.week11_1.baek;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [유기농 배추]
 *
 * 차세대 영농인 한나는 강원도 고랭지에서 유기농 배추를 재배하기로 하였다. 농약을 쓰지 않고 배추를 재배하려면 배추를 해충으로부터 보호하는 것이 중요하기 때문에, 한나는 해충 방지에 효과적인 배추흰지렁이를 구입하기로 결심한다. 이 지렁이는 배추근처에 서식하며 해충을 잡아 먹음으로써 배추를 보호한다. 특히, 어떤 배추에 배추흰지렁이가 한 마리라도 살고 있으면 이 지렁이는 인접한 다른 배추로 이동할 수 있어, 그 배추들 역시 해충으로부터 보호받을 수 있다. 한 배추의 상하좌우 네 방향에 다른 배추가 위치한 경우에 서로 인접해있는 것이다.
 *
 * 한나가 배추를 재배하는 땅은 고르지 못해서 배추를 군데군데 심어 놓았다. 배추들이 모여있는 곳에는 배추흰지렁이가 한 마리만 있으면 되므로 서로 인접해있는 배추들이 몇 군데에 퍼져있는지 조사하면 총 몇 마리의 지렁이가 필요한지 알 수 있다. 예를 들어 배추밭이 아래와 같이 구성되어 있으면 최소 5마리의 배추흰지렁이가 필요하다. 0은 배추가 심어져 있지 않은 땅이고, 1은 배추가 심어져 있는 땅을 나타낸다.
 *
 * 1	1	0	0	0	0	0	0	0	0
 * 0	1	0	0	0	0	0	0	0	0
 * 0	0	0	0	1	0	0	0	0	0
 * 0	0	0	0	1	0	0	0	0	0
 * 0	0	1	1	0	0	0	1	1	1
 * 0	0	0	0	1	0	0	1	1	1
 *
 *
 * 입력
 * 입력의 첫 줄에는 테스트 케이스의 개수 T가 주어진다. 그 다음 줄부터 각각의 테스트 케이스에 대해 첫째 줄에는 배추를 심은 배추밭의 가로길이 M(1 ≤ M ≤ 50)과 세로길이 N(1 ≤ N ≤ 50), 그리고 배추가 심어져 있는 위치의 개수 K(1 ≤ K ≤ 2500)이 주어진다. 그 다음 K줄에는 배추의 위치 X(0 ≤ X ≤ M-1), Y(0 ≤ Y ≤ N-1)가 주어진다. 두 배추의 위치가 같은 경우는 없다.
 *
 *
 * 출력
 * 각 테스트 케이스에 대해 필요한 최소의 배추흰지렁이 마리 수를 출력한다.
 *
 *
 * 예제 입력 1
 * 2
 * 10 8 17
 * 0 0
 * 1 0
 * 1 1
 * 4 2
 * 4 3
 * 4 5
 * 2 4
 * 3 4
 * 7 4
 * 8 4
 * 9 4
 * 7 5
 * 8 5
 * 9 5
 * 7 6
 * 8 6
 * 9 6
 * 10 10 1
 * 5 5
 *
 * 예제 출력 1
 * 5
 * 1
 *
 * 예제 입력 2
 * 1
 * 5 3 6
 * 0 2
 * 1 2
 * 2 2
 * 3 2
 * 4 2
 * 4 0
 *
 * 예제 출력 2
 * 2
*/

public class Ex3 {
    // 배추밭을 나타내는 2차원 배열
    int[][] field;
    // 방문 여부를 나타내는 2차원 배열
    boolean[][] visited;
    // 배추밭의 가로 길이와 세로 길이
    int M, N;
    // 상하좌우 이동을 위한 배열
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        new Ex3().solve();
    }

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            // 배추밭의 가로 길이
            M = Integer.parseInt(st.nextToken());
            // 배추밭의 세로 길이
            N = Integer.parseInt(st.nextToken());
            // 배추가 심어진 위치의 개수
            int K = Integer.parseInt(st.nextToken());

            // 배추밭과 방문 여부 배열 초기화
            field = new int[N][M];
            visited = new boolean[N][M];

            // 배추의 위치 입력 받기
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());
                // 배추가 심어진 위치를 1로 표시
                field[Y][X] = 1;
            }

            // 필요한 배추흰지렁이 마리 수
            int count = 0;

            // 배추밭을 순회하면서 배추가 있는 위치를 찾음
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    // 배추가 있고 방문하지 않은 위치를 찾음
                    if (field[i][j] == 1 && !visited[i][j]) {
                        // DFS를 통해 인접한 배추들을 모두 방문
                        dfs(i, j);
                        // 배추흰지렁이 마리 수 증가
                        count++;
                    }
                }
            }

            // 결과 출력
            System.out.println(count);
        }
    }

    // 깊이 우선 탐색(DFS)
    void dfs(int x, int y) {
        // 현재 위치를 방문 처리
        visited[x][y] = true;

        // 상하좌우로 이동하면서 인접한 배추를 찾음
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 이동한 위치가 배추밭 범위 내에 있고
            // 배추가 있으며 방문하지 않은 경우
            if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
                if (field[nx][ny] == 1 && !visited[nx][ny]) {
                    // 재귀적으로 DFS 호출
                    dfs(nx, ny);
                }
            }
        }
    }
}