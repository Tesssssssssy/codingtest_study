package week12.baek.july23.baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class S6236 {

    static int arr[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] str = br.readLine().split(" ");

        int N = Integer.parseInt(str[0]);
        int M = Integer.parseInt(str[1]);

        arr = new int[N];

        int end = 0;
        int start = 0;
        int max = 0;
        int result = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            end += arr[i];
            max = Math.max(max, arr[i]);
        }

        // 돈을 가장 많이 쓰는 날 이상의 금액을 인출해야 한다.
        // 그렇지 않으면 인출을 하더라도 금액이 부족하기 때문에 계속 인출을 반복한다.
        start = max;

        while (start <= end) {
            int mid = (start + end) / 2;

            // 지정한 횟수 이하의 횟수만큼 인출해야 할 경우,
            // 인출 금액이 더 적은 경우에 해답이 있는지 탐색해 봐야 한다.
            if (M >= getMid(mid)) {
                result = mid;
                end = mid - 1;
            } else {
                // 지정한 횟수보다 더 많이 인출해야 할 경우
                // 인출 금액이 더 커야한다.
                start = mid + 1;
            }
        }

        System.out.println(result);
    }

    static int getMid(int tempMoney) {
        int count = 1;
        int money = tempMoney;

        for (int num : arr) {
            money -= num;

            // 여기서 money는 하루 쓸 돈인데 0이상이면 인출한 돈이 모자라므로 한번 더 인출
            if (money < 0) {
                count++;
                money = tempMoney - num;
            }
        }
        return count;
    }
}
