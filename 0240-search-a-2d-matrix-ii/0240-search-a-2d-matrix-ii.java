// class Solution {
//     public boolean searchMatrix(int[][] matrix, int target) {

//         int m = matrix.length;
//         int n = matrix[0].length;

//         for (int i = 0; i < m; i++) {
//             for (int j = 0; j < n; j++) {
//                 if (matrix[i][j] == target)
//                     return true;
//             }
//         }

//         return false;
//     }
// }

// 

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {

        int m = matrix.length;
        int n = matrix[0].length;

        int row = 0;
        int col = n - 1;

        while (row < m && col >= 0) {

            if (matrix[row][col] == target)
                return true;

            else if (matrix[row][col] > target)
                col--;

            else
                row++;
        }

        return false;
    }
}