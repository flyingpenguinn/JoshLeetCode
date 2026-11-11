public class MinCostToAquireRequiredItems {
    // cost(k) for buying k "both" items is piecewise linear in k with breakpoints at 0, min(need), max(need),
    // so only those endpoints need checking. k=0 is only better when costBoth > cost1 + cost2.
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        if(costBoth<=cost1 + cost2){
            long minneed = Math.min(need1, need2);
            long buyminneed = minneed*costBoth;
            long extra1 = need1-minneed;
            long extra2 = need2-minneed;
            long cur1 = extra1*cost1 + extra2*cost2+buyminneed;
            long maxneeded = Math.max(need1, need2);
            long cur2 = costBoth*maxneeded;
            return Math.min(cur1, cur2);
        }else{
            long buy1 = 1L*need1*cost1;
            long buy2 = 1L*need2*cost2;
            long cur = buy1 + buy2;
            return cur;
        }
    }
}
