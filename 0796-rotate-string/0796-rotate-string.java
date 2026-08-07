class Solution {
    public boolean rotateString(String s, String goal) {
        // 1. Rotations must have the exact same length
        if (s.length() != goal.length()) {
            return false;
        }

        // 2. Check if goal exists inside (s + s)
        return (s + s).contains(goal);
    }
}