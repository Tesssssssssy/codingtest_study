package week2.kim.week2_2.dfs_Bfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * [아이템 줍기]
 *
 * 다각형 모양 지형에서 캐릭터가 아이템을 줍기 위해 이동하려 합니다.
 *
 * 지형은 각 변이 x축, y축과 평행한 직사각형이 겹쳐진 형태로 표현하며, 캐릭터는 이 다각형의 둘레(굵은 선)를 따라서 이동합니다.
 *
 * 만약 직사각형을 겹친 후 다음과 같이 중앙에 빈 공간이 생기는 경우, 다각형의 가장 바깥쪽 테두리가 캐릭터의 이동 경로가 됩니다.
 *
 * 단, 서로 다른 두 직사각형의 x축 좌표 또는 y축 좌표가 같은 경우는 없습니다.
 *
 * 즉, 서로 다른 두 직사각형이 꼭짓점에서 만나거나, 변이 겹치는 경우 등은 없습니다.
 *
 * 지형이 2개 이상으로 분리된 경우도 없습니다.
 *
 * 한 직사각형이 다른 직사각형 안에 완전히 포함되는 경우 또한 없습니다.
 *
 * 지형을 나타내는 직사각형이 담긴 2차원 배열 rectangle, 초기 캐릭터의 위치 characterX, characterY, 아이템의 위치 itemX, itemY가 solution 함수의 매개변수로 주어질 때, 캐릭터가 아이템을 줍기 위해 이동해야 하는 가장 짧은 거리를 return 하도록 solution 함수를 완성해주세요.
 *
 * 제한사항
 * rectangle의 세로(행) 길이는 1 이상 4 이하입니다.
 * rectangle의 원소는 각 직사각형의 [좌측 하단 x, 좌측 하단 y, 우측 상단 x, 우측 상단 y] 좌표 형태입니다.
 * 직사각형을 나타내는 모든 좌표값은 1 이상 50 이하인 자연수입니다.
 * 서로 다른 두 직사각형의 x축 좌표, 혹은 y축 좌표가 같은 경우는 없습니다.
 * 문제에 주어진 조건에 맞는 직사각형만 입력으로 주어집니다.
 * charcterX, charcterY는 1 이상 50 이하인 자연수입니다.
 * 지형을 나타내는 다각형 테두리 위의 한 점이 주어집니다.
 * itemX, itemY는 1 이상 50 이하인 자연수입니다.
 * 지형을 나타내는 다각형 테두리 위의 한 점이 주어집니다.
 * 캐릭터와 아이템의 처음 위치가 같은 경우는 없습니다.
 * 전체 배점의 50%는 직사각형이 1개인 경우입니다.
 * 전체 배점의 25%는 직사각형이 2개인 경우입니다.
 * 전체 배점의 25%는 직사각형이 3개 또는 4개인 경우입니다.
 *
 * 입출력 예
 * rectangle					                characterX	characterY	itemX		itemY		result
 * [[1,1,7,4],[3,2,5,5],[4,3,6,9],[2,6,8,8]]	1		    3		    7		    8		    17
 * [[1,1,8,4],[2,2,4,9],[3,6,9,8],[6,3,7,7]]	9		    7		    6		    1		    11
 * [[1,1,5,7]]					                1		    1		    4		    7		    9
 * [[2,1,7,5],[6,4,10,10]]			            3		    1		    7		    10		    15
 * [[2,2,5,5],[1,3,6,4],[3,1,4,6]]		        1		    4		    6		    3		    10
 *
 * 입출력 예 설명
 * 입출력 예 #1
 *
 * 캐릭터 위치는 (1, 3)이며, 아이템 위치는 (7, 8)입니다. 위 그림과 같이 굵은 선을 따라 이동하는 경로가 가장 짧습니다.
 *
 * 입출력 예 #2
 *
 * 캐릭터 위치는 (9, 7)이며, 아이템 위치는 (6, 1)입니다. 위 그림과 같이 굵은 선을 따라 이동하는 경로가 가장 짧습니다.
 *
 * 입출력 예 #3
 *
 * 캐릭터 위치는 (1, 1)이며, 아이템 위치는 (4, 7)입니다. 위 그림과 같이 굵은 선을 따라 이동하는 경로가 가장 짧습니다.
 *
 * 입출력 예 #4, #5
 *
 * 설명 생략
*/

public class Ex3_1 {
    public static void main(String[] args) {
        Ex3_1 ex3 = new Ex3_1();

        int[][] rectangle1 = {{1,1,7,4},{3,2,5,5},{4,3,6,9},{2,6,8,8}};
        int[][] rectangle2 = {{1,1,8,4},{2,2,4,9},{3,6,9,8},{6,3,7,7}};
        int[][] rectangle3 = {{1,1,5,7}};
        int[][] rectangle4 = {{2,1,7,5},{6,4,10,10}};
        int[][] rectangle5 = {{2,2,5,5},{1,3,6,4},{3,1,4,6}};

        System.out.println(ex3.solution(rectangle1, 1, 3, 7, 8));
        System.out.println(ex3.solution(rectangle2, 9, 7, 6, 1));
        System.out.println(ex3.solution(rectangle3, 1, 1, 4, 7));
        System.out.println(ex3.solution(rectangle4, 3, 1, 7, 10));
        System.out.println(ex3.solution(rectangle5, 1, 4, 6, 3));
    }

    String[][] shape;
    int startX, startY, targetX, targetY, answer, total;

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        // 지형을 나타내는 2차원 배열
        shape = new String[52][52];
        startX = characterX;
        startY = characterY;
        targetX = itemX;
        targetY = itemY;
        answer = total = 0;

        // ""로 초기화
        for(int i=0; i<52; i++) Arrays.fill(shape[i],"");

        for(int[] xy : rectangle){
            int leftX = xy[0], rightX = xy[2], leftY = xy[1], rightY = xy[3];

            // 꼭지점 (왼쪽아래(LDX), 오른쪽아래(RDX), 왼쪽위(LUX), 오른쪽위(RUX))
            shape[leftX][leftY] = "LDX";
            shape[rightX][leftY] = "RDX";
            shape[leftX][rightY] = "LUX";
            shape[rightX][rightY] = "RUX";

//            lux 2u 3u 4u  rux
//            l
//            l               r
//
//            ldx 1d 2d 3d rdx

            for(int x=leftX+1; x<rightX; x++){// 상(U), 하(D)
                shape[x][rightY] += "U"; shape[x][leftY] += "D";
            }

            for(int y=leftY+1; y<rightY; y++){// 좌(L), 우(R)
                shape[leftX][y] += "L"; shape[rightX][y] += "R";
            }
        }

        followLine(characterX, characterY);

        return Math.min(answer, total-answer);
    }

    public void followLine(int x, int y){
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
}