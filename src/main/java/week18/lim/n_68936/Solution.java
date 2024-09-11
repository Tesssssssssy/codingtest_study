package week18.lim.n_68936;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/68936

import java.util.*;

public class Solution {
    /**
     *  4등분 재귀
     *      영역을 4개로 분할할 때 각 부분에 대해 compress 함수를 재귀 호출하고, 각각의 결과 합산.
     *
     *  newSize 계산
     *      새로운 크기를 size / 2로 설정.
     *
     *  최종 결과 합산
     *      네 개의 분할된 영역에서 얻은 결과를 각각 합산하여 최종 결과 반환.
     *
     *  문제에서 요구하는 대로 2차원 배열을 쿼드 트리 방식으로 압축하고,
     *  최종적으로 남은 0과 1의 개수를 정확히 계산하여 반환.
     */

    public static int[] solution(int[][] arr) {
        return compress(arr, 0, 0, arr.length);
    }

    private static int[] compress(int[][] arr, int x, int y, int size) {
        // 주어진 영역이 모두 같은 값인지 확인하고,
        // 그렇지 않으면 4등분해서 재귀적으로 압축을 시도하는 메소드

        int[] result = new int[2];
        // 0과 1의 개수를 담을 배열 초기화

        // 현재 영역이 모두 동일한 값으로 이루어져 있는지 확인
        if (isUniform(arr, x, y, size)) {
            result[arr[x][y]]++;
            // 동일한 값이라면 해당 값(0 또는 1)의 개수를 증가

            return result;
        }

        // 동일하지 않다면, 영역을 4등분하여 각각 재귀적으로 처리
        int newSize = size / 2;
        // 4등분된 영역의 새로운 크기

        /*
            1 1 0 0       시작: (0, 0)  :  1 1
            1 0 0 0                       1 0
            1 0 0 1
            1 1 1 1

            -> 오른쪽 위 시작: (0, 2)    :   0 0
                                          0 0


               왼쪽 아래 시작: (2, 0)    :    1 0
                                           1 1

               오른쪽 아래 시작: (2, 2)   :   0 1
                                          1 1
        */

        int[] topLeft = compress(arr, x, y, newSize); // 왼쪽 위
        int[] topRight = compress(arr, x, y + newSize, newSize); // 오른쪽 위
        int[] bottomLeft = compress(arr, x + newSize, y, newSize); // 왼쪽 아래
        int[] bottomRight = compress(arr, x + newSize, y + newSize, newSize); // 오른쪽 아래

        // 4개의 영역에서 나온 결과를 합산하여 최종 결과 반환
        result[0] = topLeft[0] + topRight[0] + bottomLeft[0] + bottomRight[0]; // 0의 개수 합산
        result[1] = topLeft[1] + topRight[1] + bottomLeft[1] + bottomRight[1]; // 1의 개수 합산

        return result;
    }

    private static boolean isUniform(int[][] arr, int x, int y, int size) {
        // 주어진 영역이 모두 동일한 값으로 이루어져 있는지 확인하는 메소드

        int value = arr[x][y]; // 기준이 될 첫 번째 값
        for (int i = x; i < x + size; i++) { // 현재 영역의 모든 값을 순회하며
            for (int j = y; j < y + size; j++) {
                if (arr[i][j] != value) { // 하나라도 다르면 false 반환
                    return false;
                }
            }
        }
        return true; // 모두 같다면 true 반환
    }

    public static void main(String[] args) {
        int[][] arr1 = {
                {1,1,0,0},
                {1,0,0,0},
                {1,0,0,1},
                {1,1,1,1}
        };
        System.out.println(Arrays.toString(solution(arr1))); // [4, 9]

        int[][] arr2 = {
                {1,1,1,1,1,1,1,1},
                {0,1,1,1,1,1,1,1},
                {0,0,0,0,1,1,1,1},
                {0,1,0,0,1,1,1,1},
                {0,0,0,0,0,0,1,1},
                {0,0,0,0,0,0,0,1},
                {0,0,0,0,1,0,0,1},
                {0,0,0,0,1,1,1,1}
        };
        System.out.println(Arrays.toString(solution(arr2))); // [10, 15]
    }
}
