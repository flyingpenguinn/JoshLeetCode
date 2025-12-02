import java.util.Arrays;

public class CountElementsWithAtleastKGreaterValues {
    public int countElements(int[] a, int k) {
        int n = a.length;
        int i = 0;
        Arrays.sort(a);
        int res = 0;
        while(i<n){
            int j = i;
            while(j<n && a[i] == a[j]){
                ++j;
            }
            int len = j-i;
            int bigger = n-j;
            if(bigger>=k){
                res += len;
            }
            i = j;
        }
        return res;
    }
}
