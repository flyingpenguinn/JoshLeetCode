import java.util.Arrays;

public class NumberOfWeakCharactersInTheArray {
    // same sorting as RussianDollEnvelope. note we just need to check if there are bigger [1] after because all characters with same [0] and bigger [1] are in front of us
    public int numberOfWeakCharacters(int[][] a) {
        return maxEnvelopes(a);
    }

    public int maxEnvelopes(int[][] a) {
        int n = a.length;
        Arrays.sort(a, (x, y) -> {
            if(x[0]!= y[0]){
                return Integer.compare(x[0], y[0]);
            }else{
                return Integer.compare(y[1], x[1]);
            }
        });
        int res = 0;
        int max1 = a[n-1][1];
        for(int i=n-2; i>=0; --i){
            // there is eq 0 and bigger 1 on the right. if it's bigger1 it must be a bigger 0
            if(a[i][1]<max1){
                ++res;
            }
            max1 = Math.max(max1, a[i][1]);
        }
        return res;
    }
}
