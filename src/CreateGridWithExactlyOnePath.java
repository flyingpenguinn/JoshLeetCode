public class CreateGridWithExactlyOnePath {
    public String[] createGrid(int m, int n) {
        String[] res = new String[m];

        for (int i = 0; i < m; ++i) {
            StringBuilder sb = new StringBuilder();
            if (i == 0) {
                for (int j = 0; j < n; ++j) {
                    sb.append(".");
                }
            } else {
                for (int j = 0; j < n - 1; ++j) {
                    sb.append("#");
                }
                sb.append(".");
            }
            res[i] = sb.toString();
        }
        return res;


    }
}
