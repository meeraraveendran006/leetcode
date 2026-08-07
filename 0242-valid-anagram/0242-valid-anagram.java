class Solution {
    public boolean isAnagram(String s, String t) {
        // 1. Anagrams must have the exact same length
        if (s.length() != t.length()) {
            return false;
        }

        int[] count = new int[26];

        // 2. Increment count for s characters, decrement for t characters
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }

        // 3. Verify all frequency counts are zero
        for (int val : count) {
            if (val != 0) {
                return false;
            }
        }

        return true;
    }
}