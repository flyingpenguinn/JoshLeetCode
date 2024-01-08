public class MinMovesToCaptureQueen {
    public int minMovesToCaptureTheQueen(int a, int b, int c, int d, int e, int f) {
        if(e==a){
            int minc = Math.min(b, f);
            int maxc = Math.max(b, f);
            if(c==a && d>minc && d<maxc){
                return 2;
            }else{
                return 1;
            }
        }
        if(f==b){
            int minr = Math.min(a, e);
            int maxr = Math.max(a, e);
            if(d==b && c>minr && c<maxr){
                return 2;
            }else{
                return 1;
            }
        }
        if(e+f == c+d){
            int minr = Math.min(c, e);
            int maxr = Math.max(c, e);
            if(a+b == c+d && a>minr && a<maxr){
                return 2;
            }else{
                return 1;
            }
        }
        if(e-f == c-d){
            int minr = Math.min(c, e);
            int maxr = Math.max(c, e);
            if(a-b == c-d && a>minr && a<maxr){
                return 2;
            }else{
                return 1;
            }
        }
        return 2;
    }
}
