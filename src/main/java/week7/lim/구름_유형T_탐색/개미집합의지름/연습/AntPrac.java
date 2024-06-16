package week7.lim.구름_유형T_탐색.개미집합의지름.연습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class AntPrac {
    /**
     *  1. N, D (N마리, 지름 D이하) 입력받기
     *     개미 위치 입력받기
     *  2. 입력받은 D 파싱 후 저장 (지름)
     *  3. 입력받은 개미 위치 배열 파싱 후 저장
     *  4. 개미 위치 정렬
     *  5. 최소 제거 개미 수를 최대 개미 수로 초기화
     *     (반복하면서 min값으로 갱신할거니까)
     *  6. 투 포인터 초기화
     *  7. right 포인터를 배열 끝까지 이동시키면서 min값 갱신
     *     (right-left가 D 이하여야 && right가 개미 개수(배열 크기)보다 작아야)
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inputStrNnD = br.readLine().split(" ");
        int D = Integer.parseInt(inputStrNnD[1]);

        String[] antPositionsStr = br.readLine().split(" ");
        int[] antPositions = new int[antPositionsStr.length];

        for (int i = 0; i < antPositionsStr.length; i++) {
            antPositions[i] = Integer.parseInt(antPositionsStr[i]);
        }

        Arrays.sort(antPositions);

        int minRemovedAntCnt = antPositions.length;

        int left = 0;
        int right = 0;

        int antPositionsLength = antPositions.length;

        while (right < antPositionsLength) {
            while (right < antPositionsLength && antPositions[right] - antPositions[left] <= D) {
                right++;
            }
            minRemovedAntCnt = Math.min(minRemovedAntCnt, antPositionsLength - (right - left));
            left++;
        }

        System.out.println(minRemovedAntCnt);
    }
}

/*
    '슬라이딩 윈도우' 알고리즘은 배열이나 리스트에서 연속된 데이터의 부분 집합을
    효율적으로 처리하기 위해 윈도우(범위)를 슬라이딩(이동) 시키면서 작업을 수행하는 방법.

    투 포인터 기법은 슬라이딩 윈도우 알고리즘의 일종으로 볼 수 있다.
    이 방식에서는 일반적으로 두 개의 포인터(변수)를 사용해 배열의 서로 다른 위치를 가리키면서,
    조건에 맞는 데이터의 범위를 찾거나 조정함.

    슬라이딩 윈도우 기법을 사용하면, 데이터의 순차적 접근이 반복되는 것을 효율적으로 처리할 수 있다.

    투 포인터 알고리즘은 주로 정렬된 배열에서 두 포인터를 사용해 범위를 조절하며
    특정 조건을 만족하는 해를 찾는데 적합한 기법.

    이 과정은 left 포인터를 배열의 시작부터 끝까지 이동시키고,
    right 포인터로 해당 범위를 확장하는 방식으로 진행되어
    범위 내의 조건을 만족하는 최대 집합을 효율적으로 찾는 데 도움을 줌.
*/