public class MaxGardenPerimeterToCollectApples {
    public long minimumPerimeter(long t) {
        long l = 1;
        long u = (long)1e6;
        while(l<=u){
            long mid = l+(u-l)/2;
            long res = 0;
            long dx=  (2*mid+1)*mid*(mid+1);
            long dy = (2*mid+1)*mid*(mid+1);
            res += dx+dy;

            if(res>=t){
                u = mid-1;
            }else{
                l = mid+1;
            }
        }
        return 8*l;
    }
}
