package week8.baek.june25;

//public class B2309 {
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        int[] dorfs = new int[9];
//
//        //9명의 난쟁이의 합을 구함
//        int sum = 0;
//        for (int i = 0; i < 9; i++) {
//            dorfs[i] = Integer.parseInt(br.readLine());
//            sum += dorfs[i];
//        }
//
//        //완전 탐색 Brute-Force를 사용해서 총합에서 뺄 2명을 선별
//        for (int i = 0; i < 8; i++) {
//            for (int j = i+1; j < 9; j++) {
//                if (sum - dorfs[i] - dorfs[j] == 100) {
//                    dorfs[i] = 0;
//                    dorfs[j] = 0;
//                    Arrays.sort(dorfs);
//                    for (int k = 2; k < 9; k++) {
//                        System.out.println(dorfs[k]);
//                    }
//                    return;
//                }
//            }
//        }
//    }
//}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class B2309 {
    static int[] dorfs;
    static boolean[] visited;
    static int flag = 0;

    public static void dfs(int idx, int depth, int sum, String path) {
        // 일곱 난쟁이를 찾은 경우
        if(flag == 0) {
            if (depth == 7) {
                // 합이 100이면 출력
                if (sum == 100) {
                    String[] s = path.split(" ");
                    int[] result = new int[s.length];
                    for (int i = 0; i < s.length; i++) {
                        result[i] = Integer.parseInt(s[i]);
                    }
                    Arrays.sort(result);
                    for (int j = 0; j < 7; j++) {
                        System.out.println(result[j]);
                    }
                    flag = 1;
                }
                return;
            }
        }

        // 현재 인덱스부터 끝까지 확인
        for (int i = idx; i < 9; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(i + 1, depth + 1, sum + dorfs[i], path + dorfs[i] + " ");
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        dorfs = new int[9];
        visited = new boolean[9];

        // 난쟁이들의 키 입력 받기
        for (int i = 0; i < 9; i++) {
            dorfs[i] = Integer.parseInt(br.readLine().trim());
        }

        // dfs 호출
        dfs(0, 0, 0, "");

        br.close();
    }
}