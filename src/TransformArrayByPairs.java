import java.util.Arrays;

public class TransformArrayByPairs {
    public int[] transformArray(int[] a) {
        int n = a.length;
        for(int i=0; i<n; ++i){
            a[i] = a[i]%2;
        }
        Arrays.sort(a);
        return a;
    }
}
