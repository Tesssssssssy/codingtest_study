package week8.lim.week8_1.n_2869;

// 문제: https://www.acmicpc.net/problem/2869

import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());

        // 마지막 날을 제외한 필요 일수 계산
        int days = (V - B) / (A - B);

        // 만약 나누어 떨어지지 않는다면 하루를 더 추가해야 함
        if ((V - B) % (A - B) != 0) {
            days++;
        }

        System.out.println(days);
    }
}

/*
    매일마다 달팽이가 얼마나 올라가고 미끄러지는지를 반복적으로 계산하고 있기 때문에,
    특히 𝐴, 𝐵, 𝑉값이 매우 큰 경우에는 매우 비효율적이다.
    이 경우, 많은 반복 계산으로 인해 시간 초과가 발생할 수 있다.

    그래서 달팽이가 최종적으로 V 미터를 올라가기 위해 필요한 순수 올라가는 일수를 계산해야 함.
    마지막 날 제외하고, V - A 높이까지는 매일 A - B만큼 순수하게 올라갈 수 있다.
    이를 이용해, 총 필요한 일수를 [ ((V - A) / (A - B)) + 1 ]로 계산할 수 있다.
*/