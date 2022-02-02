import java.util.ArrayList;
import java.util.List;

public class AllDivisionsOfTheHighestScore {
    public List<Integer> maxScoreIndices(int[] a) {
        int n = a.length;
        int[] zeros = new int[n];
        zeros[0] = (a[0]==0?1:0);
        for(int i=1; i<n; ++i){
            zeros[i] = zeros[i-1]+(a[i]==0?1:0);
        }
        int[] ones = new int[n];
        ones[0] = a[0];
        for(int i=1; i<n; ++i){
            ones[i] = ones[i-1]+a[i];
        }
        int max = 0;
        List<Integer> res = new ArrayList<>();
        for(int i=0; i<=n; ++i){
            int s1 = psum(zeros, 0, i-1);
            int s2 = psum(ones, i, n-1);
            int cur = s1+s2;
            if(cur>max){
                max  = cur;
                res = new ArrayList<>();
                res.add(i);
            }else if(cur==max){
                res.add(i);
            }
        }
        return res;
    }

    private int psum(int[] a, int i, int j){
        if(j<0 || i>j){
            return 0;
        }
        return a[j]-(i==0?0: a[i-1]);
    }
}
