/**
 * Runtime: 9 ms, faster than 83.62% of Java online submissions for 3Sum Closest.
 * Memory Usage: 37.7 MB, less than 78.34% of Java online submissions for 3Sum Closest.
 */
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        
        int minDif = Integer.MAX_VALUE;
        int ans = 0;
        for(int i = 0; i < nums.length; i++) {
            // Jump duplicates elements to save time
            if(i != 0 && nums[i] == nums[i - 1]) continue;
            
            int curTgt = target - nums[i];
            int left = i + 1, right = nums.length - 1;
            while(left < right) {
                int dif = curTgt - (nums[left] + nums[right]);
                
                if(Math.abs(dif) < minDif) {
                    minDif = Math.abs(dif);
                    ans = nums[i] + nums[left] + nums[right];
                }
                
                
                // Move left/right
                if(dif < 0) {
                    right--;
                    while(right > left && nums[right] == nums[right + 1]) right--;
                }
                else {
                    left++;
                    while(left < right && nums[left] == nums[left - 1]) left++;
                }
            }
        }
        return ans;
    }
}