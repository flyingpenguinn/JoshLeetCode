public class MaximizeStrengthOfGroup {
    // due to small scale can also use status bits
    public long maxStrength(int[] a) {
        int n = a.length;
        long allres = 1L;
        long maxneg = -1000;
        int counted = 0;
        boolean has0 = false;
        for(int ai: a){
            if(ai<0){
                allres *= ai;
                maxneg = Math.max(maxneg, ai);
                ++counted;
            }else if(ai==0){
                has0 = true;
            }else{
                allres *= ai;
                ++counted;
            }
        }
        if(counted==0){
            // all 0
            return 0;
        }
        else if(allres>0){
            return allres;
        }else {
            if(counted==1){
                // all neg, and there is only 1 neg
                return has0? 0: maxneg;
            }else{
                return allres/maxneg;
            }
        }
    }
}
