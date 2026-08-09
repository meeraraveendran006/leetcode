class Solution {
    public int stoneGameII(int[] piles) {
        int n = piles.length;

        int[] suffix = new int[n + 1];

        // suffix[i] = stones from i to end
        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + piles[i];
        }

        int[][] dp = new int[n][n + 1];

        return solve(0, 1, piles, suffix, dp);
    }

    private int solve(int i, int M, int[] piles, int[] suffix, int[][] dp) {

        // All piles have been taken
        if (i == piles.length) {
            return 0;
        }

        // Already calculated
        if (dp[i][M] != 0) {
            return dp[i][M];
        }

        int best = 0;

        // Current player can take 1 to 2*M piles
        for (int X = 1; X <= 2 * M && i + X <= piles.length; X++) {

            int newM = Math.max(M, X);

            // Stones current player gets
            int current = suffix[i] - suffix[i + X];

            // Stones opponent can get
            int opponent = solve(i + X, newM, piles, suffix, dp);

            // Current player's total
            int total = current + (suffix[i + X] - opponent);

            best = Math.max(best, total);
        }

        dp[i][M] = best;

        return best;
    }
}