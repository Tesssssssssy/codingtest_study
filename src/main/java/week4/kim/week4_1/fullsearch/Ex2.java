package week4.kim.week4_1.fullsearch;

import java.util.Arrays;

/**
 * [카펫]
 *
 * 문제 설명
 * Leo는 카펫을 사러 갔다가 아래 그림과 같이 중앙에는 노란색으로 칠해져 있고 테두리 1줄은 갈색으로 칠해져 있는 격자 모양 카펫을 봤습니다.
 *
 * carpet.png
 *
 * Leo는 집으로 돌아와서 아까 본 카펫의 노란색과 갈색으로 색칠된 격자의 개수는 기억했지만, 전체 카펫의 크기는 기억하지 못했습니다.
 *
 * Leo가 본 카펫에서 갈색 격자의 수 brown, 노란색 격자의 수 yellow가 매개변수로 주어질 때 카펫의 가로,
 * 세로 크기를 순서대로 배열에 담아 return 하도록 solution 함수를 작성해주세요.
 *
 * 제한사항
 * 갈색 격자의 수 brown은 8 이상 5,000 이하인 자연수입니다.
 * 노란색 격자의 수 yellow는 1 이상 2,000,000 이하인 자연수입니다.
 * 카펫의 가로 길이는 세로 길이와 같거나, 세로 길이보다 깁니다.
 *
 * 입출력 예
 * brown	yellow	return
 * 10	    2	    [4, 3]
 * 8	    1	    [3, 3]
 * 24	    24	    [8, 6]
 */

public class Ex2 {
    public static void main(String[] args) {
        Ex2 ex2 = new Ex2();

        int brown = 10;
        int yellow = 2;
        int[] result = solution(brown, yellow);

        // [4, 3]
        System.out.println(Arrays.toString(result));
    }

    public static int[] solution(int brown, int yellow) {
        // 전체 격자 수
        int total = brown + yellow;

        // 세로 길이는 3부터 시작
        for (int height = 3; height <= total; height++) {
            // 세로 길이로 나누어 떨어지면 가로 길이를 구할 수 있음
            if (total % height == 0) {
                // 가로 길이 계산
                int width = total / height;

                // 가로와 세로 길이를 이용하여 노란색 격자 수를 계산
                // 주어진 노란색 격자 수와 비교
                if ((width - 2) * (height - 2) == yellow) {
                    // 가로 길이가 세로 길이보다 크거나 같으므로 가로 길이를 먼저 반환
                    return new int[]{width, height};
                }
            }
        }

        return null;
    }
}