import java.util.Arrays;

public class RotateOperationToMakeArrayEqual {
    public int reductionOperations(int[] a) {
        Arrays.sort(a);
        int n = a.length;
        int last = 0;
        int res = 0;
        for(int i=1; i<n; i++){
            int cur = 0;
            if(a[i]==a[i-1]){
                cur = last;
            }else{
                cur = last+1;
            }
            res += cur;
            last = cur;
        }
        return res;
    }
}
