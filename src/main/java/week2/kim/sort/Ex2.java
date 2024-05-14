package week2.kim.sort;

import java.util.Arrays;

/**
 * [가장 큰 수]
 *
 * 0 또는 양의 정수가 주어졌을 때, 정수를 이어 붙여 만들 수 있는 가장 큰 수를 알아내 주세요.
 *
 * 예를 들어, 주어진 정수가 [6, 10, 2]라면 [6102, 6210, 1062, 1026, 2610, 2106]를 만들 수 있고, 이중 가장 큰 수는 6210입니다.
 *
 * 0 또는 양의 정수가 담긴 배열 numbers가 매개변수로 주어질 때, 순서를 재배치하여 만들 수 있는 가장 큰 수를 문자열로 바꾸어 return 하도록 solution 함수를 작성해주세요.
 *
 * 제한 사항
 * numbers의 길이는 1 이상 100,000 이하입니다.
 * numbers의 원소는 0 이상 1,000 이하입니다.
 * 정답이 너무 클 수 있으니 문자열로 바꾸어 return 합니다.
 *
 * 입출력 예
 * numbers		        return
 * [6, 10, 2]		    "6210"
 * [3, 30, 34, 5, 9]	"9534330"
*/

public class Ex2 {
    public static void main(String[] args) {
        Ex2 ex2 = new Ex2();

        int[] numbers = {6, 10, 2};

        // "6210" 반환
        System.out.println(ex2.solution(numbers));
    }

    public String solution(int[] numbers) {
        String answer = "";

        // 숫자 배열을 문자열 배열로 변환
        String[] strNums = new String[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            strNums[i] = String.valueOf(numbers[i]);
        }

        // 문자열 배열을 정렬
        // 두 문자열을 순서에 따라 더한 값을 비교하여 정렬
        Arrays.sort(strNums, (o1, o2) -> (o2 + o1).compareTo(o1 + o2));

        // 가장 큰 수가 0이라면, 전체 숫자가 0이므로 "0"을 반환
        if (strNums[0].equals("0")) {
            return "0";
        }

        // 그렇지 않다면, 모든 숫자를 이어서 반환
        StringBuilder sb = new StringBuilder();
        for (String strNum : strNums) {
            sb.append(strNum);
        }

//        // 정렬된 문자 하나로 합치기
//        for(int i=0; i< strNums.length; i++){
//            answer += strNums[i];
//        }

        answer = sb.toString();

        return answer;
    }
}
