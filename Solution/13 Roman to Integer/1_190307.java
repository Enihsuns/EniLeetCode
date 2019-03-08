/**
 * Runtime: 48 ms, faster than 35.49% of Java online submissions for Roman to Integer.
 * Memory Usage: 39.4 MB, less than 11.82% of Java online submissions for Roman to Integer.
 */
class Solution {
    public int helper(StringBuffer sBuf, char curSymbol, char subSymbol, int curVal, int subVal) {
        int ans = 0;
        
        char c;
        while(sBuf.length() > 0) {
            c = sBuf.charAt(0);
            
            if(c == curSymbol) {
                ans += curVal;
                sBuf.deleteCharAt(0);
            }
            else if(c == subSymbol){
                if(sBuf.length() > 1 && sBuf.charAt(1) == curSymbol) {
                    ans += subVal;
                    sBuf.delete(0, 2);
                }
                break;
            }
            else {
                break;
            }
        }
        return ans;
    }
    
    public int romanToInt(String s) {
        StringBuffer sBuf = new StringBuffer(s.replaceAll("\\s", ""));
        if(sBuf.length() <= 0) return 0;
        
        int ans = 0;
        ans += helper(sBuf, 'M', 'C', 1000, 900);
        ans += helper(sBuf, 'D', 'C',  500, 400);
        ans += helper(sBuf, 'C', 'X',  100,  90);
        ans += helper(sBuf, 'L', 'X',   50,  40);
        ans += helper(sBuf, 'X', 'I',   10,   9);
        ans += helper(sBuf, 'V', 'I',    5,   4);
        ans += helper(sBuf, 'I', ' ',    1,   0);
        
        return ans;
    }
}