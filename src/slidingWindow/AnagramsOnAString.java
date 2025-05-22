package slidingWindow;
import java.util.*;

public class AnagramsOnAString {
    public static void main(String[] args) {
        System.out.println(findAnagrams("abab", "ab"));
    }
    static List<Integer> findAnagrams(String s, String p) {
        List<Integer> indices = new ArrayList<>();
        int m = s.length(), n = p.length();
        if (m < n) return indices;
        HashMap<Character, Integer> freq = new HashMap<>();
        for (char c : p.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }
        HashMap<Character, Integer> window = new HashMap<>();
        int match = 0;

        for (int i = 0; i < m; i++) {
            char c = s.charAt(i);
            window.put(c, window.getOrDefault(c, 0) + 1);
            if (freq.containsKey(c) && window.get(c).intValue() == freq.get(c).intValue()) {
                match++;
            }
            if (i >= n) {
                char left = s.charAt(i - n);
                if (freq.containsKey(left) && window.get(left).intValue() == freq.get(left).intValue()) {
                    match--;
                }
                window.put(left, window.get(left) - 1);
                if (window.get(left) == 0) window.remove(left);
            }
            if (match == freq.size()) {
                indices.add(i - n + 1);
            }
        }
        return indices;
    }
}
