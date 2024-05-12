package week1.lim.StackQueue.stock;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/42584
// 참고: https://velog.io/@imok-_/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EC%A3%BC%EC%8B%9D%EA%B0%80%EA%B2%A9-java-Stack-%ED%99%9C%EC%9A%A9

import java.util.Arrays;

public class stock1 {
    /**
     *  stack/queue 사용하지 않고 이중 for문을 사용한 방법
     */
    public static int[] solution(int[] prices) {
        int[] answer = new int[prices.length];

        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                answer[i]++; // 뒤에 있는 값들 보다 작거나 같을 때 인덱스 값 하나씩 추가

                if (prices[i] > prices[j]) { // 크면 다음 인데스 비교
                    break;
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] prices = {1, 2, 3, 2, 3};
        System.out.print(Arrays.toString(solution(prices)));  // [4, 3, 1, 1, 0]
    }
}
