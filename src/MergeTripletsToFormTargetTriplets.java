import java.util.Arrays;

public class MergeTripletsToFormTargetTriplets {
    // we can't pick triplet that has one dimension >. so for all others try to see if we can get that
    public boolean mergeTriplets(int[][] triplets, int[] target) {
        int[] res = new int[3];
        for(int[] trip: triplets){
            if(trip[0]<=target[0] && trip[1] <= target[1] && trip[2] <= target[2]){
                res[0] = Math.max(trip[0], res[0]);
                res[1] = Math.max(trip[1], res[1]);
                res[2] = Math.max(trip[2], res[2]);
            }
        }
        return Arrays.equals(res, target);
    }
}
