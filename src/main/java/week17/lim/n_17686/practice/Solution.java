package week17.lim.n_17686.practice;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/17686

import java.util.Arrays;

public class Solution {
    public static String[] solution(String[] files) {
        Arrays.sort(files, (file1, file2) -> {
            // file명을 HEAD, NUMBER, TAIL로 분리
            String[] file1Parts = splitFileName(file1);
            String[] file2Parts = splitFileName(file2);

            // HEAD 비교 (대소문자 구분하지 않음)
            int headComparison = file1Parts[0].compareToIgnoreCase(file2Parts[0]);
            if (headComparison != 0) {
                return headComparison;
            }

            // HEAD가 같다면 NUMBER 비교 (숫자로 비교) (headComparison == 0)
            int number1 = Integer.parseInt(file1Parts[1]);
            int number2 = Integer.parseInt(file2Parts[1]);
            return Integer.compare(number1, number2);
        });

        return files;
    }

    private static String[] splitFileName(String file) {
        // 정규식으로 HEAD, NUMBER, TAIL 분리
        String head = "";
        String number = "";
        String tail = "";

        int i = 0;

        // HEAD 분리
        while (i < file.length() && !Character.isDigit(file.charAt(i))) {
            head += file.charAt(i);
            i++;
        }

        // NUMBER 분리
        while (i < file.length() && Character.isDigit(file.charAt(i))) {
            number += file.charAt(i);
            i++;
        }

        // TAIL 분리
        if (i < file.length()) {
            tail = file.substring(i);
        }

        return new String[]{head, number, tail};
    }

    public static void main(String[] args) {
        String[] files1 = {"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"};
        System.out.println(Arrays.toString(solution(files1)));
        // ["img1.png", "IMG01.GIF", "img02.png", "img2.JPG", "img10.png", "img12.png"]

        String[] files2 = {"F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat"};
        System.out.println(Arrays.toString(solution(files2)));
        // ["A-10 Thunderbolt II", "B-50 Superfortress", "F-5 Freedom Fighter", "F-14 Tomcat"]
    }
}
/*
    headComparison의 의미:
        headComparison은 두 파일명의 HEAD 부분을 대소문자 구분 없이 비교한 결과이다.

    이 비교는 compareToIgnoreCase 메서드를 사용하여 이루어지며, 그 결과는 다음과 같다:
        headComparison < 0: file1의 HEAD가 file2의 HEAD보다 사전 순으로 앞에 위치한다는 의미. (즉, file1이 file2보다 먼저 와야 한다.)
        headComparison > 0: file1의 HEAD가 file2의 HEAD보다 사전 순으로 뒤에 위치한다는 의미. (즉, file2가 file1보다 먼저 와야 한다.)
        headComparison == 0: 두 파일명의 HEAD가 같다는 의미.

    if (headComparison != 0):
        이 조건은 두 HEAD가 다르다면, 즉 headComparison이 0이 아닌 경우에만 HEAD의 비교 결과를 반환한다는 의미.
        HEAD가 다른 경우에는 headComparison의 값에 따라 두 파일명의 순서가 결정된다.
        이때 정렬 기준은 HEAD의 사전 순서(알파벳 순서).

    return headComparison;:
        HEAD가 다르다면, 그 차이에 따라 바로 결과를 반환한다.
        HEAD가 같지 않다면 다른 비교는 할 필요가 없으므로, 이 시점에서 비교를 종료하고 결과를 반환한다.

    HEAD가 같다면:
        만약 HEAD 부분이 같다면 (headComparison == 0), HEAD 부분으로는 두 파일을 구분할 수 없으므로,
        다음 기준인 NUMBER 부분을 비교하게 된다. 이때는 NUMBER 부분의 숫자 크기 비교로 정렬 순서를 결정한다.
*/