public class CountPositionsOnStreetWithRequiredBrightness {
    public int meetRequirement(int n, int[][] lights, int[] requirement) {
        int[] b = new int[n+1];
        for(int[] light: lights){
            int start = Math.max(0, light[0]-light[1]);
            int end = Math.min(light[0]+light[1], n-1);
            ++b[start];
            --b[end+1];
        }
        int cur = 0;
        int res = 0;
        for(int i=0; i<n; ++i){
            cur += b[i];
            if(requirement[i]<=cur){
                ++res;
            }
        }
        return res;
    }
}
