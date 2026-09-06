public class CountRobotGroups {
    public int countGroups(int[] a, int[] speed, int distance) {
        int n = a.length;
        int res = 1;
        int cs = speed[n-1];
        for(int i=n-2; i>=0; --i){
            if(speed[i]<=cs && a[i]+distance<a[i+1]){
                ++res;
                cs = speed[i];
            }
        }
        return res;
    }
}
