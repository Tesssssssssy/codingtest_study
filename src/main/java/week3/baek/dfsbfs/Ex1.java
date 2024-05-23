package week3.baek.dfsbfs;

public class Ex1 {
    int answer = 0;

    public int solution(int[] numbers, int target) {
        dfs(numbers, target, 0, 0);

        return answer;
    }

    public void dfs(int[] numbers, int target, int depth, int sum){
        System.out.print("depth = " + depth);
        System.out.println("sum = " + sum);

        if(depth == numbers.length){
            if(target == sum) answer++;
        }

        else{
            dfs(numbers, target, depth+1, sum + numbers[depth]);
            dfs(numbers, target, depth+1, sum - numbers[depth]);
        }
    }
}

class Ex1Main{
    public static void main(String[] args) {
        Ex1 ex1 = new Ex1();
        int[] numbers = {1, 1, 1, 1, 1};
        int target =3;
        System.out.println(ex1.solution(numbers, target));
    }
}