import java.util.Arrays;

public class ArrayElementsNotEqualToAverageOfNeighbors {
    public int[] rearrangeArray(int[] a) {
        Arrays.sort(a);
        int n = a.length;
        int[] res = new int[n];
        int j = 0;
        int k = n-1;
        for(int i=0; i<n; i++){
            if(i%2==0){
                res[i] = a[j++];
            }else{
                res[i] = a[k--];
            }
        }
        return res;
    }
}
