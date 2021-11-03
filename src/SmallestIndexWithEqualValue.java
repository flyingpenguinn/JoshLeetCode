public class SmallestIndexWithEqualValue {
    int smallestEqual(int[] a) {
        int n = a.length;
        int min = n+1;
        for(int i=0; i<n; ++i){
            if(i%10 == a[i]){
                return i;
            }
        }
        return -1;
    }
}
