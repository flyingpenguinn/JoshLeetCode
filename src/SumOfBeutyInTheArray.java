public class SumOfBeutyInTheArray {
    public int sumOfBeauties(int[] a) {
        int n = a.length;
        int[] min = new int[n];
        min[n-1] = a[n-1];
        for(int i=n-2; i>=0; --i){
            min[i] = Math.min(a[i], min[i+1]);
        }
        int max = a[0];
        int res = 0;
        for(int i=1; i<n-1; ++i){
            if(a[i]>max && a[i]<min[i+1]){
                res+= 2;
            }else if(a[i]>a[i-1] && a[i]<a[i+1]){
                res +=1;
            }
            max = Math.max(max, a[i]);
        }
        return res;
    }
}
