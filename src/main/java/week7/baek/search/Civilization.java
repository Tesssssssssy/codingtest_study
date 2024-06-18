package week7.baek.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Civilization {

    // 방향 배열 (상, 우, 하, 좌)
    static final int[][] d = { { -1, 0, 1, 0 }, { 0, 1, 0, -1 } };

    static StringTokenizer st = null;
    static int[][] board;
    static int[] parents;
    static Queue<int[]> q = new ArrayDeque<>();
    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫 번째 줄에서 N (맵 크기)와 K (문명의 수) 입력 받기
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 맵과 문명의 부모 배열 초기화
        board = new int[N + 1][N + 1];
        parents = new int[K + 1];
        MakeSet();  // 유니온-파인드를 위한 부모 배열 초기화

        // 각 문명의 위치 입력 받기
        for (int i = 1; i <= K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            q.add(new int[] { x, y });  // 큐에 위치 추가
            board[x][y] = i;  // 문명 번호로 맵 표시
        }

        // BFS를 통해 모든 문명을 하나로 통합하는 최소 시간을 계산
        int ans = bfs();
        System.out.println(ans);  // 결과 출력
    }

    // BFS를 이용하여 문명을 확장하며 통합하는 함수
    private static int bfs() {
        int depth = 0;
        int cnt = 1;  // 문명 통합 횟수

        while (true) {
            int size = q.size();

            while (size-- > 0) {
                int[] cur = q.poll();

                for (int i = 0; i < 4; i++) {
                    int nx = cur[0] + d[0][i];
                    int ny = cur[1] + d[1][i];

                    // 맵 경계를 벗어나거나 같은 문명끼리는 무시
                    if (IsOutBound(nx, ny) || board[cur[0]][cur[1]] == board[nx][ny]) {
                        continue;
                    }

                    // 다른 문명일 경우 통합 시도
                    if (board[nx][ny] != 0) {
                        if (Union(board[cur[0]][cur[1]], board[nx][ny])) {
                            cnt++;
                        }
                        continue;
                    }
                }
                q.add(cur);  // 현재 위치를 큐에 다시 추가
            }

            if (cnt == K) {  // 모든 문명이 통합되면 종료
                break;
            }
            depth++;  // 시간 증가

            size = q.size();
            while (size-- > 0) {
                int[] cur = q.poll();

                for (int i = 0; i < 4; i++) {
                    int nx = cur[0] + d[0][i];
                    int ny = cur[1] + d[1][i];

                    // 맵 경계를 벗어나거나 같은 문명끼리는 무시
                    if (IsOutBound(nx, ny) || board[cur[0]][cur[1]] == board[nx][ny]) {
                        continue;
                    }

                    // 다른 문명일 경우 통합 시도
                    if (board[nx][ny] != 0) {
                        if (Union(board[cur[0]][cur[1]], board[nx][ny])) {
                            cnt++;
                        }
                        continue;
                    }

                    // 문명 확장
                    board[nx][ny] = board[cur[0]][cur[1]];
                    q.add(new int[] { nx, ny });
                }
            }

            if (cnt == K) {  // 모든 문명이 통합되면 종료
                break;
            }
        }

        return depth;  // 모든 문명이 통합되는 데 걸린 시간 반환
    }

    // 디버깅을 위해 맵을 출력하는 함수
    private static void PrintForDebug(int depth) {
        System.out.println("---------" + depth + "------------");
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    // 유니온-파인드를 위한 초기화 함수
    private static void MakeSet() {
        for (int i = 1; i <= K; i++) {
            parents[i] = i;
        }
    }

    // 유니온-파인드의 Find 함수
    private static int FindSet(int a) {
        if (parents[a] == a) {
            return a;
        }

        return parents[a] = FindSet(parents[a]);
    }

    // 유니온-파인드의 Union 함수
    private static boolean Union(int a, int b) {
        int aRoot = FindSet(a);
        int bRoot = FindSet(b);

        if (aRoot == bRoot) {
            return false;
        }
        parents[bRoot] = aRoot;
        return true;
    }

    // 주어진 좌표가 맵의 경계를 벗어나는지 확인하는 함수
    private static boolean IsOutBound(int x, int y) {
        return x <= 0 || x > N || y <= 0 || y > N;
    }
}
