package week7.lim.groom_datastructure.blockgame.practice;

// 문제: https://level.goorm.io/exam/191052/%EB%B8%94%EB%A1%9D-%EA%B2%8C%EC%9E%84/quiz/1

import java.io.*;
import java.util.*;

public class BlockGamePrac {
    /**
     *  1. 블록 올려놓은 횟수 N 입력받기
     *  2. 블록 놓을 방향 문자열 D 입력받기
     *  3. 블록 점수 배열 입력받기
     *  4. 블록 놓는 보드판 저장할 자료구조 필요
     *  5. 만약 블록을 놓을 자리에 이미 블록이 있으면
     *     최근에 놓은 블록을 순서대로 제거해야 하므로
     *     블록을 올려 놓은 순서를 저장할 자료구조 필요
     *  6. 방향 문자열대로 블록을 올려놓기
     *  7. 종료 후 남아있는 블록들의 점수 합 출력
     */
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] D = br.readLine().split("");
        String[] scoreStr = br.readLine().split(" ");
        int[] score = new int[N];

        for (int i = 0; i < N; i++) {
            score[i] = Integer.parseInt(scoreStr[i]);
        }

        Map<Point, Integer> board = new HashMap<>();
        Stack<Point> history = new Stack<>();

        Point current = new Point(0, 0);

        board.put(current, 1);
        history.push(current);

        for (int i = 0; i < N; i++) {
            int dx = 0;
            int dy = 0;
            switch (D[i]) {
                case "L":
                    dx = -1;
                    break;
                case "R" :
                    dx = 1;
                    break;
                case "U":
                    dy = 1;
                    break;
                case "D":
                    dy = -1;
                    break;
            }

            Point next = new Point(current.x + dx, current.y + dy);
            while (board.containsKey(next)) {
                Point removed = history.pop();
                board.remove(removed);
                if (removed.equals(next)) {
                    break;
                }
            }

            board.put(next, score[i]);
            history.push(next);
            current = next;
        }

        int totalScore = 0;
        for (int sco : board.values()) {
            totalScore += sco;
        }

        System.out.println(totalScore);
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            return 31 * x + y;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;

            if (obj == null || getClass() != obj.getClass())
                return false;

            Point point = (Point) obj;

            return x == point.x && y == point.y;
        }
    }
}
