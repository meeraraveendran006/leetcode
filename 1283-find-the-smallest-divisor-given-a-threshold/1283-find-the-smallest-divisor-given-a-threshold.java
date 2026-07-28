import java.util.Arrays;

class Solution {
    // Helper method to calculate sum by divisor using integer division
    private int sumByD(int[] arr, int div) {
        int sum = 0;
        for (int num : arr) {
            // Equivalent to Math.ceil((double) num / div)
            sum += (num + div - 1) / div; 
        }
        return sum;
    }

    public int smallestDivisor(int[] nums, int threshold) {
        // Base case: minimal possible sum with any divisor is nums.length (when div is infinitely large)
        if (nums.length > threshold) return -1;

        int low = 1;
        int high = 0;
        for (int num : nums) {
            high = Math.max(high, num);
        }

        while (low <= high) {
            int mid = low + (high - low) / 2;
            
            if (sumByD(nums, mid) <= threshold) {
                high = mid - 1; // Try finding a smaller valid divisor
            } else {
                low = mid + 1;  // Divisor is too small, sum exceeded threshold
            }
        }

        return low; // low ends up pointing to the smallest valid divisor
    }
}