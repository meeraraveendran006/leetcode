class Solution {
    // Function to search target in 2D matrix using binary search
    public boolean searchMatrix(int[][] matrix, int target) {
        // Get the number of rows
        int n = matrix.length;                                                  //flatten matrix method

        // Get the number of columns
        int m = matrix[0].length;

        // Set initial binary search range
        int low = 0, high = n * m - 1;

        // Perform binary search
        while (low <= high) {
            // Calculate middle index
            int mid = (low + high) / 2;

            // Map 1D index to 2D coordinates
            int row = mid / m;
            int col = mid % m;

            // Check if target is found
            if (matrix[row][col] == target)
                return true;

            // Discard left half
            else if (matrix[row][col] < target)
                low = mid + 1;

            // Discard right half
            else
                high = mid - 1;
        }

        // Target not found
        return false;
    }
}