package dynamicprogramming;

import java.util.Arrays;

public class CherryPickupII {
    public static void main(String[] args) {
        int[][] arr1 = new int[][]{{3, 1, 1}, {2, 5, 1}, {1, 5, 5}, {2, 1, 1}};
        int[][] arr2 = new int[][]{{1, 0, 0, 0, 0, 0, 1}, {2, 0, 0, 0, 0, 3, 0}, {2, 0, 9, 0, 0, 0, 0}, {0, 3, 0, 5, 4, 0, 0}, {1, 0, 2, 3, 0, 0, 6}};
        System.out.println(cherryPickup3(arr2));
    }

    //DP::Top-down memoization
    static int cherryPickup(int[][] grid) {
        int[][][] memo = new int[grid.length][grid[0].length][grid[0].length];
        for (int[][] arr : memo) {
            for (int[] arr2 : arr) {
                Arrays.fill(arr2, -1);
            }
        }
        return helper(grid, 0, 0, grid[0].length - 1, memo);
    }

    static int helper(int[][] grid, int row, int col1, int col2, int[][][] memo) {
        if (col1 < 0 || col1 >= grid[0].length || col2 < 0 || col2 >= grid[0].length) return -100000;
        if (row == grid.length - 1) {
            if (col1 == col2) return grid[row][col1];
            return grid[row][col1] + grid[row][col2];
        }
        if (memo[row][col1][col2] != -1) {
            return memo[row][col1][col2];
        }
        int max = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int val = 0;
                if (col1 != col2) {
                    val = grid[row][col1] + grid[row][col2] + helper(grid, row + 1, col1 + i, col2 + j, memo);
                } else {
                    val = grid[row][col2] + helper(grid, row + 1, col1 + i, col2 + j, memo);
                }
                max = Math.max(max, val);
            }
        }
        return memo[row][col1][col2] = max;
    }

    //DP:: Bottom-up Tabulation
    static int cherryPickup2(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][][] dp = new int[m][n][n];
        for (int j = 0; j < n; j++) {
            for (int k = 0; k < n; k++) {
                dp[m - 1][j][k] = (j == k) ? grid[m - 1][j] : grid[m - 1][j] + grid[m - 1][k];
            }
        }
        for (int i = m - 2; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    int max = 0;
                    for (int i1 = -1; i1 <= 1; i1++) {
                        for (int j1 = -1; j1 <= 1; j1++) {
                            int newJ = j + i1;
                            int newK = k + j1;
                            if (newJ >= 0 && newJ < n && newK >= 0 && newK < n) {
                                int val = (j == k ? grid[i][j] : grid[i][j] + grid[i][k]) + dp[i + 1][newJ][newK];
                                max = Math.max(max, val);
                            }
                        }
                    }
                    dp[i][j][k] = max;
                }
            }
        }
        System.out.println(Arrays.deepToString(dp));
        return dp[0][0][n - 1];
    }

    //DP:: Bottom-up Tabulation space optimized
    static int cherryPickup3(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] next = new int[n][n];
        for (int j = 0; j < n; j++) {
            for (int k = 0; k < n; k++) {
                next[j][k] = (j == k) ? grid[m - 1][j] : grid[m - 1][j] + grid[m - 1][k];
            }
        }
        for (int i = m - 2; i >= 0; i--) {
            int[][] curr = new int[n][n];
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    int max = 0;
                    for (int dj = -1; dj <= 1; dj++) {
                        for (int dk = -1; dk <= 1; dk++) {
                            int newJ = j + dj;
                            int newK = k + dk;
                            if (newJ >= 0 && newJ < n && newK >= 0 && newK < n) {
                                int val = (j == k ? grid[i][j] : grid[i][j] + grid[i][k]) + next[newJ][newK];
                                max = Math.max(max, val);
                            }
                        }
                    }
                    curr[j][k] = max;
                }
            }
            next = curr;
        }
        return next[0][n - 1];
    }
}
