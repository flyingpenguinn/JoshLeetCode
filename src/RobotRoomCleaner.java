
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
    // from the entrance dir we keep cleaning then move back, turn right and clean up the next direction
    // gotcha is how to sync the robot with our dfs note when dfs returns robot needs to return to the old position at the same dir
    // from the entrance dir we keep cleaning then move back, turn right and clean up the next direction
    // gotcha is how to sync the robot with our dfs note when dfs returns robot needs to return to the old position at the same dir
    private Set<String> seen = new HashSet<>();
    // from i to i+1 it takes a right turn
    private int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public void cleanRoom(Robot robot) {
        dfs(robot,0,0,0);
    }

    // rob is now i,j, facing cd. we make sure when this method ends, it remains in this status
    private void dfs(Robot rob, int i, int j, int cd){
        //  System.out.println(i+" "+j);
        seen.add(code(i,j));
        rob.clean();
        for(int d = cd; d<cd+4; d++){
            int nd = d%4;
            int ni = i+dirs[nd][0];
            int nj = j+dirs[nd][1];
            if(!seen.contains(code(ni, nj)) && rob.move()){
                dfs(rob,ni, nj, nd);
                turn180(rob);
                rob.move();
                turn180(rob);
            }
            rob.turnRight();
        }
    }

    private void turn180(Robot rob){
        rob.turnRight();
        rob.turnRight();
    }

    private String code(int i, int j) {
        return i + "," + j;
    }
}
