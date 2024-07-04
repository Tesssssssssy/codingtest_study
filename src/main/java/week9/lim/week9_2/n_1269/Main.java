package week9.lim.week9_2.n_1269;

// 문제: https://www.acmicpc.net/problem/1269

import java.io.*;
import java.util.*;

public class Main {
    /**
     *  자연수를 원소로 갖는 공집합이 아닌 두 집합 A와 B가 있다.
     *  이때, 두 집합의 대칭 차집합의 원소의 개수를 출력하는 프로그램을 작성해라.
     *  두 집합 A와 B가 있을 때, (A-B)와 (B-A)의 합집합을 A와 B의 대칭 차집합이라고 함.
     *
     *  A = { 1, 2, 4 } 이고, B = { 2, 3, 4, 5, 6 } 라고 할 때,
     *  A-B = { 1 } 이고, B-A = { 3, 5, 6 } 이므로,
     *  대칭 차집합의 원소의 개수는 1 + 3 = 4개
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int aSize = Integer.parseInt(st.nextToken());
        int bSize = Integer.parseInt(st.nextToken());

        Set<Integer> setA = new HashSet<>();
        Set<Integer> setB = new HashSet<>();

        // 집합 A의 원소 읽기
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < aSize; i++) {
            setA.add(Integer.parseInt(st.nextToken()));
        }

        // 집합 B의 원소 읽기
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < bSize; i++) {
            setB.add(Integer.parseInt(st.nextToken()));
        }

        // 집합 A와 B의 교집합 찾기
        Set<Integer> intersection = new HashSet<>(setA);
        intersection.retainAll(setB);

        // 교집합 원소를 각 집합에서 제거
        setA.removeAll(intersection);
        setB.removeAll(intersection);

        // 대칭 차집합의 크기 계산
        int symmetricDifferenceSize = setA.size() + setB.size();

        // 결과 출력
        System.out.println(symmetricDifferenceSize);
    }
}
