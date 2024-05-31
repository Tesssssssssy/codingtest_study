package week3.kim.week3_2.dp;

/**
 * [N으로 표현]
 *
 * 아래와 같이 5와 사칙연산만으로 12를 표현할 수 있습니다.
 *
 * 12 = 5 + 5 + (5 / 5) + (5 / 5)
 * 12 = 55 / 5 + 5 / 5
 * 12 = (55 + 5) / 5
 *
 * 5를 사용한 횟수는 각각 6,5,4 입니다. 그리고 이중 가장 작은 경우는 4입니다.
 * 이처럼 숫자 N과 number가 주어질 때,
 * N과 사칙연산만 사용해서 표현 할 수 있는 방법 중 N 사용횟수의 최솟값을 return 하도록 solution 함수를 작성하세요.
 *
 * 제한사항
 * N은 1 이상 9 이하입니다.
 * number는 1 이상 32,000 이하입니다.
 * 수식에는 괄호와 사칙연산만 가능하며 나누기 연산에서 나머지는 무시합니다.
 * 최솟값이 8보다 크면 -1을 return 합니다.
 *
 * 입출력 예
 * N	number	return
 * 5	12		4
 * 2	11		3
 *
 * 입출력 예 설명
 * 예제 #1
 * 문제에 나온 예와 같습니다.
 *
 * 예제 #2
 * 11 = 22 / 2와 같이 2를 3번만 사용하여 표현할 수 있습니다.
 */

public class Ex1 {
    public static void main(String[] args) {
        Ex1 ex1 = new Ex1();

        // 4 반환
        System.out.println(ex1.solution(5, 12));
        // 3 반환
        System.out.println(ex1.solution(2, 11));
    }

    int answer = -1;

    public int solution(int N, int number) {
        answer = -1;

        // 초기 카운트 0, 누적값 0
        dfs(N, number, 0, 0);

        // 최소 연산 횟수 반환
        return answer;
    }

    public void dfs(int N, int number, int count, int accum) {
        // 임시 변수에 저장
        int tempN = N;

        // 연산 횟수가 8을 초과하면 탐색 종료
        if (count > 8)
            return;

        // 누적값이 목표값과 같으면
        if (number == accum) {
            // answer가 -1이거나 answer보다 count가 작으면
            if (answer == -1 || answer > count)
                // answer를 count로 업데이트
                answer = count;

            return;
        }

        // 8 - count 만큼 반복하면서
        for (int i = 0; i < 8 - count; i++) {
            // 덧셈, 뺄셈, 나눗셈, 곱셈 연산을 수행하고 DFS를 계속 수행
            dfs(N, number, count + i + 1, accum + tempN);
            dfs(N, number, count + i + 1, accum - tempN);
            dfs(N, number, count + i + 1, accum / tempN);
            dfs(N, number, count + i + 1, accum * tempN);

            // 값을 증가
            tempN = increaseNumber(tempN, N);
        }
    }

    // 주어진 값에 N을 추가하여 숫자를 증가
    public int increaseNumber(int value, int N) {
        return value * 10 + N;
    }
}