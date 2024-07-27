public class NumberOfBitChangesToMakeTwoStringsEqual {
    public int minChanges(int n, int k) {
        int res = 0;
        for(int i=0; i<32; ++i){
            int nd = (((n>>i)&1));
            int kd = (((k>>i)&1));
            if(kd == 0 && nd==1){
                ++res;
            }else if(kd==1 && nd==0){
                return -1;
            }
        }
        return res;
    }
}
