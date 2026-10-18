public class MinDeletionsMakeArraySharp {
    public int minDeletions(int[] a) {
        int n = a.length;
        int i = 0;
        int res = 0;
        while(i<n){
            int j = i;
            while(j<n && a[j]==a[i]){
                ++j;
            }
            int len = j-i;
            int del = len-1;
            res += del;
            i = j;
        }
        return res;
    }
}
