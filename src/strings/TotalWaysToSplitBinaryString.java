/*---Approach---*/
/*
 * Problem Statement: (LC:1573)
 *  ->Given a binary string s, you can split s into 3 non-empty strings s1, s2, and s3 where s1 + s2 + s3 = s.
 *  Return the number of ways s can be split such that the number of ones is the same in s1, s2, and s3. Since the answer may be too large, return it modulo 109 + 7.
 *
 * ---Constraints---
 *  3 <= s.length <= 105
 *  s[i] is either '0' or '1'.
 *
 * ---Approaches---
 * ->Brute Force (nested loop):
 *  Time Complexity: O(n ^ 3) // Three nested loops
 *  Space Complexity: O(n) // to store the string
 * - in brute force approach we need three nested loop to calculate the total no.
 * - Two loops for generating all valid sub strings.
 * - One loop for checking which are the possible outputs.
 *
 * ->Optimized Approach (math):
 *  Time Complexity: O(n) // two passes one for calculating total 1's and one for adding the two split points.
 *  Space Complexity: O(1) // we are not storing the string elsewhere.
 * - As mentioned in problem statement we have split the string into three parts with equal no of one's.
 * - The 1st thing we can do is to calculate total no of one's and if it's divisible by three then there will be possible solutions.
 * - If it's not then there is no possible solutions so we can return 0.
 * - If it contains all zeroes then as per math the no of solutions will be ((n - 1)(n - 2))/2 Note: n - 1 coz we have to do three splits no possible range is n -1.
 * - In the next step we can add points by doing 1/3 of total no of 1's. (e.g. firstSplitPoint & secondSplitPoint).
 * - After getting the exact number of points,the combinations will be multiplication of number of zeroes after the points.(firstSplitPoint & secondSplitPoint).
 * - The solution can be large so we will simply modulo it with (10^9+7). Note: Make sure to handle the large no correctly with long and then after typecast it to int.
 * ->Most Optimized Approach:
 * Time Complexity: O(n) // one pass for storing indices of 1's.
 * Space Complexity: O(n) // The new array we are creating to store the indices.
 * - In this approach we can store the index of the one's in an array.
 * - After that we don't have to again traverse through the entire array.
 * - As we have all the one's indices, we can calculate the total result by just dividing the whole array with three we will get out two slice points.
 * - after that we can calculate from the slice point what is the distance to the next 1's index then we can multiply both.
 */

/*---Corner Cases---*/
/*
 * 1. Minimum Length (3 characters) with All Identical Characters
 *    - Input: `"111"`
 *    - Expected Output: `1`
 *    - Explanation: All characters are the same ('1'), so the longest substring with unique characters is of length 1.
 *
 * 2. Minimum Length (3 characters) with Alternating Characters
 *    - Input: `"101"`
 *    - Expected Output: `3`
 *    - Explanation: The entire string `"101"` has all unique characters, so the longest substring with unique characters is of length 3.
 *
 * 3. Minimum Length (3 characters) with Repeating Characters
 *    - Input: `"110"`
 *    - Expected Output: `2`
 *    - Explanation: The longest substring with unique characters is `"10"` or `"01"`, both with length 2.
 *
 * 4. All `1`s
 *    - Input: `"111111"`
 *    - Expected Output: `1`
 *    - Explanation: All characters are the same ('1'), so the longest substring with unique characters is of length 1.
 *
 * 5. All `0`s
 *    - Input: `"000000"`
 *    - Expected Output: `1`
 *    - Explanation: All characters are the same ('0'), so the longest substring with unique characters is of length 1.
 *
 * 6. String with Equal Numbers of `1`s and `0`s (Even Length)
 *    - Input: `"110101"`
 *    - Expected Output: `3`
 *    - Explanation: The longest substring with unique characters is `"101"`, of length 3.
 *
 * 7. String with Consecutive `1`s and `0`s
 *    - Input: `"111000"`
 *    - Expected Output: `2`
 *    - Explanation: The longest substring with unique characters is `"10"`, of length 2.
 *
 * 8. String with Multiple Repeated Substrings
 *    - Input: `"101010101"`
 *    - Expected Output: `2`
 *    - Explanation: The longest substring with unique characters is `"10"` or `"01"`, of length 2.
 *
 * 9. Large String with Only `0`s (Length \(10^5\))
 *    - Input: `"0" * 10^5`
 *    - Expected Output: `1`
 *    - Explanation: Since all characters are the same, the longest substring with unique characters is of length 1.
 *
 * 10. Large String with Only `1`s (Length \(10^5\))
 *     - Input: `"1" * 10^5`
 *     - Expected Output: `1`
 *     - Explanation: Since all characters are the same, the longest substring with unique characters is of length 1.
 *
 * 11. Large String with Alternating `1`s and `0`s (Length \(10^5\))
 *     - Input: `"1010101010... (length 10^5)"`
 *     - Expected Output: `2`
 *     - Explanation: The longest substring with unique characters is `"10"` or `"01"`, of length 2.
 *
 * 12. Large String with Equal Number of `1`s and `0`s (Length \(10^5\))
 *     - Input: `"1010101010... (length 10^5)"`
 *     - Expected Output: `2`
 *     - Explanation: The longest substring with unique characters is `"10"` or `"01"`, of length 2.
 *
 * 13. String with Single `1` Surrounded by `0`s
 *     - Input: `"100100"`
 *     - Expected Output: `2`
 *     - Explanation: The longest substring with unique characters is `"10"`, of length 2.
 *
 * 14. String with Multiple `1`s and `0`s (Length 6)
 *     - Input: `"110101"`
 *     - Expected Output: `3`
 *     - Explanation: The longest substring with unique characters is `"101"`, of length 3.
 *
 * 15. String with Random Distribution of `1`s and `0`s
 *     - Input: `"110011001"`
 *     - Expected Output: `2`
 *     - Explanation: The longest substring with unique characters is `"10"` or `"01"`, of length 2.
 */

package strings;
import java.util.*;

public class TotalWaysToSplitBinaryString {
    public static void main(String[] args) {
        System.out.println(numWays("10101"));
    }
    static int numWays(String s) {
        int oneCount = 0;
        int MOD = 1_000_000_007;
        //counting total no of 1's.
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '1'){
                oneCount++;
            }
        }

        //if there is no of one is 0 the as per formula the count will be this as (n - 1) partitions can be done
        if(oneCount == 0){
            int n = s.length();
            return (int)((long)(n - 1) * (n - 2) / 2 % MOD);
        }

        //if no of 1's is not divisible by three then we can split it equal three parts
        if(oneCount % 3 != 0){
            return 0;
        }
        int splitVal = oneCount / 3;
        int firstPoint = 0;
        int secondPoint = 0;
        int count = 0;
        //looping through the array and adding the split positions
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '1'){
                count++;
            }
            //adding and counting no of zeros after 1st point
            if(count == splitVal){
                firstPoint++;
            }else if(count == splitVal * 2){
                secondPoint++;
            }
        }
        return (int)(((long) firstPoint * secondPoint) % MOD);
    }

    //most optimal approach
    static int numWays2(String s) {
        int MOD = 1_000_000_007;
        List<Integer> ones = new ArrayList<>();
        //counting total no of 1's.
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '1'){
                ones.add(i);
            }
        }
        //if the size is zero there is only zeroes
        if(ones.isEmpty()){
            int n = s.length();
            return (int)((long)(n - 1) * (n - 2) / 2 % MOD);
        }
        //if the size it divisible by three then there is no solutions
        //if the size is zero there is only zeroes
        if (ones.size() % 3 != 0) {
            return 0;
        }
        //adding split points and calculating the total ways
        int splitVal = ones.size() / 3;
        int firstPoint = ones.get(splitVal) - ones.get(splitVal - 1);
        int secondPoint = ones.get(splitVal * 2) - ones.get(splitVal * 2 - 1);

        return (int)(((long) firstPoint * secondPoint) % MOD);
    }
}
