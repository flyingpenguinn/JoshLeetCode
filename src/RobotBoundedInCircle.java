public class RobotBoundedInCircle {
    // if after traversing, we are back to origin, then bounded
    // otherwise as long as not facing up we are good.
    // can be proved by showing that if faces right/left, then after 4 rounds it will go back. if facing down then  will end in 2 rounds
    private int[][] dirs = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    public boolean isRobotBounded(String ins) {
        int n = ins.length();
        int x = 0;
        int y = 0;
        int dir = 3;
        for (int i = 0; i < n; i++) {
            char c = ins.charAt(i % n);
            if (c == 'G') {
                x += dirs[dir][0];
                y += dirs[dir][1];
            } else if (c == 'L') {
                dir = (dir + 1) % 4;
            } else {
                dir = (dir + 3) % 4;
            }
        }
        if (x == 0 && y == 0) {
            return true;
        }
        if (dir != 3) {
            return true;
        }
        return false;
    }
}
