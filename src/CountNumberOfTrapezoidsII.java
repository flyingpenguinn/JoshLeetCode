import java.util.HashMap;

public class CountNumberOfTrapezoidsII {
    // TODO
    public int gcd(int a, int b) {
        while (b != 0) {
            int tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }

    public String hash(int a, int b) {
        int g = gcd(Math.abs(a), Math.abs(b));
        if (g == 0) {
            return "0/0";
        }

        int num = a / g;
        int den = b / g;
        return (num * den < 0 && den != 0 ? "-" : "") + Math.abs(num) + "/" + Math.abs(den);
    }

    public int countTrapezoids(int[][] points) {
        // 1. Trapezium contains two pairs of opposite sides
        // 2. Atleast one pair should be parallel
        // 3. A Trapezium will be counted twice if it has two parallel pairs
        // 4. So parallelogram will be counted twice.
        // 5. So, Result = Trapeziums - Parallelograms
        // 6. Intercept = y1 - m * x1 = (y1 * (x2 - x1) - (y2 - y1) * x) / (x2 - x1)


        int trapeziums_parallelograms = 0;
        HashMap<String, Integer> parallel_lines = new HashMap<>();
        HashMap<String, Integer> collinear_lines = new HashMap<>();

        int n = points.length;

        for (int i = 0; i < n; i++) {
            int[] p2 = points[i];
            for (int j = 0; j < i; j++) {
                int[] p1 = points[j];

                String slope = p1[0] != p2[0] ? hash(p2[1] - p1[1], p2[0] - p1[0]) : "infinity";
                String intercept = p1[0] != p2[0] ? hash(p1[1] * (p2[0] - p1[0]) - (p2[1] - p1[1]) * p1[0], p2[0] - p1[0]) : p1[0] + "";
                String h = slope + "," + intercept;

                int seen_parallel_lines = parallel_lines.getOrDefault(slope, 0);
                int seen_collinear_lines = collinear_lines.getOrDefault(h, 0);

                trapeziums_parallelograms += seen_parallel_lines - seen_collinear_lines;

                parallel_lines.put(slope, parallel_lines.getOrDefault(slope, 0) + 1);
                collinear_lines.put(h, collinear_lines.getOrDefault(h, 0) + 1);
            }
        }

        int parallelograms = 0;
        HashMap<String, Integer> parallel_lines_dist = new HashMap<>();
        HashMap<String, Integer> collinear_lines_dist = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int[] p2 = points[i];
            for (int j = 0; j < i; j++) {
                int[] p1 = points[j];

                String slope = p1[0] != p2[0] ? hash(p2[1] - p1[1], p2[0] - p1[0]) : "infinity";
                String intercept = p1[0] != p2[0] ? hash(p1[1] * (p2[0] - p1[0]) - (p2[1] - p1[1]) * p1[0], p2[0] - p1[0]) : p1[0] + "";
                int dist = (p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1]);

                String h1 = slope + "," + dist;
                String h2 = slope + "," + intercept + "," + dist;

                int seen_parallel_lines_dist = parallel_lines_dist.getOrDefault(h1, 0);
                int seen_collinear_lines_dist = collinear_lines_dist.getOrDefault(h2, 0);

                parallelograms += seen_parallel_lines_dist - seen_collinear_lines_dist;

                parallel_lines_dist.put(h1, parallel_lines_dist.getOrDefault(h1, 0) + 1);
                collinear_lines_dist.put(h2, collinear_lines_dist.getOrDefault(h2, 0) + 1);

            }
        }

        // Again in above loop each parallelogram will be read twice

        return trapeziums_parallelograms - parallelograms / 2;
    }
}
