package week6.lim.fullsearch.primenumber;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/42839
// 참고: https://m.blog.naver.com/sosow0212/222683674446

import java.util.HashSet;
import java.util.Iterator;

public class findPrimeNumber2 {
    static HashSet<Integer> numberSet = new HashSet<>();

    public static boolean isPrime(int num) {
        System.out.println();

        if (num == 0 || num == 1) // 0과 1은 소수가 아님
            return false;

        System.out.println("num: " + num);

        int limit = (int) Math.sqrt(num); // 에라토스테네스의 체의 limit 계산
        System.out.println("에라토스테네스의 체 limit 계산> limit: " + limit);

        for (int i = 2; i <= limit; i++) { // limit까지 배수 여부 확인
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void recursive(String combination, String numbers) {
        System.out.println();
        System.out.println("---recursive 메소드 실행----");

        // 조합을 숫자로 변환하여 중복 제거를 위해 Set에 저장
        if (!combination.equals("") && !combination.startsWith("0")) {
            numberSet.add(Integer.parseInt(combination)); // "0"으로 시작하지 않는 숫자만 추가
            System.out.println("combination: " + Integer.parseInt(combination));
        }

        // 남은 숫자로 모든 조합을 생성
        System.out.println("numbers.length: " + numbers.length());
        System.out.println("combination: " + combination);
        System.out.println("numbers: " + numbers);
        for (int i = 0; i < numbers.length(); i++) {
            System.out.println("numbers: " + numbers);
            System.out.println("numbers.substring(0, " + i + "): " + numbers.substring(0, i));
            System.out.println("numbers.substring(" + i + " + 1): " + numbers.substring(i + 1));
            recursive(combination + numbers.charAt(i), numbers.substring(0, i) + numbers.substring(i + 1));
        }
    }

    public static int solution(String numbers) {
        numberSet.clear(); // 매 호출 시 Set 초기화
        recursive("", numbers); // 모든 숫자 조합 생성

        int count = 0;

        // Set에 저장된 모든 숫자에 대해 소수 여부 확인
        Iterator<Integer> it = numberSet.iterator();
        while (it.hasNext()) {
            if (isPrime(it.next())) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
//        System.out.println(solution("17"));  // 예상 출력: 3
        System.out.println("\nreturn: " + solution("011")); // 예상 출력: 2

        /*
            ---recursive 메소드 실행----
            numbers.length: 3
            combination:
            numbers: 011
            numbers: 011
            numbers.substring(0, 0):
            numbers.substring(0 + 1): 11

            ---recursive 메소드 실행----
            numbers.length: 2
            combination: 0
            numbers: 11
            numbers: 11
            numbers.substring(0, 0):
            numbers.substring(0 + 1): 1

            ---recursive 메소드 실행----
            numbers.length: 1
            combination: 01
            numbers: 1
            numbers: 1
            numbers.substring(0, 0):
            numbers.substring(0 + 1):

            ---recursive 메소드 실행----
            numbers.length: 0
            combination: 011
            numbers:
            numbers: 11
            numbers.substring(0, 1): 1
            numbers.substring(1 + 1):

            ---recursive 메소드 실행----
            numbers.length: 1
            combination: 01
            numbers: 1
            numbers: 1
            numbers.substring(0, 0):
            numbers.substring(0 + 1):

            ---recursive 메소드 실행----
            numbers.length: 0
            combination: 011
            numbers:
            numbers: 011
            numbers.substring(0, 1): 0
            numbers.substring(1 + 1): 1

            ---recursive 메소드 실행----
            combination: 1
            numbers.length: 2
            combination: 1
            numbers: 01
            numbers: 01
            numbers.substring(0, 0):
            numbers.substring(0 + 1): 1

            ---recursive 메소드 실행----
            combination: 10
            numbers.length: 1
            combination: 10
            numbers: 1
            numbers: 1
            numbers.substring(0, 0):
            numbers.substring(0 + 1):

            ---recursive 메소드 실행----
            combination: 101
            numbers.length: 0
            combination: 101
            numbers:
            numbers: 01
            numbers.substring(0, 1): 0
            numbers.substring(1 + 1):

            ---recursive 메소드 실행----
            combination: 11
            numbers.length: 1
            combination: 11
            numbers: 0
            numbers: 0
            numbers.substring(0, 0):
            numbers.substring(0 + 1):

            ---recursive 메소드 실행----
            combination: 110
            numbers.length: 0
            combination: 110
            numbers:
            numbers: 011
            numbers.substring(0, 2): 01
            numbers.substring(2 + 1):

            ---recursive 메소드 실행----
            combination: 1
            numbers.length: 2
            combination: 1
            numbers: 01
            numbers: 01
            numbers.substring(0, 0):
            numbers.substring(0 + 1): 1

            ---recursive 메소드 실행----
            combination: 10
            numbers.length: 1
            combination: 10
            numbers: 1
            numbers: 1
            numbers.substring(0, 0):
            numbers.substring(0 + 1):

            ---recursive 메소드 실행----
            combination: 101
            numbers.length: 0
            combination: 101
            numbers:
            numbers: 01
            numbers.substring(0, 1): 0
            numbers.substring(1 + 1):

            ---recursive 메소드 실행----
            combination: 11
            numbers.length: 1
            combination: 11
            numbers: 0
            numbers: 0
            numbers.substring(0, 0):
            numbers.substring(0 + 1):

            ---recursive 메소드 실행----
            combination: 110
            numbers.length: 0
            combination: 110
            numbers:


            num: 101
            에라토스테네스의 체 limit 계산> limit: 10

            num: 10
            에라토스테네스의 체 limit 계산> limit: 3

            num: 11
            에라토스테네스의 체 limit 계산> limit: 3

            num: 110
            에라토스테네스의 체 limit 계산> limit: 10

            return: 2
        */
    }
}