package week17.lim.n_77885;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/77885

import java.util.*;

public class Solution {
    public static long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            long num = numbers[i];

            if (num % 2 == 0) {
                // 짝수인 경우, 바로 다음 홀수는 비트가 1개 다른 경우.
                // -> 비트가 1개 다른 가장 작은 수는 num + 1
                answer[i] = num + 1;
            } else {
                // 홀수인 경우, 최소 비트 차이를 만들기 위해
                // 가장 오른쪽의 0을 1로 바꾸고, 그 다음 1을 0으로 바꾼다.
                long bitMask = 1;
                // 가장 오른쪽의 0 위치를 찾기 위해 사용됨.

                while ((num & bitMask) != 0) {
                    bitMask <<= 1;
                }
                /*
                    num & bitMask가 0이 될 때까지 bitMask를 왼쪽으로 이동(shift)시키고,
                    bitMask는 오른쪽부터 0이 있는 첫 번째 위치를 가리키게 된다.
                    bitMask가 해당 위치의 0을 1로 변경하게 된다.

                    그 다음 1을 0으로 바꾸기 위해 bitMask >> 1을 사용하여 그 위치의 값을 빼준다.
                */

                answer[i] = num + bitMask - (bitMask >> 1);
                /*
                    num + bitMask는 이 0을 1로 바꾸는 역할.
                    예를 들어, num이 7(111)이라면,
                    bitMask는 8(1000) 위치를 가리키게 되며, num + bitMask는 15(1111)가 됨.

                    bitMask >> 1은 bitMask의 바로 직전 비트 위치(즉, 처음으로 0이었던 위치 바로 왼쪽의 비트)를 가리킨다.
                    이 비트를 0으로 바꾸기 위해, num + bitMask에서 bitMask >> 1을 빼준다.

                    [예시]
                    cf.)
                    A  B   AND
                    0  0    0
                    0  1    0
                    1  0    0
                    1  1    1

                    입력 값: num = 7 (111 이진수)

                    bitMask 계산:
                        초기 bitMask = 1 (001 이진수)
                        num & bitMask = 1, bitMask <<= 1
                        bitMask = 2 (010 이진수)
                        num & bitMask = 2, bitMask <<= 1
                        bitMask = 4 (100 이진수)
                        num & bitMask = 4, bitMask <<= 1
                        bitMask = 8 (1000 이진수) -> num & bitMask = 0, 이때 루프를 종료

                    num + bitMask:
                        num + bitMask = 7 + 8 = 15 (1111 이진수)
                        (0111 -> 1111)

                    bitMask >> 1:
                        bitMask >> 1 = 4 (100 이진수)

                    최종 계산:
                        answer[i] = 15 - 4 = 11
                        (1111 - 0100 = 1011 이진수)
                        (0111 -> 1111 -> 1011)

                    [정리]
                    num + bitMask - (bitMask >> 1)는 오른쪽에서 가장 첫 번째 0을 1로 바꾸고,
                    그 다음 비트(바뀐 0 바로 왼쪽에 있는 1)를 0으로 바꾸는 역할을 힌다.
                */
            }
        }

        return answer;

        /*
        for (int i = 0; i < numbers.length; i++) {
            long candidate = numbers[i] + 1;
            while (true) {
                long xorResult = numbers[i] ^ candidate;
                // 두 숫자 간 비트 차이는 XOR 연산으로

                int bitCount = Long.bitCount(xorResult);

                if (bitCount <= 2) {
                    answer.add(candidate);
                    break;
                }
                candidate++;
            }
        }
        -> 시간 초괴 실패 (테스트 10, 11)
        */
    }

    public static void main(String[] args) {
        long[] numbers = {2, 7};
        System.out.println(Arrays.toString(solution(numbers)));
    }
}
