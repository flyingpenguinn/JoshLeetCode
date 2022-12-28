import java.util.*;

public class RewardTopKStdudents {
    public List<Integer> topStudents(String[] positive_feedback, String[] negative_feedback, String[] report, int[] student_id, int k) {
        Set<String> posset = new HashSet<>();
        for (String p : positive_feedback) {
            posset.add(p);
        }

        Set<String> negset = new HashSet<>();
        for (String neg : negative_feedback) {
            negset.add(neg);
        }
        int n = report.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> {
            if (x[0] != y[0]) {
                return Integer.compare(x[0], y[0]);
            } else {
                return Integer.compare(y[1], x[1]);
            }
        });
        for (int i = 0; i < n; ++i) {
            int index = student_id[i];
            String[] ss = report[i].split(" ");
            int score = 0;
            for (String s : ss) {
                if (posset.contains(s)) {
                    score += 3;
                } else if (negset.contains(s)) {
                    score -= 1;
                }
            }
            //    System.out.println("i="+i+" index="+index+" score="+score);
            pq.offer(new int[]{score, index});
            if (pq.size() > k) {
                pq.poll();
            }
        }
        List<Integer> list = new ArrayList<>();
        while (!pq.isEmpty()) {
            list.add(pq.poll()[1]);
        }
        Collections.reverse(list);
        return list;
    }
}
