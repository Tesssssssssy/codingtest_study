package week9.lim.week9_2.n_19941;

// 문제: https://www.acmicpc.net/problem/19941

import java.io.*;

public class Main {
    /**
     *  그리디(greedy) 문제.
     *      그리디 방식으로 해결할 수 있는 가장 간단한 접근 방법 중 하나는,
     *      각 사람이 햄버거를 찾을 때 가능한 한 가장 가까운 햄버거를 먹게 하고,
     *      해당 햄버거를 더 이상 사용할 수 없도록 하는 것
     *
     *  투 포인터(two pointers) 기법을 사용하여 사람과 햄버거의 위치를 관리할 수 있다.
     *  햄버거의 위치를 관리하는 포인터와 사람의 위치를 관리하는 포인터를 사용하여,
     *  각 사람이 햄버거를 먹을 수 있는지 확인하고 카운트.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        int N = Integer.parseInt(inputs[0]);
        int K = Integer.parseInt(inputs[1]);
        String line = br.readLine();

        // 햄버거와 사람의 위치를 저장할 배열
        int[] burgers = new int[N];
        int[] people = new int[N];
        int burgerCount = 0, peopleCount = 0;

        // 위치 정보를 배열로 저장
        for (int i = 0; i < N; i++) {
            char ch = line.charAt(i);
            if (ch == 'H') {
                burgers[burgerCount++] = i;
            } else if (ch == 'P') {
                people[peopleCount++] = i;
            }
        }

        int answer = 0;
        int i = 0, j = 0;

        // 두 포인터 기법을 사용하여 최대 매칭 수 찾기
        while (i < burgerCount && j < peopleCount) {
            if (Math.abs(burgers[i] - people[j]) <= K) {
                // 햄버거를 먹을 수 있는 경우
                answer++;
                i++; // 다음 햄버거로 이동
                j++; // 다음 사람으로 이동
            } else if (burgers[i] < people[j] - K) {
                // 햄버거 위치가 사람의 범위보다 왼쪽에 있는 경우
                i++;
            } else {
                // 햄버거 위치가 사람의 범위보다 오른쪽에 있는 경우
                j++;
            }
        }

        System.out.println(answer);
    }
}
