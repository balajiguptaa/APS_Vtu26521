class Solution {
    public int[][] matrixBlockSum(int[][] mat, int k) {
        int m = mat.length, n = mat[0].length;

        // Step 1: Build prefix sum matrix
        int[][] pre = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                pre[i][j] = mat[i - 1][j - 1]
                        + pre[i - 1][j]
                        + pre[i][j - 1]
                        - pre[i - 1][j - 1];
            }
        }

        // Step 2: Compute result
        int[][] res = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                int r1 = Math.max(0, i - k);
                int c1 = Math.max(0, j - k);
                int r2 = Math.min(m - 1, i + k);
                int c2 = Math.min(n - 1, j + k);

                // convert to prefix indices (+1 shift)
                r1++; c1++; r2++; c2++;

                res[i][j] = pre[r2][c2]
                        - pre[r1 - 1][c2]
                        - pre[r2][c1 - 1]
                        + pre[r1 - 1][c1 - 1];
            }
        }

        return res;
    }
}