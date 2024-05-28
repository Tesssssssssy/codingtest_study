package week4.lim.graph.numberofroom;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/49190

import java.util.*;

public class numberOfRoom {
    public static int solution(int[] arrows) {
        int answer = 0;

        // 각 방향별 x, y 좌표의 이동 값을 배열로 정의
        int[] dx = { 0, 1, 1, 1, 0, -1, -1, -1 };
        int[] dy = { 1, 1, 0, -1, -1, -1, 0, 1 };
        Node curNode = new Node(0, 0); // 시작 노드 초기화

        // 방문한 노드와 그 노드에 연결된 노드 리스트를 저장하는 맵
        // 방문 여부 관련 선언
        // key = 시작 node의 hashcode, value = 연결된 node들의 hashcode
        Map<Node, List<Node>> visited = new HashMap<>();

        // 각 화살의 방향에 대해 반복
        for (int arrow : arrows) {
            for (int i = 0; i <= 1; i++) {
                // 각 방향을 2번 이동하면서 교차점 처리
                // 교차점 처리를 위한 스케일업(반복 2번)

                Node nextNode = new Node(curNode.x + dx[arrow], curNode.y + dy[arrow]);
                // 이동

                if (!visited.containsKey(nextNode)) {
                    // 현재 노드가 방문하지 않은 노드라면
                    // 처음 방문하는 경우 = map에 키값이 없는 경우

                    visited.put(nextNode, makeEdgeList(curNode));
                    // 리스트에 연결점 추가
                    // 새 노드와 현재 노드 연결 리스트 생성 및 추가

                    // 현재 노드에 대한 처리
                    if (visited.get(curNode) == null) {
                        // 기존점도 없다면 업데이트

                        visited.put(curNode, makeEdgeList(nextNode));
                    } else {
                        // 기존점이 있다면 추가하기

                        visited.get(curNode).add(nextNode);
                    }
                } else if (!visited.get(nextNode).contains(curNode)) {
                    // 이미 방문한 노드이지만 첫 연결이라면 방을 형성하므로 카운트 증가
                    // 재방문했고 간선을 처음 통과하는 경우

                    visited.get(nextNode).add(curNode);
                    visited.get(curNode).add(nextNode);
                    answer++;
                }

                // 현재 노드를 다음 노드로 업데이트
                // 이동 완료
                curNode = nextNode;
            }
        }

        return answer; // 계산된 방의 수 반환
    }

    // 주어진 노드에 대해 연결 리스트를 생성하는 도우미 메소드
    // value 값에 넣기 위한 리스트 만드는 메소드
    private static List<Node> makeEdgeList(Node node) {
        List<Node> edge = new ArrayList<>();
        edge.add(node);
        return edge;
    }

    // 좌표를 나타내는 Node 클래스 정의
    private static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        // 동일성 체크를 위한 equals 메소드 오버라이드
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return x == node.x && y == node.y;
        }

        // 해시코드 생성을 위한 hashCode 메소드 오버라이드
        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public static void main(String[] args) {
        int[] arrows = {6, 6, 6, 4, 4, 4, 2, 2, 2, 0, 0, 0, 1, 6, 5, 5, 3, 6, 0};
        System.out.println(solution(arrows));  // 3
    }
}