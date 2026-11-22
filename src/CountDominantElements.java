public class CountDominantElements {
    // Weekly 529 Q1. nums[i] >= average of its existing neighbors; a single element counts.
    public int countDominantElements(int[] a) {
        int n = a.length;
        if(n==1){
            return 1;
        }
        int res = 0;
        for(int i=0; i<n; ++i){
            if(i==0){
                if(a[i]>=a[i+1]){
                    ++res;
                }
            }
            else if(i==n-1){
                if(a[i]>=a[i-1]){
                    ++res;
                }
            }else{
                double v1 = a[i-1];
                double v2 = a[i+1];
                double avg = (v1+v2)/2;
                if(a[i]>=avg){
                    ++res;
                }
            }
        }
        return res;
    }
}
