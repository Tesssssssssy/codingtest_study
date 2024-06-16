package week6.lim.bfsdfs.targetnumber;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/43165

public class targetNumber2 {
    /**
     *  dfs를 이용한 풀이
     */
    private static int answer = 0;

    public static int solution(int[] numbers, int target) {
        answer = 0;
        dfs(numbers, target, 0, 0);
        return answer;
    }

    private static void dfs(int[] numbers, int target, int index, int currentSum) {
        if (index == numbers.length) {
            if (currentSum == target) {
                answer++;
            }
            return;
        }

        // 현재 숫자를 더하는 경우
        dfs(numbers, target, index + 1, currentSum + numbers[index]);
        // 현재 숫자를 빼는 경우
        dfs(numbers, target, index + 1, currentSum - numbers[index]);
    }

    public static void main(String[] args) {
        int[] numbers1 = {1, 1, 1, 1, 1};
        int target1 = 3;
        System.out.println(solution(numbers1, target1)); // 5

        int[] numbers2 = {4, 1, 2, 1};
        int target2 = 4;
        System.out.println(solution(numbers2, target2)); // 2
    }
}
