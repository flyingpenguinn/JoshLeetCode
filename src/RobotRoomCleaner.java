
import java.util.HashSet;
import java.util.Set;

/*
LC#489
Given a robot cleaner in a room modeled as a grid.

Each cell in the grid can be empty or blocked.

The robot cleaner with 4 given APIs can move forward, turn left or turn right. Each turn it made is 90 degrees.

When it tries to move into a blocked cell, its bumper sensor detects the obstacle and it stays on the current cell.

Design an algorithm to clean the entire room using only the 4 given APIs shown below.

interface Robot {
  // returns true if next cell is open and robot moves into the cell.
  // returns false if next cell is obstacle and robot stays on the current cell.
  boolean move();

  // Robot will stay on the same cell after calling turnLeft/turnRight.
  // Each turn will be 90 degrees.
  void turnLeft();
  void turnRight();

  // Clean the current cell.
  void clean();
}
Example:

Input:
room = [
  [1,1,1,1,1,0,1,1],
  [1,1,1,1,1,0,1,1],
  [1,0,1,1,1,1,1,1],
  [0,0,0,1,0,0,0,0],
  [1,1,1,1,1,1,1,1]
],
row = 1,
col = 3

Explanation:
All grids in the room are marked by either 0 or 1.
0 means the cell is blocked, while 1 means the cell is accessible.
The robot initially starts at the position of row=1, col=3.
From the top left corner, its position is one row below and three columns right.
Notes:

The input is only given to initialize the room and the robot's position internally. You must solve this problem "blindfolded". In other words, you must control the robot using only the mentioned 4 APIs, without knowing the room layout and the initial robot's position.
The robot's initial position will always be in an accessible cell.
The initial direction of the robot will be facing up.
All accessible cells are connected, which means the all cells marked as 1 will be accessible by the robot.
Assume all four edges of the grid are all surrounded by wall.
 */
interface Robot {
    // Returns true if the cell in front is open and robot moves into the cell.
    // Returns false if the cell in front is blocked and robot stays in the current cell.
    public boolean move();

    // Robot will stay in the same cell after calling turnLeft/turnRight.
    // Each turn will be 90 degrees.
    public void turnLeft();

    public void turnRight();

    // Clean the current cell.
    public void clean();
}

public class RobotRoomCleaner {

    public void cleanRoom(Robot robot) {
        Set<String> seen = new HashSet<>();
        dfs(robot, 0, 0, 0, 1, seen);
    }

    // i = x, j = y
    private void dfs(Robot robot, int i, int j, int di, int dj, Set<String> seen) {
        // keeping the old position
        seen.add(code(i, j));
        robot.clean();
        int ni = i + di;
        int nj = j + dj;
        visit(robot, seen, ni, nj, di, dj);
        robot.turnLeft();
        // 0,1-> -1,0.. -1,0-> 0,-1...0,-1... 1,0.... 1,0.... 0,1
        int ndi = di == 0 ? -dj : 0;
        int ndj = dj == 0 ? di : 0;
        ni = i + ndi;
        nj = j + ndj;
        visit(robot, seen, ni, nj, ndi, ndj);
        robot.turnRight();
        robot.turnRight();
        // the reverse of the above
        ndi = di == 0 ? dj : 0;
        ndj = dj == 0 ? -di : 0;
        ni = i + ndi;
        nj = j + ndj;
        visit(robot, seen, ni, nj, ndi, ndj);
        // return to incoming position, or visit "down" node
        robot.turnRight();
        // for the starting point there is no "coming back" so we visit the "down" node
        if (i == 0 && j == 0 && !seen.contains(code(0, -1)) && robot.move()) {
            dfs(robot, 0, -1, 0, -1, seen);
        } else {
            // and restore the direction
            robot.move();
            robot.turnLeft();
            robot.turnLeft();
        }
    }

    private void visit(Robot robot, Set<String> seen, int ni, int nj, int ndi, int ndj) {
        if (!seen.contains(code(ni, nj)) && robot.move()) {
            dfs(robot, ni, nj, ndi, ndj, seen);
        }
    }

    private String code(int r, int c) {
        return r + "," + c;
    }
}
