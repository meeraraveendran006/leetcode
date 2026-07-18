// class Solution {
//     public int singleNonDuplicate(int[] nums) {
//         if(nums.length==1){
//             return nums[0];
//         }
//         for(int i=0;i<nums.length-1;i++){
//             if(i==0){  //first element
//                 if(nums[i] !=nums[i+1]){
//                     return nums[i];
//                 }

//             }
//             else if(i==nums.length-1){   //last element
//                 if(nums[i]!=nums[i-1]){
//                     return nums[i];

//                 }
//             }
//             else{
//                 if(nums[i]!=nums[i+1]&& nums[i]!=nums[i-1]){
//                     return nums[i];
//                 }
//             }
//         }
//         return -1;
        
//     }
    
// 

class Solution {
    public int singleNonDuplicate(int[] nums) {
        int low = 0;
        int high = nums.length - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;

            // Make mid even
            if (mid % 2 == 1) {
                mid--;
            }

            if (nums[mid] == nums[mid + 1]) {
                low = mid + 2;
            } else {
                high = mid;
            }
        }

        return nums[low];
    }
}