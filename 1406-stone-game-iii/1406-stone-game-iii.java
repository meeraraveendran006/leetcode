class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        int[] dp = new int[n + 1];
        
        // Base case: dp[n] = 0 (no stones left)
        
        // Fill DP array from right to left
        for (int i = n - 1; i >= 0; i--) {
            int maxDiff = Integer.MIN_VALUE;
            int currentSum = 0;
            
            // Try taking 1, 2, or 3 stones
            for (int k = 1; k <= 3 && i + k <= n; k++) {
                currentSum += stoneValue[i + k - 1];
                maxDiff = Math.max(maxDiff, currentSum - dp[i + k]);
            }
            
            dp[i] = maxDiff;
        }
        
        // Alice starts at index 0
        if (dp[0] > 0) {
            return "Alice";
        } else if (dp[0] < 0) {
            return "Bob";
        } else {
            return "Tie";
        }
    }
}