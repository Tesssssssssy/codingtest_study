package week18.lim.n_68936.practice;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/68936

import java.util.Arrays;

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
        // 결과 저장 배열
        int[] result = new int[2];

        // 현재 영역이 모두 같은 값으로 되어 있는지 확인
        if (isUniform(arr, x, y, size)) {
            result[arr[x][y]]++;
            return result;
        }

        // 배열을 4개의 균일한 정사각형으로 분리하기 위해 size / 2
        int newSize = size / 2;

        int[] topLeft = compress(arr, x, y, newSize);
        int[] topRight = compress(arr, x, y + newSize, newSize);
        int[] bottomLeft = compress(arr, x + newSize, y, newSize);
        int[] bottomRight = compress(arr, x + newSize, y + newSize, newSize);

        result[0] = topLeft[0] + topRight[0] + bottomLeft[0] + bottomRight[0];
        result[1] = topLeft[1] + topRight[1] + bottomLeft[1] + bottomRight[1];

        return result;
    }

    private static boolean isUniform(int[][] arr, int x, int y, int size) {
        // 현재 영역이 모두 같은 값으로 되어있는지 확인하는 메소드

        // 기준값: 첫 번째 값
        int value = arr[x][y];

        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (arr[i][j] != value) {
                    return false;
                }
            }
        }

        return true;
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
