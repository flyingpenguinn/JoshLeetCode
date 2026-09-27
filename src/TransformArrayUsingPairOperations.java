public class TransformArrayUsingPairOperations {
    public boolean canTransform(int[] s, int[] t) {
        int n = s.length;
        long sum1 = 0;
        for(long si: s){
            sum1 += si;
        }
        long sum2 = 0;
        for(long sj: t){
            sum2 += sj;
        }
        return sum1 == sum2;
    }
}
