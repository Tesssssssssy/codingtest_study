package week9.lim.week9_2.n_19941;

// 문제: https://www.acmicpc.net/problem/19941

import java.io.*;
import java.util.*;

public class Main2 {
    /**
     *  TreeSet를 사용하여 사람과 햄버거의 위치를 관리할 수 있다. TreeSet은 자동으로 정렬되며,
     *  주어진 키 값에 대해 이진 검색 트리를 통해 빠르게 접근할 수 있기 때문에,
     *  사람이 햄버거를 찾을 때 햄버거의 범위 내에서 가장 가까운 햄버거를 효율적으로 찾을 수 있다.
     *
     *  TreeSet을 사용하면 각 사람의 위치에서 가능한 가까운 햄버거를 찾고,
     *  그 햄버거를 먹은 후에는 TreeSet에서 해당 햄버거를 제거함으로써 이미 선택된 햄버거를 다시 선택하지 않도록 관리할 수 있다.
     *
     *  TreeSet의 ceiling 및 remove 메소드는 log(N)의 시간 복잡도를 가짐.
     *  이 접근 방법은 N번의 순회에서 각각 log(N) 작업을 수행하므로,
     *  전체적인 시간 복잡도는 O(N log N)이 됨.
     *  이는 햄버거의 개수가 많고, 범위 검색이 필요한 상황에서 매우 효과적.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        int N = Integer.parseInt(inputs[0]);
        int K = Integer.parseInt(inputs[1]);
        String line = br.readLine();

        TreeSet<Integer> burgerPositions = new TreeSet<>();

        for (int i = 0; i < N; i++) {
            if (line.charAt(i) == 'H') {
                burgerPositions.add(i);
            }
        }

        int maxPeople = 0;

        for (int i = 0; i < N; i++) {
            if (line.charAt(i) == 'P') {
                // 사람 위치에서 K 거리 내의 햄버거를 찾기
                // TreeSet의 ceiling 메소드를 사용하여 현재 사람 위치에서 가능한 가장 가까운 햄버거를 찾는다.
                Integer burger = burgerPositions.ceiling(i - K);
                if (burger != null && burger <= i + K) {
                    // 햄버거가 사람 위치 ±K 범위 내에 있으면
                    maxPeople++;
                    burgerPositions.remove(burger);  // 햄버거를 섭취하고 제거
                }
            }
        }

        System.out.println(maxPeople);
    }
}
