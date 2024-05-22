package week3.lim.greedy.joystick;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/42860
// 출처: https://yeoeun-ji.tistory.com/143

public class joyStick2 {
    public static int solution(String name) {
        int answer = 0;
        int cursor = name.length()-1;

        for (int i = 0; i < name.length(); i++) {
            //앞으로 갈 경우와 뒤로 갈 경우 중 작은 수 계산
            int front = name.charAt(i) - 'A';
            int back = 'Z' - name.charAt(i) + 1;
            int min = Math.min(front, back);

            answer += min;

            int next = i + 1;
            int count_a = 0;

            //연속하는 A 개수
            while (next < name.length() && name.charAt(next) == 'A') {
                count_a += 1;
                next++;
            }

            // 전체 길이에서 A를 제외하고 움직인 횟수
            int moveWithoutA = name.length() - count_a - 1;

            int min2 = Math.min(i, name.length() - next);

            cursor = Math.min(cursor, moveWithoutA + min2);
        }

        return answer + cursor;
    }

    public static void main(String[] args) {
        String name1 = "JEROEN";
        System.out.println(solution(name1));    // 56

        String name2 = "JAN";
        System.out.println(solution(name2));    // 23
    }
}