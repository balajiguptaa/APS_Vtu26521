import java.util.*;

class Solution {
    public int[][] updateMatrix(int[][] mat) {
        int rows = mat.length, cols = mat[0].length;
        Queue<int[]> q = new LinkedList<>();

        // Step 1: initialize queue
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (mat[i][j] == 0) {
                    q.offer(new int[]{i, j});
                } else {
                    mat[i][j] = -1; // mark unvisited
                }
            }
        }

        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

        // Step 2: BFS
        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int[] d : dirs) {
                int r = cur[0] + d[0];
                int c = cur[1] + d[1];

                if (r >= 0 && c >= 0 && r < rows && c < cols && mat[r][c] == -1) {
                    mat[r][c] = mat[cur[0]][cur[1]] + 1;
                    q.offer(new int[]{r, c});
                }
            }
        }

        return mat;
    }
}