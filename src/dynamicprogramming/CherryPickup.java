package dynamicprogramming;

import java.util.Arrays;

public class CherryPickup {
    public static void main(String[] args) {
        int[][] arr1 = new int[][]{{0,1,-1},{1,0,-1},{1,1,1}};
        System.out.println(cherryPickup(arr1));
    }
    static int cherryPickup(int[][] grid) {
        int n = grid.length;
        int[][][] memo = new int[n][n][n];
        for(int[][] arr: memo){
            for(int[] arr2: arr){
                Arrays.fill(arr2, -1);
            }
        }
        return Math.max(0, helper(grid, 0, 0, 0, 0, memo));
    }

    static int helper(int[][] grid, int r1, int c1, int r2, int c2, int[][][] memo) {
         int n = grid.length;
         if(r1 > n - 1 || r2 > n - 1 || c1 > n - 1 || c2 > n - 1){
             return -100000;
         }
         if(grid[r1][c1] == -1 || grid[r2][c2] == -1) return -100000;
         if(r1 == n - 1 && r2 == n - 1 && c1 == n - 1 && c2 == n - 1) return grid[r1][c1];
         if(memo[r1][c1][c2] != -1){
             return memo[r1][c1][c2];
         }
         int maxVal = 0;
         if(r1 == r2 && c1 == c2){
             maxVal += grid[r1][c1];
         }else {
             maxVal += grid[r1][c1] + grid[r2][c2];
         }
         maxVal += Math.max(
           Math.max(helper(grid, r1 + 1, c1, r2 + 1, c2, memo), helper(grid, r1 + 1, c1, r2, c2 + 1, memo)),
           Math.max(helper(grid, r1, c1 + 1, r2 + 1, c2, memo), helper(grid, r1, c1 + 1, r2, c2 + 1, memo))
         );
         return memo[r1][c1][c2] = maxVal;
    }
}
