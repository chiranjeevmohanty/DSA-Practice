package dynamicprogramming;

import java.util.Arrays;

public class DecodeWays {
    public static void main(String[] args) {
        System.out.println(numDecodings("11106"));
    }

    //DP::top-down 
    static int numDecodings(String s) {
        int[] memo = new int[s.length()];
        Arrays.fill(memo, -1);
        return helper(s, 0, memo);
    }
    static int helper(String s, int i, int[] memo){
        if(i >= s.length()){
            return 1;
        }
        if(memo[i] != -1){
            return memo[i];
        }
        int count = 0;
        if(checkRange(s.substring(i, i + 1))){
            count += helper(s, i + 1, memo);
        }
        if(i < s.length() - 1 && checkRange(s.substring(i, i + 2))){
            count += helper(s, i + 2, memo);
        }
        memo[i] = count;
        return memo[i];
    }
    static boolean checkRange(String n){
        if(n.charAt(0) == '0') return false;
        int m = Integer.parseInt(n);
        return m > 0 && m <= 26;
    }
}
