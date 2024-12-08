import java.util.*;

public class MaxAreaRectangleWithPointConstraintII {
    // TODO- understand and do myself
    public long maxRectangleArea(int[] xCoord, int[] yCoord)
    {
        int n = xCoord.length;
        int[][] co = new int[n][];
        int[] sy = imap(yCoord);

        // Step 1: Map coordinates to compressed y-values
        for (int i = 0; i < n; i++)
        {
            co[i] = new int[]{xCoord[i], Arrays.binarySearch(sy, yCoord[i])};
        }

        // Step 2: Sort the coordinates first by x, then by y
        Arrays.sort(co, (x, y) -> {
            if (x[0] != y[0]) return x[0] - y[0];
            return x[1] - y[1];
        });

        // Step 3: Initialize Fenwick Tree and helper maps
        Map<Long, Integer> map = new HashMap<>();
        Map<Long, Integer> mapx = new HashMap<>();
        long ans = -1;
        int[] ft = new int[sy.length + 1];

        // Step 4: Iterate through the coordinates and find rectangles
        for (int i = 0; i < co.length; i++)
        {
            addFenwick(ft, co[i][1], 1);

            // Step 5: Check for possible rectangles
            if (i - 1 >= 0 && co[i][0] == co[i - 1][0])
            {
                long yc = (long) co[i][1] << 32 | co[i - 1][1];
                int aft = sumFenwick(ft, co[i][1]) - sumFenwick(ft, co[i - 1][1] - 1);

                // Step 6: Update the map and check if the rectangle is valid
                if (map.containsKey(yc))
                {
                    int bef = map.get(yc);
                    if (aft == bef + 2)
                    {
                        int x = mapx.get(yc);
                        long S = (long) (co[i][0] - x) * (sy[co[i][1]] - sy[co[i - 1][1]]);
                        ans = Math.max(ans, S);
                    }
                }

                map.put(yc, aft);
                mapx.put(yc, co[i][0]);
            }
        }

        // Step 7: Return the maximum area found
        return ans;
    }

    // Fenwick Tree sum query
    public static int sumFenwick(int[] ft, int i)
    {
        int sum = 0;
        for (i++; i > 0; i -= i & -i)
        {
            sum += ft[i];
        }

        return sum;
    }

    // Fenwick Tree update
    public static void addFenwick(int[] ft, int i, int v)
    {
        if (v == 0 || i < 0)
        {
            return;
        }

        int n = ft.length;
        for (i++; i < n; i += i & -i)
        {
            ft[i] += v;
        }
    }

    // Compress y-values
    public static int[] imap(int[] a)
    {
        int[] imap = Arrays.copyOf(a, a.length);
        Arrays.sort(imap);
        int p = 1;
        for (int i = 1; i < imap.length; i++)
        {
            if (imap[i] != imap[p - 1])
            {
                imap[p++] = imap[i];
            }

        }

        return Arrays.copyOf(imap, p);
    }

}
