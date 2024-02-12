public class WalkingRobotSimulation {
    // tricky part is if it goes a full circle, the direction needs to be carefully sorted out- 0,0 going back to 0, 0 should face south
    public class Robot {
        private int w;
        private int h;
        private int d = 0;
        private int x = 0;
        private int y = 0;

        public Robot(int width, int height) {
            this.w = width;
            this.h = height;
        }

        public void step(int num) {
            int circum = w * 2 + (h - 2) * 2;
            num %= circum;
            if (num == 0 && x == 0 && y == 0) {
                d = 3;
            }
            while (num > 0) {
                if (d == 0) {
                    if (x + num >= w) {
                        int walked = w - 1 - x;
                        num -= walked;
                        x = w - 1;
                        d = 1;
                    } else {
                        x += num;
                        break;
                    }
                } else if (d == 1) {
                    if (y + num >= h) {
                        int walked = h - 1 - y;
                        num -= walked;
                        y = h - 1;
                        d = 2;
                    } else {
                        y += num;
                        break;
                    }
                } else if (d == 2) {
                    if (x - num < 0) {
                        int walked = x;
                        num -= walked;
                        x = 0;
                        d = 3;
                    } else {
                        x -= num;
                        break;
                    }
                } else if (d == 3) {
                    if (y - num < 0) {
                        int walked = y;
                        num -= walked;
                        y = 0;
                        d = 0;
                    } else {
                        y -= num;
                        break;
                    }
                } else {
                    System.out.println("bad dir");
                }
            }
        }

        public int[] getPos() {
            return new int[]{x, y};
        }

        public String getDir() {
            switch (d) {
                case 0:
                    return "East";
                case 1:
                    return "North";
                case 2:
                    return "West";
                case 3:
                    return "South";
                default:
                    return "Unknown";
            }
        }
    }

}
