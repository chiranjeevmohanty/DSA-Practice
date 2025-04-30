/*---Approach---*/
/*
 * Problem Statement: (LC: 2280)
 *  You are given an array `stockPrices` where `stockPrices[i] = [day_i, price_i]`.
 *  The goal is to draw the **minimum number of straight lines** connecting these points in chronological order (sorted by day),
 *  such that if multiple points lie on the same straight line (i.e., same slope), they are represented by one line segment.
 *  You can only draw a line segment between **consecutive** days.
 *  Return the minimum number of lines needed to represent the full chart.
 *
 * ---Constraints---
 *  1 <= stockPrices.length <= 10^5
 *  stockPrices[i].length == 2
 *  0 <= day_i, price_i <= 10^9
 *
 * -> Optimized Approach (Sorting + Cross Product Slope Check):
 *  Time Complexity: O(n log n) // For sorting the stockPrices array based on day.
 *  Space Complexity: O(1) // No extra space used apart from a few variables.
 *
 * - First, we sort the `stockPrices` array by day to ensure chronological order.
 * - We start with 1 line because the first segment (between first two points) always counts as one.
 * - Then we iterate from the 3rd point onward and check if the current three points are collinear.
 * - Instead of calculating floating-point slopes, we use **cross multiplication** to avoid precision errors and division-by-zero.
 *   That is: for points A(x1, y1), B(x2, y2), C(x3, y3),
 *   check if (y2 - y1)*(x3 - x2) == (y3 - y2)*(x2 - x1)
 * - If this condition is false, the new point cannot lie on the same line, so we increase the line count.
 * - Else, the point continues on the same line, and we do not increment the count.
 * - At the end of the loop, we return the total count of lines needed.
 */

/*---Corner Cases---*/
/*
 * 1. Only one point
 *    - Input: [[3,4]]
 *    - Expected Output: 0
 *    - Explanation: No line is needed to represent a single point.
 *
 * 2. All points in straight line
 *    - Input: [[1,1],[2,2],[3,3]]
 *    - Expected Output: 1
 *    - Explanation: All points are on the same straight line, only one line needed.
 *
 * 3. Every point changes slope
 *    - Input: [[1,1],[2,2],[3,1]]
 *    - Expected Output: 2
 *    - Explanation: First two points form one line; third point creates a new slope.
 */


package arrays;

import java.util.Arrays;
import java.util.Comparator;

public class MinimumLinesToRepresentLineChart {
    public static void main(String[] args) {
        int[][] arr = new int[][]{
                {3, 3},
                {1, 5},
                {2, 8},
                {5, 10}
        };
        System.out.println(minimumLines(arr));
    }
    static int minimumLines(int[][] stockPrices) {
        if (stockPrices.length <= 1) return 0;

        Arrays.sort(stockPrices, Comparator.comparingInt(a -> a[0]));
        int lines = 1;

        for (int i = 2; i < stockPrices.length; i++) {
            long x1 = stockPrices[i - 2][0], y1 = stockPrices[i - 2][1];
            long x2 = stockPrices[i - 1][0], y2 = stockPrices[i - 1][1];
            long x3 = stockPrices[i][0],     y3 = stockPrices[i][1];
            long dx1 = x2 - x1, dy1 = y2 - y1;
            long dx2 = x3 - x2, dy2 = y3 - y2;
            if (dy1 * dx2 != dy2 * dx1) {
                lines++;
            }
        }

        return lines;
    }
}
