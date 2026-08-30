import java.util.List;

public class SnakeInMatrix {
    public int finalPositionOfSnake(int n, List<String> commands) {
        int i = 0, j = 0;
        for(String c: commands){
            if(c.equals("RIGHT")){ ++j; }
            else if(c.equals("LEFT")){ --j; }
            else if(c.equals("UP")){ --i; }
            else{ ++i; }
        }
        return i*n + j;
    }
}
