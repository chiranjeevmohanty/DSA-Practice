package searching;

public class BinarySearchIn2DArray {
    public static void main(String[] args) {
        int[][] arr = new int[][]{
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 60}
        };
        System.out.println(searchMatrix(arr, 16));
    }
    static boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix[0].length;
        int n = matrix.length;
        int t = 0, b = n - 1;
        int ans = 0;
        while(t <= b){
            int mid = (t + b)/2;
            if(matrix[mid][0] <= target && target <= matrix[mid][m - 1]){
                ans = mid;
                break;
            }else if(matrix[mid][m - 1] < target){
                t = mid + 1;
            }else{
                b = mid - 1;
            }
        }

        int l = 0, r = m - 1;
        while(l <= r){
            int mid = (l + r)/2;
            if(matrix[ans][mid] == target){
                return true;
            }else if(matrix[ans][mid] > target){
                r = mid - 1;
            }else{
                l = mid + 1;
            }
        }
        return false;
    }
}
