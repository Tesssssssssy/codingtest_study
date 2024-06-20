package week7.lim.groom_search.civilization;

// 문제: https://level.goorm.io/exam/48145/%EB%AC%B8%EB%AA%85/quiz/1

import java.util.*;
import java.io.*;

// 테스트케이스 2개 통과 x
public class Civilization1 {
    /**
     * 문제를 효과적으로 해결하기 위해서는 BFS (Breadth-First Search, 너비 우선 탐색) 방식과 Union-Find 자료구조를 사용할 수 있다.
     * 문명의 확장은 BFS로 처리하고, 서로 다른 문명의 결합 여부는 Union-Find를 사용하여 관리하는 것이 핵심.
     *
     * 초기화: 문명 발상지를 기반으로 시작 위치를 설정하고, 인접한 발상지를 하나의 그룹으로 통합.
     * 문명 확장: BFS를 사용하여 문명을 확장하면서 주변에 다른 문명이 있는지 검사.
     * 문명 결합: 다른 문명이 인접해 있을 경우, Union-Find 자료구조를 사용하여 두 문명을 통합하고, 통합된 문명의 그룹을 업데이트.
     * 종료 조건: 모든 문명이 하나의 그룹으로 통합될 때까지 위의 과정을 반복.
     */
    static int N, K; // N: 세계의 크기, K: 문명 발상지의 수
    static int[] parent; // Union-Find 자료구조를 위한 배열
    static int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 동, 남, 서, 북 방향
    static Queue<int[]> queue = new LinkedList<>(); // 문명 확장을 처리하기 위한 큐

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = br.readLine().split(" ");
        N = Integer.parseInt(firstLine[0]);
        K = Integer.parseInt(firstLine[1]);

        parent = new int[K + 1];
        for (int i = 1; i <= K; i++) {
            parent[i] = i; // Union-Find 초기화: 각 문명이 자기 자신을 대표
        }

        int[][] map = new int[N + 1][N + 1]; // 문명의 위치를 표시하는 지도
        for (int i = 1; i <= K; i++) {
            String[] line = br.readLine().split(" ");
            int a = Integer.parseInt(line[0]);
            int b = Integer.parseInt(line[1]);
            map[a][b] = i; // 문명 위치 초기화
            queue.offer(new int[]{a, b, i}); // 각 문명의 시작 위치를 큐에 삽입
        }

        System.out.println(simulateExpansion(map));
        // 문명이 전체로 통합되는 데 걸리는 시간을 계산
    }

    static int simulateExpansion(int[][] map) {
        int years = 0; // 경과한 햇수

        while (!queue.isEmpty()) {
            int size = queue.size();
            HashSet<Integer> visitedRoots = new HashSet<>();
            // 그 해에 방문한 문명 그룹을 저장

            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();
                int x = current[0];
                int y = current[1];
                int root = find(current[2]); // 현재 문명의 루트를 찾음

                // 인접한 네 방향에 대해 문명 확장 시도
                for (int[] dir : directions) {
                    int nx = x + dir[0];
                    int ny = y + dir[1];
                    if (nx >= 1 && nx <= N && ny >= 1 && ny <= N) { // 경계 내부인지 확인
                        if (map[nx][ny] == 0) { // 아직 문명이 없는 영역이라면
                            map[nx][ny] = root; // 문명 확장
                            queue.offer(new int[]{nx, ny, root}); // 확장된 영역을 큐에 추가
                        } else {
                            int neighborRoot = find(map[nx][ny]); // 인접한 영역의 문명 루트 찾기
                            if (neighborRoot != root) { // 서로 다른 문명이라면
                                union(root, neighborRoot); // 두 문명을 통합
                                root = find(root); // 새로운 루트 찾기
                            }
                        }
                    }
                }
                visitedRoots.add(root); // 방문한 문명 그룹 추가
            }

            // 모든 문명이 하나로 통합되었는지 확인
            if (visitedRoots.size() == 1 && allConnected(visitedRoots.iterator().next())) {
                break;
            }

            years++; // 1년 증가
        }

        return years; // 총 경과한 햇수 반환
    }

    // Union-Find: x의 루트 찾기
    static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    // Union-Find: x와 y를 통합
    static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            parent[rootY] = rootX;
        }
    }

    // 모든 문명이 하나의 루트로 연결되어 있는지 확인
    static boolean allConnected(int root) {
        for (int i = 1; i <= K; i++) {
            if (find(i) != root) {
                return false;
            }
        }
        return true;
    }
}
