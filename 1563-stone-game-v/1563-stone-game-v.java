class Solution {
    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;

        // Prefix sum
        int[] prefix = new int[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + stoneValue[i];
        }

        // dp[l][r] = maximum score from l to r
        int[][] dp = new int[n][n];

        // Length of subarray
        for (int len = 2; len <= n; len++) {

            for (int l = 0; l + len - 1 < n; l++) {

                int r = l + len - 1;
                int best = 0;

                // Try every split
                for (int k = l; k < r; k++) {

                    int leftSum = prefix[k + 1] - prefix[l];
                    int rightSum = prefix[r + 1] - prefix[k + 1];

                    if (leftSum < rightSum) {

                        // Right is thrown away
                        best = Math.max(
                            best,
                            leftSum + dp[l][k]
                        );

                    } else if (leftSum > rightSum) {

                        // Left is thrown away
                        best = Math.max(
                            best,
                            rightSum + dp[k + 1][r]
                        );

                    } else {

                        // Equal -> Alice chooses
                        best = Math.max(
                            best,
                            Math.max(
                                leftSum + dp[l][k],
                                rightSum + dp[k + 1][r]
                            )
                        );
                    }
                }

                dp[l][r] = best;
            }
        }

        return dp[0][n - 1];
    }
}