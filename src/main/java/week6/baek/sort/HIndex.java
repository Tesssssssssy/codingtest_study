package week6.baek.sort;

import java.util.Arrays;

public class HIndex {
    public static int solution(int[] citations) {
        Arrays.sort(citations);

        int h=0;
        for (int i = 0; i < citations.length; i++) {
            int discussionCount = citations.length - i;

            if (citations[i] >= discussionCount) {
                h = discussionCount;
                break;
            }
        }
        return h;
    }

    public static void main(String[] args) {
        int[] citations = {3, 0, 6, 1, 5};
        System.out.println(solution(citations));
    }
}
