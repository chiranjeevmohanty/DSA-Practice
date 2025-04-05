/*---Approach---*/
/*
 * Problem Statement: (LC:777)
 * You are given two strings: `start` and `end`, both of the same length and consisting only of the characters 'L', 'R', and 'X'.
 * The only operation allowed is:
 *   - Replace "XL" with "LX"  → means 'L' can move left over an 'X'
 *   - Replace "RX" with "XR"  → means 'R' can move right over an 'X'
 * In other words:
 *   - 'L' can move left but not right
 *   - 'R' can move right but not left
 *
 * You need to determine whether it's possible to transform the `start` string into the `end` string by performing **zero or more** of the allowed moves.
 *
 * ---Constraints---
 * - 1 <= start.length == end.length <= 10^4
 * - start and end contain only the characters 'L', 'R', and 'X'
 *
 *
 * ---Key Observations---
 * 1. The sequence of L's and R's (ignoring X's) must be the same in both strings. Otherwise, transformation is impossible.
 * 2. Each 'L' must not move to the right, i.e., its index in `start` must be >= index in `end`
 * 3. Each 'R' must not move to the left, i.e., its index in `start` must be <= index in `end`
 *
 *
 * ---Optimized Approach (Two Pointers)---
 * -> Use two pointers `i` and `j` to iterate through `start` and `end` respectively.
 * -> Skip all 'X' characters in both strings.
 * -> At every step:
 *      - Check if the characters at `i` and `j` are the same.
 *      - If not, return false immediately.
 *      - If it's 'L', ensure `i >= j` (L moved left or stayed)
 *      - If it's 'R', ensure `i <= j` (R moved right or stayed)
 * -> After traversal, ensure both strings are fully processed (no leftover non-'X' characters).
 *
 * ---Time Complexity---
 * O(n) where n is the length of the strings — one pass through both strings.
 *
 * ---Space Complexity---
 * O(1) — no extra space used apart from a few pointers.
 *
 * ---Example---
 * start = "RXXLRXRXL"
 * end   = "XRLXXRRLX"
 *
 * Steps:
 * - Remove Xs:  start => "RLRRL", end => "RLRRL" → valid sequence
 * - Check index positions:
 *     - R moved right ✅
 *     - L moved left ✅
 *     - All constraints satisfied → return true
 */

/*---Corner Cases---*/
/*
 *
 * 1. Start equals End (No Transformation Needed)
 *    - Input: start = "LXR", end = "LXR"
 *    - Expected Output: true
 *    - Explanation: Both strings are identical.
 *
 * 2. Strings Consist Only of 'X'
 *    - Input: start = "XXX", end = "XXX"
 *    - Expected Output: true
 *    - Explanation: No 'L' or 'R' present, so nothing to transform.
 *
 * 3. Mismatched Sequence (Ignoring 'X's)
 *    - Input: start = "LXXR", end = "RXXL"
 *    - Expected Output: false
 *    - Explanation: After removing X's, start becomes "LR" and end becomes "RL". The order differs.
 *
 * 4. Illegal Move for 'L' (Trying to Move Right)
 *    - Input: start = "LX", end = "XL"
 *    - Expected Output: false
 *    - Explanation: 'L' can only move left. In this case, it would have to move right.
 *
 * 5. Illegal Move for 'R' (Trying to Move Left)
 *    - Input: start = "RX", end = "XR"
 *    - Expected Output: false
 *    - Explanation: 'R' can only move right. Here, transforming "RX" to "XR" requires an illegal left move.
 *
 * 6. Multiple Valid Moves Required
 *    - Input: start = "RXXLRXRXL", end = "XRLXXRRLX"
 *    - Expected Output: true
 *    - Explanation: With a series of valid moves ("RX" -> "XR" and "XL" -> "LX"), the transformation is possible.
 *
 * 7. Extra 'L' or 'R' in the Sequence
 *    - Input: start = "XXRXXLXX", end = "XRXLLXX"
 *    - Expected Output: false
 *    - Explanation: After removing X's, the character sequences do not match (start: "RL", end: "RLL").
 *
 * 8. Single Character String
 *    - Input: start = "X", end = "X"
 *    - Expected Output: true
 *    - Explanation: Only one character exists and no transformation is needed.
 *
 * 9. Only 'R's and 'X's (Valid Transformation)
 *    - Input: start = "XRXXR", end = "XXRXR"
 *    - Expected Output: true
 *    - Explanation: After removing X's, both become "RR". For each R, the index condition (start <= end) is satisfied.
 *
 * 10. Only 'L's and 'X's (Invalid Transformation)
 *     - Input: start = "XLXXL", end = "XXLLX"
 *     - Expected Output: false
 *     - Explanation: Removing X's, start becomes "LL" (positions: 1 and 4) and end becomes "LL" (positions: 2 and 3).
 *                  For the first 'L', index 1 (start) is less than index 2 (end), which violates the rule that 'L'
 *                  can only move left (must have start index >= end index).
 */

package strings;

public class SwapAdjacentLRStrings {
    public static void main(String[] args) {
        System.out.println(canTransform("RXXLRXRXL", "XRLXXRRLX"));
    }

    static boolean canTransform(String start, String result) {
        if (!start.replace("X", "").equals(result.replace("X", ""))) return false;

        int i = 0, j = 0;
        while (i < start.length() && j < result.length()) {
            while (i < start.length() && start.charAt(i) == 'X') i++;
            while (j < result.length() && result.charAt(j) == 'X') j++;
            if (i == start.length() && j == result.length()) return true;
            if (i == start.length() || j == result.length()) return false;
            if (start.charAt(i) != result.charAt(j)) return false;
            char c = start.charAt(i);
            if (c == 'L' && i < j) return false;
            if (c == 'R' && i > j) return false;
            i++;
            j++;
        }

        return true;
    }

    //recursive way to get the answer
    public boolean canTransformRecursive(String start, String result) {
        return canTransformRecursive(start, result, 0, 0);
    }

    public boolean canTransformRecursive(String start, String result, int startFrom, int resultFrom) {
        int startIdx = getFirstNonXIdx(start, startFrom);
        int resultIdx = getFirstNonXIdx(result, resultFrom);

        if ((startIdx == -1 || resultIdx == -1) && startIdx != resultIdx) {
            return false;
        }

        if (startIdx == -1 && startIdx == resultIdx) {
            return true;
        }

        if (start.charAt(startIdx) != result.charAt(resultIdx)) {
            return false;
        } else if (start.charAt(startIdx) == 'L' && resultIdx > startIdx) {
            return false;
        } else if (start.charAt(startIdx) == 'R' && resultIdx < startIdx) {
            return false;
        }

        return canTransformRecursive(start, result, startIdx + 1, resultIdx + 1);
    }

    public int getFirstNonXIdx(String str, int startIdx) {
        for (int i = startIdx; i < str.length(); i++) {
            if (str.charAt(i) != 'X') {
                return i;
            }
        }

        return -1;
    }

}
