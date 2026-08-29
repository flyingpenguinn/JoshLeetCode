class Solution {
    public int minPenalty(int period, int[] lights, int[] at) {
        int ln = lights.length;
        int an = at.length;
        Arrays.sort(lights);
        int best = lights[ln-1];
        int res = 0;
        for(int i=0; i<an; ++i){
            int ri = at[i]%period;
        //    System.out.println("ri="+ri);
            if(ri>=best){
                int cwait = period -ri;
          //      System.out.println("cwait="+cwait);
                res = Math.max(res, cwait);
            }
        }
        return res;
    }
}
