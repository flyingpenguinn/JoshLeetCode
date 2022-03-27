public class MinDeletionToMakeArrayBeautiful {
    public int minDeletion(int[] a) {
        int n= a.length;
        int i=0;
        int removed = 0;
        while(i<n){
            int j = i+1;
            while(j<n && a[j]==a[i]){
                ++j;
            }
            int cr = 0;
            if((i-removed)%2==1){
                cr= Math.max(0,(j-i-2));
            }else{
                cr = (j-i-1);
            }
            removed += cr;
            i=j;
        }
        if((n-removed)%2==1){
            ++removed;
        }
        return removed;
    }
}
