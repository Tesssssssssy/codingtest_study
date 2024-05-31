package week2.kim.week2_2.dfs_Bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * [아이템 줍기]
 *
 * 다각형 모양 지형에서 캐릭터가 아이템을 줍기 위해 이동하려 합니다.
 *
 * 지형은 각 변이 x축, y축과 평행한 직사각형이 겹쳐진 형태로 표현하며, 캐릭터는 이 다각형의 둘레(굵은 선)를 따라서 이동합니다.
 *
 * 만약 직사각형을 겹친 후 다음과 같이 중앙에 빈 공간이 생기는 경우, 다각형의 가장 바깥쪽 테두리가 캐릭터의 이동 경로가 됩니다.
 *
 * 단, 서로 다른 두 직사각형의 x축 좌표 또는 y축 좌표가 같은 경우는 없습니다.
 *
 * 즉, 서로 다른 두 직사각형이 꼭짓점에서 만나거나, 변이 겹치는 경우 등은 없습니다.
 *
 * 지형이 2개 이상으로 분리된 경우도 없습니다.
 *
 * 한 직사각형이 다른 직사각형 안에 완전히 포함되는 경우 또한 없습니다.
 *
 * 지형을 나타내는 직사각형이 담긴 2차원 배열 rectangle, 초기 캐릭터의 위치 characterX, characterY, 아이템의 위치 itemX, itemY가 solution 함수의 매개변수로 주어질 때, 캐릭터가 아이템을 줍기 위해 이동해야 하는 가장 짧은 거리를 return 하도록 solution 함수를 완성해주세요.
 *
 * 제한사항
 * rectangle의 세로(행) 길이는 1 이상 4 이하입니다.
 * rectangle의 원소는 각 직사각형의 [좌측 하단 x, 좌측 하단 y, 우측 상단 x, 우측 상단 y] 좌표 형태입니다.
 * 직사각형을 나타내는 모든 좌표값은 1 이상 50 이하인 자연수입니다.
 * 서로 다른 두 직사각형의 x축 좌표, 혹은 y축 좌표가 같은 경우는 없습니다.
 * 문제에 주어진 조건에 맞는 직사각형만 입력으로 주어집니다.
 * charcterX, charcterY는 1 이상 50 이하인 자연수입니다.
 * 지형을 나타내는 다각형 테두리 위의 한 점이 주어집니다.
 * itemX, itemY는 1 이상 50 이하인 자연수입니다.
 * 지형을 나타내는 다각형 테두리 위의 한 점이 주어집니다.
 * 캐릭터와 아이템의 처음 위치가 같은 경우는 없습니다.
 * 전체 배점의 50%는 직사각형이 1개인 경우입니다.
 * 전체 배점의 25%는 직사각형이 2개인 경우입니다.
 * 전체 배점의 25%는 직사각형이 3개 또는 4개인 경우입니다.
 *
 * 입출력 예
 * rectangle					                characterX	characterY	itemX		itemY		result
 * [[1,1,7,4],[3,2,5,5],[4,3,6,9],[2,6,8,8]]	1		    3		    7		    8		    17
 * [[1,1,8,4],[2,2,4,9],[3,6,9,8],[6,3,7,7]]	9		    7		    6		    1		    11
 * [[1,1,5,7]]					                1		    1		    4		    7		    9
 * [[2,1,7,5],[6,4,10,10]]			            3		    1		    7		    10		    15
 * [[2,2,5,5],[1,3,6,4],[3,1,4,6]]		        1		    4		    6		    3		    10
 *
 * 입출력 예 설명
 * 입출력 예 #1
 *
 * 캐릭터 위치는 (1, 3)이며, 아이템 위치는 (7, 8)입니다. 위 그림과 같이 굵은 선을 따라 이동하는 경로가 가장 짧습니다.
 *
 * 입출력 예 #2
 *
 * 캐릭터 위치는 (9, 7)이며, 아이템 위치는 (6, 1)입니다. 위 그림과 같이 굵은 선을 따라 이동하는 경로가 가장 짧습니다.
 *
 * 입출력 예 #3
 *
 * 캐릭터 위치는 (1, 1)이며, 아이템 위치는 (4, 7)입니다. 위 그림과 같이 굵은 선을 따라 이동하는 경로가 가장 짧습니다.
 *
 * 입출력 예 #4, #5
 *
 * 설명 생략
*/

public class Ex3 {
    public static void main(String[] args) {
        Ex3 ex3 = new Ex3();

        int[][] rectangle1 = {{1,1,7,4},{3,2,5,5},{4,3,6,9},{2,6,8,8}};
        int[][] rectangle2 = {{1,1,8,4},{2,2,4,9},{3,6,9,8},{6,3,7,7}};
        int[][] rectangle3 = {{1,1,5,7}};
        int[][] rectangle4 = {{2,1,7,5},{6,4,10,10}};
        int[][] rectangle5 = {{2,2,5,5},{1,3,6,4},{3,1,4,6}};

        System.out.println(ex3.solution(rectangle1, 1, 3, 7, 8));
        System.out.println(ex3.solution(rectangle2, 9, 7, 6, 1));
        System.out.println(ex3.solution(rectangle3, 1, 1, 4, 7));
        System.out.println(ex3.solution(rectangle4, 3, 1, 7, 10));
        System.out.println(ex3.solution(rectangle5, 1, 4, 6, 3));
    }

    // 이동할 네 방향을 정의 (상, 하, 좌, 우)
    static final int[] dx = {1, -1, 0, 0};
    static final int[] dy = {0, 0, 1, -1};

    // 노드 클래스 (x, y 좌표와 해당 좌표까지의 거리 저장)
    static class Node {
        int x;
        int y;
        int dist;

        Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        // 지형을 표현하는 2차원 배열 (2배수)
        Boolean[][] map = new Boolean[102][102];

        // 캐릭터와 아이템의 위치를 확장 (2배수)
        characterX *= 2;
        characterY *= 2;
        itemX *= 2;
        itemY *= 2;

        // 각 직사각형에 대해 지형을 설정
        for (int[] rect : rectangle) {
            for (int i = 0; i < 4; i++) rect[i] *= 2;
            for (int x = rect[0]; x <= rect[2]; x++) {
                for (int y = rect[1]; y <= rect[3]; y++) {
                    map[x][y] = (x == rect[0] || x == rect[2] || y == rect[1] || y == rect[3]) && map[x][y] != Boolean.FALSE;
                }
            }
        }

        // BFS를 위한 큐를 생성
        Queue<Node> queue = new LinkedList<>();
        map[characterX][characterY] = false;
        queue.offer(new Node(characterX, characterY, 0));

        int minDist = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            Node node = queue.poll();

            // 아이템 위치에 도달한 경우, 거리를 업데이트
            if (node.x == itemX && node.y == itemY && minDist > node.dist) {
                minDist = node.dist;
                continue;
            }

            // 네 방향으로 이동
            for (int i = 0; i < 4; i++) {
                int nx = node.x + dx[i], ny = node.y + dy[i];
                if (nx < 2 || ny < 2 || nx > 100 || ny > 100) continue;
                if (map[nx][ny] != Boolean.TRUE) continue;

                map[nx][ny] = false;
                queue.offer(new Node(nx, ny, node.dist + 1));
            }
        }

        // 최단 거리를 반환 (원래 배수로 수정)
        return minDist / 2;
    }
}