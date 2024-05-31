package week3.kim.week3_1.greedy;

/**
 * [조이스틱]
 *
 * 조이스틱으로 알파벳 이름을 완성하세요. 맨 처음엔 A로만 이루어져 있습니다.
 * ex) 완성해야 하는 이름이 세 글자면 AAA, 네 글자면 AAAA
 *
 * 조이스틱을 각 방향으로 움직이면 아래와 같습니다.
 *
 * ▲ - 다음 알파벳
 * ▼ - 이전 알파벳 (A에서 아래쪽으로 이동하면 Z로)
 * ◀ - 커서를 왼쪽으로 이동 (첫 번째 위치에서 왼쪽으로 이동하면 마지막 문자에 커서)
 * ▶ - 커서를 오른쪽으로 이동 (마지막 위치에서 오른쪽으로 이동하면 첫 번째 문자에 커서)
 * 예를 들어 아래의 방법으로 "JAZ"를 만들 수 있습니다.
 *
 * - 첫 번째 위치에서 조이스틱을 위로 9번 조작하여 J를 완성합니다.
 * - 조이스틱을 왼쪽으로 1번 조작하여 커서를 마지막 문자 위치로 이동시킵니다.
 * - 마지막 위치에서 조이스틱을 아래로 1번 조작하여 Z를 완성합니다.
 * 따라서 11번 이동시켜 "JAZ"를 만들 수 있고, 이때가 최소 이동입니다.
 * 만들고자 하는 이름 name이 매개변수로 주어질 때,
 * 이름에 대해 조이스틱 조작 횟수의 최솟값을 return 하도록 solution 함수를 만드세요.
 *
 * 제한 사항
 * name은 알파벳 대문자로만 이루어져 있습니다.
 * name의 길이는 1 이상 20 이하입니다.
 *
 * 입출력 예
 * name		return
 * "JEROEN"	56
 * "JAN"	23
*/

public class Ex2 {
    public static void main(String[] args) {
        Ex2 ex2 = new Ex2();

        // 56 반환
        System.out.println(ex2.solution("JEROEN"));
        // 23 반환
        System.out.println(ex2.solution("JAN"));
    }


    public int solution(String name) {
        int answer = 0;
        int leng = name.length();

        // 가장 짧은 좌, 우 이동
        int move = leng - 1;

        for(int i = 0; i < leng; i++) {
            // 상, 하 이동 중 최소값 찾기
            answer += Math.min(name.charAt(i) - 'A', 'Z' - name.charAt(i) + 1);

            // 다음 문자 위치 찾기 ('A'인지 확인)
            int next = i + 1;

            while(next < leng && name.charAt(next) == 'A') {
                next++;
            }

            // 현재 위치에서 왼쪽으로 돌아가서 다음 문자를 찾는 이동 횟수와
            // 기존의 move와 비교해서 최소값 업데이트
            move = Math.min(move, i + leng - next + Math.min(i, leng - next));


//            move = Math.min(move, i * 2 + leng - next);
//            move = Math.min(move, (leng - next) * 2 + i);
        }

        // 상, 하 이동 횟수와 좌, 우 이동 횟수를 더해서 반환
        answer += move;

        return answer;
    }
}