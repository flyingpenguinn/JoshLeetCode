import java.util.*;

public class MostPopularVideoCreator {
    private class Video {
        String id;
        int views;

        public Video(String id, int v) {
            this.id = id;
            this.views = v;
        }
    }

    private Map<String, Long> cviews = new HashMap<>();
    private Map<String, PriorityQueue<Video>> c2v = new HashMap<>();

    public List<List<String>> mostPopularCreator(String[] creators, String[] ids, int[] views) {
        int n = creators.length;
        long maxcount = 0;
        for (int i = 0; i < n; ++i) {
            String cc = creators[i];
            long ncv = cviews.getOrDefault(cc, 0L) + views[i];
            cviews.put(cc, ncv);
            maxcount = Math.max(maxcount, ncv);
            Video cv = new Video(ids[i], views[i]);
            PriorityQueue<Video> pq = c2v.getOrDefault(cc, new PriorityQueue<>((x, y) -> {
                if (x.views != y.views) {
                    return Integer.compare(x.views, y.views);
                } else {
                    return y.id.compareTo(x.id);
                }
            }));
            pq.offer(cv);
            if (pq.size() > 1) {
                pq.poll();
            }
            c2v.put(cc, pq);
        }
        List<List<String>> res = new ArrayList<>();
        for (String c : cviews.keySet()) {
            if (cviews.get(c) == maxcount) {
                List<String> cur = new ArrayList<>();
                cur.add(c);
                cur.add(c2v.get(c).peek().id);
                res.add(cur);
            }
        }
        return res;
    }
}
