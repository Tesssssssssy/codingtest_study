package week17.lim.n_17679;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/17679

public class Solution {
    public static int solution(int m, int n, String[] board) {
        char[][] map = new char[m][n];
        // 주어진 board 문자열 배열을 문자 배열로 변환하여 저장.

        for (int i = 0; i < m; i++) {
            map[i] = board[i].toCharArray();
        }

        int totalRemoved = 0;

        while (true) {
            boolean[][] toRemove = new boolean[m][n];
            int removed = 0;

            /*
                보드의 각 위치에서 2x2 블록을 탐색.
                같은 모양의 블록이 2x2로 배치되어 있으면, 해당 위치들을 toRemove 배열에 방문 표시
            */
            for (int i = 0; i < m - 1; i++) {
                for (int j = 0; j < n - 1; j++) {
                    char block = map[i][j];
                    if (block != ' ' && block == map[i][j + 1]
                            && block == map[i + 1][j]
                            && block == map[i + 1][j + 1])
                    {
                        toRemove[i][j] = toRemove[i][j + 1] = true;
                        toRemove[i + 1][j] = toRemove[i + 1][j + 1] = true;
                    }
                }
            }

            /*
                toRemove 배열에 표시된 위치의 블록을 ' '(빈 공간)으로 설정하여 제거.
                제거된 블록의 개수를 removed 변수에 저장.
            */
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
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

            // 각 열에 대해 아래에서부터 위로 탐색하면서, 빈 공간이 있으면 그 위에 있는 블록을 떨어뜨린다.
            for (int j = 0; j < n; j++) {
                int emptyRow = m - 1;
                for (int i = m - 1; i >= 0; i--) {
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
