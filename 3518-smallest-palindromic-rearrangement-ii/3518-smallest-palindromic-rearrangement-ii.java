import java.util.*;

class Solution {
    public String smallestPalindrome(String s, int k) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // Step 1: Validate palindrome feasibility
        int oddCount = 0;
        char middleChar = 0;
        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 != 0) {
                oddCount++;
                middleChar = (char) ('a' + i);
            }
        }
        if (oddCount > 1) {
            return "";
        }

        // Step 2: Build frequency map for the left half
        int[] halfFreq = new int[26];
        int halfLength = 0;
        for (int i = 0; i < 26; i++) {
            halfFreq[i] = freq[i] / 2;
            halfLength += halfFreq[i];
        }

        // Step 3: Check if k exceeds total valid permutations
        long targetK = k;
        long totalPermutations = countPermutations(halfFreq, k);
        if (targetK > totalPermutations) {
            return "";
        }

        // Step 4: Construct the left half character by character
        StringBuilder leftHalf = new StringBuilder();

        for (int pos = 0; pos < halfLength; pos++) {
            for (int i = 0; i < 26; i++) {
                if (halfFreq[i] > 0) {
                    // Tentatively place character i
                    halfFreq[i]--;
                    long cnt = countPermutations(halfFreq, k);

                    if (targetK <= cnt) {
                        // The target permutation starts with this character
                        leftHalf.append((char) ('a' + i));
                        break;
                    } else {
                        // Skip 'cnt' permutations and restore frequency
                        targetK -= cnt;
                        halfFreq[i]++;
                    }
                }
            }
        }

        // Step 5: Mirror the left half to form the full palindrome
        String leftStr = leftHalf.toString();
        StringBuilder result = new StringBuilder(leftStr);
        if (oddCount == 1) {
            result.append(middleChar);
        }
        result.append(new StringBuilder(leftStr).reverse());

        return result.toString();
    }

    /**
     * Calculates distinct permutations using combinations, capping at k + 1
     * to prevent 64-bit long integer overflow while maintaining speed.
     */
    private long countPermutations(int[] freq, int k) {
        long cap = (long) k + 1;
        long permutations = 1;
        int currentLength = 0;

        for (int count : freq) {
            if (count > 0) {
                for (int j = 1; j <= count; j++) {
                    currentLength++;
                    permutations = permutations * currentLength / j;
                    if (permutations > cap) {
                        permutations = cap;
                    }
                }
            }
        }
        return permutations;
    }
}