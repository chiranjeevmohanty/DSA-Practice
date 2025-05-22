package dynamicprogramming;
import java.util.*;

public class Triangle {
    //DP::Memoization
    public int minimumTotal(List<List<Integer>> triangle) {
        int[][] memo = new int[triangle.size()][triangle.get(triangle.size() - 1).size()];
        for(int[] arr: memo) Arrays.fill(arr, Integer.MAX_VALUE);
        return helper(triangle, 0, 0, memo);
    }
    public int helper(List<List<Integer>> triangle, int row, int col, int[][] memo){
        if(row == triangle.size() - 1){
            return triangle.get(row).get(col);
        }
        if(memo[row][col] != Integer.MAX_VALUE){
            return memo[row][col];
        }
        int bottom = triangle.get(row).get(col) + helper(triangle, row + 1, col, memo);
        int rightDiagonal = triangle.get(row).get(col) + helper(triangle, row + 1, col + 1, memo);
        return memo[row][col] = Math.min(bottom, rightDiagonal);
    }

    //DP::Tabulation
    public int minimumTotal2(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[n][n];

        for (int col = 0; col < triangle.get(n - 1).size(); col++) {
            dp[n - 1][col] = triangle.get(n - 1).get(col);
        }

        for (int row = n - 2; row >= 0; row--) {
            for (int col = 0; col <= row; col++) {
                int down = dp[row + 1][col];
                int diag = dp[row + 1][col + 1];
                dp[row][col] = triangle.get(row).get(col) + Math.min(down, diag);
            }
        }

        return dp[0][0];
    }

    //DP::Tabulation-space optimization
    public int minimumTotal3(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] prev = new int[n];

        for (int col = 0; col < n; col++) {
            prev[col] = triangle.get(n - 1).get(col);
        }

        for (int row = n - 2; row >= 0; row--) {
            int[] cur = new int[n];
            for (int col = 0; col <= row; col++) {
                int down = prev[col];
                int diag = prev[col + 1];
                cur[col] = triangle.get(row).get(col) + Math.min(down, diag);
            }
            prev = cur;
        }

        return prev[0];
    }
}
