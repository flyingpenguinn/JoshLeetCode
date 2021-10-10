import java.util.Arrays;

public class MinOperationsUniValueGrid {
    // If an element of the array (assume its index is m) can't make a Uni-Value Grid, there must be an element of the array (assume its index is i) that satisfied:
    //(a[i] - a[m]) % x != 0
    // if there is an element in the array that can't make a Uni-Value Grid, then all the element (no matter it is in the array nor not) can't make a Uni-Value Grid
    private long MAX = (long) (2e9+1);
    public int minOperations(int[][] grid, int x) {
        int[] a = new int[grid.length*grid[0].length];
        int ai = 0;
        for(var g: grid){
            for(var num: g){
                a[ai++] = num;
            }
        }
        int n = a.length;
        Arrays.sort(a);
        long rt = 0;
        if(n%2==1){
            rt = solve(a, a[n/2], x);
        }else{
            rt = Math.min(solve(a, a[n/2], x), solve(a, a[(n+1)/2], x));

        }
        return rt>=MAX? -1: (int) rt;
    }

    private long solve(int[] a, int t, int x){
        int res = 0;
        for(int i=0; i<a.length; ++i){
            if( (Math.abs(t-a[i]))%x != 0){
                return MAX;
            }
            res += Math.abs(t-a[i])/x;
        }
        return res;
    }
}
