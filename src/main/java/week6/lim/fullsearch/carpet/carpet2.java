package week6.lim.fullsearch.carpet;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/42842
// 참고: https://easybrother0103.tistory.com/110

import java.util.Arrays;

public class carpet2 {
    /**
     *  먼저, 위와 같이 가운데에 노란색 격자가 위치하기 위해서는 가로 세로가 3 이상이여야한다.
     *
     *  문제에서 요구하는 경우의 수를 찾으려면 일단 카펫 사이즈의 경우의 수를 구해야한다. ( 0 x 0 )
     *
     *  카펫 사이즈 경우의 수를 구하기 위해서 brown 격자 수 + yellow 격자 수의 약수를 구한다.
     *
     *  예를 들어, brown = 10,  yellow = 2 이라고 가정할 때,
     *  12의 약수를 구한다 (1, 12) , (2, 6) , (3, 4) , (4, 3) , (6, 2) , (12, 1)
     *
     *  이렇게 구한 경우의 수 중에서 정답을 골라야 한다.
     *
     *  일단 가로의 길이가 세로보다 길거나 같다고 제한사항에 있으므로
     *  경우의 수가 (4, 3) , (6, 2) , (12, 1)로 줄어든다.
     *
     *  또한, 가운데에 노란색 격자가 위치하기 위해선
     *  가로, 세로 길이가 모두 3 이상이여야 하므로 (6, 2) , (12, 1)는 걸러진다.
     *
     *  마지막으로 해당 카펫이 입력으로 주어진 yellow의 개수만큼
     *  노란색 격자가 가운데에 위치할 수 있는지 구해야한다.
     *
     *  (가로 - 2) * (세로 - 2 ) = yellow의 개수이므로
     *  (4 - 2) * (3 - 1) = 2이다. 그러므로 정답
     */
    public static int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        int sum = brown + yellow;

        for (int i = 3; i < sum; i++) {
            int j = sum / i;

            if (sum % i == 0 && j >= 3) {
                int col = Math.max(i, j);
                int row = Math.min(i, j);
                int center = (col - 2) * (row - 2);

                if (center == yellow) {
                    answer[0] = col;
                    answer[1] = row;
                    return answer;
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        int brown1 = 10;
        int yellow1 = 2;
        System.out.println(Arrays.toString(solution(brown1, yellow1))); // [4, 3]

        int brown2 = 8;
        int yellow2 = 1;
        System.out.println(Arrays.toString(solution(brown2, yellow2))); // [3, 3]

        int brown3 = 24;
        int yellow3 = 24;
        System.out.println(Arrays.toString(solution(brown3, yellow3))); // [8, 6]
    }
}
