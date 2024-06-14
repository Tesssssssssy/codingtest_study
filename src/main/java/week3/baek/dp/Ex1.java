package week3.baek.dp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Ex1 {
//    static HashSet<Integer> set =  new HashSet<Integer>();
    static List<HashSet<Integer>> countList = new ArrayList<>();

//    public static boolean cal(int N, int number, int length){
//        HashSet<Integer> set = new HashSet<>();
//
//        for (int i = 0; i < length; i++) {
//
//        }
//
//    }

    public static int solution(int N, int number) {

        for(int i=0; i<9; i++)
            countList.add(new HashSet<>());

        countList.get(1).add(N); // N을 1개 쓴 값은 N 혼자이다.

        for(int i=2; i<9; i++){
            Set<Integer> countSet = countList.get(i);

            for(int j=1; j<=i; j++){
                Set<Integer> preSet = countList.get(j);
                Set<Integer> postSet = countList.get(i - j);

                for(int preNum : preSet){
                    for(int postNum : postSet){
                        countSet.add(preNum + postNum);
                        countSet.add(preNum - postNum);
                        countSet.add(preNum * postNum);

                        if(preNum != 0 && postNum != 0)
                            countSet.add(preNum / postNum);
                    }
                }
            }

            countSet.add(Integer.parseInt(String.valueOf(N).repeat(i)));
        }

        for(Set<Integer> sub : countList){
            if(sub.contains(number))
                return countList.indexOf(sub);
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println(solution(1, 121));
//        System.out.println(solution(2, 11));
    }
}
