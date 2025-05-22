package dynamicprogramming;

import java.util.Arrays;

public class UniquePaths {
    public static void main(String[] args) {
        System.out.println(uniquePaths3(3, 5));
    }
    static int uniquePaths(int m, int n) {
        int[][] memo = new int[m + 1][n + 1];
        return helper(0, 0, m, n, memo);
    }

    //DP:: top-down memoization approach
    static int helper(int row, int col, int m, int n, int[][] memo){
        if(memo[row][col] != 0){
            return memo[row][col];
        }
        if(row == m - 1 && col == n - 1){
            return 1;
        }
        if(row >= m || col >= n){
            return 0;
        }
        int count = 0;
        count += helper(row + 1, col, m, n, memo);
        count += helper(row, col + 1, m, n, memo);
        memo[row][col] = count;
        return memo[row][col];
    }

    //DP:: Bottom-up tabulation
    static int uniquePaths2(int m, int n) {
        int[][] dp = new int[m][n];
        for(int i = 0; i < m; i++) dp[i][0] = 1;
        for(int j = 0; j < n; j++) dp[0][j] = 1;
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
            }
        }
        return dp[m-1][n-1];
    }

    //space-optimization
    static int uniquePaths3(int m, int n) {
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                dp[j] += dp[j - 1];
            }
        }
        return dp[n-1];
    }
}
