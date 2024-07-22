package week12.lim.week12_1.silver.n_10816;

// 문제: https://www.acmicpc.net/problem/10816
// 참고: https://st-lab.tistory.com/267

import java.io.*;
import java.util.*;

public class Main2 {
    /**
     *  1. 숫자 N개를 갖고 있다.
     *  2. 정수 M개가 주어졌을 떄, 이 수가 적혀있는 숫자 카드를
     *     몇 개 가지고 있는지 출력.
     *
     *  [접근]
     *
     *
     *  [풀이]
     *  이진 탐색 사용
     *  - 요청된 카드 숫자에 대해 이진 탐색을 수행하여 해당 숫자가 시작되는 인덱스와 끝나는 인덱스를 찾아야 함.
     *  - lowerBound는 주어진 값 이상이 처음 등장하는 위치를 찾고,
     *    upperBound는 주어진 값 초과가 처음 등장하는 위치를 찾는다.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] myCards = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            myCards[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(myCards);

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int[] yourCards = new int[M];
        for (int i = 0; i < M; i++) {
            yourCards[i] = Integer.parseInt(st.nextToken());
        }

        StringBuilder output = new StringBuilder();
        for (int yourCard : yourCards) {
            int count = upperBound(myCards, yourCard) - lowerBound(myCards, yourCard);
            output.append(count).append(" ");
        }

        System.out.println(output);
    }

    // 주어진 값 이상이 처음 등장하는 위치
    private static int lowerBound(int[] array, int target) {
        int low = 0, high = array.length;
        while (low < high) {
            int mid = (low + high) / 2;
            if (array[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    // 주어진 값 초과가 처음 등장하는 위치
    private static int upperBound(int[] array, int target) {
        int low = 0, high = array.length;
        while (low < high) {
            int mid = (low + high) / 2;
            if (array[mid] <= target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}
