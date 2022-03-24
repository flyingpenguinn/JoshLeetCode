public class MinHealthToBeatGame {
    public long minimumHealth(int[] a, int armor) {
        long sum = 0;
        long max = 0;
        for(int ai: a){
            sum += ai;
            max = Math.max(max, ai);
        }
        for(int i=0; i<a.length; ++i){
            if(a[i]==max){
                int nv = Math.max(a[i]-armor, 0);
                sum -= (a[i]-nv);
                break;
            }
        }
        return sum+1;
    }
}
