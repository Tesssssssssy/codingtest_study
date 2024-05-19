package week2.lim.dfsbfs.pickitem;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/87694
// 참고: https://velog.io/@jp-share/%EC%BD%94%EB%94%A9%ED%85%8C%EC%8A%A4%ED%8A%B8-%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EC%95%84%EC%9D%B4%ED%85%9C-%EC%A4%8D%EA%B8%B0Java

import java.util.Arrays;

public class pickItem1 {
    static String[][] shape;
    static int startX, startY, targetX, targetY, answer, total;

    public static int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        shape = new String[52][52];
        startX = characterX;
        startY = characterY;
        targetX = itemX;
        targetY = itemY;
        answer = total = 0;

        for(int i=0; i<52; i++)
            Arrays.fill(shape[i],""); // ""로 초기화

        for(int[] xy : rectangle){
            int leftX = xy[0], rightX = xy[2], leftY = xy[1], rightY = xy[3];

            // 꼭지점 (왼쪽아래(LDX), 오른쪽아래(RDX), 왼쪽위(LUX), 오른쪽위(RUX))
            shape[leftX][leftY] = "LDX";
            shape[rightX][leftY] = "RDX";
            shape[leftX][rightY] = "LUX";
            shape[rightX][rightY] = "RUX";

            for(int x=leftX+1; x<rightX; x++){// 상(U), 하(D)
                shape[x][rightY] += "U";
                shape[x][leftY] += "D";
            }

            for(int y=leftY+1; y<rightY; y++){// 좌(L), 우(R)
                shape[leftX][y] += "L";
                shape[rightX][y] += "R";
            }
        }

        followLine(characterX, characterY);

        return Math.min(answer, total-answer);
    }

    public static void followLine(int x, int y){
        String location = shape[x][y];
        if(location.equals("RU") || location.equals("UR") || location.equals("LUX") || location.equals("U"))
            x++;
        if(location.equals("LD") || location.equals("DL") || location.equals("RDX") || location.equals("D"))
            x--;
        if(location.equals("LU") || location.equals("UL") || location.equals("LDX") || location.equals("L"))
            y++;
        if(location.equals("RD") || location.equals("DR") || location.equals("RUX") || location.equals("R"))
            y--;

        total++;
        if(x == targetX && y == targetY)
            answer = total;

        if(x == startX && y == startY)
            return;

        followLine(x, y);
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
