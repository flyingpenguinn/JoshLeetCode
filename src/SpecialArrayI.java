public class SpecialArrayI {
    public boolean isArraySpecial(int[] a) {
        int n = a.length;
        for(int i=0; i+1<n; ++i){
            if(a[i]%2 == a[i+1]%2){
                return false;
            }
        }
        return true;
    }
}
