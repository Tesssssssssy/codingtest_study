package week3.lim.greedy.makebignumber;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/42883
// 참고: https://velog.io/@joonghyun/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%ED%81%B0-%EC%88%98-%EB%A7%8C%EB%93%A4%EA%B8%B0-%EC%9E%90%EB%B0%94

public class makeBigNumber2 {
    public static String solution(String number, int k) {
        // 그리디 알고리즘
        // 각 자리에서 최고로 높은 수가 나오는 경우를 생각하기

        String answer = "";
        StringBuilder answerBuilder = new StringBuilder();

        char[] array = number.toCharArray();

        int len = array.length - k;

        // 문자 비교를 시작하는 인덱스를 나타내는 start 변수
        int start = 0;

        for (int i = 0; i < len; i++){
            char max = '0';
            for (int j = start; j <= i + k; j++) {
                // 가장 큰수를 골라서 그 다음 인덱스를 시작 인덱스로 설정하기
                if (array[j] > max) {
                    max = array[j];
                    start = j + 1;
                }
            }
            // 가장 큰 문자를 String에 넣어주기
            answerBuilder.append(Character.toString(max));
        }

        // k개의 수를 제거할 때 얻을 수 있는 가장 큰 숫자를 구하려 한다
        answer = answerBuilder.toString();
        return answer;
    }

    public static void main(String[] args) {
        String number1 = "1924";
        int k1 = 2;
        System.out.println(solution(number1, k1));  // 94

//        String number2 = "1231234";
//        int k2 = 3;
//        System.out.println(solution(number2, k2));  // 3234
//
//        String number3 = "4177252841";
//        int k3 = 4;
//        System.out.println(solution(number3, k3));  // 775841
    }
}
