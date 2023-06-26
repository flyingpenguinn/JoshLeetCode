public class CountLegnthOfLongestNewString {
    // xyxyxy then put all zs at the end or beginning. we can get one more x or y at the end or beginning
    public int longestString(int x, int y, int z) {
        int min = Math.min(x, y);
        if (x > y || y > x) {
            min = 2 * min + 1;
        } else {
            min *= 2;
        }
        return (min + z) * 2;
    }
}
