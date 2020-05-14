public class RobertReturnToOrigin {
    public boolean judgeCircle(String moves) {
        int[]end= pos(0,0,0,moves);
        return end[0]==0&&end[1]==0;
    }

    int [] pos(int r,int c,int i,String m){

        if(i==m.length()){
            int [] res={r,c};
            return res;
        }
        char d=m.charAt(i);
        if(d=='U'){
            return pos(r-1,c,i+1,m);
        }
        if(d=='D'){
            return pos(r+1,c,i+1,m);
        }
        if(d=='L'){
            return pos(r,c-1,i+1,m);
        }
        if(d=='R'){
            return pos(r,c+1,i+1,m);
        }else{
            return null;
        }
    }
}
