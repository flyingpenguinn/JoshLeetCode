import base.ArrayUtils;

import java.util.*;

/*
LC#353
Design a Snake game that is played on a device with screen size = width x height. Play the game online if you are not familiar with the game.

The snake is initially positioned at the top left corner (0,0) with length = 1 unit.

You are given a list of food's positions in row-column order. When a snake eats the food, its length and the game's score both increase by 1.

Each food appears one by one on the screen. For example, the second food will not appear until the first food was eaten by the snake.

When a food does appear on the screen, it is guaranteed that it will not appear on a block occupied by the snake.

Example:

Given width = 3, height = 2, and food = [[1,2],[0,1]].

Snake snake = new Snake(width, height, food);

Initially the snake appears at position (0,0) and the food at (1,2).

|S| | |
| | |F|

snake.move("R"); -> Returns 0

| |S| |
| | |F|

snake.move("D"); -> Returns 0

| | | |
| |S|F|

snake.move("R"); -> Returns 1 (Snake eats the first food and right after that, the second food appears at (0,1) )

| |F| |
| |S|S|

snake.move("U"); -> Returns 1

| |F|S|
| | |S|

snake.move("L"); -> Returns 2 (Snake eats the second food)

| |S|S|
| | |S|

snake.move("U"); -> Returns -1 (Game over because snake collides with border)
 */
public class DesignSnakeGame {
    // use deque. if eat food, tail is not popped!
    public static void main(String[] args) {
        int[][] food = ArrayUtils.read("[[0,1]]");
        SnakeGame sg = new SnakeGame(2, 2, food);
        System.out.println(sg.move("R"));
        System.out.println(sg.move("D"));
    }
}

class SnakeGame {
    int m;
    int n;
    Set<Integer> sset = new HashSet<>();
    Deque<Integer> snake = new ArrayDeque<>();
    int[][] food;
    int nextfood = 0;
    int fi = 0;
    int score = 0;

    public SnakeGame(int width, int height, int[][] food) {
        m = height;
        n = width;
        this.food = food;
        nextfood = fi < food.length ? code(food[fi][0], food[fi][1]) : -1;
        snake.offerLast(0);
        sset.add(0);
    }

    Map<String, int[]> dirs = new HashMap<>();
    {
        dirs.put("U", new int[]{-1, 0});
        dirs.put("L", new int[]{0, -1});
        dirs.put("R", new int[]{0, 1});
        dirs.put("D", new int[]{1, 0});
    }

    public int move(String direction) {
        int head = snake.peekLast();
        int i = head / n;
        int j = head % n;
        int ni = i + dirs.get(direction)[0];
        int nj = j + dirs.get(direction)[1];
        if (ni >= 0 && ni < m && nj >= 0 && nj < n) {
            // move tail first!
            int ncode = code(ni, nj);
            if (nextfood == ncode) {
                score++;
                fi++;
                nextfood = fi < food.length ? code(food[fi][0], food[fi][1]) : -1;
            } else {
                int tail = snake.pollFirst();
                sset.remove(tail);
            }
            if (sset.contains(ncode)) {
                // ate self
                return -1;
            }
            snake.offerLast(ncode);
            sset.add(ncode);
            return score;
        } else {
            return -1;
        }
    }

    int code(int i, int j) {
        return i * n + j;
    }
}