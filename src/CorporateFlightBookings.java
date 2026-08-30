public class CorporateFlightBookings {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] f= new int[n+2];
        for(int[] bk: bookings){
            f[bk[0]] += bk[2];
            f[bk[1]+1] -= bk[2];
        }
        int[] r= new int[n];
        int sum=0;
        for(int i=1;i<=n;i++){
            sum += f[i];
            r[i-1] =sum;
        }
        return r;
    }
}
