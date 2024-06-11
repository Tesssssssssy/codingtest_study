package week6.lim.heap.diskcontroller;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/42627
// 참고: https://easybrother0103.tistory.com/119

import java.util.PriorityQueue;

public class DiskController2 {
    /**
     *  대기시간을 최소로 줄여서 요청되는 작업을 수행하면 되는 문제.
     *
     *  예시처럼, 더 빨리 요청된 작업이 있더라도 이후에 요청된 작업을 먼저 수행하는 경우에
     *  대기시간이 더 줄어들면 그것부터 수행하도록 한다.
     *  이 기준은 현재 시간에서 수행할 수 있는 작업중에서
     *  가장 소요시간이 적게 드는 작업부터 수행하면 대기시간을 최소로 할 수 있다.
     *
     *  예를 들어 [0, 10] , [1, 10], [7, 7], [12, 3]과 같은 작업이 들어온다고 가정해보자.
     *
     *  먼저 가장 빨리 들어온 [0, 10] 작업을 수행하면 현재 시간은 10ms이다.
     *  10ms에서 수행할 수 있는 작업은 [1, 10]뿐이므로 다름으로 [1, 10]을 수행한다.
     *
     *  이러한 과정을 거치면 [7, 7], [12, 3] 작업이 남아있다. 현재는 20ms이므로 두 작업 모두 수행이 가능하다.
     *  여기서 주의해야 할 것은 [7, 7]이 먼저 요청된 작업이긴 하지만 [12, 3]작업을 먼저 수행해야 대기시간이 최소가 된다.
     *
     *  이를 구현하기 위해 아래와 같은 과정을 수행한다.

     *  1. 두 개의 우선순위큐를 사용(대기큐, 작업큐)라고 가정함.
     *     대기큐는 작업요청시간대로 우선 순위를 가지도록, 작업큐는 소요시간대로 우선순위를 가지도록 한다.
     *  2. 모든 작업을 수행 완료 할 때까지 while문을 돌린다.
     *  3. while문을 한번 더 돌려 작업큐 내의 원소중에서 요청시간이 현재 소요된 시간보다 작거나 같은 경우 대기큐에 삽입
     *  4. 대기큐가 비어있는 경우 다음 작업까지 메모리가 놀고있는 것. 소요된 시간값을 대기큐의 peek값의 요청시간으로 변경
     *  5. 대기큐에 들어있는 원소를 작업수행함(큐에서 뺌)
     */
    public static int solution(int[][] jobs) {
        // 요청 시간 순으로 정렬하는 우선순위 큐
        PriorityQueue<Job> wait = new PriorityQueue<>((o1, o2) -> o1.start - o2.start);
        // 소요 시간 순으로 정렬하는 우선순위 큐
        PriorityQueue<Job> ready = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        int waitTime = 0; // 총 대기 시간
        int costTime = 0; // 작업에 소요된 총 시간
        int idle = 0; // 작업 기다림 시간
        int count = 0; // 처리된 작업 수

        // 모든 작업을 대기 큐에 추가
        for (int i = 0; i < jobs.length; i++) {
            wait.add(new Job(jobs[i][0], jobs[i][1]));
        }

        // 모든 작업이 처리될 때까지 반복
        while (count < jobs.length) {
            // 현재 시간 이하의 요청 시간을 가진 작업을 작업 큐로 이동
            while (!wait.isEmpty() && wait.peek().start <= costTime) {
                ready.offer(wait.poll());
            }

            // 작업 큐가 비어 있으면 다음 작업 시간까지 기다림
            if (ready.isEmpty()) {
                idle += wait.peek().start - costTime;
                costTime = wait.peek().start;  // 현재 시간을 다음 작업 시간으로 조정
            } else {
                // 가장 소요 시간이 짧은 작업을 수행
                Job job = ready.poll();
                waitTime += costTime - job.start;  // 대기 시간 추가
                costTime += job.cost;  // 작업 소요 시간 추가
                count++;  // 처리된 작업 수 증가
            }
        }

        // 모든 작업의 총 대기 시간을 작업 수로 나누어 평균을 반환
        return (waitTime + costTime - idle) / jobs.length;
    }

    // 작업을 나타내는 클래스
    private static class Job {
        int start; // 작업 요청 시간
        int cost;  // 작업 소요 시간

        // 작업 생성자
        public Job(int start, int cost) {
            this.start = start;
            this.cost = cost;
        }
    }

    public static void main(String[] args) {
        int[][] jobs = {{0, 3}, {1, 9}, {2, 6}};
        System.out.println(solution(jobs)); // 9
    }
}