package week12.lim.week12_1.silver.n_10816;

// 문제: https://www.acmicpc.net/problem/10816

import java.io.*;
import java.util.*;

public class Main {
    /**
     *  1. 숫자 N개를 갖고 있다.
     *  2. 정수 M개가 주어졌을 떄, 이 수가 적혀있는 숫자 카드를
     *     몇 개 가지고 있는지 출력.
     *
     *  [풀이]
     *  해시맵 사용
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        HashMap<Integer, Integer> myCardsMap = new HashMap<>();

        for (int i = 0; i < N; i++) {
            int card = Integer.parseInt(st.nextToken());
            if (myCardsMap.containsKey(card)) {
                myCardsMap.put(card, myCardsMap.get(card) + 1);
            } else {
                myCardsMap.put(card, 1);
            }
        }

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int[] yourCards = new int[M];

        StringBuilder output = new StringBuilder();
        for (int i = 0; i < M; i++) {
            yourCards[i] = Integer.parseInt(st.nextToken());
            output.append(myCardsMap.getOrDefault(yourCards[i], 0)).append(" ");
        }

        System.out.println(output);
    }
}
