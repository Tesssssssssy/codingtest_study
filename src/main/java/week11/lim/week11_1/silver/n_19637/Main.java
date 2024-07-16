package week11.lim.week11_1.silver.n_19637;

// 문제: https://www.acmicpc.net/problem/19637

import java.io.*;
import java.util.*;

public class Main {
    /**
     *  1. 밀리는 전투력 시스템을 만들어, 캐릭터가 가진 전투력을 기준으로 칭호를 붙여주려고 한다.
     *  2. 전투력 10,000 이하의 캐릭터는 WEAK,
     *     10,000 초과 그리고 100,000 이하의 캐릭터는 NORMAL,
     *     100,000 초과 그리고 1,000,000 이하의 캐릭터는 STRONG
     *     칭호를 붙여준다.
     *  3. 캐릭터의 전투력에 맞는 칭호를 출력.
     *
     *  [입력]
     *  - 칭호의 개수 N  / 칭호를 출력해야 하는 캐릭터들의 개수 M
     *  - N개의 줄에 각 칭호의 이름을 나타내는 길이 1 이상, 11 이하의 영어 대문자로만 구성된 문자열
     *    해당 칭호의 전투력 상한값을 나타내는 109 이하의 음이 아닌 정수
     *  - 칭호는 전투력 상한값의 비내림차순으로 주어짐.
     *  - N + 2번째 줄부터 M개의 각 줄에는 캐릭터의 전투력을 나타내는 음이 아닌 정수가 주어짐.
     *
     *  [출력]
     *  M개의 줄에 걸쳐 캐릭터의 전투력에 맞는 칭호를 입력 순서대로 출력
     *  어떤 캐릭터의 전투력으로 출력할 수 있는 칭호가 여러 개인 경우 가장 먼저 입력된 칭호 하나만 출력
     *
     *  [풀이]
     *  - 칭호와 상한값을 리스트에 저장.
     *  - 각 캐릭터의 전투력에 대해 적절한 칭호를 찾는다.
     *  - 전투력은 상한값을 이진 탐색(binary search)로 빠르게 찾아 효율을 높인다.
     */
    static class Title {
        String name;
        int powerLimit;

        Title(String name, int powerLimit) {
            this.name = name;
            this.powerLimit = powerLimit;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 칭호의 개수
        int M = Integer.parseInt(st.nextToken()); // 캐릭터의 개수

        Title[] titles = new Title[N];

        // 칭호와 그 전투력 상한값을 입력받는다.
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            int powerLimit = Integer.parseInt(st.nextToken());
            titles[i] = new Title(name, powerLimit);
        }

        StringBuilder sb = new StringBuilder();

        // 각 캐릭터의 전투력에 대해 칭호를 찾아 출력한다.
        for (int i = 0; i < M; i++) {
            int power = Integer.parseInt(br.readLine());
            String titleName = findTitle(titles, power);
            sb.append(titleName).append("\n");
        }

        System.out.print(sb);
    }

    // 이진 탐색을 사용하여 주어진 전투력에 맞는 칭호를 찾는다.
    static String findTitle(Title[] titles, int power) {
        int left = 0;
        int right = titles.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (titles[mid].powerLimit < power) {
                left = mid + 1;
            } else if (titles[mid].powerLimit >= power) {
                right = mid - 1;
            }
        }

        return titles[left].name;
    }
}
