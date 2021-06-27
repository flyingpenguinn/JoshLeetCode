import base.ArrayUtils;

public class RemoveOneElementToMakeArrayStrictlyIncreasing {
    public boolean canBeIncreasing(int[] a) {
        int n = a.length;
        int last = -1;
        for(int i=0; i+1<n; i++){
            if(a[i]>=a[i+1]){
                // either remove this one, or remove the next one
                if(good(a, i+1, last)){
                    return true;
                }
                else if(good(a, i+2, a[i])){
                    return true;
                }else{
                    return false;
                }
            }
            last = a[i];
        }
        return true;
    }

    private boolean good(int[] a, int i, int last) {
        for(int j=i; j<a.length; j++){
            if(a[j]<=last){
                return false;
            }
            last = a[j];
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new RemoveOneElementToMakeArrayStrictlyIncreasing().canBeIncreasing(ArrayUtils.read1d("1,1,1")));
    }
}
