public class RobotBoundedInCircle {
    // if after traversing, we are back to origin, then bounded
    // otherwise as long as not facing up we are good.
    // can be proved by showing that if faces right/left, then after 4 rounds it will go back. if facing down then  will end in 2 rounds
    int[][] dirs = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};

    public boolean isRobotBounded(String s) {
        int x = 0;
        int y = 0;
        int d = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == 'G') {
                x += dirs[d][0];
                y += dirs[d][1];
            } else if (c == 'L') {
                d = (d + 1) % 4;
            } else {
                d = (d + 3) % 4;
            }
        }
        if (d == 0 && !(x == 0 && y == 0)) {
            return false;
        }
        return true;
    }
}
