class Solution {
    public int findPeakElement(int[] nums) {
        for(int i=0;i<nums.length;i++){
            
            boolean left = (i == 0) || (nums[i] >= nums[i - 1]);
            // Check right neighbor if exists
            boolean right = (i == nums.length - 1) || (nums[i] >= nums[i + 1]);
            // If both conditions are true
            if (left && right) return i;
        }
        return -1;
        
    }
}