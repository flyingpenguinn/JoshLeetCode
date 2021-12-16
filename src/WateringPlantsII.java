public class WateringPlantsII {
    public int minimumRefill(int[] a, int ca, int cb) {
        int n = a.length;
        int i = 0;
        int j = n-1;
        int res = 0;
        int pa = ca;
        int pb = cb;
        while(i<=j){
            if(i==j){
                if(pa==pb){
                    if(pa<a[i]){
                        ++res;
                    }
                    break;
                }else{
                    int maxv = Math.max(pa, pb);
                    if(maxv<a[i]){
                        ++res;
                    }
                    break;
                }
            }else{
                if(a[i]>pa){
                    ++res;
                    pa = ca;
                }
                pa -= a[i];

                if(a[j]>pb){
                    ++res;
                    pb = cb;
                }
                pb -= a[j];

                ++i;
                --j;
            }
        }
        return res;
    }
}
