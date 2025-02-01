public class WalkingRobotSimulationII {
    // tricky part is if it goes a full circle, the direction needs to be carefully sorted out- 0,0 going back to 0, 0 should face south
    class Robot {
        private int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        private String[] dm = new String[]{"East", "North", "West", "South"};
        private int cdir = 0;
        private int m;
        private int n;
        private int x = 0;
        private int y = 0;
        private int circle = 0;

        public Robot(int width, int height) {
            m = width;
            n = height;
            circle = (m - 2) * 2 + (n - 2) * 2 + 4;
        }

        public void step(int num) {
            while (num > 0) {
                if (x + dirs[cdir][0] >= 0 && x + dirs[cdir][0] < m && y + dirs[cdir][1] >= 0 && y + dirs[cdir][1] < n) {
                    x += dirs[cdir][0];
                    y += dirs[cdir][1];
                    --num;
                } else {
                    break;
                }
            }
            num %= circle;
            while (num > 0) {
                if (x + dirs[cdir][0] >= 0 && x + dirs[cdir][0] < m && y + dirs[cdir][1] >= 0 && y + dirs[cdir][1] < n) {
                    x += dirs[cdir][0];
                    y += dirs[cdir][1];
                    --num;
                } else {
                    ++cdir;
                    cdir %= 4;
                }
            }

        }

        public int[] getPos() {
            return new int[]{x, y};
        }

        public String getDir() {
            return dm[cdir];
        }
    }



/**
 * Your Robot object will be instantiated and called as such:
 * Robot* obj = new Robot(width, height);
 * obj->step(num);
 * vector<int> param_2 = obj->getPos();
 * string param_3 = obj->getDir();
 */
}


