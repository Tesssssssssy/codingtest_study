package week14.lim.silver.n_30804.practice;

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
     *  앞에서 a개, 뒤에서 b개의 과일을 빼면 S_{a+1}, S_{a+2}, ... , S_{N-b-1}, S_{N-b} 과일,
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
        int N = Integer.parseInt(br.readLine());  // 과일 개수
        int[] fruits = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            fruits[i] = Integer.parseInt(st.nextToken());
        }

        HashMap<Integer, Integer> fruitsCnt = new HashMap<>();
        int maxLength = 0; // 최대 과일 개수
        int left = 0; // 왼쪽 포인터

        for (int right = 0; right < N; right++) {
            // map에 과일 정보 입력 받는다.
            fruitsCnt.put(fruits[right], fruitsCnt.getOrDefault(fruits[right], 0) + 1);

            // 2 종류만 사용해야 하므로 map 크기가 2 이상이고
            // map에서 value가 0이면 사용하지 않는 것이니까 map에서 뺀다.
            while (fruitsCnt.size() > 2) {
                // 왼쪽 포인터인 left를 통해 왼쪽부터 하나씩 뺀다.
                fruitsCnt.put(fruits[left], fruitsCnt.get(fruits[left]) - 1);

                // 왼쪽부터 하나씩 뺐는데, map에서 확인 결과 크기가 0이면
                // 사용하지 않은 것이니까 아예 map에서도 뺀다.
                if (fruitsCnt.get(fruits[left]) == 0) {
                    fruitsCnt.remove(fruits[left]);
                }

                left++;
            }
            maxLength = Math.max(maxLength, right - left + 1);
        }
        System.out.println(maxLength);
    }
}
