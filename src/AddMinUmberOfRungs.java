public class AddMinUmberOfRungs {
    public int addRungs(int[] a, int dist) {
        int cur = 0;
        int res = 0;
        int n = a.length;
        int i = 0;
        while(i<n){
            if(a[i]-cur>dist){
                int gap = a[i]-cur;
                int delta = gap/dist;
                if(gap % dist==0){
                    delta--;
                    // dont need the last rung in this case
                    // or,  delta = (gap-1)/dist
                }
                cur += dist*delta;
                res += delta;
            }else{
                cur = a[i];
                i++;
            }
        }
        return res;
    }
}
