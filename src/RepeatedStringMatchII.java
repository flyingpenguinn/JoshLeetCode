public class RepeatedStringMatchII {
    private int lcm(int x, int y){
        return x*y/gcd(x,y);
    }

    private int gcd(int a, int b){
        return b==0?a: gcd(b, a%b);
    }
    public boolean repeatedStringMatch(String a, String b, int x, int y) {
        int an = a.length();
        int bn = b.length();
        long lenb = 1L*bn*y;
        long lena = 1L*an*x;
        int lcmlen = lcm(an,bn);
        if(lenb<lcmlen){
            lcmlen = (int)lenb;
        }
        for(int i=0; i<an; ++i){
            long remlena = i+lenb;
            if(remlena>lena){
                break;
            }
            boolean bad = false;
            for(int j=0; j<lcmlen; ++j){
                int aindex = (i+j)%an;
                int bindex = j%bn;
                if(a.charAt(aindex) != b.charAt(bindex)){
                   bad = true;
                   break;
                }
            }
            if(!bad){
                return true;
            }
        }
        return false;
    }
}
