package week1.lim.Hash.phoneketmon;

import java.util.HashSet;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/1845?language=java

public class phoneketmon2 {
    static int solution(int[] nums) {
        int max = nums.length / 2;  //  2

        // 중복 제거
        HashSet<Integer> numsSet = new HashSet<>();

        for (int num : nums) {
            numsSet.add(num);   // {3, 1, 2}
        }

        // 중복을 제거한 셋의 크기가 max보다 크면 max를, 작으면 numsSet의 size를 리턴
        if (numsSet.size() > max) {     // numsSet.size = 3, max = 2
            return max;
        } else {
            return numsSet.size();
        }
    }

    public static void main(String[] args) {
        int[] nums = {3, 1, 2, 3};
        int ans = solution(nums);
        System.out.println(ans);    // 2
    }
}
