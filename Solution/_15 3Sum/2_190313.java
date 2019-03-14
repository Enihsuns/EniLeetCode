/**
 * Runtime: 39 ms, faster than 87.21% of Java online submissions for 3Sum.
 * Memory Usage: 47.8 MB, less than 88.20% of Java online submissions for 3Sum.
 */
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        
        Arrays.sort(nums);
        
        for(int i = 0; i < nums.length; i++) {
            if(i != 0 && nums[i] == nums[i - 1]) continue;
            if(nums[i] > 0) break;
            
            int sum = 0 - nums[i];
            int left = i + 1, right = nums.length - 1;
            while(left < right) {
                int curSum = nums[left] + nums[right];
                if(curSum == sum) {
                    ans.add(Arrays.asList(nums[i], nums[left], nums[right]));
                }
                
                if(curSum > sum) {
                    right--;
                    while(right > left && nums[right + 1] == nums[right]) right--;
                }
                else {
                    left++;
                    while(left < right && nums[left - 1] == nums[left]) left++;
                }
            }
        }
        return ans;
    }
}