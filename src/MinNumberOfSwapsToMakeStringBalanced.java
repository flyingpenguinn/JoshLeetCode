public class MinNumberOfSwapsToMakeStringBalanced {
    // cancel out all matching []. now we are left with ]]][[[, it's basically the (rights +1)/2
    public int minSwaps(String s) {
        int n = s.length();
        int openleft = 0;
        int bad = 0;
        for(int i=0; i<n; i++){
            if(s.charAt(i)=='['){
                ++openleft;
            }else{
                if(openleft == 0) {
                    bad++;
                }else{
                    --openleft;
                }
            }
        }
        return (bad+1)/2;
    }
}
