package week14.lim.silver.n_30804;

// 문제: https://www.acmicpc.net/problem/30804

import java.io.*;
import java.util.*;

public class Main {
    /**
     *  은하는 긴 막대에 N개의 과일이 꽂혀있는 과일 탕후루를 만들었습니다.
     *  과일의 각 종류에는 1부터 9까지의 번호가 붙어있고,
     *  앞쪽부터 차례로 S_1, S_2, ... , S_N번 과일이 꽂혀있습니다.
     *
     *  과일 탕후루를 다 만든 은하가 주문을 다시 확인해보니 과일을 두 종류 이하로 사용해달라는 요청이 있었습니다.
     *  탕후루를 다시 만들 시간이 없었던 은하는, 막대의 앞쪽과 뒤쪽에서 몇 개의 과일을 빼서 두 종류 이하의 과일만 남기기로 했습니다.
     *  앞에서 a개, 뒤에서 b개의 과일을 빼면 S_{a+1}, S_{a+2}, ... , S_{N-b-1}, S_{N-b}$ 과일,
     *  총 N-(a+b)개가 꽂혀있는 탕후루가 됩니다. ( 0 <= a, b; a+b < N )
     *
     *  이렇게 만들 수 있는 과일을 두 종류 이하로 사용한 탕후루 중에서,
     *  과일의 개수가 가장 많은 탕후루의 과일 개수를 구하라.
     *
     *  [입력]
     *  첫 줄에 과일의 개수 N이 주어집니다. (1 <= N <= 200,000)
     *  둘째 줄에 탕후루에 꽂힌 과일을 의미하는 N개의 정수 S_1, ... , S_N이 공백으로 구분되어 주어집니다.( 1 <= S_i <= 9 )
     *
     *  [출력]
     *  만들 수 있는 과일을 두 종류 이하로 사용한 탕후루 중에서, 과일의 개수가 가장 많은 탕후루의 과일 개수를 첫째 줄에 출력
     *
     *  [풀이]
     *  투 포인터 & 해시맵
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());  // 과일의 개수
        int[] fruits = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            fruits[i] = Integer.parseInt(st.nextToken());
        }

        int maxLength = 0;
        int left = 0;
        Map<Integer, Integer> fruitCount = new HashMap<>();

        for (int right = 0; right < N; right++) {
            // 현재 right 포인터가 가리키는 과일을 map에 추가하거나 개수를 증가시킴
            fruitCount.put(fruits[right], fruitCount.getOrDefault(fruits[right], 0) + 1);

            // 두 종류를 초과하는 과일이 map에 있으면 범위를 조정
            while (fruitCount.size() > 2) {
                // 현재 left 포인터가 가리키는 과일의 개수를 하나 줄임
                fruitCount.put(fruits[left], fruitCount.get(fruits[left]) - 1);

                // 만약 left 포인터의 과일 개수가 0이 되면, map에서 해당 과일을 제거
                if (fruitCount.get(fruits[left]) == 0) {
                    fruitCount.remove(fruits[left]);
                }

                // left 포인터를 오른쪽으로 한 칸 이동 (탐색 범위를 좁힘)
                left++;
            }

            // 두 종류 이하의 과일만 남았을 때, 현재 구간의 길이를 계산하여 최대 길이를 갱신
            maxLength = Math.max(maxLength, right - left + 1);
        }

        // 결과 출력: 가장 긴 두 종류 이하의 과일로 구성된 탕후루의 길이
        System.out.println(maxLength);
    }
}

/*
    투 포인터와 해시맵 사용
    - left 와 right 두 포인터를 사용하여 배열을 순차적으로 탐색.
    - fruitCount 해시맵을 사용해 현재 부분 배열에 포함된 각 과일의 개수를 추적.
    - right 포인터를 오른쪽으로 이동시키며 새로운 과일을 fruitCount 에 추가.
    - 만약 과일의 종류가 두 종류를 초과하면, left 포인터를 오른쪽으로 이동시키면서 과일의 개수를 줄인다.

    최대 길이 계산
    - fruitCount.size()가 2 이하인 경우, 현재 right - left + 1의 값으로 최대 길이 maxLength 를 갱신.

    결과 출력
    - 최종적으로 구한 maxLength 를 출력합니다.
*/