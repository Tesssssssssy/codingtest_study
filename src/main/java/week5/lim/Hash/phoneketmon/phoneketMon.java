package week5.lim.Hash.phoneketmon;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/1845

import java.util.HashSet;

public class phoneketMon {
    /**
     *  1. N마리 중 N/2 선택
     *  2. 중류에 따라 번호 붙여서 구분 => 중복 X
     *     -> hash 사용해서 자체 중복 제거
     *  3. 중복 제거해서 종류가 N/2보다 많으면 안됨 (N/2 반환)
     *     => 최대 N/2 선택할 수 있다는 의미
     */
    public static int solution(int[] nums) {
        int answer = 0;
        int max = nums.length / 2;

        HashSet<Integer> hashSet = new HashSet<>();

        for (int num : nums) {
            hashSet.add(num);
        }

        if (hashSet.size() > max)
            answer = max;
        else
            answer = hashSet.size();

        return answer;
    }

    public static void main(String[] args) {
        int[] nums1 = {3, 1, 2, 3};
        System.out.println(solution(nums1));  // 2

        int[] nums2 = {3, 3, 3, 2, 2, 4};
        System.out.println(solution(nums2));  // 3

        int[] nums3 = {3, 3, 3, 2, 2, 2};
        System.out.println(solution(nums3));  // 2
    }
}
