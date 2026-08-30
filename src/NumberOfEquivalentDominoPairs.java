public class NumberOfEquivalentDominoPairs {
    public int numEquivDominoPairs(int[][] ds) {
        int[] map=new int[100];
        for(int[] d: ds){
            int s=Math.min(d[0],d[1]);
            int e=Math.max(d[0],d[1]);
            int c=s*10+e;
            map[c]++;
        }
        int r=0;
        for(int i=0;i<100;i++){
            int v=map[i];
            if(v>0){ r+=v*(v-1)/2; }
        }
        return r;
    }
}
