import java.util.*;

class Solution {
    public int orangesRotting(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        Queue<int[]> q = new LinkedList<>();
        int fresh = 0;

        // Step 1: initialize queue + count fresh
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 2) q.offer(new int[]{i, j});
                if (grid[i][j] == 1) fresh++;
            }
        }

        if (fresh == 0) return 0;

        int minutes = 0;
        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

        // Step 2: BFS
        while (!q.isEmpty()) {
            int size = q.size();
            boolean infected = false;

            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();

                for (int[] d : dirs) {
                    int r = cur[0] + d[0];
                    int c = cur[1] + d[1];

                    if (r >= 0 && r < rows && c >= 0 && c < cols && grid[r][c] == 1) {
                        grid[r][c] = 2;
                        q.offer(new int[]{r, c});
                        fresh--;
                        infected = true;
                    }
                }
            }

            if (infected) minutes++;
        }

        return fresh == 0 ? minutes : -1;
    }
}