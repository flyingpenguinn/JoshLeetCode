class Solution {
    public int nearestDrone(int[][] drones, int[] target) {
        int index = -1;
        int n = drones.length;
        int mindist = (int)(1e9);

        for(int i=0; i<n; ++i){
            int dist = Math.abs(target[0]-drones[i][0]) + Math.abs(target[1]-drones[i][1]);
            if(dist<=drones[i][2]){
                if(dist<mindist){
                    index = i;
                    mindist = dist;
                }
            }
        }
        return index;
    }
}
