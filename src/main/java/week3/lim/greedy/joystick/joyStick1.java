package week3.lim.greedy.joystick;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/42860

public class joyStick1 {
    public static int solution(String name) {
        int minMoves = name.length() - 1; // 오른쪽으로만 갔을 때의 기본 이동 횟수
        int answer = 0;

        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            // 'A'에서 'B'로 가는 데는 1회의 조작 필요 (c - 'A').
            // 'A'에서 'Z'를 거쳐 'B'로 가는 데는 26 - 1 + 1 = 26회 조작이 필요 ('Z' - c + 1).

            answer += Math.min(c - 'A', 'Z' - c + 1);

            // 커서 이동을 최소화하기 위해, 각 위치에서 뒤로 돌아가는 것이 더 나은지, 아니면 계속 진행하는 것이 나은지 계산해야 함.
            int next = i + 1;
            while (next < name.length() && name.charAt(next) == 'A') {
                next++;
            }

            /*
                i는 현재 위치, next는 다음에 'A'가 아닌 문자가 나타나는 위치.
                i + name.length() - next + Math.min(i, name.length() - next) 계산은 다음을 고려:
                    - i + i: 현재 위치에서 시작 위치로 돌아간 후, 다시 next 위치까지 이동.
                    - name.length() - next: 'A'가 끝난 후 남은 문자들까지 이동해야 하는 거리.
            */
            minMoves = Math.min(minMoves, i + name.length() - next + Math.min(i, name.length() - next));

            /*
                커서 이동 최소화
                    : 연속된 'A' 다음의 문자로 이동하는 경우를 고려하여 커서 이동을 최소화.

                이동 패턴 두 가지 고려:
                    - 시작 지점으로 돌아간 후, 연속된 'A'의 끝 지점 다음으로 이동하는 경우.
                    - 연속된 'A'가 끝나는 지점부터 문자열의 끝까지 이동하는 경우.
            */
        }

        answer += minMoves;
        // 문자 변경 조작 횟수 (answer)와 최소 커서 이동 횟수 (minMoves)를 합산

        return answer;

        // 이름의 길이가 최대 20이므로, O(n) 시간복잡도
    }

    public static void main(String[] args) {
        String name1 = "JEROEN";
        System.out.println(solution(name1));    // 56

        String name2 = "JAN";
        System.out.println(solution(name2));    // 23
    }
}

/*
        문제 설명:
        각 문자를 원하는 알파벳으로 변경하는 데 필요한 최소 조작 횟수.
        커서를 움직여 다른 문자로 이동하는 최소 횟수 계산.

        최적의 이동 전략:
        이 문제의 핵심은 언제 뒤로 돌아가는 것이 더 효율적인지 판단하는 것입니다.
        문자열에 연속된 'A'가 많이 있으면 그 구간을 돌아서 가는 것이 더 나을 수 있습니다.

        예제 "BBBAAAAB"의 경우:
        목표 문자열: BBBAAAAB
        알파벳 변경 최소 횟수 계산:

        'B'로 변경하는데 1번의 조작이 필요.
        네 개의 'B'가 필요하므로 변경 횟수는 4번입니다.
        최적의 커서 이동:

        첫 번째 B 세 개를 만듭니다. (현재 위치: 인덱스 2)
        'A' 세 개를 건너뛰고 마지막 'B'로 직접 이동하는 대신, 돌아가서 마지막 'B'에 도달합니다.
        이동 경로는: 시작 → B → B → B → (왼쪽으로 돌아가기) → B
        오른쪽으로 2번 이동해서 'BBB'를 만들고, 다시 왼쪽으로 돌아가 마지막 'B'에 도달하기 위해 총 3번의 이동이 필요합니다.

        총 조작 횟수:
        알파벳 변경: 4번
        커서 이동: 2(오른쪽) + 3(왼쪽) = 5번

        총 조작 횟수: 4 + 5 = 9번
*/