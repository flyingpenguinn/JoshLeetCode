public class DistanceBetweenBusStops {
    public int distanceBetweenBusStops(int[] distance, int start, int destination) {
        if (start > destination) {
            return distanceBetweenBusStops(distance, destination, start);
        }
        int n = distance.length;
        int direct = 0;
        for (int i = start; i < destination; i++) {
            direct += distance[i];
        }
        int reverse = 0;
        for (int i = destination; i < n; i++) {
            reverse += distance[i];
        }
        for (int i = 0; i < start; i++) {
            reverse += distance[i];
        }
        return Math.min(direct, reverse);
    }
}

// if we need to query a lot below takes On to initialize but O1 to query
class DistanceBetweenBusStopO1Query {
    public int distanceBetweenBusStops(int[] distance, int start, int destination) {
        if (start > destination) {
            return distanceBetweenBusStops(distance, destination, start);
        }
        int n = distance.length;
        int[] left = new int[n];
        int[] right = new int[n];
        for (int i = 1; i < n; i++) {
            left[i] = left[i - 1] + distance[i - 1];
        }
        right[n - 1] = distance[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            right[i] = right[i + 1] + distance[i];
        }
        int direct = left[destination] - left[start];
        int reverse = right[destination] + left[start];
        return Math.min(direct, reverse);
    }
}
