public class MinFunctionCallsToMakeTargetArray {
    // we can also check every bit
    //The number of operation 0 equals to the total number of bits "1".
    //The number of operation 1 equals to maximum bit length - 1.
    public int minOperations(int[] a) {
        int res = 0;
        while(true){
            for(int i=0; i<a.length;i++){
                if(a[i]%2==1){
                    a[i]--;
                    res++;
                }
            }
            boolean again = false;
            for(int i=0; i<a.length;i++){
                if(a[i]>0){
                    again = true;
                    a[i]/=2;
                }
            }
            if(!again){
                break;
            }else{
                res++;
            }
        }
        return res;
    }
}
