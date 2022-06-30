import java.util.Arrays;

public class FindMinTimeToFinishAllJobsII {
    public int minimumTime(int[] jobs, int[] workers) {
        Arrays.sort(jobs);
        Arrays.sort(workers);
        int n = jobs.length;
        int res = 0;
        for (int i = 0; i < n; ++i) {
            res = Math.max(res, (int) (Math.ceil(jobs[i] * 1.0 / workers[i])));
        }
        return res;
    }
}
