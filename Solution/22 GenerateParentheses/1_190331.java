class Solution {
    public List<String> generateParenthesis(int n) {
        char[] array = new char[n * 2];
        
        List<String> ans = new ArrayList<>();
        DFS(array, 0, 0, n, ans);
        
        return ans;
    }
    
    private void DFS(char[] array, int leftCnt, int rightCnt, int n, List<String> ans) {
        if(rightCnt == n) {
            ans.add(String.valueOf(array));
            return;
        }
        
        // Left Bracket
        if(leftCnt < n) {
            array[leftCnt + rightCnt] = '(';
            DFS(array, leftCnt + 1, rightCnt, n, ans);
        }
        
        // Right Bracket
        if(rightCnt < leftCnt) {
            array[leftCnt + rightCnt] = ')';
            DFS(array, leftCnt, rightCnt + 1, n, ans);
        }
    }
}