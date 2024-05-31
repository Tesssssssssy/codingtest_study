package week2.kim.week2_1.sort;

import java.util.Arrays;

/**
 * [H-Index]
 *
 * H-Index는 과학자의 생산성과 영향력을 나타내는 지표입니다. 어느 과학자의 H-Index를 나타내는 값인 h를 구하려고 합니다. 위키백과1에 따르면, H-Index는 다음과 같이 구합니다.
 *
 * 어떤 과학자가 발표한 논문 n편 중, h번 이상 인용된 논문이 h편 이상이고 나머지 논문이 h번 이하 인용되었다면 h의 최댓값이 이 과학자의 H-Index입니다.
 *
 * 어떤 과학자가 발표한 논문의 인용 횟수를 담은 배열 citations가 매개변수로 주어질 때, 이 과학자의 H-Index를 return 하도록 solution 함수를 작성해주세요.
 *
 * 제한사항
 * 과학자가 발표한 논문의 수는 1편 이상 1,000편 이하입니다.
 * 논문별 인용 횟수는 0회 이상 10,000회 이하입니다.
 *
 * 입출력 예
 * citations		return
 * [3, 0, 6, 1, 5]	3
 *
 * [0 1 3 5 6]
 * 0h 5
 * 1h 4
 * 3h 3
 * 5h 2
 *
 * 입출력 예 설명
 * 이 과학자가 발표한 논문의 수는 5편이고, 그중 3편의 논문은 3회 이상 인용되었습니다.
 * 그리고 나머지 2편의 논문은 3회 이하 인용되었기 때문에 이 과학자의 H-Index는 3입니다.
*/

public class Ex3 {
    public static void main(String[] args) {
        Ex3 ex3 = new Ex3();

        int[] citations = {3, 0, 6, 1, 5};

        // 3 반환
        System.out.println(ex3.solution(citations));
    }

    public int solution(int[] citations) {
        int answer = 0;

        // 배열을 정렬
        Arrays.sort(citations);

        // H-Index 계산
        for(int i = 0; i < citations.length; i++) {
            // 인용된 논문의 수
            int h = citations.length - i;

            // h번 이상 인용된 논문, 나머지 h번 이하 인용
            if(citations[i] >= h) {
                answer = h;
                break;
            }
        }

//        Integer[] arr = Arrays.stream(citations).boxed().toArray(Integer[]::new);
//        Arrays.sort(arr, (o1, o2) -> o2.compareTo(o1));
//
//        // H-Index 계산
//        int i = 0;
//        while (i < arr.length && arr[i] > i) {
//            i++;
//        }


//        int n = citations.length;
//        int[] count = new int[n + 1];
//
//        // 각 인용 횟수에 해당하는 논문 수 카운팅
//        for (int c : citations) {
//            if (c > n) {
//                count[n]++;
//            } else {
//                count[c]++;
//            }
//        }
//
//        int total = 0;
//        // 뒤에서부터 논문 수 누적하며 H-Index 찾기
//        for (int i = n; i >= 0; i--) {
//            total += count[i];
//            if (total >= i) {
//                return i;
//            }
//        }
//        return 0;

        return answer;
    }
}
