package week6.lim.dp.arithmetic;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/1843
// 참고: https://velog.io/@kamo0915/Java-%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EC%82%AC%EC%B9%99%EC%97%B0%EC%82%B0-4%EB%8B%A8%EA%B3%84

public class arithmetic3 {
    static int[][] min, max;

    public static int solution(String arr[]) {
        int size = arr.length/2 + 1;
        min = new int[size][size];
        max = new int[size][size];

        int[] list = new int[size];

        for (int i = 0; i < arr.length; i+=2) {
            int num = Integer.parseInt(arr[i]);
            if (i == 0) {
                list[i/2] = num;
            } else {
                list[i/2] = arr[i-1].equals("+") ? num : -num;
            }
        }

        for (int i = size-1; i >= 0; i--) {
            for (int j = i; j < size; j++) {
                if (i == j) {
                    min[i][j] = list[i];
                    max[i][j] = list[i];
                } else {
                    min[i][j] = Integer.MAX_VALUE;
                    max[i][j] = Integer.MIN_VALUE;

                    for (int k = i; k < j; k++) {
                        boolean value = k == i ? true : false;
                        func(min[i][k], min[k+1][j], i, j, value);
                        func(min[i][k], max[k+1][j], i, j, value);
                        func(max[i][k], min[k+1][j], i, j, value);
                        func(max[i][k], max[k+1][j], i, j, value);
                    }
                }
            }
        }
        return max[0][size-1];
    }

    public static void func(int a, int b, int x, int y, boolean value) {
        if (value && a < 0) {
            min[x][y] = Math.min(min[x][y], Math.min(a-b,a+b));
            max[x][y] = Math.max(max[x][y], Math.max(a-b,a+b));
        } else {
            min[x][y] = Math.min(min[x][y], a+b);
            max[x][y] = Math.max(max[x][y], a+b);
        }
    }

    public static void main(String[] args) {
        String[] arr1 = {"1", "-", "3", "+", "5", "-", "8"};
        String[] arr2 = {"5", "-", "3", "+", "1", "+", "2", "-", "4"};

        System.out.println(solution(arr1)); // 1
        System.out.println(solution(arr2)); // 3
    }
}
