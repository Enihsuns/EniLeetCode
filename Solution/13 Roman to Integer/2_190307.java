/**
 * Runtime: 32 ms, faster than 99.98% of Java online submissions for Roman to Integer.
 * Memory Usage: 39.5 MB, less than 10.76% of Java online submissions for Roman to Integer.
 */
class Solution {
    public int romanToInt(String s) {
        int ans = 0;
        
        int[] nums = new int[s.length()];
        for(int i = 0; i < s.length(); i++) {
            switch(s.charAt(i)) {
                case 'M': nums[i] = 1000; break;
                case 'D': nums[i] = 500; break;
                case 'C': nums[i] = 100; break;
                case 'L': nums[i] = 50; break;
                case 'X': nums[i] = 10; break;
                case 'V': nums[i] = 5; break;
                case 'I': nums[i] = 1; break;
            }
        }
        
        for(int i = 0; i < nums.length - 1; i++) {
            if(nums[i] < nums[i + 1]) {
                ans -= nums[i];
            }
            else {
                ans += nums[i];
            }
        }
        
        return ans + nums[nums.length - 1];
    }
}