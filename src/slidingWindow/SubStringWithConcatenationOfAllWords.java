package slidingWindow;

import java.util.*;

public class SubStringWithConcatenationOfAllWords {
    public static void main(String[] args) {
        System.out.println(findSubstring("barfoothefoobarman", new String[]{"foo", "bar"}));
    }

    static List<Integer> findSubstring(String s, String[] words) {
        List<Integer> indices = new ArrayList<>();
        int wordLength = words[0].length();
        int tabSize = words.length * wordLength;
        if (s.length() < tabSize) return indices;

        Map<String, Integer> freq = new HashMap<>();
        for (String word : words) {
            freq.put(word, freq.getOrDefault(word, 0) + 1);
        }

        for (int i = 0; i < wordLength; i++) {
            int left = i, right = i, count = 0;
            Map<String, Integer> curFreq = new HashMap<>();

            while (right + wordLength <= s.length()) {
                String word = s.substring(right, right + wordLength);
                right += wordLength;

                if (freq.containsKey(word)) {
                    curFreq.put(word, curFreq.getOrDefault(word, 0) + 1);

                    if (Objects.equals(curFreq.get(word), freq.get(word))) {
                        count++;
                    }
                    while (curFreq.get(word) > freq.get(word)) {
                        String leftWord = s.substring(left, left + wordLength);
                        if (Objects.equals(curFreq.get(leftWord), freq.get(leftWord))) {
                            count--;
                        }
                        curFreq.put(leftWord, curFreq.get(leftWord) - 1);
                        left += wordLength;
                    }
                    if (count == freq.size()) {
                        indices.add(left);
                    }
                } else {
                    curFreq.clear();
                    count = 0;
                    left = right;
                }
            }
        }
        return indices;
    }
}
