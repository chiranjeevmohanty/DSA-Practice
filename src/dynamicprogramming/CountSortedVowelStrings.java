package dynamicprogramming;

import strings.MaxNoOfRemovableCharacters;

import java.util.HashMap;
import java.util.Map;

public class CountSortedVowelStrings {
    public static void main(String[] args) {
        System.out.println(countVowelStrings(2));
    }
    static int countVowelStrings(int n) {
        char[] vowels = new char[]{'a', 'e', 'i', 'o', 'u'};
        Map<String, Integer> memo = new HashMap<>();
        return helper(vowels, n, 0, memo);
    }
    //top-down memoization approach
    static int helper(char[] vowels, int n, int index, Map<String, Integer> memo) {
        if (n == 0) {
            return 1;
        }

        String key = n + "," + index;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        int count = 0;
        for (int i = index; i < vowels.length; i++) {
            count += helper(vowels, n - 1, i, memo);
        }

        memo.put(key, count);
        return memo.get(key);
    }
}
