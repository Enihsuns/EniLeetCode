/**
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Search in Rotated Sorted Array.
 * Memory Usage: 37.5 MB, less than 95.38% of Java online submissions for Search in Rotated Sorted Array.
 */
class Solution {
    public int search(int[] nums, int target) {
        if(nums.length == 0) {
            return -1;
        }
        
        // Find the smallest num index.
        int smallestIdx = searchSmallest(nums);
        
        //System.out.println(smallestIdx);
        
        // Decide which side to search.
        int ansIdx;
        if(nums[0] <= target && smallestIdx != 0) {  // Search left side.
            ansIdx = searchTarget(nums, 0, smallestIdx - 1, target);
        }
        else {
            ansIdx = searchTarget(nums, smallestIdx, nums.length - 1, target);
        }
        
        // Return
        if(nums[ansIdx] == target) {
            return ansIdx;
        }
        else {
            return -1;
        }
    }
    
    private int searchSmallest(int[] nums) {
        int left = 0, right = nums.length - 1;
        while(left < right) {
            int mid = left + (right - left)/2;
            if(nums[mid] > nums[mid + 1]) {
                return mid + 1;
            }
            if(nums[mid] > nums[left]) {    // Too left
                left = mid + 1;
            }
            else {  // Too right
                right = mid;
            }
        }
        return 0;
    }
    
    private int searchTarget(int[] nums, int left, int right, int target) {
        while(left < right) {
            int mid = left + (right - left)/2;
            if(nums[mid] < target) {
                left = mid + 1;
            }
            else {
                right = mid;
            }
        }
        return left;
    }
}