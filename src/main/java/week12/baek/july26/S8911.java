package week12.baek.july26;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S8911 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        //좌, 상, 우, 하
        int [] dx = {-1,0,1,0};
        int [] dy = {0,1,0,-1};

        for (int i = 0; i < t; i++) {
            String op = br.readLine();

            //좌표 초기화
            int x = 0, y = 0;

            //방향 초기화
            int p = 1;

            int minX=0, minY=0, maxX=0, maxY=0;
            for (int j = 0; j < op.length(); j++) {
                switch (op.charAt(j)) {
                    //한 눈금 앞으로
                    case 'F':
                        x += dx[p];
                        y += dy[p];
                        break;

                    //한 눈금 뒤로
                    case 'B':
                        x -= dx[p];
                        y -= dy[p];
                        break;

                    //왼쪽으로 90도 회전
                    case 'L':
                        p--;
                        if( p < 0 ) p += 4;
                        break;

                    //오른쪽으로 90도 회전
                    case 'R':
                        p++;
                        if( p >= 4) p %= 4;
                        break;

                }

                minX = Math.min(minX, x);
                minY = Math.min(minY, y);
                maxX = Math.max(maxX, x);
                maxY = Math.max(maxY, y);
            }

            System.out.println((Math.abs(minX)+Math.abs(maxX))*(Math.abs(minY)+Math.abs(maxY)));
        }
    }
}
