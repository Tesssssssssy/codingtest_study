package week10.lim.week10_2.bronze.n_2596;

// 문제: https://www.acmicpc.net/problem/2596

import java.io.*;

public class Main {
    /**
     *  1. 병현이는 지은이에게 문자 A, B, C, D, E, F, G, H 로 쓰여진 편지를 날마다 보내는데,
     *     컴퓨터로 보내는 비밀편지로, 한 문자마다 0 또는 1인 숫자 여섯 개를 사용하여 보낸다
     *  2. A 000000
     *     B 001111
     *     C 010011
     *     D 011100
     *     E 100110
     *     F 101001
     *     G 110101
     *     H 111010
     *  3. 병현이가 어느 날 001111000000011100 을 보내면 지은이는 이것을 BAD로 이해
     *     그런데 둘 사이에 약속이 잘 만들어져 있기 때문에,
     *     통신에 문제가 생겨서 한 문자를 표시하는 여섯 숫자 중 어느 한 숫자만 틀리게 오는 경우,
     *     지은이는 원래 보내려는 문자를 알아 낼 수가 있다.
     *  4. 예를 들어 지은이가 000100을 받았을 때, A와 숫자 한자만 다르고,
     *     다른 문자들과는 각각 숫자 두 자 이상이 다르므로 지은이는 이것이 A라고 알아보게 된다.
     *  5. 다만 111111과 같이 모든 문자의 표현과 숫자 두 자 이상이 다른 경우에는 무슨 문자인지 알 수가 없게 된다.
     *     예를 들어 지은이가 011111000000111111000000111111 을 받았을 때,
     *     BA 다음에 알아 볼 수 없는 문자가 나오는데. 이 경우 이런 것이 처음 나오는 문자의 위치인 3을 출력.
     *  6. 지은이가 받은 편지를 보고 문자들을 알아내어 출력하거나, 모르는 문자가 있는 경우, 이것이 처음 나오는 위치를 출력.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 문자의 개수
        String input = br.readLine(); // 문자의 개수의 여섯 배 만큼의 숫자 입력

        // 문자와 이진 코드 매핑
        String[] codes = {"000000", "001111", "010011", "011100", "100110", "101001", "110101", "111010"};
        char[] chars = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};

        StringBuilder sb = new StringBuilder();
        boolean foundError = false;

        for (int i = 0; i < N; i++) {
            String segment = input.substring(i * 6, i * 6 + 6); // 각 문자에 해당하는 6자리 숫자를 추출
            boolean matched = false;
            for (int j = 0; j < codes.length; j++) {
                if (hammingDistance(segment, codes[j]) <= 1) { // 해밍 거리가 1 이하이면
                    sb.append(chars[j]);
                    matched = true;
                    break;
                }
            }
            if (!matched) {
                // 일치하는 문자를 찾지 못하면
                // (= 만약 모든 코드와 해밍 거리가 2 이상이라면, 그 순간의 위치를 출력하고 바로 종료)
                System.out.println(i + 1);
                foundError = true;
                break;
            }
        }

        if (!foundError) {
            System.out.println(sb);
        }
    }

    // 두 이진 문자열 간의 해밍 거리 계산
    private static int hammingDistance(String s1, String s2) {
        int distance = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                distance++;
            }
        }
        return distance;
    }
}
