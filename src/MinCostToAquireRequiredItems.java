public class MinCostToAquireRequiredItems {
    public long minimumCost(int c1, int c2, int c3, int need1, int need2) {
        if(c3>c1+c2){
            long cc1 = 1L*need1*c1;
            long cc2 = 1L*need2*c2;
            return cc1+cc2;
        }else{
            long minc = Math.min(c1, c2);
            long maxc = Math.max(c1, c2);
            if(c3<=minc){
                return 1L*c3*Math.max(need1, need2);
            }else if(c3>=maxc){
                long bothneed = Math.min(need1, need2);
                long cc3 = 1L*c3*bothneed;
                long cc1 = 1L*c1*(need1-bothneed);
                long cc2  = 1L*c2*(need2-bothneed);
                return cc1 + cc2 + cc3;
            }else{
                if(c3<c1){
                    long bothneed = Math.min(need1, need2);
                    long cc3 = 1L*c3*bothneed;
                    long cc1 = 1L*c3*(need1-bothneed);
                    long cc2  = 1L*c2*(need2-bothneed);
                    return cc1 + cc2 + cc3;
                }else{
                    long bothneed = Math.min(need1, need2);
                    long cc3 = 1L*c3*bothneed;
                    long cc1 = 1L*c1*(need1-bothneed);
                    long cc2  = 1L*c3*(need2-bothneed);
                    return cc1 + cc2 + cc3;
                }
            }
        }
    }
}
