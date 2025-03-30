import base.ArrayUtils;

public class MaxScoreAfterPairDeletions {
    public int maxScore(int[] a) {
        int n = a.length;
        if(n<=2){
            return 0;
        }
        int sum = 0;
        int res = (int)1e9;
        int min = (int)1e9;
        for(int ai: a){
            sum += ai;
            min = Math.min(min, ai);
        }
        if(n%2==1){
            return sum - min;
        }
        for (int i = 0; i+1 < n; ++i) {
            int cur = a[i] + a[i+1];
            res = Math.min(res, cur);
        }
        return sum - res;
    }


    public static void main(String[] args) {
        System.out.println(new MaxScoreAfterPairDeletions().maxScore(ArrayUtils.read1d("[-5755,4989]")));
    }
}
