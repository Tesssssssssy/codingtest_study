package week2.kim.week2_2.search;

import java.util.HashSet;
import java.util.Set;

/**
 * [소수 찾기]
 *
 * 한자리 숫자가 적힌 종이 조각이 흩어져있습니다.
 * 흩어진 종이 조각을 붙여 소수를 몇 개 만들 수 있는지 알아내려 합니다.
 *
 * 각 종이 조각에 적힌 숫자가 적힌 문자열 numbers가 주어졌을 때,
 * 종이 조각으로 만들 수 있는 소수가 몇 개인지 return 하도록 solution 함수를 완성해주세요.
 *
 * 제한사항
 * numbers는 길이 1 이상 7 이하인 문자열입니다.
 * numbers는 0~9까지 숫자만으로 이루어져 있습니다.
 * "013"은 0, 1, 3 숫자가 적힌 종이 조각이 흩어져있다는 의미입니다.
 *
 * 입출력 예
 * numbers	return
 * "17"		3
 * "011"	2
 *
 * 입출력 예 설명
 * 예제 #1
 * [1, 7]으로는 소수 [7, 17, 71]를 만들 수 있습니다.
 *
 * 예제 #2
 * [0, 1, 1]으로는 소수 [11, 101]를 만들 수 있습니다.
 *
 * 11과 011은 같은 숫자로 취급합니다.
*/

public class Ex1 {
    public static void main(String[] args) {
        Ex1 ex1 = new Ex1();

        // 3 반환
        System.out.println(ex1.solution("17"));
        // 2 반환
        System.out.println(ex1.solution("011"));
    }

    public int solution(String numbers) {
        int answer = 0;

        // 중복 허용하지 않고 소수를 저장
        Set<Integer> primes = new HashSet<>();

        generatePrimes(primes, "", numbers.toCharArray(), new boolean[numbers.length()]);

        answer = primes.size();

        return answer;
    }

    // 재귀 > 숫자 조합 생성 + 소수인지 확인
    // primes > 소수를 저장
    // current > 현재까지 생성된 숫자 조합
    // digits > 주어진 숫자 조각들을 담은 배열
    // used > 각 숫자 조각의 사용 여부를 나타내는 배열
    private void generatePrimes(Set<Integer> primes, String current, char[] digits, boolean[] used) {
        if (!current.isEmpty()) {
            int num = Integer.parseInt(current);
            if (isPrime(num)) {
                primes.add(num);
            }
        }

        for (int i = 0; i < digits.length; i++) {
            if (!used[i]) {
                used[i] = true;
                generatePrimes(primes, current + digits[i], digits, used);
                used[i] = false;
            }
        }
    }

    // 소수인지 확인
    private boolean isPrime(int num) {
        if (num < 2) {
            return false;
        }

        // 에라토스테네스 체
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }
}
