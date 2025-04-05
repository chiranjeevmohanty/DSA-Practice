/*---Approach---*/
/*
 * Problem Statement: (LC:1898)
 * You are given two strings s and p where p is a subsequence of s. You are also given a distinct 0-indexed integer array removable containing a subset of indices of s (s is also 0-indexed).
 * You want to choose an integer k (0 <= k <= removable.length) such that, after removing k characters from s using the first k indices in removable, p is still a subsequence of s. More formally, you will mark the character at s[removable[i]] for each 0 <= i < k, then remove all marked characters and check if p is still a subsequence.
 * Return the maximum k you can choose such that p is still a subsequence of s after the removals.
 * A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.
 *
 * ---Constraints---
 *  1 <= p.length <= s.length <= 10^5
 *  0 <= removable.length < s.length
 *  0 <= removable[i] < s.length
 *  p is a subsequence of s.
 *  s and p both consist of lowercase English letters.
 *  The elements in removable are distinct.
 *
 * ---Approaches---
 * ->Brute Force (linear search):
 *  Time Complexity: O(n ^ 2) // in one loop we will remove one element and other nested loop we check whether uts valid sub-sequence or not.
 *  Space Complexity: O(1) // no extra space just some variables
 *
 * ->Optimized Approach (binary search and two pointers):
 *  Time Complexity: O(nlog(n)) // in each step we will half our result, and we will check whether its valid sub-sequence or not.
 *  Space Complexity: O(n) // for the boolean marked array.
 * - Thing to notice here is we actually don't need to remove element one by one and check for sub-sequence.
 * - simply in each step we can just make our result half by size.
 * - At 1st we will create our search space its is nothing but the size of the removable array.
 * - we will take start as 0 and end as removable.length. (Why not removable.length - 1, here we are not checking the values we are checking the size so it's logical to take length only)
 * - Another thing we have to consider is that for the removable array we need one extra array to mark the positions where the element will be removed that is our boolean-marked array.
 * - Now as usual we will define our while loop for the binary search, and we will create a new function which will check whether it's valid subsequence of not by taking two strings and the marked array this method internally uses two pointer technique.
 * - before adding this check function we have to modify the marked array by specifying certain index to false by traversing the removable array from 0 to the mid.
 * - if the helper function returns true means we have one possible answer we will store that in one answer and we will make start to mid + 1. to see is there any more possible answers.
 * - if returns false then we will make end = mid - 1. the answer lies in the 1st half.
 * - after the while loop terminates we got our answer and we will simply return that
 * */

/*---Corner Cases---*/
/*
 *
 * 1. Basic Match Without Removals
 *    - Input: s = "abcacb", p = "ab", removable = [3,1,0]
 *    - Expected Output: 2
 *    - Explanation: Removing indices 3 and 1 gives "a*c*cb" → "acb", and "ab" is still a subsequence.
 *
 * 2. Single Valid Removal
 *    - Input: s = "abcbddddd", p = "abcd", removable = [3,2,1,4,5,6]
 *    - Expected Output: 1
 *    - Explanation: Only one character can be removed. More than that breaks the match with `p`.
 *
 * 3. All Characters Can Be Removed
 *    - Input: s = "abcde", p = "a", removable = [1,2,3,4]
 *    - Expected Output: 4
 *    - Explanation: Only 'a' is needed, so the rest can be safely removed.
 *
 * 4. No Characters Removable
 *    - Input: s = "abc", p = "abc", removable = [0,1,2]
 *    - Expected Output: 0
 *    - Explanation: Removing even one character breaks the entire match with `p`.
 *
 * 5. Longest Removable Prefix
 *    - Input: s = "xyzabc", p = "abc", removable = [0,1,2]
 *    - Expected Output: 3
 *    - Explanation: All of "xyz" can be removed; "abc" remains intact as a subsequence.
 *
 * 6. Repeated Characters in s
 *    - Input: s = "aabbaabb", p = "ab", removable = [1,3,5,7]
 *    - Expected Output: 4
 *    - Explanation: Removing second occurrence of each character still keeps one copy for subsequence.
 */


package strings;

public class MaxNoOfRemovableCharacters {
    public static void main(String[] args) {
        System.out.println(maximumRemovals("abcbddddd", "abcd", new int[]{3,2,1,4,5,6}));
    }
    static int maximumRemovals(String s, String p, int[] removable) {
        boolean[] marked = new boolean[s.length()]; //boolean array to mark the index which should remove
        int start = 0;
        int end = removable.length; //taking removable.length coz we are considering the size of k.
        int ans = 0;
        while(start <= end){
            int mid = start + (end - start)/2;
            //marking the indexes true using removable array
            for (int i = 0; i < mid; i++) {
                marked[removable[i]] = true;
            }
            if (checkSubSeq(marked, s, p)) {
                ans = mid; //storing one possible answer
                start = mid + 1; //searching right half for other possible answers
            } else {
                end = mid - 1; //condition fails: checking in left half for the possible answer
            }
            //backtracking the changes so that it won't affect the result
            for (int i = 0; i < mid; i++) {
                marked[removable[i]] = false;
            }
        }
        return ans;
    }
    //function to check whether its p is valid sub-sequence of s or not with considering the removable indexes by using marked array
    static boolean checkSubSeq(boolean[] marked, String s, String p){
        int i = 0, j = 0;
        while (i < p.length() && j < s.length()) {
            if (!marked[j] && s.charAt(j) == p.charAt(i)) {
                i++;
            }
            j++;
        }
        return i == p.length(); //means i if fully traversed the p string so true otherwise false
    }
}
