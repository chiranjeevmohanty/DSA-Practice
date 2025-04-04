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
