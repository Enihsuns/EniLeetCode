/**
 * Runtime: 1 ms, faster than 93.32% of Java online submissions for Find First and Last Position of Element in Sorted Array.
 * Memory Usage: 44.3 MB, less than 5.19% of Java online submissions for Find First and Last Position of Element in Sorted Array.
 */
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] ans = {-1, -1};
        
        // Check if nums.length > 0
        if(nums.length == 0) {
            return ans;
        }
        
        // Find first
        int left = 0, right = nums.length - 1, mid;
        while(left < right) {
            mid = left + (right - left)/2;
            
            if(nums[mid] < target) {
                left = mid + 1;
            }
            else {
                right = mid;
            }
        }
        if(nums[left] == target) {
            ans[0] = left;
        }
        else {
            return ans;
        }
        
        // Find last
        right = nums.length - 1;
        while(left < right) {
            mid = left + (int)Math.ceil((right - left)/2.0);
            
            if(nums[mid] > target) {
                right = mid - 1;
            }
            else {
                left = mid;
            }
        }
        ans[1] = left;
        return ans;
    }
}