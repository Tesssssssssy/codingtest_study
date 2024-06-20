package week7.lim.kakao2024winter.dice;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/258709
// 참고: https://velog.io/@funnysunny08/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EC%B9%B4%EC%B9%B4%EC%98%A4-%EC%A3%BC%EC%82%AC%EC%9C%84-%EA%B3%A0%EB%A5%B4%EA%B8%B0-java

import java.util.*;

public class Dice1 {
    // 주사위의 총 개수
    static int N;
    // A가 선택한 주사위의 인덱스를 저장할 리스트
    static List<Integer> choice = new ArrayList<>();
    // 각 주사위의 면의 값을 저장하는 2차원 배열
    static int[][] dices;
    // A와 B가 각각의 조합으로 얻을 수 있는 모든 점수의 합을 저장할 리스트
    static List<Integer> arrA, arrB;
    // 최적의 조합을 저장할 배열
    static int[] answer;
    // 최대 승리 횟수를 추적하는 변수
    static int max = Integer.MIN_VALUE;


    /*
        특정 주사위 조합으로 가능한 모든 점수의 합을 계산.
        재귀적으로 각 주사위의 모든 면을 시도하면서 점수 누적.
        depth가 주사위 수의 절반에 도달하면, 계산된 점수를 arr 리스트에 추가.
     */
    public static void makeArr(int depth, int[][] dice, int sum, List<Integer> arr) {
        // 기저 조건: 주사위 개수의 절반까지 모든 주사위를 고려했을 때
        if (depth == N / 2) {
            arr.add(sum); // 계산된 합을 리스트에 추가
            return;
        }
        // 주사위의 모든 면을 고려하여 재귀 호출
        for (int i = 0; i < 6; i++) {
            makeArr(depth + 1, dice, sum + dice[depth][i], arr);
        }
    }

    /*
        선택된 주사위 조합(choice 리스트에 저장된 인덱스 기반)에 따라 두 그룹 diceA와 diceB를 구성.
        makeArr 함수를 호출하여 각 그룹의 모든 가능한 점수 합을 arrA와 arrB에 저장.
    */
    public static void makeArrAB() {
        arrA = new ArrayList<>();
        arrB = new ArrayList<>();

        int[][] diceA = new int[N / 2][6];
        int[][] diceB = new int[N / 2][6];
        int a = 0, b = 0;
        // 주사위를 A와 B의 조합으로 분리
        for (int i = 0; i < N; i++) {
            if (choice.contains(i)) { // A의 조합
                diceA[a++] = dices[i];
            } else { // B의 조합
                diceB[b++] = dices[i];
            }
        }

        // 재귀적으로 arrA와 arrB의 모든 가능한 점수 합 계산
        makeArr(0, diceA, 0, arrA);
        makeArr(0, diceB, 0, arrB);
    }

    /*
        arrA의 각 점수에 대해 arrB에서 이 점수보다 작은 값들의 개수를 계산.
        이분 탐색을 사용하여 효율적으로 arrB에서 각 점수보다 작은 요소의 개수를 찾는다.
        이렇게 계산된 값들의 합이 A의 승리 횟수.
    */
    public static int calculateWinningPercent() {
        int count = 0;

        makeArrAB(); // arrA와 arrB 리스트를 생성

        // arrB를 정렬하여 이진 탐색이 가능하게 함
        Collections.sort(arrB);
        for (int i = 0; i < arrA.size(); i++) {
            int number = arrA.get(i);

            int left = 0, right = arrB.size() - 1;
            int index = Integer.MIN_VALUE;
            // 이진 탐색으로 number보다 작은 arrB의 원소 개수 찾기
            while (left <= right) {
                int middle = (left + right) / 2;
                if (arrB.get(middle) < number) {
                    left = middle + 1;
                    index = Math.max(index, middle);
                } else {
                    right = middle - 1;
                }
            }
            // index가 갱신된 경우, 승리 횟수에 추가
            if (index != Integer.MIN_VALUE) {
                count += index + 1;
            }
        }
        return count; // 계산된 승리 횟수 반환
    }

    /*
        가능한 모든 주사위 조합을 백트래킹으로 생성.
        각 조합에 대해 calculateWinningPercent 함수를 호출하여 승리 횟수를 계산하고,
        이를 최대화하는 조합을 찾는다.
    */
    public static void choiceDice(int depth, int s) {
        if (depth == N / 2) {
            int winning = calculateWinningPercent();
            if (max < winning) {
                max = winning;
                for (int i = 0; i < choice.size(); i++) {
                    answer[i] = choice.get(i) + 1;
                }
            }
            return;
        }
        for (int i = s; i < N; i++) {
            choice.add(i);
            choiceDice(depth + 1, i + 1);
            choice.remove(choice.size() - 1);
        }
    }

    public static int[] solution(int[][] dice) {
        N = dice.length;
        dices = dice;
        answer = new int[N / 2];
        choiceDice(0, 0);
        return answer;
    }

    public static void main(String[] args) {
        int[][] dice1 = {
                {1, 2, 3, 4, 5, 6},
                {3, 3, 3, 3, 4, 4},
                {1, 3, 3, 4, 4, 4},
                {1, 1, 4, 4, 5, 5}
        };
        System.out.println(Arrays.toString(solution(dice1))); // [1, 4]
    }
}
