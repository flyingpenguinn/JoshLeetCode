public class TransposeMatrix {
    public int[][] transpose(int[][] a) {
        int[][] r = new int[a[0].length][a.length];
        for(int i=0; i<a[0].length;i++){
            for(int j=0; j<a.length;j++){
                r[i][j] = a[j][i];
            }
        }
        return r;
    }
}
