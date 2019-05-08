/**
 * Runtime: 2 ms, faster than 99.94% of Java online submissions for Combination Sum.
 * Memory Usage: 36.3 MB, less than 95.58% of Java online submissions for Combination Sum.
 */
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // Sort
        Arrays.sort(candidates);
        
        // DFS
        List<List<Integer>> ans = new ArrayList<>();
        
        DFS(new ArrayList<>(), ans, candidates, 0, target);
        
        return ans;
    }
    
    private void DFS(List<Integer> curList, List<List<Integer>> ans, int[] candidates, int curPos, int remain) {
        if(remain == 0) {
            ans.add(new ArrayList<>(curList));
        }
        
        for(int i = curPos; i < candidates.length; i++) {
            int curVal = candidates[i];
            if(curVal > remain) {
                break;
            }
            curList.add(curVal);
            DFS(curList, ans, candidates, i, remain - curVal);
            curList.remove(curList.size() - 1);
        }
    }
}