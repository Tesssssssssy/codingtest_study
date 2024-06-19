package week7.lim.groom_search.civilization;

import java.util.*;
import java.io.*;

// 테스트케이스 1개 통과 x
public class Civilization2 {
    /**
     *  너비 우선 탐색(BFS)를 사용하여 문명이 확장되는 과정을 시뮬레이션할 수 있다.
     *  하지만 문명이 연결되었는지 추적하기 위해 사용하는 connectedCivilizations 집합을 이용하는 방법으로는
     *  문명들이 전체적으로 언제 하나로 결합되었는지 정확히 알아내기 어려운 문제가 있다.
     *
     *  문명이 연결되었다는 것을 더 정확하게 판단하기 위해선,
     *  초기에 각 문명 발상지가 서로 인접한지를 확인하여 인접한 문명들을 하나의 그룹으로 묶어 초기 설정을 해야 함.
     *  그 후, BFS를 진행하면서 문명이 확장될 때마다 인접한 다른 문명과의 연결을 확인하고,
     *  필요한 경우 연결된 문명들을 하나의 그룹으로 통합다.
     *  이때, 문명의 모든 그룹이 하나로 통합되면 모든 문명이 하나로 연결된 것.
     *
     *  초기화: 문명 발상지를 초기화하고 각각의 문명을 그룹화.
     *  문명 확장: BFS를 사용하여 문명을 확장하고, 확장 중에 다른 문명과 만나면 그룹을 통합.
     *  연결 확인: 모든 문명이 하나의 그룹으로 연결되었는지 확인.
     */

    // 문명 위치와 소속 그룹을 저장하는 포인트 클래스
    static class Point {
        int x, y, group;  // 각 문명의 위치 (x, y)와 소속 그룹 ID

        Point(int x, int y, int group) {
            this.x = x;
            this.y = y;
            this.group = group;
        }
    }

    static int N, K; // 세계의 크기 N, 문명 발상지의 수 K
    static int[][] map; // 세계 지도, 각 위치에는 문명 그룹 ID가 저장됨
    static int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 문명이 확장될 수 있는 네 방향 (상, 하, 좌, 우)
    static Map<Integer, Integer> groupMap = new HashMap<>(); // 각 문명 그룹의 루트를 추적

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N+1][N+1];

        Queue<Point> queue = new LinkedList<>();
        int groupId = 1;

        // 문명 발상지 초기화
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map[x][y] = groupId;
            queue.add(new Point(x, y, groupId));
            groupMap.put(groupId, groupId);
            groupId++;
        }

        System.out.println(bfs(queue));
    }

    static int bfs(Queue<Point> queue) {
        int years = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            Set<Integer> seenGroups = new HashSet<>();

            for (int i = 0; i < size; i++) {
                Point current = queue.poll();

                // 문명 확장 과정
                for (int[] dir : direction) {
                    int nx = current.x + dir[0];
                    int ny = current.y + dir[1];

                    if (nx >= 1 && nx <= N && ny >= 1 && ny <= N) {
                        if (map[nx][ny] == 0) {
                            map[nx][ny] = current.group;
                            queue.add(new Point(nx, ny, current.group));
                        } else if (map[nx][ny] != current.group) {
                            union(current.group, map[nx][ny]);
                        }
                    }
                }
                seenGroups.add(find(current.group));
            }

            // 모든 문명이 하나로 연결되었는지 확인
            if (seenGroups.size() == 1 && allConnected()) {
                return years;
            }

            years++;
        }

        return years;
    }

    static void union(int group1, int group2) {
        int root1 = find(group1);
        int root2 = find(group2);
        if (root1 != root2) {
            groupMap.put(root2, root1);
        }
    }

    static int find(int group) {
        if (groupMap.get(group) != group) {
            groupMap.put(group, find(groupMap.get(group)));
        }
        return groupMap.get(group);
    }

    // 모든 문명이 하나의 그룹으로 연결되었는지 확인하는 함수
    static boolean allConnected() {
        int root = find(groupMap.keySet().iterator().next());
        for (int key : groupMap.keySet()) {
            if (find(key) != root) {
                return false;
            }
        }
        return true;
    }
}
