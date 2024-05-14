package week2.lim.Sort.biggestnum;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/42746
// 참고: https://hstory0208.tistory.com/entry/Java%EC%9E%90%EB%B0%94-%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-Lv2-%EA%B0%80%EC%9E%A5-%ED%81%B0-%EC%88%98-%EC%A0%95%EB%A0%AC

import java.util.Arrays;

public class BigNumber {
    public static String solution(int[] numbers) {
        String[] str = new String[numbers.length];

        for(int i = 0; i < numbers.length; i++){
            str[i] = String.valueOf(numbers[i]);
        }

        // 두 수를 합친 값이 큰 순서대로 (내림차순)
        // o1 : 10, o2 : 2 이면 102과 210 중 뭐가 더 큰지 비교
        Arrays.sort(str, (o1, o2) -> (o2 + o1).compareTo(o1 + o2));

        System.out.println(Arrays.toString(str));
        // [6, 2, 10]
        // [9, 5, 34, 3, 30]

        // 첫 번째 수가 0이면 0으로만 이뤄진 배열이므로 0을 리턴.
        if (str[0].equals("0")) {
            return "0";
        }

        return String.join("", str);

        /*
            StringBuilder sb = new StringBuilder();
            for (String str : strs) {
                sb.append(str);
            }

            return sb.toString();
        */
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[] {6, 10, 2})); // 6210
        System.out.println(solution(new int[] {3, 30, 34, 5, 9}));  // 9534330
    }
}
