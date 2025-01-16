public class MinFlipsBinaryGridPalindromicII {
    // trick is in the middle row/col
    // because the 4 corners will either have all 1 or all 0 which is divisible by 4
    public int minFlips(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        int res = 0;
        int flips = 0;
        for(int i=0; i<m/2; ++i){
            for(int j=0; j<n/2; ++j){
                int[][] pos = {{i, j}, {i, n-1-j}, {m-1-i, j}, {m-1-i, n-1-j}};
                int[] count = new int[2];
                for(int[] p: pos){
                    ++count[a[p[0]][p[1]]];
                }
                int f0 = 4-count[0];
                int f1 = 4-count[1];
                flips += Math.min(f0, f1);
            }
        }
        // middle has to be 0
        if(m%2==1 && n%2==1 && a[m/2][n/2] == 1){
            ++res;
        }
        int singles = 0;
        int doubles = 0;
        if(m%2==1) {
            for(int j=0; j<n/2; ++j){
                int sum = a[m/2][j] + a[m/2][n-1-j];
                if(sum==2){
                    ++doubles;
                }else if(sum==1){
                    ++singles;
                }
            }
        }
        if(n%2==1){
            for(int i=0; i<m/2; ++i){
                int sum = a[i][n/2] + a[m-1-i][n/2];
                if(sum==2){
                    ++doubles;
                }else if(sum==1){
                    ++singles;
                }
            }
        }
        if(m%2==1 && n%2==1){
            flips += a[m/2][n/2];
        }

        if(doubles % 2 == 0){
            if(singles %2 == 0){
                // all change to 1,1
                return flips + singles;
            }else{
                // all change to 0,0
                return flips + singles;
            }
        }else{
            if(singles > 0){
                // one of the singles to be 1,1  all others be 0,0
                return flips + singles;
            }else{
                // no singles to spare, just change one 1,1 to 0,0
                return flips + 2;
            }
        }
    }

    public static void main(String[] args) {
        MinFlipsBinaryGridPalindromicII sol = new MinFlipsBinaryGridPalindromicII();
        int[][] grid1 = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        System.out.println(sol.minFlips(grid1)); // Expected output: 3

        int[][] grid2 = {{0, 1}, {0, 1}, {0, 0}};
        System.out.println(sol.minFlips(grid2)); // Expected output: 2

        int[][] grid3 = {{1}, {1}};
        System.out.println(sol.minFlips(grid3)); // Expected output: 2
    }
}
