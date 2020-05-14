/*LC#130
Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

Example:

X X X X
X O O X
X X O X
X O X X
After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X
Explanation:

Surrounded regions shouldn’t be on the border, which means that any 'O' on the border of the board are not flipped to 'X'. Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'. Two cells are connected if they are adjacent cells connected horizontally or vertically.
 */

public class SurroundedRegions {
    // can also go from borders to mark live Os
    int[][] dirs= {{1,0},{-1,0},{0,1},{0,-1}};
    int rows=0;
    int cols=0;
    char[][] b;
    public void solve(char[][] b) {
        rows=b.length;
        if(rows==0){
            return;
        }
        cols=b[0].length;
        this.b=b;
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                if(b[i][j]=='O'){
                    boolean live= check(i,j);// O -> Q
                    if(!live){
                        flip(i,j,'X');
                    }
                }
            }
        }
        // flip outside to avoid running into checked O
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                if(b[i][j]=='Q'){
                    flip(i,j,'O');
                }
            }
        }

    }

    boolean inrange(int i,int j){
        return i>=0 && i<rows && j>=0 && j<cols;
    }

    boolean check(int i,int j){
        b[i][j]='Q';
        boolean rt= (i==0 || i==rows-1 || j==0 || j==cols-1);
        for(int[] d: dirs){
            int ni= i+d[0];
            int nj= j+d[1];
            if(inrange(ni,nj) && b[ni][nj]=='O'){
                boolean ot= check(ni,nj);
                rt= rt || ot;
            }
        }
        return rt;
    }

    void flip(int i, int j,char nc){
        b[i][j]=nc;
        for(int[] d: dirs){
            int ni= i+d[0];
            int nj= j+d[1];
            if(inrange(ni,nj) && b[ni][nj]=='Q'){
                flip(ni,nj,nc);
            }
        }
    }
}
