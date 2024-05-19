package week2.lim.fullsearch.primenumber;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/42839

import java.util.HashSet;
import java.util.Set;

public class findPrimeNumber1 {
    public static int solution(String numbers) {
        Set<Integer> primes = new HashSet<>(); // 소수 저장을 위한 Set

        // 가능한 모든 숫자 조합을 생성하여 소수 판별
        permuteAndCheck(numbers, "", primes);

        return primes.size(); // 소수의 개수 반환
    }

    // 순열을 사용하여 가능한 모든 숫자 조합 생성 및 소수 검사
    private static void permuteAndCheck(String numbers, String current, Set<Integer> primes) {
        if (!current.isEmpty()) {
            int num = Integer.parseInt(current);
            if (isPrime(num)) {
                primes.add(num);
            }
        }

        for (int i = 0; i < numbers.length(); i++) {
            char ch = numbers.charAt(i);
            // 재귀적으로 다음 숫자를 추가하여 순열 생성
            permuteAndCheck(numbers.substring(0, i) + numbers.substring(i + 1),
                    current + ch, primes);
        }
    }

    // 주어진 숫자가 소수인지 판별
    private static boolean isPrime(int number) {
        if (number <= 1)
            return false; // 1 이하의 숫자는 소수가 아님
        if (number == 2)
            return true; // 2는 소수
        if (number % 2 == 0)
            return false; // 2를 제외한 짝수는 소수가 아님

        for (int i = 3; i <= Math.sqrt(number); i += 2) {
            if (number % i == 0)
                return false; // 3 이상의 홀수로 나누어떨어지면 소수가 아님
        }
        return true; // 위 조건에 걸리지 않으면 소수
    }

    public static void main(String[] args) {
        System.out.println(solution("17"));  // 3
        System.out.println(solution("011")); // 2
    }
}
