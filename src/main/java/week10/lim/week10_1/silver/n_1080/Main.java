package week10.lim.week10_1.silver.n_1080;

// 문제: https://www.acmicpc.net/problem/1080

import java.io.*;
import java.util.*;

public class Main {
    /**
     *  1. 0과 1로만 이루어진 행렬 A, B
     *  2. 행렬을 변환하는 연산은 "어떤 3×3크기의 부분 행렬에 있는 모든 원소를 뒤집는 것".
     *     (0 → 1, 1 → 0)
     *  3. 행렬 A를 행렬 B로 바꾸는데 필요한 연산의 횟수의 최솟값 출력
     *
     *  [풀이]
     *  행렬 A와 B의 각 원소를 비교.
     *  첫 번째로 다른 원소를 만나면, 그 위치를 기준으로 3x3 부분 행렬을 뒤집을 수 있는지 확인.
     *
     *  뒤집기 가능 여부: i + 2 < N과 j + 2 < M을 만족해야만 3x3 뒤집기 가능.
     *                만약 가능하다면 뒤집고, 연산 횟수 증가.
     *
     * 최종 검증: 모든 뒤집기 연산 후에 행렬 A가 B와 완전히 동일한지 확인.
     *          만약 동일하지 않다면, -1 출력.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        char[][] matrixA = new char[N][]; // M 넣을 때 에러나므로 값 받고 길이만큼 자동 할당하도록.
        char[][] matrixB = new char[N][];

        // 행렬 A 입력
        for (int i = 0; i < N; i++) {
            matrixA[i] = br.readLine().toCharArray();
        }

        // 행렬 B 입력
        for (int i = 0; i < N; i++) {
            matrixB[i] = br.readLine().toCharArray();
        }

        // 연산 횟수 계산
        int operationCount = 0;
        for (int i = 0; i <= N - 3; i++) {
            for (int j = 0; j <= M - 3; j++) {
                /*
                    3x3 크기의 부분 행렬을 뒤집기 위해 필요한 최소 크기를 보장하기 위한 조건.
                    행렬의 각 원소 (i, j)에 대해, i + 2와 j + 2가 행렬의 경계 내에 있도록 하여
                    3x3 부분 행렬이 항상 행렬 크기 내에 완전히 포함되도록.

                    즉, 행렬의 크기가 3x3 부분 행렬 뒤집기를 수행할 수 있을 만큼 충분히 큰 경우에만
                    그 부분 행렬의 좌상단 시작점 (i, j)를 순회
                */

                // 행렬 A의 현재 위치의 값과 행렬 B의 해당 값이 다르면 3x3 뒤집기 수행
                if (matrixA[i][j] != matrixB[i][j]) {
                    flip3x3(matrixA, i, j); // 3x3 부분 행렬 뒤집기
                    operationCount++; // 뒤집기 연산 카운트 증가
                }
            }
        }

        // 최종 결과가 같은지 확인
        if (check(matrixA, matrixB)) {
            System.out.println(operationCount); // 변환 성공 시 연산 횟수 출력
        } else {
            System.out.println(-1); // 변환 불가능 시 -1 출력
        }
    }

    // 주어진 위치에서 3x3 크기의 부분 행렬을 뒤집는 함수
    private static void flip3x3(char[][] matrix, int x, int y) {
        for (int i = x; i < x + 3; i++) {
            for (int j = y; j < y + 3; j++) {
                matrix[i][j] = matrix[i][j] == '0' ? '1' : '0'; // 0을 1로, 1을 0으로 변환
            }
        }
    }

    // 행렬 A와 B가 완전히 동일한지 검사
    private static boolean check(char[][] matrixA, char[][] matrixB) {
        for (int i = 0; i < matrixA.length; i++) {
            for (int j = 0; j < matrixA[0].length; j++) {
                if (matrixA[i][j] != matrixB[i][j]) {
                    return false; // 하나라도 다르면 false 반환
                }
            }
        }
        return true; // 모두 같으면 true 반환
    }
}
