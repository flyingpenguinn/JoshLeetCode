public class EmployeeWorkedTheLongestTime {
    public int hardestWorker(int n, int[][] logs) {
        int start = 0;
        int maxlast = 0;
        int maxi = -1;
        for (int[] l : logs) {
            int end = l[1];
            int last = end - start;
            int emp = l[0];
            if (last > maxlast) {
                maxlast = last;
                maxi = emp;
            } else if (last == maxlast) {
                maxi = Math.min(maxi, emp);
            }
            start = end;
        }
        return maxi;
    }
}
