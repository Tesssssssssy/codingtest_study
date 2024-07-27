package week12.baek.july26.baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class S1475 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String n = br.readLine();

        Map<Integer, Integer> map = new HashMap<>();

        // 각 숫자의 개수를 센다
        for (int i = 0; i < n.length(); i++) {
            int digit = n.charAt(i) - '0';
            map.put(digit, map.getOrDefault(digit, 0) + 1);
        }

        // 6과 9의 개수를 더하고, 반으로 나눈 뒤 올림
        int count6 = map.getOrDefault(6, 0);
        int count9 = map.getOrDefault(9, 0);
        int count69 = (count6 + count9 + 1) / 2; // 올림 계산

        // 최대 값을 찾기
        int max = count69;
        for (int i = 0; i <= 9; i++) {
            if (i == 6 || i == 9) continue;
            max = Math.max(max, map.getOrDefault(i, 0));
        }

        System.out.println(max);
    }
}