package week2.baek.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Ex1 {
    public ArrayList<Integer> solution(int[] array, int[][] commands) {
        ArrayList<Integer> answer = new ArrayList<>();


        for(int i=0; i<commands.length; i++){
            ArrayList<Integer> list = new ArrayList<>();
            for (int j = commands[i][0]-1; j < commands[i][1]; j++) {
                list.add(array[j]);
            }

            Collections.sort(list);
//            System.out.println(list);

            answer.add(list.get(commands[i][2] - 1));
        }

        return answer;
    }
}

class Ex1Main {
    public static void main(String[] args) {
        Ex1 ex1 = new Ex1();
        int[] array = {1, 5, 2, 6, 3, 7, 4};
        int[][] commands = {{2, 5, 3}, {4, 4, 1}, {1, 7, 3}};
        System.out.println(ex1.solution(array, commands));
    }
}