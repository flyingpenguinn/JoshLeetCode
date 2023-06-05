public class SemiOrderedPermutation {
    public int semiOrderedPermutation(int[] a) {
        int n = a.length;
        int i1 = -1;
        int in = -1;
        for(int i=0; i<n; ++i){
            if(a[i]==1){
                i1 = i;
            }else if(a[i]==n){
                in = i;
            }
        }
        if(i1<in){
            return i1+n-1-in;
        }else{
            return i1+n-1-in-1;
        }
    }
}
