package week2.lim.fullsearch.mocktest;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/42840

import java.util.ArrayList;
import java.util.Arrays;

public class mockTest {
    public static int[] solution(int[] answers) {
        int[] student1 = {1, 2, 3, 4, 5};
        int[] student2 = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] student3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};

        int[] scores = new int[3];

        /*
        for (int i : answers) {
            for (int a : student1) {
                if (i == a) scores[0]++;
            }
            for (int b : student2) {
                if (i == b) scores[1]++;
            }
            for (int c : student3) {
                if (i == c) scores[2]++;
            }
        }

        수포자들의 찍기 패턴과 문제의 정답을 비교하는 방식에서 발생.
        각 수포자의 찍기 패턴을 올바르게 문제의 순서와 비교해야 하는데, 각 정답을 수포자들의 전체 패턴과 비교한 것.
        이렇게 하면 각 정답이 해당 수포자의 패턴 내에 존재하는지만 확인하고,
        실제로 그 정답이 그 위치에 있어야 하는지는 검증하지 않는다.

        올바른 접근 방법은 각 정답을 해당하는 위치의 수포자 패턴과 비교해야 함.
        즉, 문제 번호에 해당하는 패턴 위치와 정답을 비교하여 점수를 매겨야 한다.
        */

        // 각 수포자의 찍기 패턴에 따라 정답과 비교
        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == student1[i % student1.length]) scores[0]++;
            if (answers[i] == student2[i % student2.length]) scores[1]++;
            if (answers[i] == student3[i % student3.length]) scores[2]++;
        }

        int maxScore = Math.max(scores[0], Math.max(scores[1], scores[2]));

        ArrayList<Integer> list = new ArrayList<>();

        if (maxScore == scores[0])
            list.add(1);
        if (maxScore == scores[1])
            list.add(2);
        if (maxScore == scores[2])
            list.add(3);

        int[] answer = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] answers1 = {1, 2, 3, 4, 5};
        int[] answers2 = {1, 3, 2, 4, 2};

        System.out.println(Arrays.toString(solution(answers1)));   // [1]
        System.out.println(Arrays.toString(solution(answers2)));   // [1, 2, 3]
    }
}
