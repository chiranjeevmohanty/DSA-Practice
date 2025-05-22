package slidingWindow;
import java.util.*;

public class NumberOfSubStringContainingAllChars {
    public static void main(String[] args) {
        System.out.println(numberOfSubstrings("acbbcac"));
    }
    static int numberOfSubstrings(String s) {
        int n = s.length();
        HashMap<Character, Integer> freq = new HashMap<>();
        int count = 0;
        int left = 0, right = 0;
        while(right < n){
            char c = s.charAt(right);
            freq.put(c, freq.getOrDefault(c, 0) + 1);
            while(left <= right && freq.size() == 3){
                count += (n - right);
                char ch = s.charAt(left);
                if(freq.get(ch) > 1){
                    freq.put(ch, freq.getOrDefault(ch, 0) - 1);
                }else{
                    freq.remove(ch);
                }
                left++;
            }
            right++;
        }
        return count;
    }
}
