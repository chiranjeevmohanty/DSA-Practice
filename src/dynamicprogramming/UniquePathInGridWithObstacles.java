package dynamicprogramming;
import java.util.*;

public class UniquePathInGridWithObstacles {
    //DP::Memoization
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
         int[][] memo = new int[m][n];
         for(int[] arr: memo){
             Arrays.fill(arr,-1);
         }
         return helper(obstacleGrid, m - 1, n - 1, memo);
    }

    public int helper(int[][] obstacleGrid, int row, int col, int[][] memo) {
        if (row >= 0 && col >= 0 && obstacleGrid[row][col] == 1) return 0;
        if (row == 0 && col == 0) return 1;
        if (row < 0 || col < 0) return 0;
        if (memo[row][col] != -1) {
            return memo[row][col];
        }

        int total = 0;
        total += helper(obstacleGrid, row - 1, col, memo);
        total += helper(obstacleGrid, row, col - 1, memo);
        return memo[row][col] = total;
    }

    //DP::Tabulation(space optimized)
    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[] prev = new int[n];
        for (int i = 0; i < m; i++) {
            int[] temp = new int[n];
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    temp[j] = 0;
                } else if (i == 0 && j == 0) {
                    temp[j] = 1;
                } else {
                    int left = 0, right = 0;
                    if (i > 0) {
                        right = prev[j];
                    }
                    if (j > 0) {
                        left = temp[j - 1];
                    }
                    temp[j] = left + right;
                }
            }
            prev = temp;
        }
        return prev[n - 1];
    }
}
