package week13.baek.august06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class S1189 {
    static int N, M; // N: 기타의 수, M: 노래의 수
    static int maxSongs = 0; // 최대 커버할 수 있는 노래의 개수
    static int minGuitars = Integer.MAX_VALUE; // 최대 커버할 수 있는 노래를 커버하기 위한 최소 기타 수
    static boolean[][] coverage; // 각 기타가 어떤 노래를 커버하는지 저장하는 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 기타와 노래의 커버 관계를 저장할 배열 초기화
        coverage = new boolean[N][M];

        // 각 기타가 어떤 노래를 커버하는지 입력 받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            st.nextToken(); // 기타의 이름을 건너뜀 (사용하지 않음)
            String s = st.nextToken(); // 노래의 커버 상태를 나타내는 문자열

            // 문자열을 사용하여 각 노래의 커버 여부를 배열에 저장
            for (int j = 0; j < M; j++) {
                coverage[i][j] = s.charAt(j) == 'Y'; // 'Y'이면 true, 아니면 false
            }
        }

        // 모든 기타 조합을 고려하여 최대 커버할 수 있는 노래의 개수와 최소 기타 수를 찾음
        dfs(0, 0, new boolean[N]);

        // 결과 출력: 최대 커버할 수 있는 노래가 없으면 -1 출력, 그렇지 않으면 최소 기타 수 출력
        System.out.println(maxSongs == 0 ? -1 : minGuitars);
    }

    /**
     * 모든 조합을 생성하고, 각 조합에서 커버할 수 있는 노래의 개수를 계산하여 최대 값을 갱신함.
     *
     * dep: 현재 깊이
     * guitarsUsed: 현재 선택한 기타의 수
     * selected: 현재 선택된 기타 배열
     */
    public static void dfs(int dep, int guitarsUsed, boolean[] selected) {
        // 모든 기타를 고려한 경우
        if (dep == N) {
            // 현재 조합에서 커버할 수 있는 노래의 개수 계산
            boolean[] songsCovered = new boolean[M];

            for (int i = 0; i < N; i++) {
                if (selected[i]) {
                    for (int j = 0; j < M; j++) {
                        if (coverage[i][j]) songsCovered[j] = true; // 해당 기타가 커버하는 노래를 true로 설정
                    }
                }
            }

            // 현재 조합에서 커버된 노래의 개수 계산
            int coveredCount = 0;
            for (boolean covered : songsCovered) if (covered) coveredCount++; // 커버된 노래의 개수 카운트

            // 현재 커버된 노래의 개수가 최대일 경우
            if (coveredCount == maxSongs) {
                minGuitars = Math.min(minGuitars, guitarsUsed); // 최소 기타 수 갱신
            }

            else if (coveredCount > maxSongs) {
                maxSongs = coveredCount; // 최대 커버된 노래 수 갱신
                minGuitars = guitarsUsed; // 현재 기타 수로 최소 기타 수 갱신
            }

            return;
        }

        // 현재 기타를 선택한 경우
        selected[dep] = true;
        dfs(dep + 1, guitarsUsed + 1, selected);

        // 현재 기타를 선택하지 않은 경우
        selected[dep] = false;
        dfs(dep + 1, guitarsUsed, selected);
    }
}
