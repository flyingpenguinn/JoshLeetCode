import java.util.Arrays;

public class StoneGameIX {
    // the key is to understand that we can use mod 0 as the last resort and if 1, use 2. if 2, use 1
    // couldnt solve this as prob 3 in the contest...
    public boolean stoneGameIX(int[] a) {
        int n = a.length;
        int[] mod = new int[3];
        for(int ai: a){
            ++mod[ai%3];
        }
        int[] mod2 = Arrays.copyOf(mod, 3);
        boolean use1 = false;
        if(mod[1]>0){
            --mod[1];
            use1 = !solve(mod, 1, 0);
        }
        boolean use2 = false;
        if(mod2[2]>0){
            --mod2[2];
            use2 = !solve(mod2, 2, 0);
        }
        return use1 || use2;
    }

    private boolean solve(int[] mod, int st, int alice){
        if(mod[0]+mod[1]+mod[2]==0){
            return alice==1? false: true;
        }
        else if(mod[st]>0){
            --mod[st];
            return !solve(mod, 3-st, alice^1);
        }else if(mod[0]>0){
            --mod[0];
            return !solve(mod, st, alice^1);
        }else{
            return false;
        }
    }
}
