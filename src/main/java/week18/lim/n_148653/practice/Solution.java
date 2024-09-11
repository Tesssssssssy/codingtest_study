package week18.lim.n_148653.practice;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/148653
// 참고: https://hy-ung.tistory.com/186

public class Solution {
    public static int solution(int storey) {
        int answer = 0;

        while (storey > 0) {
            int digit = storey % 10;
            storey /= 10;

            if (digit == 5) {
                if (storey % 10 >= 5) {
                    answer += (10 - digit);
                    storey++;
                }
                else {
                    answer += digit;
                }
            } else if (digit > 5) {
                answer += (10 - digit);
                storey++;
            } else {
                answer += digit;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(16)); // 6
        System.out.println(solution(2554)); // 16
    }
}
