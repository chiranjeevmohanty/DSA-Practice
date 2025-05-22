package slidingWindow;
import java.util.*;

public class MinimumWindowSubstring {
    public static void main(String[] args) {
        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
    }
    static String minWindow(String s, String t) {
        if(t.length() > s.length()) return "";
        HashMap<Character, Integer> freq = new HashMap<>();
        for(int i = 0; i < t.length(); i++){
            freq.put(t.charAt(i), freq.getOrDefault(t.charAt(i), 0) + 1);
        }
        int left = 0;
        int right = 0;
        int minSize = Integer.MAX_VALUE;
        int required = freq.size();
        int minLeft = 0;
        int count = 0;
        HashMap<Character, Integer> curFreq = new HashMap<>();

        while(right < s.length()){
            char c = s.charAt(right);
            curFreq.put(c, curFreq.getOrDefault(c, 0) + 1);
            if(freq.containsKey(c) && Objects.equals(freq.get(c), curFreq.get(c))){
                count++;
            }
            while (left <= right && count == required){
                if (right - left + 1 < minSize) {
                    minSize = right - left + 1;
                    minLeft = left;
                }
                char removed = s.charAt(left);
                curFreq.put(removed, curFreq.getOrDefault(removed, 0) - 1);
                if(freq.containsKey(removed) && !Objects.equals(freq.get(removed), curFreq.get(removed))){
                    count--;
                }
                left++;
            }
            right++;
        }
        return minSize != Integer.MAX_VALUE ? s.substring(minLeft, minLeft + minSize): "";
    }
}
