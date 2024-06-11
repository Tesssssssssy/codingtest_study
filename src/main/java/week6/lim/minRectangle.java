package week6.lim;

public class minRectangle {
    public static int solution(int[][] sizes) {
        int answer = 0;

        /*
            가로 길이가 가로, 세로 중 가장 긴 길이로 생각하자.
            (명함은 회전시켜 수납할 수 있으니까)
        */
        int maxW = 0;
        int maxH = 0;

        // 모든 명함에 대해
        for (int[] size : sizes) {
            // 가로와 세로 중 작은 값과 큰 값을 정렬
            int w = Math.max(size[0], size[1]);
            int h = Math.min(size[0], size[1]);

            // 최대 가로 및 세로 길이 업데이트
            maxW = Math.max(maxW, w);
            maxH = Math.max(maxH, h);
        }

        // 최소 지갑 크기 반환
        answer = maxW * maxH;

        return answer;
    }

    public static void main(String[] args) {
        int[][] sizes1 = {{60, 50}, {30, 70}, {60, 30}, {80, 40}};
        int[][] sizes2 = {{10, 7}, {12, 3}, {8, 15}, {14, 7}, {5, 15}};
        int[][] sizes3 = {{14, 4}, {19, 6}, {6, 16}, {18, 7}, {7, 11}};

        System.out.println(solution(sizes1)); // 4000
        System.out.println(solution(sizes2)); // 120
        System.out.println(solution(sizes3)); // 133
    }
}
