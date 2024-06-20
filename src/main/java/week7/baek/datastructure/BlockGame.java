package week7.baek.datastructure;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
public class BlockGame {
    static Stack<Integer> score;
    static List<Point> map;

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Point point = (Point) obj;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public static void search(int x, int y, int s) {
        Point p = new Point(x, y);

        // 들어갈 자리가 있는지 확인
        while (map.contains(p)) {
            map.remove(map.size()-1);
            score.pop();
        }

        // 이제 queue와 map에 추가
        score.push(s);
        map.add(p);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        int N = Integer.parseInt(input);
        String D = br.readLine();
        String[] s = br.readLine().split(" ");

        score = new Stack<>();
        map = new ArrayList<>();
        int x = 0, y = 0;
        score.add(1);
        map.add(new Point(0,0));

        for (int i = 0; i < N; i++) {
            char c = D.charAt(i);
            switch (c) {
                case 'L':
                    x -= 1;
                    break;
                case 'R':
                    x += 1;
                    break;
                case 'U':
                    y += 1;
                    break;
                case 'D':
                    y -= 1;
                    break;
            }
            search(x, y, Integer.parseInt(s[i]));
//            System.out.println(score);
        }

        int count = 0;
        while (!score.isEmpty()) {
            count += score.pop();
        }
        System.out.println(count);
    }
}

