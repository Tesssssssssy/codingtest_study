package week6.baek.sort;

import java.util.ArrayList;
import java.util.Arrays;

public class KNum {
    public static ArrayList<Integer> solution(int[] array, int[][] commands) {
        ArrayList<Integer> result = new ArrayList<>();

        for (int[] command : commands){
            int[] copy = Arrays.copyOfRange(array, command[0] -1, command[1]);
            Arrays.sort(copy);
            result.add(copy[command[2]-1]);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] array = {1, 5, 2, 6, 3, 7, 4};
        int[][] commands = {{2, 5, 3}, {4, 4, 1}, {1, 7, 3}};
        System.out.println(solution(array, commands));
    }
}
