package sorting;
import java.util.*;

public class BubbleSortImpl {
    static void bubbleSort(int[] nums) {
        int steps = 0; // Count the number of iterations for analysis

        // Outer loop for passes (each pass moves the largest element to its correct position)
        for (int i = 0; i < nums.length - 1; i++) {
            boolean swapped = false; // Flag to check if any swapping happened in this pass

            // Inner loop for comparing adjacent elements
            for (int j = 1; j < nums.length - i; j++) {
                if (nums[j] < nums[j - 1]) { // If current element is smaller than previous, swap them
                    int temp = nums[j];
                    nums[j] = nums[j - 1];
                    nums[j - 1] = temp;
                    swapped = true; // Mark swap as true
                }
                steps++; // Increment step counter
            }

            // If no swaps occurred in a pass, the array is already sorted, break early
            if (!swapped) {
                break;
            }
        }

        // Print the sorted array and total steps taken
        System.out.println("Sorted Array: " + Arrays.toString(nums));
        System.out.println("Total Steps: " + steps);
    }
}
