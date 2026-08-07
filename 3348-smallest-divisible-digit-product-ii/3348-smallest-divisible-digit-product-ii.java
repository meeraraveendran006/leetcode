class Solution {

    // Order: 2, 3, 5, 7
    int[] need = new int[4];

    // Prime factor counts for digits 0 to 9
    int[][] factor = {
        {0, 0, 0, 0}, // 0
        {0, 0, 0, 0}, // 1
        {1, 0, 0, 0}, // 2
        {0, 1, 0, 0}, // 3
        {2, 0, 0, 0}, // 4
        {0, 0, 1, 0}, // 5
        {1, 1, 0, 0}, // 6
        {0, 0, 0, 1}, // 7
        {3, 0, 0, 0}, // 8
        {0, 2, 0, 0}  // 9
    };

    // Minimum number of digits required to provide the factors
    int minDigits(int a, int b, int c, int d) {

        int ans = c + d;
        int best = Integer.MAX_VALUE;

        // Try using some number of 6s
        for (int six = 0; six <= Math.min(a, b); six++) {

            int twos = a - six;
            int threes = b - six;

            // 8 gives 3 twos
            int digitsFor2 = (twos + 2) / 3;

            // 9 gives 2 threes
            int digitsFor3 = (threes + 1) / 2;

            best = Math.min(best,
                    six + digitsFor2 + digitsFor3);
        }

        return ans + best;
    }

    // Remove the factors supplied by a digit
    void subtract(int[] rem, int digit) {

        for (int i = 0; i < 4; i++) {
            rem[i] = Math.max(0,
                    rem[i] - factor[digit][i]);
        }
    }

    // Can the remaining factors be completed in 'slots' digits?
    boolean canFinish(int[] rem, int slots) {

        return minDigits(
                rem[0],
                rem[1],
                rem[2],
                rem[3]
        ) <= slots;
    }

    // Build the smallest possible suffix
    String buildSuffix(int[] rem, int length) {

        StringBuilder ans = new StringBuilder();

        for (int pos = 0; pos < length; pos++) {

            for (int digit = 1; digit <= 9; digit++) {

                int[] next = rem.clone();

                subtract(next, digit);

                if (canFinish(next, length - pos - 1)) {

                    ans.append(digit);
                    rem = next;
                    break;
                }
            }
        }

        return ans.toString();
    }

    public String smallestNumber(String num, long t) {

        // -----------------------------------
        // 1. Factorize t
        // -----------------------------------

        long x = t;

        while (x % 2 == 0) {
            need[0]++;
            x /= 2;
        }

        while (x % 3 == 0) {
            need[1]++;
            x /= 3;
        }

        while (x % 5 == 0) {
            need[2]++;
            x /= 5;
        }

        while (x % 7 == 0) {
            need[3]++;
            x /= 7;
        }

        // Any remaining prime cannot be formed
        // using digits 1 to 9.
        if (x != 1) {
            return "-1";
        }

        int n = num.length();

        // -----------------------------------
        // 2. Prefix factor counts
        // -----------------------------------

        int[][] prefix = new int[n + 1][4];
        int[] zeroPrefix = new int[n + 1];

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < 4; j++) {
                prefix[i + 1][j] = prefix[i][j];
            }

            zeroPrefix[i + 1] = zeroPrefix[i];

            int digit = num.charAt(i) - '0';

            if (digit == 0) {
                zeroPrefix[i + 1]++;
            } else {
                for (int j = 0; j < 4; j++) {
                    prefix[i + 1][j] =
                        Math.min(
                            need[j],
                            prefix[i + 1][j] + factor[digit][j]
                        );
                }
            }
        }

        // -----------------------------------
        // 3. Check if num itself works
        // -----------------------------------

        if (zeroPrefix[n] == 0) {

            boolean works = true;

            for (int j = 0; j < 4; j++) {
                if (prefix[n][j] < need[j]) {
                    works = false;
                    break;
                }
            }

            if (works) {
                return num;
            }
        }

        // -----------------------------------
        // 4. Try to make a same-length number
        // -----------------------------------

        for (int i = n - 1; i >= 0; i--) {

            // Prefix cannot contain zero
            if (zeroPrefix[i] > 0) {
                continue;
            }

            int[] rem = new int[4];

            for (int j = 0; j < 4; j++) {
                rem[j] = need[j] - prefix[i][j];
            }

            int current = num.charAt(i) - '0';

            // Increase this digit as little as possible
            for (int digit = current + 1; digit <= 9; digit++) {

                if (digit == 0) {
                    continue;
                }

                int[] next = rem.clone();

                subtract(next, digit);

                int slots = n - i - 1;

                if (canFinish(next, slots)) {

                    StringBuilder ans =
                            new StringBuilder();

                    // Original prefix
                    ans.append(num.substring(0, i));

                    // Increased digit
                    ans.append(digit);

                    // Smallest possible suffix
                    ans.append(
                        buildSuffix(next, slots)
                    );

                    return ans.toString();
                }
            }
        }

        // -----------------------------------
        // 5. Same length impossible
        //    Build smallest longer number
        // -----------------------------------

        int minLength = minDigits(
                need[0],
                need[1],
                need[2],
                need[3]
        );

        // Must have more digits than num
        int length = Math.max(n + 1, minLength);

        return buildSuffix(need.clone(), length);
    }
}