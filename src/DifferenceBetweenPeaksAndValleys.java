public class DifferenceBetweenPeaksAndValleys {
    public int peakValleyDifference(int[] a) {
        int n= a.length;
        int peaksum = 0;
        int valleysum = 0;
        for(int i=1; i<n-1; ++i){
            if(a[i]>a[i-1] && a[i]>a[i+1]){
                peaksum += a[i];
            }else if(a[i]<a[i-1] && a[i]<a[i+1]){
                valleysum += a[i];
            }
        }
        return peaksum - valleysum;
    }
}
