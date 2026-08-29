class Solution {
    public int elevatorRequests(int n, int[] reqs) {
        int res = 0;
        int cur = 0;
        for(int req: reqs){
           int diff = Math.abs(req- cur);
           res += diff;
           cur = req;
        }
        return res;
    }
}
