public class CompareSumOfBitonicParts {
    public int compareBitonicSums(int[] a) {
        int n = a.length;
        int i = 0;
        long sum1 = 0;
        while(i+1<n && a[i]<a[i+1]){
            sum1 += a[i];
            ++i;
        }
        ++i;
        long sum2 = 0;
        while(i<n && a[i]<a[i-1]){
            sum2 += a[i];
            ++i;
        }
        //   System.out.println(sum1+" "+sum2);
        if(sum1>sum2){
            return 0;
        }
        else if(sum1<sum2){
            return 1;
        }else{
            return -1;
        }
    }
}
