import java.util.*;

class Solution {
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {

        List<Integer>[] redGraph = new ArrayList[n];
        List<Integer>[] blueGraph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            redGraph[i] = new ArrayList<>();
            blueGraph[i] = new ArrayList<>();
        }

        for (int[] e : redEdges) redGraph[e[0]].add(e[1]);
        for (int[] e : blueEdges) blueGraph[e[0]].add(e[1]);

        int[][] dist = new int[n][2]; // 0 = red, 1 = blue
        for (int[] d : dist) Arrays.fill(d, -1);

        Queue<int[]> q = new LinkedList<>();

        // Start from node 0 with both colors
        q.offer(new int[]{0, 0}); // last red
        q.offer(new int[]{0, 1}); // last blue

        dist[0][0] = 0;
        dist[0][1] = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int node = cur[0], color = cur[1];

            if (color == 0) { // last was red → go blue
                for (int next : blueGraph[node]) {
                    if (dist[next][1] == -1) {
                        dist[next][1] = dist[node][0] + 1;
                        q.offer(new int[]{next, 1});
                    }
                }
            } else { // last was blue → go red
                for (int next : redGraph[node]) {
                    if (dist[next][0] == -1) {
                        dist[next][0] = dist[node][1] + 1;
                        q.offer(new int[]{next, 0});
                    }
                }
            }
        }

        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            if (dist[i][0] == -1) result[i] = dist[i][1];
            else if (dist[i][1] == -1) result[i] = dist[i][0];
            else result[i] = Math.min(dist[i][0], dist[i][1]);
        }

        return result;
    }
}