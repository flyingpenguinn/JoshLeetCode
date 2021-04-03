import java.util.HashMap;
import java.util.Map;

public class MaxNumGroupsGettingFreshDonuts {
    // try to group numbers so that as many groups can be divided by batchsize as possible
    // hash the state of the array. the dp states are dp[hash][rem]
    // O( (n/k)^k * k) at most = 3^10. k is batch size
    private Map<String, Map<Integer,Integer>> dp = new HashMap<>();
    public int maxHappyGroups(int bs, int[] a) {
        int[] mods = new int[bs];
        int res = 0;
        int rem = 0;
        for (int i = 0; i < a.length; i++) {
            int mod = a[i] % bs;
            if (mod == 0) {
                res++;
            } else {
                mods[mod]++;
                rem++;
            }
        }
        //System.out.println(Arrays.toString(mods));
        String hash = tostring(mods);
        res += solve(hash, 0);
        return res;
    }

    private int solve(String hash, int rem) {
        int[] mods = fromstring(hash);
        boolean nonz = false;
        for(int i=0; i<mods.length;i++){
            if(mods[i]>0){
                nonz = true;
                break;
            }
        }
        if(!nonz){
            return rem>0?1:0;
            // what we have accumulated requires a share
        }
        Map<Integer,Integer> cm = dp.getOrDefault(hash, new HashMap<>());
        if(cm.containsKey(rem)){
            return cm.get(rem);
        }
        int res = 0;
        for(int i=0; i<mods.length;i++){
            if(mods[i]==0){
                continue;
            }
            mods[i]--;
            String newhash = tostring(mods);
            int newrem = (rem+i) % mods.length;
            int cur = 0;
            if(newrem==0){
                cur++;
            }
            cur += solve(newhash, newrem);
            res = Math.max(res, cur);
            mods[i]++;
        }
        cm.put(rem, res);
        dp.put(hash, cm);
        return res;
    }

    private String tostring(int[] mods){
        int n = mods.length;
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<mods.length; i++){
            sb.append(mods[i]);
            sb.append(",");
        }
        return sb.toString();
    }

    private int[] fromstring(String str){
        String[] ss = str.split(",");
        int[] res = new int[ss.length];
        for(int i=0; i<ss.length; i++){
            res[i] = Integer.valueOf(ss[i]);
        }
        return res;
    }
}
