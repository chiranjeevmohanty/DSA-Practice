package dynamicprogramming;

import java.util.Arrays;

public class EditDistance {
    public static void main(String[] args) {
        System.out.println(minDistance3("intention", "execution"));
    }

    static int minDistance(String word1, String word2) {
        int[][] memo = new int[word1.length()][word2.length()];
        return helper(word1, word2, word1.length() - 1, word2.length() - 1, memo);
    }

    //DP::top-down memoization
    static int helper(String w1, String w2, int i, int j, int[][] memo) {
        if (i < 0) return j + 1;
        if (j < 0) return i + 1;
        if (w1.charAt(i) == w2.charAt(j)) {
            return helper(w1, w2, i - 1, j - 1, memo);
        }
        if(memo[i][j] != 0){
            return memo[i][j];
        }
        int insert = 1 + helper(w1, w2, i, j - 1, memo);
        int delete = 1 + helper(w1, w2, i - 1, j, memo);
        int replace = 1 + helper(w1, w2, i - 1, j - 1, memo);
        memo[i][j] = Math.min(insert, Math.min(delete, replace));
        return memo[i][j];
    }

    //DP::bottom-up tabulation
    static int minDistance2(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for(int i = 0; i <= word1.length(); i++) dp[i][0] = i;
        for(int j = 0; j <= word2.length(); j++) dp[0][j] = j;
        for(int i = 1; i <= word1.length(); i++){
            for(int j = 1; j <= word2.length(); j++){
                if(word1.charAt(i - 1) == word2.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1];
                }else{
                    int insert = dp[i][j - 1] + 1;
                    int delete = dp[i - 1][j] + 1;
                    int replace = dp[i - 1][j - 1] + 1;
                    dp[i][j] = Math.min(insert, Math.min(delete, replace));
                }
            }
        }
        System.out.println(Arrays.deepToString(dp));
        return dp[word1.length()][word2.length()];
    }

    //space optimization
    static int minDistance3(String word1, String word2) {
        int[] prev = new int[word2.length() + 1];
        int[] curr = new int[word2.length() + 1];
        for(int i = 0; i <= word2.length(); i++) prev[i] = i;
        for(int i = 1; i <= word1.length(); i++){
            curr[0] = i;
            for(int j = 1; j <= word2.length(); j++){
                if(word1.charAt(i - 1) == word2.charAt(j - 1)){
                    curr[j] = prev[j - 1];
                }else{
                    int insert = curr[j - 1] + 1;
                    int delete = prev[j] + 1;
                    int replace = prev[j - 1] + 1;
                    curr[j] = Math.min(insert, Math.min(delete, replace));
                }
            }
            int[] temp = prev;
            prev = curr;
            curr = temp;

        }
        return prev[word2.length()];
    }
}
