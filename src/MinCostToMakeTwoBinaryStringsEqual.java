public class MinCostToMakeTwoBinaryStringsEqual {
    // first swap so that 01 and 10 are cancelling out
    // then we face 01 01 same pairs. a swap + a cross will fix it. if odd, use flip
    // we may also just use flip
    public long minimumCost(String s, String t, int flipCost, int swapCost, int crossCost) {
        int n = s.length();
        int[] cnt = new int[2];
        long diffs = 0;
        for(int i=0; i<n; ++i){
            if(s.charAt(i) != t.charAt(i)){
                ++cnt[s.charAt(i)-'0'];
                ++diffs;
            }
        }
        long way1 = diffs*flipCost;
        int mincnt = Math.min(cnt[0], cnt[1]);
        long swaps = swapCost * mincnt;
        long rem = Math.max(cnt[0], cnt[1]) - mincnt; // same type of diff, like both 01 or both 10
        long remcost1 = flipCost*rem;
        long halfrem = rem/2;
        long remcost2 = halfrem*(swapCost + crossCost) + (rem-halfrem*2)*flipCost;
        long way2 = swaps + Math.min(remcost1, remcost2);
        return Math.min(way1, way2);
    }
}
