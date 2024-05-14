package week2.baek.heap;

import java.util.*;

public class Ex2 {
    public int solution(int[][] jobs) {
        ArrayList<Integer> list = new ArrayList<>();

        //요청 시각으로 정렬
        Arrays.sort(jobs, ((o1, o2) -> o1[0] - o2[0]));

//        for(int[] i : jobs){
//            System.out.println(i[0] + ", " + i[1]);
//        }

//        //요청 시각&처리 시간으로 정렬
//        PriorityQueue<int[]> pq = new PriorityQueue(new Comparator<int[]>() {
//            @Override
//            public int compare(int[] o1, int[] o2) {
//                if(o1[0] == o2[0]) {
//                    return o1[1] - o1[1];
//                }
//                else return o1[0] - o2[0];
//            }
//        });

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2)->o1[1]-o2[1]);

//        for(int[] i : jobs){
//            pq.offer(i);
//        }

//        while (!pq.isEmpty()) {
//            int[] j = pq.poll();
//            System.out.println(j[0] + ", " + j[1] + "\n");
//        }

        int count = jobs[0][1];
        list.add(jobs[0][1]);
        int idx=1;

        while(list.size() < jobs.length){
            while(idx < jobs.length && count >= jobs[idx][0]){
                pq.offer(jobs[idx]);
                idx++;
            }

            //pq가 비어있고, 아직 처리되어야 하는 요청이 남아있지만 들어오지 않은 상태 =
            if(pq.isEmpty()){
                count = jobs[idx][0];
            }
            else {
                int[] i = pq.poll();
                count += i[1];
                System.out.println(count);
                list.add(count - i[0]);
            }
        }

//        System.out.println(list);

        int avg = 0;
        for(int i : list){
            avg += i;
        }

        return avg/list.size();
    }
}

class Ex2Main{
    public static void main(String[] args) {
        Ex2 ex2 = new Ex2();
//        int[][] jobs = {{0, 3}, {5,5},{5,3},{1, 9}, {2, 6}};
        int[][] jobs = {{0, 3}, {1, 9}, {2, 6}};
        System.out.println(ex2.solution(jobs));
    }
}
