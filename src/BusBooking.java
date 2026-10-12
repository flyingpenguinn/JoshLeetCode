public class BusBooking {
        private int[][] sm;

        public BusBooking(int n) {
            sm = new int[n + 1][4];
        }

        private int alltime = 0;
        private int allseats = 0;

        public void toggle(String seat) {
            int sn = seat.charAt(0) - 'A';
            int row = Integer.valueOf(seat.substring(1));
            int v = sm[row][sn];
            if (v == 0) {
                int delta = 0;
                if (sn == 0 && sm[row][1] > 0) {
                    delta = 3;
                } else if (sn == 3 && sm[row][2] > 0) {
                    delta = 3;
                } else {
                    delta = 1;
                }
                ++allseats;
                sm[row][sn] = delta;
                alltime += delta;
            } else {
                sm[row][sn] = 0;
                alltime -= v;
                --allseats;
            }

        }

        public int getTotalTime() {
            return alltime;
        }

        public int getMinTime() {
            return allseats;
        }
    }
