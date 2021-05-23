public class MinSpeedToArriveOnTime {
    int max = 10000000;
    public int minSpeedOnTime(int[] dist, double hour) {
        int l = 1;
        int u = max;
        while(l<=u){
            int mid = l+(u-l)/2;
            if(time(dist, mid)<=hour){
                u = mid-1;
            }else{
                l = mid+1;
            }
        }
        return l>max? -1: l;
    }

    double time(int[] dist, int mid){
        double res = 0;
        for(int i=0; i<dist.length; i++){
            int d = dist[i];
            double time = 1.0*d/mid;
            if(i<dist.length-1){
                res += Math.ceil(time);
            }else{
                res += time;
            }
        }
        return res;
    }

}
