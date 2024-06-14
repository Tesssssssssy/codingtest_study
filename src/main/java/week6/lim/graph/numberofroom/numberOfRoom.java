package week6.lim.graph.numberofroom;

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
/*
    Q. 왜 2번 scale up?

        교차점 처리를 위해 각 방향을 2번 이동하는 "스케일업" 접근은 다음과 같은 이유에서 중요합니다:

        더 세밀한 검사를 위해: 일반적인 그리드 상에서 한 칸씩 이동하는 것만으로는 두 선이 교차하는 정확한 지점을 파악하기 어렵습니다.
        예를 들어, 두 선이 대각선으로 서로를 교차할 경우,
        각각의 이동이 서로 다른 칸을 통과할 수 있어서 실제로는 교차하지만 그리드 상의 이동만으로는 교차를 감지하지 못할 수 있습니다.

        세밀한 경로 모델링: 각 방향에 대해 이동을 2회 수행하면, 모든 경로 사이의 중간점을 고려하여 보다 세밀하게 경로를 모델링할 수 있습니다.
        이는 특히 경로가 서로 겹치거나 교차하는 복잡한 시나리오에서 유용합니다.

        교차점 파악: 예를 들어, 두 경로가 십자 형태로 교차하는 경우를 생각해 보세요.
        각 경로가 한 칸씩만 이동할 경우, 이들이 교차하는 중간 지점을 놓칠 수 있습니다.
        하지만 각 경로를 두 칸씩 이동하면, 이 중간 지점에서의 교차를 파악할 수 있습니다.

        벡터 연장: 각 이동을 두 번 수행함으로써, 이동 벡터를 실질적으로 연장하고 있습니다.
        이는 경로의 기하학적 특성을 더 명확하게 만들어, 경로가 다른 경로를 어떻게 교차하는지 더 정확히 파악할 수 있게 해줍니다.

        이러한 스케일업은 특히 복잡한 형태의 경로에서 교차점을 정확하게 파악하고,
        결과적으로 올바른 방의 수를 계산하는 데 필수적인 기술입니다.
        이는 문제에서 요구하는 정확한 교차점 검출을 위해 필요한 조치로,
        단순히 각 칸을 이동하는 것보다는 경로의 교차 패턴을 더 세밀하게 추적하고자 하는 목적이 있습니다.
*/

/*
    Q. 왜 해싱 사용?

        해시(hash) 기능은 객체가 가진 데이터를 기반으로 일정한 길이의 숫자를 생성하는 과정입니다.
        Java에서는 모든 객체에 대해 hashCode() 메서드를 통해 이를 구현할 수 있습니다.
        이 해시 코드는 해시 테이블에서 객체를 효율적으로 저장하고 검색하는 데 사용됩니다.
        해시 코드를 사용함으로써, 객체를 빠르게 비교하고, 컬렉션(예: HashMap, HashSet 등) 내에서 객체를 효율적으로 관리할 수 있습니다.

        여기서 Node 클래스는 각 노드의 위치를 나타내는 x와 y 좌표를 가지고 있습니다.
        hashCode() 메서드는 이 좌표를 사용하여 각 Node 객체에 대한 고유한 해시 코드를 생성합니다.
        이를 통해 Node 객체들을 HashMap이나 HashSet 내에서 키로 사용할 수 있게 됩니다.

        hashCode() 메서드의 구현:

            Objects.hash() 메서드 사용: 이 메서드는 하나 이상의 값을 입력받아, 그 값들을 조합하여 단일 해시 코드를 생성합니다.
            x와 y 좌표값을 이용해 각 Node 인스턴스마다 고유한 해시 코드를 생성합니다.

        해시 코드 사용의 중요성:

            데이터 접근 속도 향상: 해시를 사용하면 데이터의 저장 및 검색 속도를 크게 향상시킬 수 있습니다.
            특히 대량의 데이터를 다루는 경우, 해시 테이블은 데이터를 빠르게 처리할 수 있게 해 줍니다.

            데이터 구조의 효율성: 해시 코드를 사용하면 HashMap이나 HashSet 같은 자료구조에서 객체를 빠르고 효율적으로 관리할 수 있습니다.
            이는 특히 키-값 쌍을 저장하거나 유니크한 데이터를 관리할 때 유용합니다.


    Q. 오버라이드한 메소드 사용하는 부분이 없는데..?

        이 코드에서는 Node 객체가 맵의 키로 사용되기 때문에,
        이 객체들의 동일성을 판단하고 효율적으로 저장 및 검색하기 위해 equals()와 hashCode() 메서드가 필수적으로 구현되어야 합니다.
        이를 통해 Node 객체들이 정확하고 빠르게 관리될 수 있습니다.

        제시된 코드 조각에서 hashCode()와 equals() 메서드는 정의되어 있지만, 직접적으로 사용하는 코드가 보이지 않는 것 같습니다.
        그러나 이 메서드들은 Node 객체가 HashMap이나 HashSet과 같은 컬렉션에서 키로 사용될 때 자동으로 사용됩니다.
        Java에서 이러한 컬렉션들은 객체의 동등성을 확인하고, 데이터 구조 내에서 정확한 위치에 객체를 저장하거나 검색하기 위해 이 메서드들을 내부적으로 호출합니다.

        Node 객체가 visited라는 HashMap의 키로 사용되고 있는 상황에서,
        Node 객체의 위치를 기반으로 특정 노드가 방문되었는지 여부를 체크하는 과정에서 hashCode()와 equals() 메서드가 사용됩니다.
        HashMap에서 키의 존재 여부를 확인하거나 키에 대응하는 값을 불러올 때, 이 메서드들이 자동으로 호출됩니다.
        이를 통해 효율적으로 노드의 방문 상태를 관리할 수 있습니다.

        위 코드에서 visited.containsKey(nextNode)와 visited.get(nextNode).contains(curNode) 호출은
        Node 객체의 equals()와 hashCode() 메서드를 내부적으로 사용합니다.
        이는 Java의 HashMap 구현에서 자동으로 이루어지는 것이며, 개발자가 별도로 호출할 필요는 없습니다.

        따라서, equals()와 hashCode()는 다음과 같은 중요한 역할을 수행합니다:

        equals(): 두 객체가 논리적으로 동등한지 비교합니다.
                  HashMap이나 HashSet에서 객체를 찾거나 중복을 방지할 때 필요합니다.
        hashCode(): 객체를 효율적으로 저장하고 검색할 수 있는 해시 코드를 생성합니다.
                    이 코드는 HashMap이나 HashSet에서 버킷에 객체를 저장하는 데 사용됩니다.
*/