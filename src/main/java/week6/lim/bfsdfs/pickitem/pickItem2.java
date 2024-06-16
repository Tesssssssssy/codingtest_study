package week6.lim.bfsdfs.pickitem;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/87694
// 참고: https://velog.io/@anwlro0212/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EC%95%84%EC%9D%B4%ED%85%9C-%EC%A4%8D%EA%B8%B0-JAVA

import java.util.LinkedList;
import java.util.Queue;

public class pickItem2 {
    static char map[][] = new char[101][101];

    public static int solution(int[][] rectangle, int X, int Y, int itemX, int itemY) {
        for (int i = 0; i < rectangle.length; i++) {
            int y1 = rectangle[i][1];
            int x1 = rectangle[i][0];
            int y2 = rectangle[i][3];
            int x2 = rectangle[i][2];
            draw(y1 * 2,x1 * 2,y2 * 2,x2 * 2);
        }

        return bfs(Y * 2,X * 2,itemY * 2,itemX * 2);



    }
    public static int bfs(int Y,int X,int findY,int findX) {
        int yy[] = {-1,1,0,0};
        int xx[] = {0,0,-1,1};

        Queue<Integer[]> queue = new LinkedList<>();
        queue.add(new Integer[]{Y,X,0});

        boolean visited[][] = new boolean[101][101];

        while (!queue.isEmpty()) {
            Integer temp[] = queue.poll();

            int prevY = temp[0];
            int prevX = temp[1];
            int count = temp[2];

            if (prevY == findY && prevX == findX)
                return count / 2;

            for (int i = 0; i < 4; i++) {
                int nextY = prevY+yy[i];
                int nextX = prevX+xx[i];

                if (nextY < 0 || nextX < 0 || nextY >= map.length || nextX >= map[0].length)
                    continue;

                if (visited[nextY][nextX] == true || map[nextY][nextX] != '2')
                    continue;

                visited[nextY][nextX] = true;
                queue.add(new Integer[]{nextY,nextX,count+1});
            }
        }

        return 0;
    }
    public static void draw(int y1,int x1,int y2,int x2) {
        for (int i = y1; i <= y2; i++) {
            for (int j = x1; j <= x2; j++) {
                if (map[i][j] == '1')
                    continue;

                map[i][j] = '1';

                if (i == y1 || i == y2 || j == x1 || j == x2)
                    map[i][j] = '2';
            }
        }

    }

    public static void main(String[] args) {
        int[][] rectangle = {{1,1,7,4},{3,2,5,5},{4,3,6,9},{2,6,8,8}};
        int characterX = 1;
        int characterY = 3;
        int itemX = 7;
        int itemY = 8;

        System.out.println(solution(rectangle, characterX, characterY, itemX, itemY));  // 17
    }
}
