/*---Approach---*/
/*
 * Binary search implementation:
 *  - search and return the index of a target value.
 *
 * Requirements:
 * - The array should be sorted(if not then go for linear search or else sort it first).
 *
 * ---Approach---
 * - In binary search the array should be sorted so that at each step we can reduce our search space by half.
 * - At start we have to take two pointers one will point start index and other will point the last(e.g. low-high or start-end)
 * - Next we will get the middle point dynamically inside the while loop. (why while loop? Coz we don't know how many times the loop will run).
 * - Then we will compare our mid with the target there will be three scenarios (1: mid == target, 2: mid < target and 3: mid > target).
 * - 1: mid == target
 *      - simply we will return the index coz the value matches our target
 * - 2: mid < target
 *      - Means the target lies in the left half we will reduce our search space by half by moving the end to the mid.(end = mid - 1).
 * - 3: mid > target
 *      - Means the target lies in the right half we will reduce our search space by half by moving the start to the mid.(start = mid + 1).
 * - eventually we will land on our target if not then the start <= end condition will be violated then simply we will return -1.
 */


package searching;

public class BinarySearchImpl {
    public static int binarySearch(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;

        while (start <= end) {
            // Avoid potential integer overflow with (start + (end - start) / 2)
            int mid = start + (end - start) / 2;

            if (target > arr[mid]) {
                // If target is greater, ignore the left half
                start = mid + 1;
            } else if (target < arr[mid]) {
                // If target is smaller, ignore the right half
                end = mid - 1;
            } else {
                // Target found, return its index
                return mid;
            }
        }

        // Target not found, return -1
        return -1;
    }
}
