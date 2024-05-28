package week4.lim.bfsdfs.puzzle;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/84021
// 참고: https://stritegdc.tistory.com/257

import java.util.*;

public class puzzle1 {
    static int[] dx = {0, 0, 1, -1};
    // X축 이동을 위한 배열
    static int[] dy = {1, -1, 0, 0};
    // Y축 이동을 위한 배열

    public static int solution(int[][] game_board, int[][] table) {
        int answer = 0;

        // 각 퍼즐과 빈 공간의 리스트
        List<List<Point>> tableShapes = new ArrayList<>();
        List<List<Point>> gameBoardShapes = new ArrayList<>();

        int length = game_board.length;

        // 게임 보드의 0과 1을 바꿔 빈 공간을 표시합니다.
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (game_board[i][j] == 1) {
                    game_board[i][j] = 0;
                } else game_board[i][j] = 1;
            }
        }

        // 방문 처리를 위한 배열
        boolean[][] visited_table = new boolean[length][length];
        boolean[][] visited_game_board = new boolean[length][length];

        // 모든 셀에 대해 퍼즐 조각과 빈 공간 추출
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                // 테이블에서 퍼즐 조각 추출
                if (table[i][j] == 1 && !visited_table[i][j])
                    bfs(i, j, table, visited_table, tableShapes);

                // 게임 보드에서 빈 공간 추출
                if (game_board[i][j] == 1 && !visited_game_board[i][j])
                    bfs(i, j, game_board, visited_game_board, gameBoardShapes);
            }
        }

        // 퍼즐 조각과 빈 공간을 비교하여 맞춰넣기
        answer = compareBlock(tableShapes, gameBoardShapes, answer);

        return answer;
    }

    // 퍼즐 조각과 빈 공간을 비교하여 채울 수 있는 공간 계산
    public static int compareBlock(List<List<Point>> table, List<List<Point>> board, int answer) {
        int table_size = table.size();
        int gameBoard_size = board.size();

        boolean[] visited = new boolean[gameBoard_size]; // 이미 매칭된 빈 공간 체크

        for (int i = 0; i < table_size; i++) {
            for (int j = 0; j < gameBoard_size; j++) {
                if (visited[j] || table.get(i).size() != board.get(j).size())
                    continue;
                if (isRotate(table.get(i), board.get(j))) {
                    visited[j] = true; // 매칭된 경우 사용 표시
                    answer += board.get(j).size(); // 매칭된 크기 누적
                    break;
                }
            }
        }

        return answer;
    }

    // 퍼즐 조각을 회전시키며 빈 공간과 비교
    public static boolean isRotate(List<Point> table, List<Point> board) {
        Collections.sort(board); // 빈 공간 정렬

        for (int i = 0; i < 4; i++) {
            Collections.sort(table); // 퍼즐 조각 정렬

            // (0,0)을 기준으로 좌표 재설정
            int curr_x = table.get(0).x;
            int curr_y = table.get(0).y;

            for (int j = 0; j < table.size(); j++) {
                table.get(j).x -= curr_x;
                table.get(j).y -= curr_y;
            }

            boolean check = true;

            // 좌표가 모두 일치하는지 확인
            for (int j = 0; j < board.size(); j++) {
                if (board.get(j).x != table.get(j).x || board.get(j).y != table.get(j).y) {
                    check = false;
                    break;
                }
            }

            if (check) {
                return true;
            } else {
                // 90도 회전 (x, y -> y, -x)
                for (int j = 0; j < table.size(); j++) {
                    int temp = table.get(j).x;
                    table.get(j).x = table.get(j).y;
                    table.get(j).y = -temp;
                }
            }
        }

        return false;
    }

    // BFS를 사용하여 퍼즐 조각 또는 빈 공간을 추출
    public static void bfs(int x, int y, int[][] board, boolean[][] visited, List<List<Point>> list) {
        visited[x][y] = true;

        Queue<Point> q = new LinkedList<>();
        q.add(new Point(x, y));

        List<Point> sub_list = new ArrayList<>();
        sub_list.add(new Point(0, 0)); // 기준점 (0,0)으로 설정

        while (!q.isEmpty()) {
            Point p = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= board.length || ny >= board.length) continue;

                if (!visited[nx][ny] && board[nx][ny] == 1) {
                    visited[nx][ny] = true;
                    q.add(new Point(nx, ny));
                    sub_list.add(new Point(nx - x, ny - y)); // 기준점 대비 위치 저장
                }
            }
        }

        list.add(sub_list);
    }

    // 좌표를 나타내는 Point 클래스, 비교 가능하도록 Comparable 인터페이스 구현
    static class Point implements Comparable<Point> {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int compareTo(Point o) {
            int result = Integer.compare(this.x, o.x);
            if (result == 0) {
                result = Integer.compare(this.y, o.y);
            }
            return result;
        }
    }

    public static void main(String[] args) {
        int[][] game_board1 = {{1, 1, 0, 0, 1, 0}, {0, 0, 1, 0, 1, 0}, {0, 1, 1, 0, 0, 1}, {1, 1, 0, 1, 1, 1}, {1, 0, 0, 0, 1, 0}, {0, 1, 1, 1, 0, 0}};
        int[][] table1 = {{1, 0, 0, 1, 1, 0}, {1, 0, 1, 0, 1, 0}, {0, 1, 1, 0, 1, 1}, {0, 0, 1, 0, 0, 0}, {1, 1, 0, 1, 1, 0}, {0, 1, 0, 0, 0, 0}};
        System.out.println(solution(game_board1, table1));  // 14

        int[][] game_board2 = {{0, 0, 0}, {1, 1, 0}, {1, 1, 1}};
        int[][] table2 = {{1, 1, 1}, {1, 0, 0}, {0, 0, 0}};
        System.out.println(solution(game_board2, table2));  // 0
    }
}
