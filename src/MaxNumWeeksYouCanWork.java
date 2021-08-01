public class MaxNumWeeksYouCanWork {
    // similar to reorganize string, task scheduler
    public long numberOfWeeks(int[] a) {
        int n = a.length;
        long sum = 0;
        long max = 0;
        for(int i=0; i<n; i++){
            sum += a[i];
            max = Math.max(max, a[i]);
        }
        if(max <= sum-max){
            return sum;
        }else{
            return 2*(sum-max)+1;
        }
    }
}
