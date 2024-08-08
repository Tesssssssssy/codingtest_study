package week13.baek.august06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S2961 {
    static int n; // 음식의 개수
    static int[] sour, bitter; // 각각 음식의 신맛과 쓴맛을 저장할 배열
    static int min = Integer.MAX_VALUE; // 최소 차이를 저장할 변수, 초기값은 최대값으로 설정

    // 깊이 우선 탐색(DFS) 함수
    public static void dfs(int index, int sourProduct, int bitterSum) {
        // 모든 음식을 검사한 경우
        if (index == n) {
            // 최소 차이 갱신: 하나 이상의 음식을 선택한 경우
            if (sourProduct > 1) {
                min = Math.min(min, Math.abs(sourProduct - bitterSum));
            }
            return;
        }

        // 현재 음식을 포함하는 경우
        dfs(index + 1, sourProduct * sour[index], bitterSum + bitter[index]);

        // 현재 음식을 포함하지 않는 경우
        dfs(index + 1, sourProduct, bitterSum);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine()); // 음식의 개수 입력

        sour = new int[n]; // 신맛 배열 초기화
        bitter = new int[n]; // 쓴맛 배열 초기화
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            sour[i] = Integer.parseInt(st.nextToken()); // 신맛 입력
            bitter[i] = Integer.parseInt(st.nextToken()); // 쓴맛 입력
        }

        // DFS 시작: 초기 인덱스 0, 신맛의 곱은 1, 쓴맛의 합은 0으로 시작
        dfs(0, 1, 0);

        // 결과 출력: 최소 차이
        System.out.println(min);
    }
}
