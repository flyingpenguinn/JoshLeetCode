public class DesignOrderedStream {
    private String[] data;
    private int ptr = 1;

    public OrderedStream(int n) {
        data = new String[n + 2];
    }

    public List<String> insert(int id, String value) {
        data[id] = value;
        List<String> res = new ArrayList<>();
        if (data[ptr] == null) {
            return res;
        }
        while (ptr < data.length) {
            if (data[ptr] == null) {
                break;
            } else {
                res.add(data[ptr]);
                ptr++;
            }
        }
        return res;
    }
}
