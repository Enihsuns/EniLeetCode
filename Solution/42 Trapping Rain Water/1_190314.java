/**
 * Runtime: 8 ms, faster than 98.36% of Java online submissions for Trapping Rain Water.
 * Memory Usage: 40.1 MB, less than 30.48% of Java online submissions for Trapping Rain Water.
 */
class Solution {
    public int trap(int[] height) {
        int left = 0, right = height.length - 1, ans = 0;
        while(left < right) {
            if(height[left] < height[right]) {  // Move left
                int maxHeight = height[left];
                int trap = 0;
                
                left++;
                while(height[left] < maxHeight && left < right) {
                    trap += maxHeight - height[left++];
                }
                
                ans += trap > 0? trap : 0;
            }
            else {      // Move right
                int maxHeight = height[right];
                int trap = 0;
                
                right--;
                while(height[right] < maxHeight && left < right) {
                    trap += maxHeight - height[right--];
                }
                
                ans += trap > 0? trap : 0;
            }
        }
        return ans;
    }
}