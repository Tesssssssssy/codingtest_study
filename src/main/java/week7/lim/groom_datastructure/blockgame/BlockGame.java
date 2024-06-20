package week7.lim.groom_datastructure.blockgame;

// 문제: https://level.goorm.io/exam/191052/%EB%B8%94%EB%A1%9D-%EA%B2%8C%EC%9E%84/quiz/1

import java.io.*;
import java.util.*;

public class BlockGame {
    /**
     *  1. N 입력 받기 (블록 놓은 횟수)
     *     D 입력받기 (블록 놓은 방향)
     *     각 블록의 점수 입력받기
     *  2. 보드판에 올려놓은 블록 저장할 자료구조 필요
     *  3. 다음에 이동할 칸에 이미 블록이 있으면 순차대로 다시 빼야하므로
     *     보드판 위에 블록을 올린 순서를 기록할 자료구조(스택) 필요
     *  4. 방향대로 보드판에 블록을 올리다가
     *     다음에 이동할 칸에 블록이 있으면 스택에서 순차대로 뺀다.
     *  5. D만큼 모두 이동이 끝나고 보드에 있는 블록들의 점수 합 계산
     */
    public static void main(String[] args) throws Exception {
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
                case "R":
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
                if (removed.equals(next))
                    break;
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
            // 해시코드를 생성하는 공식.
            // 31은 소수(prime number)이며, 해시 충돌을 줄이는데 도움을 주는 값으로 자주 사용된다.
            // 이 방식은 자바의 `String` 클래스에서도 사용하는 방식과 유사.
            // x와 y 값의 조합을 통해 이 객체의 고유한 해시 코드를 생성.
            return 31 * x + y;
        }

        @Override
        public boolean equals(Object obj) {
            // 자기 자신과 비교했을 때는 항상 true를 반환.
            if (this == obj)
                return true;

            // obj가 null이거나, obj의 클래스 타입이 현재 클래스와 같지 않다면 false 반환.
            // 이는 서로 다른 타입의 객체는 절대 같을 수 없다는 원칙을 따름.
            if (obj == null || getClass() != obj.getClass())
                return false;

            // obj를 현재 클래스 타입인 Point로 캐스팅.
            Point point = (Point) obj;

            // x 좌표와 y 좌표가 모두 같을 때만 두 Point 객체가 동일하다고 판단.
            return x == point.x && y == point.y;
        }
        /*
            객체를 HashMap이나 HashSet 같은 컬렉션의 키로 사용할 때 그 중요성이 더 커진다.
            이 메소드들이 제대로 구현되어 있지 않으면, 컬렉션의 효율성이 떨어지거나 올바르게 동작하지 않을 수 있다.
         */
    }
}
