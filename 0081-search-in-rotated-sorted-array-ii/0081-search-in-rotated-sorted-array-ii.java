class Solution {
    public boolean search(int[] nums, int target) {
        int low=0;int high=nums.length-1;
        while(low<=high){
            int mid=(low+high)/2;
            if (nums[mid] == target) return true;

            // Handle duplicates: cannot determine sorted side
            if (nums[low] == nums[mid] && nums[mid] == nums[high]) {
                low++;
                high--;
                continue;
            }
            if (nums[low] <= nums[mid]) {
                if (nums[low] <= target && target <= nums[mid]) {
                    high = mid - 1; // Search left
                } else {
                    low = mid + 1;  // Search right
                }
            }
            else {
                if (nums[mid] <= target && target<= nums[high]) {
                    low = mid + 1;  // Search right
                } else {
                    high = mid - 1; // Search left
                }
            }
        }
        return false;


        
    }
}