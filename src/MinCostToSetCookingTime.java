import java.util.ArrayList;
import java.util.List;

public class MinCostToSetCookingTime {
    private int Max = (int)2e9;
    // either exact minutes, or min-1, sec + 60
    // mind the cases where we need to pad seconds: only when min is non empty, and min is allowed to be empty
    public int minCostSetTime(int startAt, int moveCost, int pushCost, int targetSeconds) {
        int minutes = targetSeconds/60;
        int seconds = targetSeconds % 60;
        return Math.min(count(startAt, moveCost, pushCost, minutes, seconds), count(startAt, moveCost, pushCost, minutes-1, seconds+60));
    }

    private int count(int st, int mc, int pc, int min, int sec){
        if(min<0 || min>99 || sec <0 || sec>99){
            return Max;
        }
        String ms = min==0?"":String.valueOf(min);
        // min can be single digits. seconds only need to be padded when min is not empty
        String ss = String.valueOf(sec);
        if(!ms.isEmpty() && ss.length()==1){
            ss = "0" + ss;
        }
        int cur = st;
        int res = 0;
        for(int i=0; i<ms.length(); ++i){
            int cind = ms.charAt(i)-'0';
            if(cind != cur){
                res += mc;
            }
            res += pc;
            cur = cind;
        }
        for(int i=0; i<ss.length(); ++i){
            int cind = ss.charAt(i)-'0';
            if(cind != cur){
                res += mc;
            }
            res += pc;
            cur = cind;
        }
        return res;
    }
}
