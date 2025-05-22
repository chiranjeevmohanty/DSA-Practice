package dynamicprogramming;
import java.util.Arrays;

public class MinPathSum {
    public static void main(String[] args) {
        System.out.println(minPathSum2(new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}}));
    }

    //DP::memoization
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] memo = new int[m][n];
        for(int[] arr: memo) Arrays.fill(arr, -1);
        return helper(grid, m - 1, n - 1, memo);
    }
    public int helper(int[][] grid, int row, int col, int[][] memo){
        if(row == 0 && col == 0){
            return grid[row][col];
        }
        if(row < 0 || col < 0){
            return 100000;
        }
        if(memo[row][col] != -1){
            return memo[row][col];
        }
        return memo[row][col] = Math.min(grid[row][col] + helper(grid, row, col - 1, memo), grid[row][col] + helper(grid, row -  1, col, memo));
    }

    //DP::tabulation
    static int minPathSum2(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(i == 0 && j == 0){
                    dp[i][j] = grid[i][j];
                }else{
                    int left = 100000;
                    int top = 100000;
                    if(j > 0) left = grid[i][j] + dp[i][j - 1];
                    if(i > 0) top = grid[i][j] + dp[i - 1][j];
                    dp[i][j] = Math.min(left, top);
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    //DP::tabulation-space optimization
    static int minPathSum3(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] dp = new int[n];
        for(int i = 0; i < m; i++){
            int[] temp = new int[n];
            for(int j = 0; j < n; j++){
                if(i == 0 && j == 0){
                    temp[j] = grid[i][j];
                }else{
                    int left = 100000;
                    int top = 100000;
                    if(j > 0) left = grid[i][j] + temp[j - 1];
                    if(i > 0) top = grid[i][j] + dp[j];
                    temp[j] = Math.min(left, top);
                }
            }
            dp = temp;
        }
        return dp[n - 1];
    }
}
