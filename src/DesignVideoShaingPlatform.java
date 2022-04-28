import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class DesignVideoShaingPlatform {
    class VideoSharingPlatform {

        private TreeSet<Integer> idpool = new TreeSet<>();

        private Map<Integer, String> id2video = new HashMap<>();
        private Map<Integer, Integer> views = new HashMap<>();
        private Map<Integer, Integer> likes = new HashMap<>();
        private Map<Integer, Integer> dislikes = new HashMap<>();

        private void update(Map<Integer,Integer> m, int k, int d){
            int nv = m.getOrDefault(k, 0)+d;
            m.put(k, nv);
        }

        private int getId(){
            if(idpool.isEmpty()){
                return id2video.size();
            }else{
                return idpool.first(); // must be smallear than map size
            }

        }

        public VideoSharingPlatform() {

        }

        public int upload(String video) {
            Integer avail = getId();
            id2video.put(avail, video);
            idpool.remove(avail);
            return avail;
        }

        public void remove(int videoId) {
            if(!id2video.containsKey(videoId)){
                return;
            }
            id2video.remove(videoId);
            likes.remove(videoId);
            dislikes.remove(videoId);
            views.remove(videoId);
            idpool.add(videoId);
        }

        public String watch(int videoId, int startMinute, int endMinute) {
            if(!id2video.containsKey(videoId)){
                return "-1";
            }
            update(views, videoId, 1);
            String str = id2video.get(videoId);
            return str.substring(startMinute, Math.min(endMinute, str.length() - 1)+1);
        }

        public void like(int videoId) {
            if(!id2video.containsKey(videoId)){
                return;
            }
            update(likes, videoId, 1);
        }

        public void dislike(int videoId) {
            if(!id2video.containsKey(videoId)){
                return;
            }
            update(dislikes, videoId, 1);
        }

        public int[] getLikesAndDislikes(int videoId) {
            if(!id2video.containsKey(videoId)){
                return new int[]{-1};
            }
            return new int[]{likes.getOrDefault(videoId, 0), dislikes.getOrDefault(videoId, 0)};
        }

        public int getViews(int videoId) {
            if(!id2video.containsKey(videoId)){
                return -1;
            }
            return views.getOrDefault(videoId, 0);
        }
    }

/**
 * Your VideoSharingPlatform object will be instantiated and called as such:
 * VideoSharingPlatform obj = new VideoSharingPlatform();
 * int param_1 = obj.upload(video);
 * obj.remove(videoId);
 * String param_3 = obj.watch(videoId,startMinute,endMinute);
 * obj.like(videoId);
 * obj.dislike(videoId);
 * int[] param_6 = obj.getLikesAndDislikes(videoId);
 * int param_7 = obj.getViews(videoId);
 */
}
