public class RobotBoundedInCircle {

    // if on origin or not facing north it's gonna return
    int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public boolean isRobotBounded(String ins) {
        int px = 0;
        int py = 0;
        int d = 0;

        int L = ins.length();
        int i = 0;

        while (i < L) {

            //    System.out.println(code)
            char c = ins.charAt(i);
            i++;
            if (c == 'L') {
                d = (d - 1 + 4) % 4;
            } else if (c == 'R') {
                d = (d + 1) % 4;
            } else {
                px = px + dirs[d][0];
                py = py + dirs[d][1];
            }
        }

        if (px == 0 && py == 0) {
            return true;
        }
        if (d != 0) {
            return true;
        }
        return false;
    }
}

// it must form a circlr in 4*L
// in 5*L if it doesnt have a circle it will break through
class RobotBoundedInCircleMyWay {
    int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public boolean isRobotBounded(String ins) {
        int px = 0;
        int py = 0;
        int d = 0;
        int step = 0;
        int L = ins.length() * 4;
        int L2 = L + ins.length();
        int i = 0;
        int xmax = -1000;
        int ymax = -1000;
        int xmin = 1000;
        int ymin = 1000;
        while (step < L2) {
            if (i == ins.length()) {
                i = 0;
            }
            String code = px + "," + py;
            //    System.out.println(code);
            step++;
            if (step < L) {
                xmax = Math.max(px, xmax);
                ymax = Math.max(py, ymax);
                xmin = Math.min(px, xmin);
                ymin = Math.min(py, ymin);
            } else {
                if (px > xmax || px < xmin | py > ymax || py < ymin) {
                    return false;
                }
            }
            char c = ins.charAt(i);
            i = (i + 1) % ins.length();
            if (c == 'L') {
                d = (d - 1 + 4) % 4;
            } else if (c == 'R') {
                d = (d + 1) % 4;
            } else {
                px = px + dirs[d][0];
                py = py + dirs[d][1];
            }
        }
        return true;
    }
}
