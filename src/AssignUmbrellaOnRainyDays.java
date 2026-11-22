public class AssignUmbrellaOnRainyDays {
    // LC 4116 (biweekly virtual Q1). Direct simulation.
    public String[] assignUmbrella(int[] a) {
        int n = a.length;
        String[] res = new String[n];
        int ac = 0;
        int bc = 0;
        
        for(int i=0; i<n; ++i){
            boolean aneed = false;
            boolean bneed = false;
            if(i+1<n && a[i]==1 && a[i+1] == 1){
                aneed = true;
            }
            if(a[i]==1){
                bneed = true;
            }
            if(aneed && bneed){
                if(ac<=bc){
                    res[i] = "Alice";
                    ++ac;
                }else if(ac>bc){
                    res[i] = "Bob";
                    ++bc;
                }
            }else if(aneed){
                res[i] = "Alice";
                ++ac;
            }else if(bneed){
                res[i] = "Bob";
                ++bc;
            }else{
                res[i] = "None";
            }
        }
        return res;
    }
}
