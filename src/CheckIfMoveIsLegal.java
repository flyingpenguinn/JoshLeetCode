public class CheckIfMoveIsLegal {
    // on 8 directions check if it's legal. legal means when we meet the next same color len>=3
    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0,1}, {-1, 1}, {-1, -1}, {1,1}, {1, -1}};
    public boolean checkMove(char[][] board, int rMove, int cMove, char color) {
        int m = board.length;
        int n = board[0].length;
        for(int[] d: dirs){
            //     System.out.println(Arrays.toString(d));
            int r = rMove + d[0];
            int c = cMove + d[1];
            int len = 1;
            while(r>=0 && c>=0 && r<m && c<n && board[r][c] != '.'){
                ++len;
                if(board[r][c]==color){
                    if(len>=3){
                        return true;
                    }else{
                        //   System.out.println("found bad "+len);
                        break;
                    }
                }
                r += d[0];
                c += d[1];
            }
        }
        return false;
    }
}
