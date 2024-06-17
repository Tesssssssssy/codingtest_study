package week7.baek.kakao2024winter;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class Present {
    public static int solution(String[] friends, String[] gifts) {
        int answer = 0;

        HashMap<String, Integer> name = new HashMap<>();
        for (int i = 0; i < friends.length ; i++) {
            name.put(friends[i], i);
        }

        //주고 받은 선물 표로 나타내기
        int[][] present = new int[friends.length][friends.length];

        for (int i = 0; i < gifts.length; i++) {
            String sender = gifts[i].split(" ")[0];
            String receiver = gifts[i].split(" ")[1];

            present[name.get(sender)][name.get(receiver)]++;

        }

        for (int i = 0; i < friends.length; i++) {
            for (int j = 0; j < friends.length; j++) {
                System.out.print(present[i][j] + " ");
            }
            System.out.println();
        }

        //선물 지수 계산
        int[] presentGrades = new int[friends.length];
        for (int i = 0; i < friends.length; i++) {
            int sent = 0;
            int received = 0;

            for (int j = 0; j < friends.length; j++) {
                sent += present[i][j];
                received += present[j][i];
            }

            presentGrades[i] = received - sent;
            System.out.println(presentGrades[i]);
        }


        //선물 예측
        int[] presentPredict = new int[friends.length];
        for (int i = 0; i < friends.length; i++) {
            for (int j = i+1; j < friends.length; j++) {
                //서로 준 선물의 수가 같을 때 = 선물 지수 비교
                if(present[i][j] == present[j][i]){
                    if(presentGrades[i] > presentGrades[j]) presentPredict[i]++;
                    else if(presentGrades[i] < presentGrades[j]) presentPredict[j]++;
                }

                //i가 더 많이 선물은 준 경우 - i가 받음
                else if(present[i][j] > present[j][i]){
                    presentPredict[i]++;
                }

                //j가 더 많이 선물은 준 경우 - j가 받음
                else if(present[i][j] < present[j][i]){
                    presentPredict[j]++;
                }
            }
        }

        Arrays.sort(presentPredict);

        return presentPredict[friends.length - 1];
    }

    public static void main(String[] args) {
//        String[] friends = {"muzi", "ryan", "frodo", "neo"};
//        String[] gifts = {"muzi frodo", "muzi frodo", "ryan muzi", "ryan muzi", "ryan muzi", "frodo muzi", "frodo ryan", "neo muzi"};

        String[] friends = {"joy", "brad", "alessandro", "conan", "david"};
        String[] gifts = {"alessandro brad", "alessandro joy", "alessandro conan", "david alessandro", "alessandro david"};

        System.out.println(solution(friends,gifts));
    }
}
