import java.util.Arrays;

class Solution {
    private long calculateTotalHours(int[] piles, int speed) {
        long totalH = 0;
        for (int bananas : piles) {
            totalH += bananas / speed;
            if (bananas % speed != 0) {
                totalH++;
            }
        }
        return totalH;
    }

    public int minEatingSpeed(int[] piles, int h) {
        int maxPile = 0;
        for (int pile : piles) {
            maxPile = Math.max(maxPile, pile);
        }

        int low = 1, high = maxPile;
        int ans = maxPile;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            long totalH = calculateTotalHours(piles, mid);

            if (totalH <= h) {
                ans = mid;
                high = mid - 1; // Try smaller speed
            } else {
                low = mid + 1;  // Try larger speed
            }
        }
        return ans;
    }
}