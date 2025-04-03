/*---Approach---*/
/*
 * Problem Statement: (LC:1813)
 *  You are given two strings sentence1 and sentence2, each representing a sentence composed of words. A sentence is a list of words that are separated by a single space with no leading or trailing spaces. Each word consists of only uppercase and lowercase English characters.
 *  Two sentences s1 and s2 are considered similar if it is possible to insert an arbitrary sentence (possibly empty) inside one of these sentences such that the two sentences become equal. Note that the inserted sentence must be separated from existing words by spaces.
 *
 * ---Constraints---
 *  1 <= sentence1.length, sentence2.length <= 100
 * sentence1 and sentence2 consist of lowercase and uppercase English letters and spaces.
 * The words in sentence1 and sentence2 are separated by a single space.
 *
 * ---Approaches---
 * ->Optimized Approach (split , two pointers):
 *  Time Complexity: O(n + m) // we traversing the sentence1 and sentence2 twice.
 *  Space Complexity: O(n + m) // we are creating a new array with the words for both sentence.
 * - at 1st we need to split both sentences into words by space.
 * - everytime we have to pass long sentence 1st and short one in 2nd argument. so if it is not we will swap the values by the same function(recursion).
 * - then we can take four pointers basically start end of both split arrays.
 * - we will compare the words at start index, and we will do the same for end as well.
 * - in the last step if the no of matched words is greater or equals to the length of 2nd array then return true or else false.
 */

/*---Corner Cases---*/
/*
 * 1. Minimum Length Input (Single Character Words)
 *    - Input: sentence1 = "a", sentence2 = "a"
 *    - Expected Output: true
 *    - Explanation: Both sentences are identical, so they are similar.
 *
 * 2. Minimum Length Input (Different Single Character Words)
 *    - Input: sentence1 = "a", sentence2 = "b"
 *    - Expected Output: false
 *    - Explanation: The words are different, so the sentences are not similar.
 *
 * 3. Identical Sentences
 *    - Input: sentence1 = "Hello world", sentence2 = "Hello world"
 *    - Expected Output: true
 *    - Explanation: Both sentences are identical.
 *
 * 4. Sentences with Different Cases
 *    - Input: sentence1 = "Hello world", sentence2 = "hello world"
 *    - Expected Output: false
 *    - Explanation: "Hello" and "hello" are different due to case sensitivity.
 *
 * 5. Sentence2 is a Prefix of Sentence1
 *    - Input: sentence1 = "good morning everyone", sentence2 = "good morning"
 *    - Expected Output: true
 *    - Explanation: The second sentence is a prefix of the first sentence.
 *
 * 6. Sentence2 is a Suffix of Sentence1
 *    - Input: sentence1 = "have a great day", sentence2 = "great day"
 *    - Expected Output: true
 *    - Explanation: The second sentence is a suffix of the first sentence.
 *
 * 7. Sentence2 is a Substring but Not a Prefix or Suffix
 *    - Input: sentence1 = "the sky is blue", sentence2 = "sky is"
 *    - Expected Output: false
 *    - Explanation: "sky is" is neither a prefix nor a suffix.
 *
 * 8. Single Word Matching
 *    - Input: sentence1 = "hello", sentence2 = "hello"
 *    - Expected Output: true
 *    - Explanation: Both sentences contain the same word.
 *
 * 9. Single Word Non-Matching
 *    - Input: sentence1 = "hello", sentence2 = "world"
 *    - Expected Output: false
 *    - Explanation: The words are different.
 *
 * 10. Completely Different Sentences
 *    - Input: sentence1 = "the quick brown fox", sentence2 = "jumps over the lazy dog"
 *    - Expected Output: false
 *    - Explanation: No common words or structure.
 *
 * 11. Sentence2 Appears in the Middle of Sentence1
 *    - Input: sentence1 = "welcome to the jungle", sentence2 = "to the"
 *    - Expected Output: false
 *    - Explanation: "to the" appears in the middle but is neither a prefix nor a suffix.
 *
 * 12. Largest Possible Input with Identical Sentences (100 Characters)
 *    - Input: sentence1 = "a ".repeat(50).trim(), sentence2 = "a ".repeat(50).trim()
 *    - Expected Output: true
 *    - Explanation: Both sentences are identical.
 *
 * 13. Largest Possible Input with a Small Difference
 *    - Input: sentence1 = "a ".repeat(50).trim(), sentence2 = "b " + "a ".repeat(49).trim()
 *    - Expected Output: false
 *    - Explanation: The first word is different.
 *
 * 14. Random Case Sentence with Mixed Words
 *    - Input: sentence1 = "A b C D e F", sentence2 = "A b C D e"
 *    - Expected Output: true
 *    - Explanation: Sentence2 is a prefix of Sentence1.
 *
 * 15. Spaces Between Words Are Strictly Single Spaces
 *    - Input: sentence1 = "hello  world", sentence2 = "hello world"
 *    - Expected Output: false
 *    - Explanation: The double space in sentence1 makes them different.
 */


package strings;

public class SentenceSimilarityIII {
    public static void main(String[] args) {
        System.out.println(areSentencesSimilar("My name is Haley", "My Haley"));
    }
    static boolean areSentencesSimilar(String sentence1, String sentence2) {
        String[] words1 = sentence1.split(" ");
        String[] words2 = sentence2.split(" ");

        if (words1.length < words2.length) {
            return areSentencesSimilar(sentence2, sentence1);
        }
        int i = 0, j = 0;
        int n1 = words1.length, n2 = words2.length;
        while (i < n2 && words1[i].equals(words2[i])) {
            i++;
        }
        while (j < n2 && words1[n1 - 1 - j].equals(words2[n2 - 1 - j])) {
            j++;
        }
        return (i + j) >= n2;
    }
}
