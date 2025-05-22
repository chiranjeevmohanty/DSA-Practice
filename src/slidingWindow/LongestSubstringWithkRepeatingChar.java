package slidingWindow;

import java.util.HashMap;

public class LongestSubstringWithkRepeatingChar {
    public static void main(String[] args) {
        System.out.println(longestSubstring("bbaaacbd", 3));
    }
    /*    static int longestSubstring(String s, int k) {
        HashMap<Character, Integer> freq = new HashMap<>();
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }
        StringBuilder sb = new StringBuilder(s);
        for(int i = 0; i < sb.length(); i++){
            char c = sb.charAt(i);
            if(freq.get(c) < k){
                sb.setCharAt(i, '*');
            }
        }
        int l = 0;
        int r = 0;
        int maxLength = 0;
        while(r < sb.length()){
            if(sb.charAt(l) == '*'){
                l++;
                r++;
                continue;
            }
            HashMap<Character, Integer> freq2 = new HashMap<>();
            while(r < sb.length() && sb.charAt(r) != '*'){
                char c = sb.charAt(r);
                freq2.put(c, freq2.getOrDefault(c, 0) + 1);
                r++;
            }
            boolean valid = true;
            for (int fr : freq2.values()) {
                if (fr < k) {
                    valid = false;
                    break;
                }
            }
            if(valid) maxLength = Math.max(r - l, maxLength);
            l = r;
        }
        return maxLength;
    }*/
    //divide&conquer approach
    static int longestSubstring(String s, int k) {
        return helper(s, 0, s.length() - 1, k);
    }
    static int helper(String s, int start, int end, int k){
        if(end - start < k) return 0;
        int[] freq = new int[26];
        for (int i = start; i < end; i++) {
            freq[s.charAt(i) - 'a']++;
        }
        for (int i = start; i < end; i++) {
            if(freq[s.charAt(i) - 'a'] < k){
                int j = i + 1;
                while (j < end && freq[s.charAt(j) - 'a'] < k) j++;
                return Math.max(helper(s, start, i, k), helper(s, j, end, k));
            }
        }
        return end - start;
    }
}
