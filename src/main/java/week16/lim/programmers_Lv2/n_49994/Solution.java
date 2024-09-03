package week16.lim.programmers_Lv2.n_49994;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/49994

import java.util.*;

public class Solution {
    public static int solution(String dirs) {
        // 좌표를 저장할 변수
        int x = 0, y = 0;

        // 이미 지나간 길을 저장할 Set
        Set<String> visited = new HashSet<>();

        // 이동 방향에 대한 좌표 변화
        int[] dx = {0, 0, 1, -1}; // R, L, U, D에 따른 x 변화
        int[] dy = {1, -1, 0, 0}; // R, L, U, D에 따른 y 변화
        String directions = "RLUD"; // 방향 문자열

        int count = 0; // 처음 걸어본 길의 길이

        // 명령어를 하나씩 처리
        for (char dir : dirs.toCharArray()) {
            int idx = directions.indexOf(dir);
            int nx = x + dx[idx];
            int ny = y + dy[idx];

            // 좌표가 경계를 벗어나면 무시
            if (nx < -5 || nx > 5 || ny < -5 || ny > 5) {
                continue;
            }

            // 현재 위치에서 다음 위치로 가는 길과 반대 길을 기록
            String path1 = x + "" + y + "" + nx + "" + ny; // 0 + "" + 0 + "" + 0 + "" + 1 = 0001
            String path2 = nx + "" + ny + "" + x + "" + y; // 0 + "" + 1 + "" + 0 + "" + 0 = 0100

            // 길을 처음 지나가는 경우에만 길이 증가
            if (!visited.contains(path1) && !visited.contains(path2)) {
                visited.add(path1);
                visited.add(path2);
                count++;
            }

            // 좌표 업데이트
            x = nx;
            y = ny;
        }

        return count;
    }

    public static void main(String[] args) {
        System.out.println(solution("ULURRDLLU")); // 7
        System.out.println(solution("LULLLLLLU")); // 7
    }
}
