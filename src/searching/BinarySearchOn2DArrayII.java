package searching;

public class BinarySearchOn2DArrayII {
    public static void main(String[] args) {
        int[][] arr = new int[][]{
                {-5}
        };
        System.out.println(searchMatrix(arr, -5));
    }
    static boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix[0].length;
        int n = matrix.length;
        int i = 0;
        int j = m - 1;
        while(i < n && j >= 0){
            if(matrix[i][j] == target){
                return true;
            }else if(matrix[i][j] > target){
                j--;
            }else{
                i++;
            }
        }
        return false;
    }
}
