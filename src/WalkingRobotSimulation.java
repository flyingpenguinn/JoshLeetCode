public class WalkingRobotSimulation {
    // tricky part is if it goes a full circle, the direction needs to be carefully sorted out
    class Robot {
        int xlimit;
        int ylimit;
        int d = 0;
        int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        String[] dirstr = {"East", "North", "West", "South"};
        int x = 0;
        int y = 0;
        int peri;

        public Robot(int width, int height) {
            xlimit = width;
            ylimit = height;
            peri = (width - 1 + height - 1) * 2;
        }

        public void move(int num) {
            int nx = x;
            int ny = y;

            num %= peri;
            if (num == 0) {
                if (d == 0) {
                    d = x == 0 ? 3 : 0;
                } else if (d == 1) {
                    d = y == 0 ? 0 : 1;
                } else if (d == 2) {
                    d = x == xlimit - 1 ? 1 : 2;
                } else {
                    d = y == ylimit - 1 ? 2 : 3;
                }
            }
            while (num > 0) {
                int ox = nx;
                int oy = ny;
                nx += dirs[d][0] * num;
                ny += dirs[d][1] * num;
                if (nx < xlimit && ny < ylimit && nx >= 0 && ny >= 0) {
                    num = 0;
                } else {
                    if (d == 0) {
                        num -= (xlimit - 1 - ox);
                        nx = xlimit - 1;
                    } else if (d == 2) {
                        num -= ox;
                        nx = 0;
                    } else if (d == 1) {
                        num -= (ylimit - 1 - oy);
                        ny = ylimit - 1;
                    } else {
                        num -= oy;
                        ny = 0;
                    }
                    d = (d + 1) % 4;

                }
            }
            x = nx;
            y = ny;
        }

        public int[] getPos() {
            return new int[]{x, y};
        }

        public String getDir() {
            return dirstr[d];
        }
    }
}
