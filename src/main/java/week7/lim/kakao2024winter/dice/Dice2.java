package week7.lim.kakao2024winter.dice;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/258709#
// 참고: https://velog.io/@ddongh1122/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EC%A3%BC%EC%82%AC%EC%9C%84-%EA%B3%A0%EB%A5%B4%EA%B8%B0

import java.util.*;

public class Dice2 {
    static int N; // 주사위의 개수
    static int[][] D; // 각 주사위의 면들의 점수 배열
    static int[] maxList; // 최대 승리를 기록한 A 팀의 주사위 인덱스 배열
    static int maxWin; // 최대 승리 횟수
    static int tmpSum; // 현재 조합에서 A 팀의 승리 횟수 임시 저장

    static int[] A; // A 팀이 선택한 주사위의 인덱스
    static int[] B; // B 팀의 주사위 인덱스

    static List<Integer> sumList; // B 팀의 모든 주사위 합계를 저장하는 리스트

    // A 팀의 주사위 합을 계산하고 승리 횟수를 누적
    public static void calSumA(int ind, int sum) {
        if (ind == N / 2) {
            // 이진 탐색으로 B 팀의 점수 리스트에서 A 팀의 점수보다 작은 개수를 찾아 누적
            int lo = 0;
            int hi = sumList.size();
            while (lo + 1 < hi) {
                int mid = (lo + hi) / 2;
                if (sumList.get(mid) < sum) lo = mid;
                else hi = mid;
            }
            tmpSum += (lo + 1);
            return;
        }
        for (int i = 0; i < 6; i++)
            calSumA(ind + 1, sum + D[A[ind]][i]);
    }

    // B 팀의 주사위 합을 계산하여 리스트에 저장
    public static void calSumB(int ind, int sum) {
        if (ind == N / 2) {
            sumList.add(sum);
            return;
        }
        for (int i = 0; i < 6; i++)
            calSumB(ind + 1, sum + D[B[ind]][i]);
    }

    // A와 B 팀의 주사위 조합을 계산하고 최대 승리 조합을 갱신
    public static void calCnt() {
        sumList = new ArrayList<>();
        tmpSum = 0;
        // B 팀의 모든 주사위 합계 계산
        calSumB(0, 0);
        Collections.sort(sumList); // 이진 탐색을 위한 정렬

        // A 팀의 주사위 합 계산과 승리 횟수 계산
        calSumA(0, 0);
        if (maxWin < tmpSum) {
            maxWin = tmpSum;
            for (int i = 0; i < N / 2; i++)
                maxList[i] = A[i] + 1; // 1-indexed 조정
        }
    }

    // A 팀의 주사위 선택
    public static void select(int cur, int ind) {
        if (ind == N / 2) {
            int index = 0;
            for (int i = 0; i < N; i++) {
                boolean flag = false;
                for (int j = 0; j < N / 2; j++)
                    if (A[j] == i) flag = true;
                if (flag) continue;
                B[index++] = i;
            }
            calCnt();
            return;
        }
        if (cur >= N) return;
        A[ind] = cur;
        select(cur + 1, ind + 1);
        select(cur + 1, ind);
    }

    // 초기화 및 주사위 게임 시작
    public static void init(int[][] dice) {
        N = dice.length;
        D = dice;
        maxList = new int[N / 2];
        maxWin = Integer.MIN_VALUE;
        A = new int[N / 2];
        B = new int[N / 2];
    }

    // 주사위 게임의 최적 해결책을 찾기 위한 메인 솔루션 함수
    public static int[] solution(int[][] dice) {
        init(dice);
        select(0, 0);
        return maxList;
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
