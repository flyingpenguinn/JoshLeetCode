public class BinarySearchableNumberInUnsortedArray {
    // all left <= pivot, all right >= pivot
    public int binarySearchableNumbers(int[] a) {
        int n = a.length;
        int[] right = new int[n];
        right[n-1] = a[n-1];
        for(int i=n-2; i>=0; i--){
            right[i] = Math.min(right[i+1], a[i]);
        }
        int[] left = new int[n];
        left[0] = a[0];
        for(int i=1; i<n; i++){
            left[i] = Math.max(left[i-1], a[i]);
        }
        int res= 0;
        for(int i=0; i<n; i++){
            if(left[i]<=a[i] && right[i]>=a[i]){
                res++;
            }
        }
        return res;
    }
}
