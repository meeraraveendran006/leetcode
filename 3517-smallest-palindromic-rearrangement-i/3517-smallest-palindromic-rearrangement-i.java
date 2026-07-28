class Solution {
    public String smallestPalindrome(String s) {
        int[] freq = new int[26];

        // Count frequency of each character
        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        StringBuilder left = new StringBuilder();
        StringBuilder middle = new StringBuilder();

        // Build the left half and find the middle character
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < freq[i] / 2; j++) {
                left.append((char) ('a' + i));
            }

            if (freq[i] % 2 == 1) {
                middle.append((char) ('a' + i));
            }
        }

        // Right half is the reverse of left half
        StringBuilder right = new StringBuilder(left).reverse();

        return left.toString() + middle.toString() + right.toString();
    }
}