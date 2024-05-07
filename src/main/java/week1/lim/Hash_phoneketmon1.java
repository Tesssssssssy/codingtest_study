package week1.lim;

import java.util.HashMap;
import java.util.Map;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/1845?language=java

public class Hash_phoneketmon1 {
    static int solution(int[] nums) {
        Map<Integer, Integer> pokemonTypes = new HashMap<>(); // 폰켓몬의 종류별로 개수 세기 위해 HashMap 사용
        int n = nums.length;

        for (int num : nums) {  // nums 배열 돌면서 폰켓몬 번호를 key로, 해당 폰켓몬 개수를 value로 저장
            pokemonTypes.put(num, pokemonTypes.getOrDefault(num, 0) + 1);
        }

        int numTypes = pokemonTypes.size(); // HashMap의 크기는 폰켓몬 종류의 수 (중복 제외), 저장된 key의 개수

        return Math.min(numTypes, n / 2);   // 중복을 제외한 폰켓몬 종류의 수와 선택할 수 있는 최대 폰켓몬 수 중 작은 값 반환
    }

    /*
        ex.)
        nums = {3, 1, 2, 3}                            nums2 = {1, 2, 3, 4, 4, 3}
        -> HashMap은 {3:2, 1:1, 2:1}                     -> HashMap은 {4:2, 3:2, 1:1, 2:1}
        -> 폰켓몬 총 3가지 종류                              -> 폰켓몬 총 4가지 종류
        -> n/2 = 2                                       -> n/2 = 3
        => 폰켓몬 종류 (3)와 n/2 (2) 중 최소값 = 2             => 폰켓몬 종류 (4)와 n/2 (3) 중 최소값 = 3
    */

    public static void main(String[] args) {
        int[] nums = {3, 1, 2, 3};
        int answer = solution(nums);
        System.out.println(answer); // 2
    }
}
