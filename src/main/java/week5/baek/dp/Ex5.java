package week5.baek.dp;

public class Ex5 {
    public static int solution(int[] money) {
        //첫번째 집을 터는 경우
        int[] dp1 = new int[money.length];
       //첫번째 집을 털지 않는 경우
        int[] dp2 = new int[money.length];

       dp1[0] = money[0];
       dp1[1] = money[1];

       dp2[0] = 0;
       dp2[1] = money[1];

        for (int i = 2; i < money.length; i++) {
            if (i == money.length - 1) {
                dp1[i] = dp1[i - 1];
                dp2[i] = money[i] + dp2[i - 2];
            } else {
                dp1[i] = Math.max(money[i] + dp1[i - 2], dp1[i - 1]);
                dp2[i] = Math.max(money[i] + dp2[i - 2], dp2[i - 1]);
            }
        }


        return Math.max(dp1[money.length-2], dp2[money.length-1]);
    }

    public static void main(String[] args) {
        int[] money = {10, 5, 3, 1, 10};
        System.out.println(solution(money));
    }
}
