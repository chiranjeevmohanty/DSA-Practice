package strings;
import java.util.*;

public class RepeatedDNASequence {
    public static void main(String[] args) {
        System.out.println(findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
    }
    static List<String> findRepeatedDnaSequences(String s) {
        HashSet<String> res = new HashSet<>();
        int k = 10;
        if(s.length() <= k) return new ArrayList<>();
        HashMap<String, Integer> freq = new HashMap<>();
        for(int i = 0; i <= s.length() - k; i++){
            if(freq.get(s.substring(i, i + k)) != null && freq.get(s.substring(i, i + k)) >= 1){
                res.add(s.substring(i, i + k));
            }else{
                freq.put(s.substring(i, i + k), freq.getOrDefault(s.substring(i, i + k), 0) + 1);
            }
        }
        return res.stream().toList();
    }
}
