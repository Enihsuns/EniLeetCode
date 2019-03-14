/**
 * Runtime: 87 ms, faster than 33.29% of Java online submissions for 3Sum.
 * Memory Usage: 48.6 MB, less than 61.11% of Java online submissions for 3Sum.
 */

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        
        List<List<Integer>> ans = new ArrayList<>();
        if(nums.length <= 2) return ans;
        
        int begin = 0;
        while(begin < nums.length && nums[begin] <= 0 ) {
            int end = nums.length - 1;
            while(end > begin + 1 && nums[end] >= 0) {
                //System.out.println("nums[" + begin + "]: " + nums[begin] + " nums[" 
                //               + end + "]: " + nums[end]);
                int target = -(nums[begin] + nums[end]);
            
                int idx = binarySearch(nums, begin + 1, end - 1, target);
                if(idx != -1) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums[begin]);
                    list.add(nums[idx]);
                    list.add(nums[end]);

                    ans.add(list);
                }
    
                // Move end
                end--;
                while(end > begin && nums[end + 1] == nums[end]) end--;
            }
                        
            // Move begin
            begin++;
            while(begin < nums.length && nums[begin - 1] == nums[begin]) begin++;
        }
        
        return ans;
    }
    
    private int binarySearch(int[] nums, int begin, int end, int target) {
        if(begin > end) return -1;
        
        int left = begin, right = end;
        while(left < right) {
            int mid = left + (right - left)/2;
            
            if(nums[mid] < target) {
                left = mid + 1;
            }
            else {
                right = mid;
            }
        }
        
        if(nums[left] != target) return -1;
        
        return left;
    }
}