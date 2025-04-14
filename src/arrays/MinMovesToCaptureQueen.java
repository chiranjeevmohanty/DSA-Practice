/*---Approach---*/
/*
 * Problem Statement: (LC: 3275)
 * You are given 6 integers: (a, b), (c, d), and (e, f)
 * - (a, b): position of the white **rook**
 * - (c, d): position of the white **bishop**
 * - (e, f): position of the black **queen**
 *
 * You can only move the white pieces.
 * Return the **minimum number of moves** to capture the black queen.
 * The rook moves horizontally/vertically; the bishop moves diagonally.
 *
 * ---Constraints---
 * - 1 <= a, b, c, d, e, f <= 8 (8x8 chessboard)
 * - All three pieces are placed on distinct squares.
 *
 * ---Approaches---
 *
 * -> Brute Force (technique used: simulate all moves in 8 directions from rook/bishop)
 *  Time Complexity: O(8) → Constant, because maximum 8 directions with max 7 steps each
 *  - Try every direction the rook/bishop can move, and check if queen is reachable before being blocked by the other piece.
 *  Space Complexity: O(1)
 *  - Only a few variables; no extra data structures used.
 * - Points:
 *   - Simulates real chessboard behavior.
 *   - Needs careful handling of blocking pieces and direction.
 *   - Might be overkill for just checking "can capture in 1 move or not".
 *
 * -> Optimized Approach (technique used: geometry-based check with math)
 *  Time Complexity: O(1)
 *  - Use math to check:
 *     1. If rook and queen are aligned (same row/column), and bishop doesn't block
 *     2. If bishop and queen are diagonal, and rook doesn't block
 *  Space Complexity: O(1)
 * - Points:
 *   - No iteration needed.
 *   - Fast and concise logic using conditions.
 *   - Must check blocking piece (rook or bishop) is not **between** attacker and queen.
 */

/*---Corner Cases---*/
/*
 * 1. Rook aligned with queen, bishop not blocking
 *    - Input: a=1, b=1, c=2, d=2, e=1, f=8
 *    - Expected Output: 1
 *    - Explanation: Rook can directly capture queen on same row.

 * 2. Rook aligned but bishop blocking
 *    - Input: a=1, b=1, c=1, d=4, e=1, f=8
 *    - Expected Output: 2
 *    - Explanation: Bishop blocks rook's path → rook needs to move first.

 * 3. Bishop aligned with queen diagonally, rook not blocking
 *    - Input: a=1, b=1, c=4, d=4, e=7, f=7
 *    - Expected Output: 1
 *    - Explanation: Bishop can capture queen diagonally.

 * 4. Bishop aligned but rook blocking
 *    - Input: a=5, b=5, c=2, d=2, e=7, f=7
 *    - Expected Output: 2
 *    - Explanation: Rook blocks bishop's diagonal path.

 * 5. Neither rook nor bishop aligned
 *    - Input: a=1, b=1, c=2, d=2, e=8, f=1
 *    - Expected Output: 2
 *    - Explanation: No one can capture in 1 move.
 */

package arrays;

public class MinMovesToCaptureQueen {
    static int minMovesToCaptureTheQueen(int a, int b, int c, int d, int e, int f) {
        if (a == e) {
            if (!(c == a && isBetween(b, f, d))) {
                return 1;
            }
        }
        if (b == f) {
            if (!(d == b && isBetween(a, e, c))) {
                return 1;
            }
        }

        if (Math.abs(c - e) == Math.abs(d - f)) {
            if (!(Math.abs(a - e) == Math.abs(b - f) && isBetween(c, e, a) && isBetween(d, f, b))) {
                return 1;
            }
        }
        return 2;
    }
    static boolean isBetween(int a, int b, int val) {
        return val > Math.min(a, b) && val < Math.max(a, b);
    }
}
