public class MaximizeConfusionOfExam {
    public int maxConsecutiveAnswers(String s, int k) {
        int n = s.length();
        return Math.max(solve(s, 'T', k), solve(s, 'F', k));
    }

    // longest substring with at most k ts
    private int solve(String s, char t, int k){
        int n = s.length();
        int ts = 0;
        int j = 0;
        int res = 0;
        for(int i=0; i<n; ++i){
            ts += s.charAt(i)==t? 1: 0;
            while(ts>k){
                ts -= s.charAt(j)==t?1:0;
                ++j;
            }
            int cur = i-j+1;
            res = Math.max(res, cur);
        }
        return res;
    }
}
