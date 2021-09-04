public class FindMiddleIndexInArray {
    public int findMiddleIndex(int[] a) {
        int sum = 0;
        for(int ai: a){
            sum += ai;
        }
        int leftsum = 0;
        int rightsum = sum;
        for(int i=0; i<a.length; ++i){
            rightsum -= a[i];
            if(leftsum == rightsum){
                return i;
            }
            leftsum += a[i];
        }
        return -1;
    }
}
