import java.util.Arrays;

public class DetermineIfMatrixCanBeObtainedWithRotation {
    public boolean findRotation(int[][] mat, int[][] target) {
        int n = mat.length;
        boolean[] c = new boolean[4];
        Arrays.fill(c, true);
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(!c[0] && !c[1] && !c[2] && !c[3]){
                    break;
                }
                if(mat[i][j] != target[i][j]){
                    c[0] = false;
                }
                // this is clockwise 90d
                if(mat[i][j] != target[n-1-j][i]){
                    c[1] = false;
                }
                // keep applying n-1-j, i to the rotation. this is flipping 180 d
                if(mat[i][j] != target[n-1-i][n-1-j]){
                    c[2] = false;
                }
                if(mat[i][j] != target[j][n-1-i]){
                    c[3] = false;
                }
            }
        }
        return c[0] || c[1] || c[2] || c[3];
    }
}
