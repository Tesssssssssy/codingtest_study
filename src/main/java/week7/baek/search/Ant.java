package week7.baek.search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Ant {
    public static void main(String[] args) throws Exception {
        //1. 입력값 저장
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        int N = Integer.parseInt(input.split(" ")[0]);
        int D = Integer.parseInt(input.split(" ")[1]);
//        System.out.println("D = " + D);
//        System.out.println("N = " + N);

        input = br.readLine();
        String[] tmp = input.split(" ");
        int[] ants = new int[N];
        for (int i = 0; i <N; i++) {
            ants[i] = Integer.parseInt(tmp[i]);
//            System.out.print(ants[i]);
        }

        //2. 개미 위치 정렬
        Arrays.sort(ants);

        //3. 개미를 제거할 필요가 없는 경우
        if(ants[ants.length-1] - ants[0] <= D ) {
            System.out.println(0);
            return;
        }

        //4. 슬라이딩 윈도우
        int n = ants.length;
        int left = 0;
        int maxGroupSize = 0;

        // 슬라이딩 윈도우를 사용하여 최대 그룹 크기를 찾습니다.
        for (int right = 0; right < n; right++) {
            // 현재 윈도우의 시작과 끝의 차이가 D를 초과하면 left를 이동합니다.
            while (ants[right] - ants[left] > D) {
                left++;
            }
            // 최대 그룹 크기를 갱신합니다.
            maxGroupSize = Math.max(maxGroupSize, right - left + 1);
        }

        System.out.println(n - maxGroupSize);
    }
}
