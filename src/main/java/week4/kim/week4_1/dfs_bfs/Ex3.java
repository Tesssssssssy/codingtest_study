package week4.kim.week4_1.dfs_bfs;

import java.util.*;

/**
 * [퍼즐 조각 채우기]
 *
 * 테이블 위에 놓인 퍼즐 조각을 게임 보드의 빈 공간에 적절히 올려놓으려 합니다. 게임 보드와 테이블은 모두 각 칸이 1x1 크기인 정사각 격자 모양입니다.
 * 이때, 다음 규칙에 따라 테이블 위에 놓인 퍼즐 조각을 게임 보드의 빈칸에 채우면 됩니다.
 *
 * 조각은 한 번에 하나씩 채워 넣습니다.
 * 조각을 회전시킬 수 있습니다.
 * 조각을 뒤집을 수는 없습니다.
 * 게임 보드에 새로 채워 넣은 퍼즐 조각과 인접한 칸이 비어있으면 안 됩니다.
 * 다음은 퍼즐 조각을 채우는 예시입니다.
 *
 * puzzle_5.png
 *
 * 위 그림에서 왼쪽은 현재 게임 보드의 상태를, 오른쪽은 테이블 위에 놓인 퍼즐 조각들을 나타냅니다.
 * 테이블 위에 놓인 퍼즐 조각들 또한 마찬가지로 [상,하,좌,우]로 인접해 붙어있는 경우는 없으며, 흰 칸은 퍼즐이 놓이지 않은 빈 공간을 나타냅니다.
 * 모든 퍼즐 조각은 격자 칸에 딱 맞게 놓여있으며, 격자 칸을 벗어나거나, 걸쳐 있는 등 잘못 놓인 경우는 없습니다.
 *
 * 이때, 아래 그림과 같이 3,4,5번 조각을 격자 칸에 놓으면 규칙에 어긋나므로 불가능한 경우입니다.
 *
 * puzzle_6.png
 *
 * 3번 조각을 놓고 4번 조각을 놓기 전에 위쪽으로 인접한 칸에 빈칸이 생깁니다.
 * 5번 조각의 양 옆으로 인접한 칸에 빈칸이 생깁니다.
 * 다음은 규칙에 맞게 최대한 많은 조각을 게임 보드에 채워 넣은 모습입니다.
 *
 * puzzle_7.png
 *
 * 최대한 많은 조각을 채워 넣으면 총 14칸을 채울 수 있습니다.
 *
 * 현재 게임 보드의 상태 game_board, 테이블 위에 놓인 퍼즐 조각의 상태 table이 매개변수로 주어집니다.
 * 규칙에 맞게 최대한 많은 퍼즐 조각을 채워 넣을 경우, 총 몇 칸을 채울 수 있는지 return 하도록 solution 함수를 완성해주세요.
 *
 * 제한사항
 * 3 ≤ game_board의 행 길이 ≤ 50
 * game_board의 각 열 길이 = game_board의 행 길이
 * 즉, 게임 보드는 정사각 격자 모양입니다.
 * game_board의 모든 원소는 0 또는 1입니다.
 * 0은 빈칸, 1은 이미 채워진 칸을 나타냅니다.
 * 퍼즐 조각이 놓일 빈칸은 1 x 1 크기 정사각형이 최소 1개에서 최대 6개까지 연결된 형태로만 주어집니다.
 * table의 행 길이 = game_board의 행 길이
 * table의 각 열 길이 = table의 행 길이
 * 즉, 테이블은 game_board와 같은 크기의 정사각 격자 모양입니다.
 * table의 모든 원소는 0 또는 1입니다.
 * 0은 빈칸, 1은 조각이 놓인 칸을 나타냅니다.
 * 퍼즐 조각은 1 x 1 크기 정사각형이 최소 1개에서 최대 6개까지 연결된 형태로만 주어집니다.
 * game_board에는 반드시 하나 이상의 빈칸이 있습니다.
 * table에는 반드시 하나 이상의 블록이 놓여 있습니다.
 *
 * 입출력 예
 * game_board	                                                                            table	                                                                                result
 * [[1,1,0,0,1,0],[0,0,1,0,1,0],[0,1,1,0,0,1],[1,1,0,1,1,1],[1,0,0,0,1,0],[0,1,1,1,0,0]]	[[1,0,0,1,1,0],[1,0,1,0,1,0],[0,1,1,0,1,1],[0,0,1,0,0,0],[1,1,0,1,1,0],[0,1,0,0,0,0]]	14
 * [[0,0,0],[1,1,0],[1,1,1]]	                                                            [[1,1,1],[1,0,0],[0,0,0]]	                                                            0
 *
 * 입출력 예 설명
 * 입출력 예 #1
 *
 * 입력은 다음과 같은 형태이며, 문제의 예시와 같습니다.
 *
 * puzzle_9.png
 *
 * 입출력 예 #2
 *
 * 블록의 회전은 가능하지만, 뒤집을 수는 없습니다.
 */

public class Ex3 {
    public static void main(String[] args) {
        Ex3 ex3 = new Ex3();

        int[][] game_board = {{1,1,0,0,1,0},{0,0,1,0,1,0},{0,1,1,0,0,1},{1,1,0,1,1,1},{1,0,0,0,1,0},{0,1,1,1,0,0}};
        int[][] table = {{1,0,0,1,1,0},{1,0,1,0,1,0},{0,1,1,0,1,1},{0,0,1,0,0,0},{1,1,0,1,1,0},{0,1,0,0,0,0}};

        // 14
        System.out.println(ex3.solution(game_board, table));
    }

    static int[] dI = {1,-1,0,0}; //bfs 할때 상하좌우 위한 배열
    static int[] dJ = {0, 0,1,-1}; //bfs 할때 상하좌우 위한 배열

    /*
     * 필요한 메소드
     * 1. board, table에서 조각들의 정보를 가져올 bfs 메소드
     * 2. board의 조각 하나씩 table의 조각을 돌려보며 맞춰볼 메소드
     * 3. 조각을 돌리는 메소드
     * */

    static List<List<int[]>> boardList = new ArrayList<>();
    // board에서 빈칸조각의 정보를 저장할 List -> 내부 리스트는 (0,0), (1,0) 등 세부조각의 인덱스가 0,0 을 기준으로 들어있다.
    static List<List<int[]>> tableList = new ArrayList<>();
    // table에서 조각 정보를 저장할 List

    //bfs를 위한 방문배열
    static boolean[][] visitedBoard;
    static boolean[][] visitedTable;

    static int result = 0; // 답을 저장할 변수

    // board의 크기 저장 , board 와 table의 크기는 같고, 둘다 정사각형이다.
    static int size;

    public static int solution(int[][] game_board, int[][] table) {
        size = game_board.length;

        visitedBoard = new boolean[size][size];
        visitedTable = new boolean[size][size];

        // 2중반복문으로 이용하여 각 노드그룹(큰조각)마다 bfs를 돌릴 수 있게 한다. -> 큰조각의 세부 위치정보를 저장하기 위함
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (!visitedBoard[i][j] && game_board[i][j] == 0) {
                    // board에서는 빈칸 bfs
                    bfs(game_board, 0, i, j, boardList, visitedBoard);
                }
                if (!visitedTable[i][j] && table[i][j] == 1) {
                    // table에서는 조각 bfs
                    bfs(table, 1, i, j, tableList, visitedTable);
                }

            }
        }


        //bfs를 돌았으면 조각정보들을 이용하여 답을 찾는 메소드를 진행한다.
        checkSegment(boardList, tableList);
//        System.out.println("result = " + result);
        return result;
    }

    /*
     * trigger는 board인 경우 0 , table인 경우 1 을 의미 -> 즉 어떤 숫자면 bfs를 진행하는지
     * bfs는 board일때와 ,talbe일때 동작방식이 달라지므로, 내부에서 if문으로 처리하는것보다는 필요한 인자를 전달받아서 처리하자.
     * 조각을 저장할 List 인자 , 방문처리를 위한 방문배열을 전달 받는다. (그렇다면 각 변수를 굳이 static으로 처리할 필요는 없긴함)
     * */
    static void bfs(int[][] map, int trigger, int startI, int startJ, List<List<int[]>> list,
                    boolean[][] visited) {

        Queue<int[]> q = new LinkedList<>();
        visited[startI][startJ] = true;
        q.add(new int[]{startI, startJ}); // bfs 시작할 인덱스 큐에 넣는다.
        // 조각의 가장 첫번째 세부조각을 원점으로 보내고 , 그 조각을 기준으로 나머지 조각의 상대인덱스를 구해 저장할 예정
        //list에 추가할 List<int[]>를 생성
        List<int[]> segmentInfo = new ArrayList<>(); //조각의 인덱스정보를 담을 리스트
        segmentInfo.add(new int[]{startI - startI, startJ - startJ}); // 0,0 원점인덱스를 기준으로 저장할것이다.

        while (!q.isEmpty()) {
            int[] current = q.poll();
            int currentI = current[0];
            int currentJ = current[1];

            for (int i = 0; i < 4; i++) {
                int nextI = currentI + dI[i];
                int nextJ = currentJ + dJ[i];

                //지금 bfs를 진행하는 큰조각의 다음 세부조각를 상하좌우에서 찾아서 큐에 저장
                if (nextI >= 0 && nextI < size && nextJ >= 0 && nextJ < size) {
                    if (!visited[nextI][nextJ] && map[nextI][nextJ] == trigger) {
                        visited[nextI][nextJ] = true;
                        q.add(new int[]{nextI, nextJ});
                        segmentInfo.add(new int[]{nextI - startI,
                                nextJ - startJ}); //지금 이 좌표도 원점기준 정렬해야하므로 기준좌표(시작좌표)를 빼준다.
                    }
                }
            }
        }
        //bfs를 다돌았으면 조각정보 List를 저장해준다.
        list.add(segmentInfo);
    }

    static void checkSegment(List<List<int[]>> boardList, List<List<int[]>> tableList) {
        // 반복문을 이용해서 빈칸의 조각하나씩 꺼내고 , 반복문을 통해서 table의 조각을 꺼내고, 회전시켜가면서 맞추는 메소드

        boolean[] completeSegT = new boolean[tableList.size()]; // 이미 사용한 조각인지 체크할 배열

        for (int i = 0; i < boardList.size(); i++) {

            List<int[]> currentBoardSeg = boardList.get(i);

            for (int j = 0; j < tableList.size(); j++) {
                if (!completeSegT[j] && currentBoardSeg.size() == tableList.get(j).size()) {
                    // 사용하지않은 조각인지 체크, 조각의 크기가 같은지 체크 ( 조각의 크기가 다르면 비교할 이유가 없음 + 딱 맞는 퍼즐만 넣어야함)
                    List<int[]> currentTableSeg = tableList.get(j);

                    boolean isCorrect = rotateCheck(currentBoardSeg, currentTableSeg);// table 조각을 0,90,180,270도 회전시켜보면서 맞는지 체크 맞으면 답 증가
                    if (isCorrect) {
                        result += currentTableSeg.size();
                        completeSegT[j] = true;
                        break;
                    }
                }


            }

        }
    }

    static boolean rotateCheck(List<int[]> currentBoardSeg, List<int[]> currentTableSeg) {
        //조각의 비교는 원점 인덱스 0,0을 기준으로 세부조각의 좌표를 정렬하여 비교한다
        //i인덱스 기준 오름차순, i가 같을시 j인덱스 기준 오름차순으로 정렬해준다. -> 두 조각의 좌표를 비교해서 맞는 퍼즐인지 확인할것이기 때문에

        //board 조각 정렬
        currentBoardSeg.sort(((o1, o2) -> {
            if (o1[0] > o2[0]) {
                return 1;
            } else if (o1[0] == o2[0]){
                if (o1[1] > o2[1]) {
                    return 1;
                }
            }
            return -1;

        }));

        for (int i = 0; i < 4; i++) { // 0,90,180,270도 회전하면서 같은 조각모양인지 체크
            // table 조각도 비교전에 같은 기준으로 정렬
            currentTableSeg.sort(((o1, o2) -> {
                if (o1[0] > o2[0]) {
                    return 1;
                } else if (o1[0] == o2[0]){
                    if (o1[1] > o2[1]) {
                        return 1;
                    }
                }
                return -1;
            }));

            int baseI = currentTableSeg.get(0)[0];
            int baseJ = currentTableSeg.get(0)[1];
            //회전을 하게되면 0,0 기준으로 상대위치 설정이 풀린다. 첫번째 좌표졍보를 0,0로 보내고 그 좌표를 기준으로 다시 정리
            for (int j = 0; j < currentTableSeg.size(); j++) {
                currentTableSeg.get(j)[0] -= baseI;
                currentTableSeg.get(j)[1] -= baseJ;
            }

            //조각이 같은 모양인지 비교
            boolean isCorrect = true;

            for (int j = 0; j < currentTableSeg.size(); j++) {
                int[] tableSeg = currentTableSeg.get(j);
                int[] boardSeg = currentBoardSeg.get(j);

                if (tableSeg[0] != boardSeg[0] || tableSeg[1] != boardSeg[1]) {
                    isCorrect = false;
                    break;
                }
            }

            if (!isCorrect) {
                //맞지않는다면 다음 회전으로 진행..
                // 90도 시계방향 회전은 (x,y) -> (y,-x)

                for (int j = 0; j < currentTableSeg.size(); j++) {
                    int temp = currentTableSeg.get(j)[0] * -1; // i 인덱스 임시 저장
                    currentTableSeg.get(j)[0] = currentTableSeg.get(j)[1]; // x에다가는 y를
                    currentTableSeg.get(j)[1] = temp; // y에다가는 -x를
                }

            }
            else{
                return true;
            }

        }
        return false;
    }

//    static int[][] game_board, table;
//    static boolean[][] visited;
//    static int n, answer = 0;
//    static ArrayList<Block> game = new ArrayList<>(), tableList = new ArrayList<>();
//
//    public static int solution(int[][] game_board, int[][] table) {
//        n = game_board.length;
//        game_board = game_board;
//        table = table;
//        visited = new boolean[n][n];
//
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                if (!visited[i][j] && game_board[i][j] == 0) {
//                    game.add(bfs(i, j, game_board));
//                }
//                if (!visited[i][j] && table[i][j] == 1) {
//                    tableList.add(bfs(i, j, table));
//                }
//            }
//        }
//
//        Collections.sort(game);
//        Collections.sort(tableList);
//
//        for (Block block : game) {
//            for (int i = 0; i < tableList.size(); i++) {
//                if (block.equals(tableList.get(i))) {
//                    answer += block.size;
//                    tableList.remove(i);
//                    break;
//                }
//            }
//        }
//
//        return answer;
//    }
//
//    public static Block bfs(int x, int y, int[][] arr) {
//        int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
//        Queue<int[]> queue = new LinkedList<>();
//        ArrayList<int[]> list = new ArrayList<>();
//        queue.offer(new int[]{x, y});
//        visited[x][y] = true;
//        while (!queue.isEmpty()) {
//            int[] cur = queue.poll();
//            list.add(new int[]{cur[0] - x, cur[1] - y});
//            for (int dir = 0; dir < 4; dir++) {
//                int nx = cur[0] + dx[dir], ny = cur[1] + dy[dir];
//                if (nx < 0 || ny < 0 || nx >= n || ny >= n || visited[nx][ny] || arr[nx][ny] != arr[x][y]) continue;
//                visited[nx][ny] = true;
//                queue.offer(new int[]{nx, ny});
//            }
//        }
//        return new Block(list);
//    }
//
//    static class Block implements Comparable<Block> {
//        ArrayList<int[]> list = new ArrayList<>();
//        int size, min_x = Integer.MAX_VALUE, min_y = Integer.MAX_VALUE;
//
//        Block(ArrayList<int[]> list) {
//            for (int[] pos : list) {
//                min_x = Math.min(min_x, pos[0]);
//                min_y = Math.min(min_y, pos[1]);
//            }
//            for (int[] pos : list) {
//                this.list.add(new int[]{pos[0] - min_x, pos[1] - min_y});
//            }
//            Collections.sort(this.list, (o1, o2) -> {
//                if (o1[0] == o2[0]) return o1[1] - o2[1];
//                else return o1[0] - o2[0];
//            });
//            size = list.size();
//        }
//
//        @Override
//        public int compareTo(Block o) {
//            return size - o.size;
//        }
//
//        @Override
//        public boolean equals(Object o) {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//            Block block = (Block) o;
//            return size == block.size && list.equals(block.list);
//        }
//    }
}