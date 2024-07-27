package week12.baek.july26.baek;


import java.io.*;
import java.util.StringTokenizer;

public class S2290 {
    static char[][] arr; // 디지털 숫자를 표시할 2차원 배열
    static int s; // 숫자의 크기를 나타내는 변수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken()); // 숫자의 크기 입력
        String n = st.nextToken(); // 표시할 숫자 문자열 입력

        // 배열의 높이와 너비 계산
        int height = 2 * s + 3;
        int width = (s + 2) * n.length();
        arr = new char[height][width + 1]; // 숫자를 저장할 배열 초기화

        // 입력된 숫자를 순차적으로 처리
        for (int i = 0; i < n.length(); i++) {
            char c = n.charAt(i);

            // 숫자별로 적절한 함수 호출하여 배열에 표시
            switch (c) {
                case '0':
                    hyphen(i, 0); // 상단 가로선
                    vertical(i, 1); // 왼쪽 상단 세로선
                    vertical(i, 3); // 오른쪽 상단 세로선
                    vertical(i, 2); // 왼쪽 하단 세로선
                    vertical(i, 4); // 오른쪽 하단 세로선
                    hyphen(i, 2); // 하단 가로선
                    break;

                case '1':
                    vertical(i, 3); // 오른쪽 상단 세로선
                    vertical(i, 4); // 오른쪽 하단 세로선
                    break;

                case '2':
                    hyphen(i, 0); // 상단 가로선
                    hyphen(i, 1); // 중앙 가로선
                    hyphen(i, 2); // 하단 가로선
                    vertical(i, 3); // 오른쪽 상단 세로선
                    vertical(i, 2); // 왼쪽 하단 세로선
                    break;

                case '3':
                    hyphen(i, 0); // 상단 가로선
                    vertical(i, 3); // 오른쪽 상단 세로선
                    hyphen(i, 1); // 중앙 가로선
                    vertical(i, 4); // 오른쪽 하단 세로선
                    hyphen(i, 2); // 하단 가로선
                    break;

                case '4':
                    vertical(i, 1); // 왼쪽 상단 세로선
                    vertical(i, 3); // 오른쪽 상단 세로선
                    hyphen(i, 1); // 중앙 가로선
                    vertical(i, 4); // 오른쪽 하단 세로선
                    break;

                case '5':
                    hyphen(i, 0); // 상단 가로선
                    vertical(i, 1); // 왼쪽 상단 세로선
                    hyphen(i, 1); // 중앙 가로선
                    vertical(i, 4); // 오른쪽 하단 세로선
                    hyphen(i, 2); // 하단 가로선
                    break;

                case '6':
                    hyphen(i, 0); // 상단 가로선
                    vertical(i, 1); // 왼쪽 상단 세로선
                    hyphen(i, 1); // 중앙 가로선
                    vertical(i, 2); // 왼쪽 하단 세로선
                    vertical(i, 4); // 오른쪽 하단 세로선
                    hyphen(i, 2); // 하단 가로선
                    break;

                case '7':
                    hyphen(i, 0); // 상단 가로선
                    vertical(i, 3); // 오른쪽 상단 세로선
                    vertical(i, 4); // 오른쪽 하단 세로선
                    break;

                case '8':
                    hyphen(i, 0); // 상단 가로선
                    vertical(i, 1); // 왼쪽 상단 세로선
                    vertical(i, 3); // 오른쪽 상단 세로선
                    hyphen(i, 1); // 중앙 가로선
                    vertical(i, 2); // 왼쪽 하단 세로선
                    vertical(i, 4); // 오른쪽 하단 세로선
                    hyphen(i, 2); // 하단 가로선
                    break;

                case '9':
                    hyphen(i, 0); // 상단 가로선
                    vertical(i, 1); // 왼쪽 상단 세로선
                    vertical(i, 3); // 오른쪽 상단 세로선
                    hyphen(i, 1); // 중앙 가로선
                    vertical(i, 4); // 오른쪽 하단 세로선
                    hyphen(i, 2); // 하단 가로선
                    break;
            }
        }

        // 배열에 저장된 내용을 출력
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < height; i++) {
            for (int j = 1; j <= width; j++) {
                if (arr[i][j] == '-' || arr[i][j] == '|') {
                    bw.write(arr[i][j]);
                } else { // 빈 공간을 ' '로 채움
                    bw.write(" ");
                }
                // 각 숫자 간의 구분을 위해 공백 추가
                if (j % (s + 2) == 0) {
                    bw.write(" ");
                }
            }
            bw.newLine();
        }
        bw.flush(); // 결과 출력
        bw.close();
        br.close();
    }

    // '-' 문자를 s번 출력하는 함수, command는 위치를 결정
    static void hyphen(int index, int command) {
        int row = command * s + command; // '-'의 행 위치 계산
        int col = index * (s + 2) + 2; // '-'의 열 위치 계산

        for (int i = 0; i < s; i++) {
            arr[row][col + i] = '-'; // '-' 출력
        }
    }

    // '|' 문자를 s번 출력하는 함수, command는 위치를 결정
    static void vertical(int index, int command) {
        if (command == 1 || command == 2) { // 왼쪽 세로줄
            int col = index * (s + 2) + 1;
            int row = (command - 1) * s + command;
            for (int i = 0; i < s; i++) {
                arr[row + i][col] = '|';
            }
        } else if (command == 3 || command == 4) { // 오른쪽 세로줄
            int col = index * (s + 2) + s + 2;
            int row = (command - 3) * s + (command - 2);
            for (int i = 0; i < s; i++) {
                arr[row + i][col] = '|';
            }
        }
    }
}

/* command

 --0--
|     |
1     3
|     |
 --1--
|     |
2     4
|     |
 --2--


 */
