package week1.baek.stackqueue;

public class Ex6 {
    public Integer[] solution(Integer[] prices) {
        Integer[] results = new Integer[prices.length];
        Integer count;
        Integer f;

        for (int i = 0; i < prices.length; i++) {
            f = prices[i];
            count = 0;
            for (int j = i+1; j < prices.length; j++) {
                if(f <= prices[j]) count++;
                else {
                    count++;
                    break;
                }
            }
            results[i] = count;
        }

        for (int i = 0; i < results.length; i++) {
            System.out.println(results[i]);
        }

        return results;
    }
}


class Ex6Main {
    public static void main(String[] args) {
        Ex6 ex6 = new Ex6();
        Integer[] prices = {1, 2, 3, 2, 3};

        ex6.solution(prices);
    }
}