public class FillingBookcaseShelves {
    int[] dp;
    public int minHeightShelves(int[][] b, int sw) {
        dp=new int[b.length];
        return dom(0,sw,b,0);
    }

    // work out one layer a time
    int dom(int i, int sw, int[][] b,int level){

        if(i==b.length){
            //System.out.println("level="+level+" i="+i+" min="+0);

            return 0;
        }
        if(dp[i]!=0){
            return dp[i];
        }
        int min= 1000000;
        int h=0;
        int j=i;
        int osw=sw;
        // do we put j on this layer<or not
        while(j<b.length && sw>=b[j][0]){
            h= Math.max(h,b[j][1]);
//            System.out.println("h= "+h);

            // i+1 to next layer
            int later=dom(j+1,osw,b,level+1);
            int res= h+later;
            sw -= b[j][0];
            //    System.out.println(level+" "+i+" "+res);
            min= Math.min(min,res);
            j++;
        }
        dp[i]=min;
        return min;
    }
}
