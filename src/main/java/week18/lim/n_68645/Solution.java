package week18.lim.n_68645;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/68645

import java.util.*;

public class Solution {
    /**
     *  정수 n이 매개변수로 주어집니다.
     *  다음 그림과 같이 밑변의 길이와 높이가 n인 삼각형에서 맨 위 꼭짓점부터 반시계 방향으로 달팽이 채우기를 진행한 후,
     *  첫 행부터 마지막 행까지 모두 순서대로 합친 새로운 배열을 return 하도록 solution 함수를 완성해주세요.
     */
    public static int[] solution(int n) {
        /*
            2차원 배열 생성: 먼저 n 크기의 2차원 배열을 생성.
            방향 설정: 반시계 방향으로 채우기 위해, 아래로 이동, 오른쪽으로 이동, 위로 이동하는 순서로 방향을 설정.
            숫자 채우기: 주어진 방향에 따라 삼각형 모양으로 숫자를 채움.
            결과 배열 생성: 최종적으로 2차원 배열에서 숫자를 순서대로 읽어 1차원 배열로 반환.
        */

        int[][] triangle = new int[n][n];  // 삼각형을 채울 2차원 배열
        int num = 1;                       // 채워넣을 숫자
        int x = -1, y = 0;                 // 시작 좌표 (초기 위치를 위해 x를 -1로 설정)

        /*
            i % 3에 따라 이동 방향을 결정. (0 -> 1 -> 2가 반복되니까)
            i % 3 == 0: 아래로 이동
            i % 3 == 1: 오른쪽으로 이동
            i % 3 == 2: 위로 이동
            반복문을 통해 해당 방향으로 숫자를 채운다.
        */
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (i % 3 == 0) {          // 아래로 이동
                    x++;
                } else if (i % 3 == 1) {   // 오른쪽으로 이동
                    y++;
                } else {                   // 위로 이동
                    x--;
                    y--;
                }
                triangle[x][y] = num++;    // 숫자를 채우고 다음 숫자로 증가
            }
        }

        // 2차원 배열을 1차원 배열로 변환
        List<Integer> resultList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                resultList.add(triangle[i][j]);
            }
        }

        // 결과를 1차원 배열로 변환하여 반환
        return resultList.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(4)));
        // [1,2,9,3,10,8,4,5,6,7]

        System.out.println(Arrays.toString(solution(5)));
        // [1,2,12,3,13,11,4,14,15,10,5,6,7,8,9]

        System.out.println(Arrays.toString(solution(6)));
        // [1,2,15,3,16,14,4,17,21,13,5,18,19,20,12,6,7,8,9,10,11]
    }
}
