package week17.lim.n_17679.practice;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/17679

public class Solution {
    public static int solution(int m, int n, String[] board) {
        // 입력받은 board를 char 2차원 배열 map에 저장.
        char[][] map = new char[m][n];

        // map 초기화
        for (int i = 0; i < m; i++) {
            map[i] = board[i].toCharArray();
        }

        // 총 제거한 블록 개수
        int totalRemoved = 0;

        while (true) {
            // 제거한 블록 개수
            int removed = 0;

            // 제거 표시할 boolean 2차원 배열
            boolean[][] toRemove = new boolean[m][n];

            // 2 x 2 블록 찾기
            for (int i = 0; i < m - 1; i++) {
                for (int j = 0; j < n - 1; j++) {
                    char block = map[i][j];

                    // 만약 블록이 ' '가 아니고 이웃한 2x2 배열이 모두 같다면 제거 true 처리
                    if (block != ' ' && block == map[i][j + 1]
                            && block == map[i + 1][j] && block == map[i + 1][j + 1]) {
                        toRemove[i][j] = true;
                        toRemove[i][j + 1] = true;
                        toRemove[i + 1][j] = true;
                        toRemove[i + 1][j + 1] = true;
                    }
                }
            }

            // 제거할 블록 체크 및 제거
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    // 만약 해당 블록이 제거 true한 블록이면 map을 ' '로 처리 + removed 개수 증가
                    if (toRemove[i][j]) {
                        map[i][j] = ' ';
                        removed++;
                    }
                }
            }

            // 제거된 블록이 없다면 종료
            if (removed == 0) {
                break;
            }

            totalRemoved += removed;

            // 블록 떨어뜨리기 (맨 아래 행부터 위로 확인)
            for (int j = 0; j < n; j++) {
                int emptyRow = m - 1;
                for (int i = m - 1; i >= 0; i--) {
                    // 만약 map이 ' '가 아니면 ' ' 처리 + emptyRow가 있는 블록에 map 저장 + emptyRow 감소
                    if (map[i][j] != ' ') {
                        char temp = map[i][j];
                        map[i][j] = ' ';
                        map[emptyRow][j] = temp;
                        emptyRow--;
                    }
                }
            }
        }

        return totalRemoved;
    }

    public static void main(String[] args) {
        System.out.println(solution(4, 5,
                new String[]{"CCBDE", "AAADE", "AAABF", "CCBBF"})); // 14
        System.out.println(solution(6, 6,
                new String[]{"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"})); // 15
    }
}
