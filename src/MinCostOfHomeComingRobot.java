public class MinCostOfHomeComingRobot {
    public int minCost(int[] startPos, int[] homePos, int[] rows, int[] cols) {
        int i = startPos[0];
        int res = 0;
        while(i<homePos[0]){
            res += rows[++i];
        }
        while(i>homePos[0]){
            res += rows[--i];
        }
        int j = startPos[1];
        while(j<homePos[1]){
            res += cols[++j];
        }
        while(j>homePos[1]){
            res += cols[--j];
        }
        return res;
    }
}
