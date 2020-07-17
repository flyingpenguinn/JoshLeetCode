public class FindPeakElement {
    public int findPeakElement(int[] a) {
        int n = a.length;
        int l = 0;
        int u = n-1;
        long inf = Integer.MIN_VALUE - 1L;
        while(l<=u){
            int mid = l+(u-l)/2;
            long left = mid==0 ? inf : a[mid-1];
            long right = mid==n-1? inf : a[mid+1];
            long cur = a[mid];
            if(cur>left && cur>right){
                return mid;
            }else if (cur>left &&  cur<right){
                l = mid+1;
            }else{
                u = mid-1;
            }
        }
        return -1;
    }
}
