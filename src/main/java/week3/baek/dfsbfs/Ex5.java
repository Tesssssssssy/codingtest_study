package week3.baek.dfsbfs;

public class Ex5 {
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int answer = 0;

        return answer;
    }
}

class Ex5Main{
    public static void main(String[] args) {
        Ex5 ex5 = new Ex5();

        int[][] rectangle = {{1,1,7,4},{3,2,5,5},{4,3,6,9},{2,6,8,8}};
        int characterX = 1;
        int characterY = 3;
        int itemX = 7;
        int itemY = 8;

        System.out.println(ex5.solution(rectangle, characterX, characterY, itemX, itemY));
    }
}
