package week4.kim.week4_1.graph;

import java.util.HashSet;
import java.util.Set;

/**
 * [방의 개수]
 *
 * 원점(0,0)에서 시작해서 아래처럼 숫자가 적힌 방향으로 이동하며 선을 긋습니다.
 *
 * 0일때는 위로 이동
 * 1일때는 오른쪽 위로 이동
 * 2일때는 오른쪽으로 이동
 * 3일때는 오른쪽 아래로 이동
 * 4일때는 아래로 이동
 * 5일때는 왼쪽 아래로 이동
 * 6일때는 왼쪽으로 이동
 * 7일때는 왼쪽 위로 이동
 *
 * 그림을 그릴 때, 사방이 막히면 방하나로 샙니다.
 * 이동하는 방향이 담긴 배열 arrows가 매개변수로 주어질 때, 방의 갯수를 return 하도록 solution 함수를 작성하세요.
 *
 * 제한사항
 * 배열 arrows의 크기는 1 이상 100,000 이하 입니다.
 * arrows의 원소는 0 이상 7 이하 입니다.
 * 방은 다른 방으로 둘러 싸여질 수 있습니다.
 *
 * 입출력 예
 * arrows	                                                    return
 * [6, 6, 6, 4, 4, 4, 2, 2, 2, 0, 0, 0, 1, 6, 5, 5, 3, 6, 0]	3
 *
 * 입출력 예 설명
 * (0,0) 부터 시작해서 6(왼쪽) 으로 3번 이동합니다. 그 이후 주어진 arrows 를 따라 그립니다.
 * 삼각형 (1), 큰 사각형(1), 평행사변형(1) = 3
 */

public class Ex3 {
    public static void main(String[] args) {
        Ex3 ex3 = new Ex3();

        // 주어진 방향에 따라 이동할 배열
        int[] arrows = {6, 6, 6, 4, 4, 4, 2, 2, 2, 0, 0, 0, 1, 6, 5, 5, 3, 6, 0};

        // 3
        System.out.println(ex3.solution(arrows));
    }

    public int solution(int[] arrows) {
        // x축 이동 방향
        int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
        // y축 이동 방향
        int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

        // 방문한 노드를 저장하는 해시셋
        Set<String> visitedNodes = new HashSet<>();
        // 방문한 간선을 저장하는 해시셋
        Set<String> visitedEdges = new HashSet<>();

        // 현재 위치 (0,0)으로 초기화
        int x = 0, y = 0;
        // 방의 개수 초기화
        int rooms = 0;

        // 시작 노드를 방문 처리
        visitedNodes.add(x + ":" + y);

        // 주어진 방향으로 이동하며 방을 그림
        for (int i = 0; i < arrows.length; i++) {
            // 대각선 경로를 포함하여 두 칸씩 이동
            for (int j = 0; j < 2; j++) {
                // 다음 위치를 계산
                int nx = x + dx[arrows[i]];
                int ny = y + dy[arrows[i]];

                // 노드와 간선을 문자열로 표현
                String node = nx + ":" + ny;
                String edge1 = x + ":" + y + ":" + nx + ":" + ny;
                String edge2 = nx + ":" + ny + ":" + x + ":" + y;

                // 이미 방문한 노드로 이동하면서 새로운 간선을 만들 경우 방을 하나 추가
                if (visitedNodes.contains(node) && !visitedEdges.contains(edge1)) {
                    rooms++;
                }

                // 노드와 간선을 방문 처리
                visitedNodes.add(node);
                visitedEdges.add(edge1);
                visitedEdges.add(edge2);

                // 현재 위치 업데이트
                x = nx;
                y = ny;
            }
        }

        return rooms;
    }
}